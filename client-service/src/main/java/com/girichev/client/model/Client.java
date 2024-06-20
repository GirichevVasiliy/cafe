package com.girichev.client.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

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
    @Column(name = "uid", unique = true, nullable = false, length = 12)
    private String uid; // Уникальный 12 значный UID
    @Column(name = "chat_id", unique = true) //telegram chat id
    private Long chatId;
    @Column(name = "phone", unique = true, length = 11, nullable = false)
    private String phoneNumber;
    @Column(name = "phone_confirmed", nullable = false)
    private Boolean phoneConfirmed;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ClientStatus status;
    @Column(name = "created_client", nullable = false)
    private Instant createdClient;
    @Column(name = "password_sms", nullable = false)
    private String password;
    @Column(name = "counter_attempt", nullable = false)
    private Integer counterAttempt; //Максимальное количество попыток получения смс
    @Column(name = "generate_time", nullable = false)
    private Instant generateTime;
   /* @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "client_id")
    @ToString.Exclude // Исключаем поле client из toString
    @EqualsAndHashCode.Exclude // Исключаем поле client из equals и hashCode
    private List<Address> addresses;
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude // Исключаем поле client из toString
    @EqualsAndHashCode.Exclude // Исключаем поле client из equals и hashCode
    private TokenClient token;
*/
    @ElementCollection(targetClass = ClientRole.class)
    @CollectionTable(name = "client_roles", joinColumns = @JoinColumn(name = "id"), indexes = {
            @Index(name = "client_RolesTable_client_id_role_unique_index", columnList = "id,role", unique = true)
    })
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private List<ClientRole> roles;
}
