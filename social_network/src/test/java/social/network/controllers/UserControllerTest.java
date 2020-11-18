package social.network.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import social.network.controller.UserController;
import social.network.dto.UserEditDto;
import social.network.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;
    private UserEditDto expectedUserEditDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);

        expectedUserEditDto = new UserEditDto("test@test.test", "Andre", "Andreev", 55, "M", "Skydiving", "Moscow");
    }

    @Test
    @DisplayName("добавление пользователя Ок")
    public void testAddOk() {
        UUID id = UUID.randomUUID();
        Mockito.when(userService.create(any())).thenReturn(id);
        ResponseEntity<String> answer = userController.create(any());
        assertEquals(id.toString(), answer.getBody());
        Mockito.verify(userService, Mockito.times(1)).create(any());
    }

    @Test
    @DisplayName("получения пользователя по id Ок")
    public void testGetByIdOk() {
        Mockito.when(userService.findById(any())).thenReturn(expectedUserEditDto);
        ResponseEntity<UserEditDto> answer = userController.get(any());
        assertEquals(expectedUserEditDto, answer.getBody());
        Mockito.verify(userService, Mockito.times(1)).findById(UUID.randomUUID());
    }

    @Test
    @DisplayName("получение списка пользователей с фильтром Ок")
    public void testGetWithFiltersOk() {
        List<UserEditDto> expectedUsers = new ArrayList<>();
        expectedUsers.add(expectedUserEditDto);
        Mockito.when(userService.findWithFilter(any())).thenReturn(expectedUsers);
        ResponseEntity<List<UserEditDto>> answer = userController.getUsersWithFilters(any());
        assertEquals(expectedUsers, answer.getBody());
        Mockito.verify(userService, Mockito.times(1)).findWithFilter(any());
    }

    @Test
    @DisplayName("обновление данных пользователя Ок")
    public void testUpdateOk() {
        UUID id = UUID.randomUUID();
        Mockito.when(userService.update(any(), any())).thenReturn(id);
        ResponseEntity<String> answer = userController.update(any(), any());
        assertEquals(id.toString(), answer.getBody());
        Mockito.verify(userService, Mockito.times(1)).update(any(), any());
    }
}
