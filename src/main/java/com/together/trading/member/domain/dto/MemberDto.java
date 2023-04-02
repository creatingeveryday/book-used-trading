package com.together.trading.member.domain.dto;

import com.together.trading.member.domain.entity.Member;
import com.together.trading.member.domain.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberDto {
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private Role role;
    private String image;

    @Builder
    public MemberDto(String loginId, String password, String name, Role role, String image) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = role;
        this.image = image;
    }

    public MemberDto(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.name = member.getName();
        this.role = member.getRole();
        this.image = member.getImage();
    }

    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .role(role)
                .image(image)
                .build();
    }
}
