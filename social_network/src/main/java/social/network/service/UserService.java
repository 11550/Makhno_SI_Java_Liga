package social.network.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import social.network.domain.User;
import social.network.dto.UserEditDto;
import social.network.dto.UserRegistrationDto;
import social.network.repository.FriendshipRepository;
import social.network.repository.UserRepository;
import social.network.service.filters.UserFilter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final FriendshipRepository friendshipRepository;

    /**
     * Метод получения пользователя по id
     *
     * @param userId Идентификатор пользователя
     * @return полная Dto пользователя
     */
    public UserEditDto findById(UUID userId) {
        return convertToEditDto(getUserFromRepo(userId));
    }

    /**
     * Метод поиска пользователей с помощью фильтров
     *
     * @param filter Фильтр пользователей
     * @return Список пользователей в виде Dto
     */
    public List<UserEditDto> findWithFilter(UserFilter filter) {
        return userRepository.findAll(filter.toSpecification())
                .stream().map(this::convertToAsListDto)
                .collect(Collectors.toList());
    }

    /**
     * Метод добавления пользователя
     *
     * @param dto Dto c данными о пользователе для регистрации
     * @return Идентификатор пользователя
     */
    @Transactional
    public UUID create(UserRegistrationDto dto) throws RuntimeException {
        isPresentEmail(dto.getEmail());
        User user = convertFromRegistrationDto(dto);
        return userRepository.save(user).getId();
    }

    /**
     * Метод обновления данных пользователя
     *
     * @param dto    Dto с данными пользователя для обновления
     * @param userId Идентификатор изменяемого пользователя
     * @return Иднетификатор пользователя с обновленными данными
     */
    @Transactional
    public UUID update(UserEditDto dto, UUID userId) {
        User temp = getUserFromRepo(userId);
        if (dto.getEmail().isBlank()) {
            dto.setEmail(temp.getEmail());
        } else {
            isPresentEmail(dto.getEmail());
        }
        if (dto.getFirstName().isBlank()) {
            dto.setFirstName(temp.getFirstName());
        }
        if (dto.getLastName().isBlank()) {
            dto.setLastName(temp.getLastName());
        }
        User user = convertFromEditDto(dto, temp);
        return userRepository.save(user).getId();
    }

    /**
     * Метод удаления пользователя
     *
     * @param userId Идентификатор пользователя
     */
    @Transactional
    public ResponseEntity<String> delete(UUID userId) {
        User user = getUserFromRepo(userId);

        friendshipRepository.findFriendsByUser(user).forEach(friend -> {
            friendshipRepository.deleteByUserAndFriend(user, friend);
            friendshipRepository.deleteByUserAndFriend(friend, user);
        });
        userRepository.deleteById(userId);
        return new ResponseEntity<>(userId.toString(), HttpStatus.OK);
    }

    /**
     * Метод конвертации Dto с регистрационными данными в сущность пользователя
     *
     * @param dto Dto с данными пользователя для регистрации
     * @return Экземпляр пользователя
     */
    public User convertFromRegistrationDto(UserRegistrationDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }

    /**
     * Метод конвертации Dto с полными данными в экземпляр пользователя
     *
     * @param dto  Dto c полными данными пользователя
     * @param user экземпляр пользователя для конвертации
     * @return Экземпляр пользователя с полными данными
     */
    public User convertFromEditDto(UserEditDto dto, User user) {
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAge(dto.getAge());
        user.setGender(dto.getGender());
        user.setInterests(dto.getInterests());
        user.setCity(dto.getCity());
        return user;
    }

    /**
     * Метод конвертации экземпляра пользователя в Dto с полными данными
     *
     * @param user Экземпляр пользователя для конвертации
     * @return Dto c полными данными пользователя
     */
    public UserEditDto convertToEditDto(User user) {
        return UserEditDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .gender(user.getGender())
                .interests(user.getInterests())
                .city(user.getCity())
                .build();
    }

    /**
     * Метод конвертации экземпляра пользователя в Dto с полными данными
     *
     * @param user Экземпляр пользователя для конвертации
     * @return Dto c полными данными пользователя
     */
    public UserEditDto convertToAsListDto(User user) {
        return UserEditDto.builder()
                .age(user.getAge())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    /**
     * Метод получения пользователя из репозитория
     *
     * @param userId идентификатор пользователя для поиска в репозитории
     * @return искомый пользователь
     */
    protected User getUserFromRepo(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user by id: " + userId));
    }

    /**
     * Метод для проверки свободен ли email
     *
     * @param email email пользователя
     */
    protected void isPresentEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("This email is already taken.");
        }
    }
}
