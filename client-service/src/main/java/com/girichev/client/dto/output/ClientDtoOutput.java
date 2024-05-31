package com.girichev.client.dto.output;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class ClientDtoOutput {
    @NotBlank(message = "Необходимо указать id клиента")
    Long id;
    Long chatId;
    @NotBlank(message = "Необходимо указать номер телефона клиента")
    String phoneNumber;
    @NotNull(message = "Необходимо указать подтвержден номер телефона клиента или нет")
    Boolean isPhoneConfirmed;
    String firstName;
    String lastName;
}
