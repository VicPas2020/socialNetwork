package meow.soft.socialnetwork.controllers;

import meow.soft.socialnetwork.exceptions.CommonException;
import meow.soft.socialnetwork.exceptions.NotFoundException;
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

import static org.mockito.Mockito.*;
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
    void testAddSubscribeOnItselc() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user/addSubscribe/")
                .param("userId", userId.toString())
                .param("subscribeId", userId.toString())
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
                .get("/api/user/" + userId)
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
                "    \"firstName\" : \"test2\",\n" +
                "    \"id\" : " + "\"" + userId.toString() + "\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/user/" + userId)
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

    @Test
    void testUserNotCreated() throws Exception {
        doThrow(NotFoundException.class).when(userService).addSubscriber(any(), any());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user/addSubscribe/")
                .param("userId", userId.toString())
                .param("subscribeId", subscribeId.toString())
                .contentType("application/json")
        ).andExpect(status().is4xxClientError());
    }

    @Test
    void testUserThrow500() throws Exception {
        doThrow(CommonException.class).when(userService).addSubscriber(any(), any());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user/addSubscribe/")
                .param("userId", userId.toString())
                .param("subscribeId", subscribeId.toString())
                .contentType("application/json")
        ).andExpect(status().is5xxServerError());
    }

    @Test
    void testUpdateWithDifferentId() throws Exception {
        String content = "{\n" +
                "    \"firstName\" : \"test2\",\n" +
                "    \"id\" : " + "\"" + UUID.randomUUID() + "\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/user/" + userId)
                .content(content)
                .contentType("application/json")
        ).andExpect(status().is5xxServerError());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme