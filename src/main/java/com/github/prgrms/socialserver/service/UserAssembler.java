package com.github.prgrms.socialserver.service;

import com.github.prgrms.socialserver.controller.dto.request.UserJoinRequestDto;
import com.github.prgrms.socialserver.controller.dto.request.UserRequestDto;
import com.github.prgrms.socialserver.controller.dto.response.UserResponseDto;
import com.github.prgrms.socialserver.domain.model.User;

public class UserAssembler {

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto.Builder()
                .seq(user.getSeq())
                .email(user.getEmail())
                .passwd(user.getPasswd())
                .loginCount(user.getLoginCount())
                .lastLoginDate(user.getLastLoginDate())
                .createAt(user.getCreateAt())
                .build();
    }

    public static User toEntity(UserRequestDto userRequestDto) {
        return new User.Builder(userRequestDto.getEmail(), userRequestDto.getPasswd())
                .seq(userRequestDto.getSeq())
                .loginCount(userRequestDto.getLoginCount())
                .lastLoginDate(userRequestDto.getLastLoginDate())
                .createAt(userRequestDto.getCreateAt())
                .build();
    }

    public static User toEntity(UserJoinRequestDto userJoinRequestDto) {
        return new User.Builder(userJoinRequestDto.getPrincipal(), userJoinRequestDto.getCredentials())
                .build();
    }

    public static User toEntity(UserResponseDto userResponseDto) {
        return new User.Builder(userResponseDto.getEmail(), userResponseDto.getPasswd())
                .seq(userResponseDto.getSeq())
                .loginCount(userResponseDto.getLoginCount())
                .lastLoginDate(userResponseDto.getLastLoginDate())
                .createAt(userResponseDto.getCreateAt())
                .build();
    }
}
