package com.girichev.cafe.owner.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "owners")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "inn")
    private Long inn;
    @Column(name = "phone", unique = true, length = 11, nullable = false)
    private String phoneNumber;
    @Column(name = "phone_confirmed", nullable = false)
    private Boolean phoneConfirmed;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
}
