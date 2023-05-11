package com.example.simplesns.service;

import com.example.simplesns.model.User;
import com.example.simplesns.model.entity.UserEntity;
import com.example.simplesns.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;
    //Todo : implement
    public User join(String userName,String password){
        //회원가입하는 userName으로 회원가입된 user가 있는지
        Optional<UserEntity> userEntity = userEntityRepository.findByUserName(userName);


        //회원가입 진행 - user 를 등록
        userEntityRepository.save(new UserEntity());
        return new User();
    }

    // TODO : implement 해줘야함
    public String login(){

        return "";
    }
}
