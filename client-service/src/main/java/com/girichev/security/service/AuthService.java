package com.girichev.security.service;

import com.girichev.client.model.Client;
import com.girichev.client.storage.ClientsRepository;
import com.girichev.excepton.ClientSmsCodeException;
import com.girichev.excepton.JwtAuthenticationException;
import com.girichev.security.jwt.model.request.JwtRequest;
import com.girichev.security.jwt.model.response.JwtResponse;
import com.girichev.security.jwt.util.JwtTokenUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    AuthenticationManager authenticationManagerBean;
    ClientDetailsService clientDetailsService;
    JwtTokenUtils jwtTokenUtils;
    ClientsRepository clientsRepository;
    BCryptPasswordEncoder passwordEncoder;

    public JwtResponse authenticate(JwtRequest request) {
        try {
            authenticationManagerBean.authenticate(new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new JwtAuthenticationException("Invalid authentication", HttpStatus.FORBIDDEN);
        }
        UserDetails userDetails = clientDetailsService.loadUserByUsername(request.getPhoneNumber());
        String uid = clientsRepository.getClientUidByPhoneNumber(request.getPhoneNumber());
        return jwtTokenUtils.generateToken(userDetails, uid);
    }

    @Transactional
    public void checkPhoneClient(String phone) {
        log.info("Пользователь с номером телефона {} запросил СМС код", phone);
        String code = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        String password = passwordEncoder.encode("777777"); //TODO:тут убрать заглушку
        checkClient(phone, password);
        //TODO: тут потом будет смс
    }

    public void checkClient(String phone, String password) {
        log.info("Проверяем пользователя с номером телефона {}", phone);
        int maxCounterAttempt = 3;  // Максимальное количество попыток получения смс
        Optional<Client> client = clientsRepository.findClientByPhoneNumber(phone);
        if (client.isPresent()) {
            log.info("Пользователь с номером телефона {} найден", phone);
            Instant timeStop = client.get().getGenerateTime().plus(1, ChronoUnit.HOURS);
            if (client.get().getCounterAttempt() < maxCounterAttempt) {
                int count = client.get().getCounterAttempt() + 1;
                clientsRepository.save(updateClient(client, count, password, phone));
                log.info("Пользователь с номером телефона {} и его сгенерированный код {} удачно сохранены", phone, password);
            } else if (timeStop.isBefore(Instant.now())) {
                int count = 1;
                clientsRepository.save(updateClient(client, count, password, phone));
                log.info("Пользователь с номером телефона {} и его сгенерированный код {} удачно сохранены и счетчик попыток обнулен", phone, password);
            } else {
                log.warn("Выполнено 3 попытки отправки смс кода, пользователем с номером телефона {}," +
                        " ожидайте 1 час и пробуйте еще раз", phone);
                throw new ClientSmsCodeException(String.format("Выполнено 3 попытки отправки смс кода пользователем с номером телефона" +
                        " '%s', ожидайте 1 час и пробуйте еще раз", phone));
            }
        } else {
            log.info("Пользователь с номером телефона {} ранее не был зарегистрирован", phone);
            String uid = generateRouteUid(phone);
            Client clientNew = ClientMapper.createClientRegisterForPhone(uid, phone, password);
            clientsRepository.save(clientNew);
            log.info("При помощи телефона зарегистрировался новый клиент {}", phone);
        }
    }

    public Client updateClient(Optional<Client> client, int count, String password, String phone) {
        client.get().setPhoneNumber(phone);
        client.get().setPassword(password);
        client.get().setCounterAttempt(count);
        client.get().setGenerateTime(Instant.now());
        client.get().setPhoneConfirmed(true);
        log.info("Пользователь с номером телефона {} обновляет свой пароль {}", client.get().getPhoneNumber(), password);
        return client.get();
    }

    /**
     * Общий метод генерации UID.
     */
    public String generateRouteUid(String phone) {
        log.info("Для пользователя с номером телефона {} запущена геренация UID", phone);
        int maxAttempts = 15;  // Максимальное количество попыток генерации
        int uidLength = 12;
        String uid = RandomStringUtils.randomAlphanumeric(uidLength);
        for (int i = 0; i < maxAttempts; i++) {
            if (!clientsRepository.existsByUid(uid)) {
                log.info("UID для пользователя с номером телефона {} успешно сгенерирован", phone);
                return uid;  // Возвращаем uid, если он уникален
            }
            uid = RandomStringUtils.randomAlphanumeric(uidLength);  // Генерируем новый uid
        }
        log.warn("Не удалось сгенерировать уникальный uid, превышено количество попыток, " +
                "для пользователя с номером телефона {}", phone);
        throw new GenerateClientUidException(String.format("Не удалось сгенерировать уникальный uid, превышено количество попыток, " +
                "для пользователя с номером телефона '%s'", phone));
    }
}
