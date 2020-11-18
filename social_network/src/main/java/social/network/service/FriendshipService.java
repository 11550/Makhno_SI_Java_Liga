package social.network.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import social.network.domain.Friendship;
import social.network.domain.User;
import social.network.dto.UserEditDto;
import social.network.repository.FriendshipRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис Дружбы
 */
@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final UserService userService;

    private final FriendshipRepository friendshipRepository;

    /**
     * создание дружбы
     *
     * @param userId   Идентификатор пользователя пригласившего
     * @param friendId Идентификатор пользователя приглашенного
     * @return Идентификатор дружбы
     */
    public UUID add(UUID userId, UUID friendId) throws RuntimeException {
        User user = userService.getUserFromRepo(userId);
        User friend = userService.getUserFromRepo(friendId);
        Optional<Friendship> friendship = friendshipRepository.findByUserAndFriend(user, friend);
        if (friendship.isEmpty()) {
            Friendship friendshipUF = new Friendship(user, friend);
            Friendship friendshipFU = new Friendship(friend, user);
            friendshipRepository.save(friendshipFU);
            return friendshipRepository.save(friendshipUF).getId();
        }
        throw new RuntimeException("You're already friends.");
    }

    /**
     * Удаление друга
     *
     * @param userId   Идентификатор пользователя-"владельца дружбы"
     * @param friendId Идентификатор друга
     */
    public String delete(UUID userId, UUID friendId) {
        User user = userService.getUserFromRepo(userId);
        User friend = userService.getUserFromRepo(friendId);
        friendshipRepository.deleteByUserAndFriend(user, friend);
        friendshipRepository.deleteByUserAndFriend(friend, user);
        return "Successful deleted.";
    }

    /**
     * Получение списка друзей пользователя
     *
     * @param userId Идентификатор пользователя
     * @return Список друзей в виде Dto
     */
    public List<UserEditDto> findAll(UUID userId) {
        User user = userService.getUserFromRepo(userId);
        return user.getFriends().stream().map(userService::convertToAsListDto).collect(Collectors.toList());
    }
}
