package social.network.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import social.network.controller.FriendshipController;
import social.network.dto.UserEditDto;
import social.network.service.FriendshipService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

public class FriendshipControllerTest {

    @Mock
    private FriendshipService friendshipService;

    private FriendshipController friendshipController;
    private UserEditDto expectedUserEditDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        friendshipController = new FriendshipController(friendshipService);
        expectedUserEditDto = new UserEditDto("test@test.ru", "Userf", "Userl", 5, "M", "Skydiving", "Moscow");
    }

    @Test
    @DisplayName("удаление друга Ок")
    public void testDeleteFriendOk() {
        Mockito.doNothing().when(friendshipService).delete(any(), any());
        ResponseEntity<String> answer = friendshipController.delete(any(), any());
        assertEquals(200, answer.getStatusCodeValue());
        assertNull(answer.getBody());
        Mockito.verify(friendshipService, Mockito.times(1)).delete(any(), any());
    }

}
