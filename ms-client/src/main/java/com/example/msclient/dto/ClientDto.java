package com.example.msclient.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ClientDto {
    private UUID id;
    private String fullName;
    private String shortName;
    private String inn;
    private Boolean active;

    // Это поле будет использоваться для связи с ClientType
    private String clientTypeKey;
}
