package com.girichev.security.service;

import com.girichev.client.model.Client;
import com.girichev.client.model.ClientStatus;
import com.girichev.client.storage.ClientsRepository;
import com.girichev.excepton.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientDetailsService implements UserDetailsService {
    private final ClientsRepository clientsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientsRepository.findClientByPhoneNumber(username).orElseThrow(() -> new NotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                client.getPhoneNumber(),
                client.getPassword(),
                client.getStatus().equals(ClientStatus.ACTIVE),
                client.getStatus().equals(ClientStatus.ACTIVE),
                client.getStatus().equals(ClientStatus.ACTIVE),
                client.getPhoneConfirmed(),
                client.getRoles()
                        .stream()
                        .map((role) -> new SimpleGrantedAuthority(role.name()))
                        .collect(Collectors.toList())
        );
    }
}
