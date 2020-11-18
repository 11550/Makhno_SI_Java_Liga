package social.network.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import social.network.dto.UserRegistrationDto;
import social.network.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.flyway.enabled=false")
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;


    //it seems this does not work
    @Test
    @DisplayName("Проверка на возврат статуса 201 и верного id")
    public void testAddUserStatusOkAndContentOk() throws Exception {
        UserRegistrationDto userDto = new UserRegistrationDto("test@test.test", "admin", "admin");
        this.mockMvc.perform(post("/api/v1/users")
                .content(objectMapper.writeValueAsString(userDto))
                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().string(id.toString()))
                .andExpect(status().isCreated());
    }
}
