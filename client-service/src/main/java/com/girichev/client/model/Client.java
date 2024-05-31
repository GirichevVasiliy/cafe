package com.girichev.client.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "clients", schema = "public", indexes = {
        @Index(name = "cl_phone_index", columnList = "phone")
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "chat_id", unique = true) //telegram chat id
    private Long chatId;
    @Column(name = "phone", unique = true, length = 11, nullable = false)
    private String phoneNumber;
    @Column(name = "is_phone_confirmed", nullable = false)
    private Boolean isPhoneConfirmed;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "created_client", nullable = false)
    private Instant createdClient;
    @Column(name = "password_sms", nullable = false)
    private String password;
    @Column(name = "counter_attempt", nullable = false)
    private Integer counterAttempt; //Максимальное количество попыток получения смс
}
