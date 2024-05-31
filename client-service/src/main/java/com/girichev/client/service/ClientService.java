package com.girichev.client.service;

import com.girichev.client.dto.input.ClientUpdateDtoInput;
import com.girichev.client.dto.output.ClientDtoOutput;

public interface ClientService {
    ClientDtoOutput createClient(String phone, ClientUpdateDtoInput clientUpdateDtoInput);

    ClientDtoOutput updateClient(String phone, ClientUpdateDtoInput clientUpdateDtoInput);

    ClientDtoOutput getClientByUid(String id);

    ClientDtoOutput deleteClientByUid(String id);
}
