package com.girichev.client.dto.output;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class ClientDtoOutput {
    @NotBlank(message = "Необходимо указать uid клиента")
    String uid;
    Long chatId;
    @NotBlank(message = "Необходимо указать номер телефона клиента")
    String phoneNumber;
    @NotNull(message = "Необходимо указать подтвержден номер телефона клиента или нет")
    Boolean isPhoneConfirmed;
    String firstName;
    String lastName;
    @NotBlank(message = "Необходимо указать статус клиента")
    String status;
    //List<AddressDtoOutput> addresses;
    // String tokenPush;
}
