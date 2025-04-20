package com.example.msclient.service;

import com.example.msclient.dto.ClientDto;
import com.example.msclient.mapper.ClientMapper;
import com.example.msclient.model.Client;
import com.example.msclient.model.ClientType;
import com.example.msclient.repository.ClientRepository;
import com.example.msclient.repository.ClientTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientTypeRepository clientTypeRepository;
    private final ClientMapper clientMapper;

    @Override
    public Page<ClientDto> searchClients(String fullName, String inn, Boolean active, String clientTypeKey, Pageable pageable) {
        return clientRepository.findAll((root, query, cb) -> {
            var predicates = cb.conjunction();

            if (fullName != null) {
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%"));
            }
            if (inn != null) {
                predicates = cb.and(predicates, cb.equal(root.get("inn"), inn));
            }
            if (active != null) {
                predicates = cb.and(predicates, cb.equal(root.get("active"), active));
            }
            if (clientTypeKey != null) {
                predicates = cb.and(predicates, cb.equal(root.get("clientType").get("key"), clientTypeKey));
            }

            return predicates;
        }, pageable).map(clientMapper::toDto);
    }

    @Override
    public Optional<ClientDto> getClientById(UUID id) {
        return clientRepository.findById(id).map(clientMapper::toDto);
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        client.setCreateDateTime(LocalDateTime.now());
        client.setUpdateDateTime(LocalDateTime.now());

        ClientType clientType = clientTypeRepository.findById(clientDto.getClientTypeKey())
                .orElseThrow(() -> new RuntimeException("ClientType not found"));
        client.setClientType(clientType);

        return clientMapper.toDto(clientRepository.save(client));
    }

    @Override
    public ClientDto updateClient(UUID id, ClientDto clientDto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        existingClient.setFullName(clientDto.getFullName());
        existingClient.setShortName(clientDto.getShortName());
        existingClient.setUpdateDateTime(LocalDateTime.now());
        existingClient.setInn(clientDto.getInn());
        existingClient.setActive(clientDto.getActive());

        ClientType clientType = clientTypeRepository.findById(clientDto.getClientTypeKey())
                .orElseThrow(() -> new RuntimeException("ClientType not found"));
        existingClient.setClientType(clientType);

        return clientMapper.toDto(clientRepository.save(existingClient));
    }

    @Override
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }
}
