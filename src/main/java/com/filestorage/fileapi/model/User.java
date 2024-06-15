package com.filestorage.fileapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable=false)
    private String username;
    @Column(unique=true, nullable=false)
    private String password;

}
