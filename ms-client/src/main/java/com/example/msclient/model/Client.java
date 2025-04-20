    package com.example.msclient.model;

    import jakarta.persistence.*;
    import lombok.*;
    import org.hibernate.annotations.GenericGenerator;

    import java.time.LocalDateTime;
    import java.util.UUID;

    @Entity
    @Table(name = "client", schema = "ms_client_schema")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Client {

        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "id")
        private UUID id;

        @Column(name = "full_name", nullable = false)
        private String fullName;

        @Column(name = "short_name", nullable = false)
        private String shortName;

        @Column(name = "create_date_time", nullable = false)
        private LocalDateTime createDateTime;

        @Column(name = "update_date_time", nullable = false)
        private LocalDateTime updateDateTime;

        @ManyToOne
        @JoinColumn(name = "client_type_key", nullable = false)
        private ClientType clientType;

        @Column(name = "inn", nullable = false)
        private String inn;

        @Column(name = "active")
        private Boolean active;
    }
