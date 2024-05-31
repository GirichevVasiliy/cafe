package com.girichev.utils;

import com.girichev.excepton.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class PhoneValidate {
    public String validatePhone(String phone) {
        final String regex = "^79[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return phone;
        } else {
            log.error("Номер телефона не валидный {}", phone);
            throw new BadRequestException(String.format("Номер телефона не валидный '%s'", phone));
        }
    }
}
