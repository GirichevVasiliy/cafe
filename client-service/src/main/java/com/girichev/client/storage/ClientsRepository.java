package com.girichev.client.storage;

import com.girichev.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {
    @Query(value = "select c.uid from Client as c where c.phoneNumber = :phoneNumber")
    String getClientUidByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    @Query(value = "select c from Client as c where c.phoneNumber = :phoneNumber")
    Optional<Client> findClientByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    boolean existsByUid(String uid);
}
