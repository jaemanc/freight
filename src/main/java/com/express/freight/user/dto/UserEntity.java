package com.express.freight.user.dto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class UserEntity {
    @Id
    private Long user_id;

    private String name;

    private String contact;

    private String email;

    private String extra;


}
