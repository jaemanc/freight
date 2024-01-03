package com.express.freight.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@RedisHash(value = "user")
public class RedisEntity {

    @Id
    @ApiModelProperty(example = "사용자 아이디")
    private String userId;

    @Column(name="name")
    @ApiModelProperty(example = "이름")
    private String name;

    @Column(name="contact")
    @ApiModelProperty(example = "연락처")
    private String contact;

    @Column(name="email")
    @ApiModelProperty(example = "이메일")
    private String email;

    @Column(name="extra")
    @ApiModelProperty(example = "비고")
    private String extra;

    @CreatedDate
    @Column(name="created_at")
    private Date createdAt;

    @Builder
    public RedisEntity(String userId, String name, String contact, String email, String extra) {
        this.userId = userId;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.extra = extra;
    }
}
