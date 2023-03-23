package com.together.trading.member.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {

    GUEST("ROLE_GUEST", "Guest"),
    USER("ROLE_USER", "User");

    private final String key;
    private final String title;

}
