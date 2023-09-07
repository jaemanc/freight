package com.express.freight.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name="TB_USER")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements Persistable<String> {

    @Id
    @Column(name="user_id")
    @ApiModelProperty(example = "사용자 아이디")
    private String userId;

    // select - insert 방지
    @Override
    public String getId() {
        return userId;
    }
    @Override
    public boolean isNew() {
        return true;
    }

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
    public UserEntity(String userId, String name, String contact, String email, String extra) {
        this.userId = userId;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.extra = extra;
    }
}