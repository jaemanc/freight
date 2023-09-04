package com.express.freight.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name="TB_USER")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @ApiModelProperty(example = "사용자 아이디")
    private Long user_id;

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

    public UserEntity(Long user_id, String name, String contact, String email, String extra, Date createdAt) {
        this.user_id = user_id;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.extra = extra;
        this.createdAt = createdAt;
    }
}