package com.girichev.client.service;

import com.girichev.client.dto.input.ClientUpdateDtoInput;
import com.girichev.client.dto.output.ClientDtoOutput;
import com.girichev.client.storage.ClientsRepository;
import com.girichev.utils.PhoneValidate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ClientServiceImpl implements ClientService {
    ClientsRepository clientsRepository;
    PhoneValidate phoneValidate;

    @Override
    public ClientDtoOutput createClient(String phone, ClientUpdateDtoInput clientUpdateDtoInput) {
        return null;
    }

    @Override
    public ClientDtoOutput updateClient(String phone, ClientUpdateDtoInput clientUpdateDtoInput) {
        return null;
    }

    @Override
    public ClientDtoOutput getClientByUid(String id) {
        return null;
    }

    @Override
    public ClientDtoOutput deleteClientByUid(String id) {
        return null;
    }
}
