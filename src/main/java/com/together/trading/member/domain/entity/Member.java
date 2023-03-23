package com.together.trading.member.domain.entity;

import com.together.trading.common.CommonEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "MEMBER")
@Entity
public class Member extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOGIN_ID", length = 50, nullable = false)
    private String loginId;

    @Column(name = "PASSWORD", length = 50, nullable = false)
    private String password;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;

    @Column(name = "IMAGE")
    private String image;

    @Builder
    public Member(String loginId, String password, String name, Role role, String image) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = role;
        this.image = image;
    }

    public Member update(String loginId, String password, String name, String image) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.image = image;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
