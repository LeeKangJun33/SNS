package com.example.simplesns.service;

import com.example.simplesns.exception.SnsApplicationException;
import com.example.simplesns.model.entity.UserEntity;
import com.example.simplesns.repository.UserEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserEntityRepository userEntityRepository;


    @Test
    void 회원가입이_정상적으로_동작하는_경우(){
        String userName="userName";
        String password = "password";

        //mocking
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());
        when(userEntityRepository.save(any())).thenReturn(Optional.of(mock(UserEntity.class)));

        Assertions.assertDoesNotThrow(() -> userService.join(userName,password));
    }

    @Test
    void 회원가입시_userName으로_회원가입한_유저가_이미_있는경우(){
        String userName="userName";
        String password = "password";

        //mocking
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(mock(UserEntity.class)));
        when(userEntityRepository.save(any())).thenReturn(Optional.of(mock(UserEntity.class)));

       Assertions.assertThrows(SnsApplicationException.class,()-> userService.join(userName,password));
    }

}
