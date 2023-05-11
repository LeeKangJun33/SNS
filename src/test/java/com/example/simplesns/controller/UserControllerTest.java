package com.example.simplesns.controller;

import com.example.simplesns.controller.request.UserJoinRequest;
import com.example.simplesns.controller.request.UserLoginRequest;
import com.example.simplesns.exception.SnsApplicationException;
import com.example.simplesns.model.User;
import com.example.simplesns.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void 회원가입() throws Exception {
        String userName = "userName";
        String password = "password";

        //TODO: Mocking
        when(userService.join(userName,password)).thenReturn(mock(User.class));


        mockMvc.perform(post("/api/v1/user/join")
                .contentType(MediaType.APPLICATION_JSON)
                // TODO : request body 추가
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입시_이미_회원가입된_userName으로_회원가입을_하는경우_에러반환() throws Exception {
        String userName = "userName";
        String password = "password";


        when(userService.join(userName,password)).thenThrow(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/user/join")
                .contentType(MediaType.APPLICATION_JSON)
                // TODO : request body 추가
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName,password)))
                ).andDo(print())
                .andExpect(status().isConflict());
    }
    @Test
    public void 로그인() throws Exception {
        String userName = "userName";
        String password = "password";

        //TODO: Mocking
        when(userService.login()).thenReturn("test_token");


        mockMvc.perform(post("/api/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        // TODO : request body 추가
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password)))
                ).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void 로그인시_회원가입이_안된_userName를_입력할경우_에러반환() throws Exception {
        String userName = "userName";
        String password = "password";

        //TODO: Mocking
        when(userService.login()).thenThrow(new SnsApplicationException());


        mockMvc.perform(post("/api/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        // TODO : request body 추가
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password)))
                ).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void 로그인시_password_입력할경우_에러반환() throws Exception {
        String userName = "userName";
        String password = "password";

        //TODO: Mocking
        when(userService.login()).thenThrow(new SnsApplicationException());


        mockMvc.perform(post("/api/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        // TODO : request body 추가
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password)))
                ).andDo(print())
                .andExpect(status().isUnauthorized());
    }
    
}
