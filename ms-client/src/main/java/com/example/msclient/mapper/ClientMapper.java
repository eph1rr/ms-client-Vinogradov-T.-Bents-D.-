package com.example.msclient.mapper;

import com.example.msclient.dto.ClientDto;
import com.example.msclient.model.Client;
import com.example.msclient.model.ClientType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mappings({
            @Mapping(source = "clientType.key", target = "clientTypeKey")
    })
    ClientDto toDto(Client client);

    @Mappings({
            @Mapping(target = "clientType", ignore = true), // Установим вручную
            @Mapping(target = "fullName", source = "fullName"),
            @Mapping(target = "shortName", source = "shortName"),
            @Mapping(target = "inn", source = "inn"),
            @Mapping(target = "active", source = "active"),
            @Mapping(target = "createDateTime", ignore = true),
            @Mapping(target = "updateDateTime", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    Client toEntity(ClientDto dto);

    default Client fromDtoWithType(ClientDto dto) {
        Client client = toEntity(dto);
        if (dto.getClientTypeKey() != null) {
            ClientType type = new ClientType();
            type.setKey(dto.getClientTypeKey());
            client.setClientType(type);
        }
        return client;
    }
}
