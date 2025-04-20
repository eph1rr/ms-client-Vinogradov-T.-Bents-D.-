package com.example.msclient.service;

import com.example.msclient.dto.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Page<ClientDto> searchClients(String fullName, String inn, Boolean active, String clientTypeKey, Pageable pageable);
    Optional<ClientDto> getClientById(UUID id);
    ClientDto createClient(ClientDto clientDto);
    ClientDto updateClient(UUID id, ClientDto clientDto);
    void deleteClient(UUID id);
}
