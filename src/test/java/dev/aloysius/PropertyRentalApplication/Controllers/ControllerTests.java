package dev.aloysius.PropertyRentalApplication.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.aloysius.PropertyRentalApplication.Models.Users.AppUsers;
import dev.aloysius.PropertyRentalApplication.Security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SecurityConfig.class)
@WebAppConfiguration
public class ControllerTests {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void createUser() throws Exception {

        AppUsers appUsers = new AppUsers();
        appUsers.setFirstName("Aloysius"); appUsers.setUserPassword("12345"); appUsers.setEmail("icangetbetter");
        //Mockito.doNothing().when(authenticationService).createUsers(appUsers);

        String regRequest = objectMapper.writeValueAsString(appUsers);
        mvc.perform(post("/api/v1/user/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(regRequest))
                .andExpect(status().isOk());
    }
}
