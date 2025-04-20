package com.example.msclient.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_type", schema = "ms_client_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientType {

    @Id
    @Column(name = "key")
    private String key;

    @Column(name = "name", nullable = false)
    private String name;
}
