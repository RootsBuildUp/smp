package com.smp.coremodule.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "users_generator" )
    @SequenceGenerator( name = "users_generator", sequenceName = "users_sequence" )
    @Column( name = "id" )
    private Long id;

    @Column( name = "name" )
    private String name;

    @Column( name = "address" )
    private String address;

}
