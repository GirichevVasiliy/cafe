package com.girichev.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClientRole {
    ROLE_CLIENT("Клиент"),
    ROLE_ADMIN("Администратор приложения");
    private final String text;
}
