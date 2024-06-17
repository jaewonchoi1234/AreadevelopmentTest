package com.sparta.areadevelopment;

import com.sparta.areadevelopment.dto.UpdateUserDto;
import com.sparta.areadevelopment.entity.StatusEnum;
import com.sparta.areadevelopment.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DtoEntityTest {

    @Test
    @DisplayName("User updateProfile")
    void test() {
        //given
        String username = "user1";
        String nickname = "닉네임";
        String email = "abce@gmail.com";
        String info = "안녕하세요";
        String password = "Ab12345678!";
        User user = new User(username, nickname, password, email, info);

        String updateNickname = "닉네임수정";
        UpdateUserDto updateUserDto = new UpdateUserDto(updateNickname, email, info, password);

        //when
        user.updateProfile(updateUserDto);

        //then
        assertEquals(updateNickname, user.getNickname());
    }

    @Test
    @DisplayName("User softDelete")
    void test2() {
        //given
        String username = "user1";
        String nickname = "닉네임";
        String email = "abce@gmail.com";
        String info = "안녕하세요";
        String password = "Ab12345678!";

        User user = new User(username, nickname, password, email, info);

        //when
        user.softDelete();

        //then
        assertEquals(StatusEnum.DELETED, user.getStatus());

    }


}
