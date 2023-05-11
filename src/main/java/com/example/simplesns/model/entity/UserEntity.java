package com.example.simplesns.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class UserEntity {

    @Id
    private Integer id;

    @Column(name="user_name")
    private String userName;
}
