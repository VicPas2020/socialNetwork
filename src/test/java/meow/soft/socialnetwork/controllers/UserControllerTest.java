package meow.soft.socialnetwork.controllers;

import meow.soft.socialnetwork.model.User;
import meow.soft.socialnetwork.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    UUID userId = UUID.randomUUID();

    UUID subscribeId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        when(userService.get(any())).thenReturn(new User());
    }

    @Test
    void testAddSubscribe() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user/addSubscribe/")
                .param("userId", userId.toString())
                .param("subscribeId", subscribeId.toString())
                .contentType("application/json")
        ).andExpect(status().isOk());

    }

    @Test
    void testRemoveSubscribe() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user/removeSubscribe/")
                .param("userId", userId.toString())
                .param("subscribeId", subscribeId.toString())
                .contentType("application/json")
        ).andExpect(status().isOk());
    }

    @Test
    void testGetPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/user/")
                .contentType("application/json")
        ).andExpect(status().isOk());
    }

    @Test
    void testGetOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/user/" + userId)
                .contentType("application/json")
        ).andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        String content = "{\n" +
                "    \"firstName\" : \"test2\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/user/")
                .content(content)
                .contentType("application/json")
        ).andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        String content = "{\n" +
                "    \"firstName\" : \"test2\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user/")
                .content(content)
                .contentType("application/json")
        ).andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/user/" + userId)
                .contentType("application/json")
        ).andExpect(status().isOk());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme