package com.meli.xmen.entity;

import lombok.*;
import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity
@Getter
@Setter
public class TypePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column
    private String dna;

    @Column
    private Boolean ismutant;
}
