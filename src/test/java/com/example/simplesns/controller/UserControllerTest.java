package com.example.simplesns.controller;

import com.example.simplesns.controller.request.UserJoinRequest;
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
        String userNme = "userName";
        String password = "password";

        //TODO: Mocking
        when(userService.join()).thenReturn(mock(User.class));


        mockMvc.perform(post("/api/v1/user/join")
                .contentType(MediaType.APPLICATION_JSON)
                // TODO : request body 추가
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userNme,password)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입시_이미_회원가입된_userName으로_회원가입을_하는경우_에러반환() throws Exception {
        String userNme = "userName";
        String password = "password";


        when(userService.join()).thenThrow(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/user/join")
                .contentType(MediaType.APPLICATION_JSON)
                // TODO : request body 추가
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userNme,password)))
                ).andDo(print())
                .andExpect(status().isConflict());
    }
    
}
