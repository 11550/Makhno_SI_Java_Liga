package social.network.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import social.network.domain.User;
import social.network.dto.UserEditDto;
import social.network.dto.UserRegistrationDto;
import social.network.repository.FriendshipRepository;
import social.network.repository.UserRepository;
import social.network.service.UserService;
import social.network.service.filters.UserFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FriendshipRepository friendshipRepository;

    private UserService userService;

    private User user;
    private UserRegistrationDto userDto;
    private UserEditDto userEditDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, friendshipRepository/*, modelMapper*/);

        user = new User("test@test.test", "Vasiliy", "Vasiliev");
        UUID id = UUID.randomUUID();
        user.setId(id);
        userDto = new UserRegistrationDto("test@test.test", "Vasiliy", "Vasiliev");
        userEditDto = new UserEditDto("user@user.ru", "Kuzma", "Kuzmin", 15, "M", "Skydiving", "Msk");
    }

    @Test
    @DisplayName("Поиск пользователя по id Ок")
    public void testFindByIdOk() {
        UserEditDto userEditDtoExpected = userService.convertToEditDto(user);
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));
        UserEditDto userEditDtoActual = userService.findById(UUID.randomUUID());
        assertEquals(userEditDtoExpected, userEditDtoActual);
        Mockito.verify(userRepository, Mockito.times(1)).findById(any());
    }

    @Test
    @DisplayName("Поиска пользователей с фильтром Ок")
    public void testFindWithFilterOk() {
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(new User("test@mail.ru", "Anton", "Anton"));
        List<UserEditDto> usersDtoExpected = users.stream()
                .map(userService::convertToEditDto)
                .collect(Collectors.toList());
        Mockito.when(userRepository.findAll((Example<User>) any())).thenReturn(users);
        List<UserEditDto> usersDtoActual = userService.findWithFilter(new UserFilter());
        assertEquals(2, usersDtoActual.size());
        assertEquals(usersDtoExpected, usersDtoActual);
        Mockito.verify(userRepository, Mockito.times(1)).findAll((Example<User>) any());
    }

    @Test
    @DisplayName("Добавление пользователя Ок")
    public void testAddOk() {
        Mockito.when(userService.convertFromRegistrationDto(userDto)).thenReturn(user);
        Mockito.when(userRepository.save(any())).thenReturn(user).thenReturn(user.getId());
        UUID expectedUserId = user.getId();
        UUID actualUserId = userService.create(userDto);
        assertEquals(expectedUserId, actualUserId);
        Mockito.verify(userService, Mockito.times(1)).create(userDto);
        Mockito.verify(userRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("Обновление данных пользователя Ок")
    public void testUpdateOk() {
        User userNew = userService.convertFromEditDto(userEditDto, new User());
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(any())).thenReturn(userNew).thenReturn(userNew.getId());
        userService.update(userEditDto, user.getId());
        String actualName = user.getFirstName();
        String expectedName = userEditDto.getFirstName();
        assertEquals(expectedName, actualName);
        Mockito.verify(userRepository, Mockito.times(1)).findById(any());
        Mockito.verify(userRepository, Mockito.times(1)).save(any());
    }


}
