package dev.aloysius.PropertyRentalApplication.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.aloysius.PropertyRentalApplication.Models.Users.AppUsers;
import dev.aloysius.PropertyRentalApplication.Models.Users.AuthenticationRequest;
import dev.aloysius.PropertyRentalApplication.Security.SecurityService.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.io.UnsupportedEncodingException;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthController.class)
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthenticationService authenticationService;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                //.apply(springSecurity())
                .build();
    }


    @Test
    void anybodyCanAccessTheCreateUserEndpoint() throws Exception {

        AppUsers appUsers = new AppUsers();
        appUsers.setFirstName("Aloysius"); appUsers.setUserPassword("12345"); appUsers.setEmail("icangetbetter");
        //Mockito.doNothing().when(authenticationService).createUsers(appUsers);

        String regRequest = objectMapper.writeValueAsString(appUsers);
        mockMvc.perform(post("/api/v1/users/auth/register")
                        .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                        .content(regRequest))
                .andExpect(status().isOk());
    }

    @Test
    void registeredUsersShouldBeAbleToLogin() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername("");
        request.setPassword("12345");
        String re = objectMapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/users/auth/login").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON).content(re))
                .andExpect(status().isOk())
                .andReturn();
       // String token = mvcResult.getResponse().getContentAsString();
    }


    @Test
    void handleException() {
    }
}