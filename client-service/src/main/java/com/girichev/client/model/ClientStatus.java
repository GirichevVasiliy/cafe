package com.girichev.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClientStatus {
    ACTIVE("Активен"),
    DISABLED("Отключен");

    private String text;

}
