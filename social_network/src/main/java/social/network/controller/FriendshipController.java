package social.network.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.dto.UserEditDto;
import social.network.service.FriendshipService;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для работы с друзьями пользователя
 */
@RestController
@RequestMapping("users/{userId}/friends")
@RequiredArgsConstructor
@Slf4j
public class FriendshipController {

    private final FriendshipService friendshipService;

    /**
     * Добавление друга
     *
     * @param userId   идентификатор пользователя
     * @param friendId идентификатор друга
     * @return response объект с соответствующим статусом состояния
     */
    @PostMapping
    public UUID add(@PathVariable UUID userId,
                    @RequestBody UUID friendId) {
        return friendshipService.add(userId, friendId);
    }

    /**
     * Удаление друга из списка друзей
     *
     * @param userId   идентификатор пользователя
     * @param friendId идентификатор друга
     * @return response объект с соответствующим статусом состояния
     */
    @DeleteMapping
    public void delete(@PathVariable UUID userId,
                       @RequestBody UUID friendId) {
        friendshipService.delete(userId, friendId);
    }

    /**
     * Получение друзей пользователя
     *
     * @param userId идентификатор пользователя
     * @return список друзей пользователя
     */
    @GetMapping
    public List<UserEditDto> findAll(@PathVariable UUID userId) {
        return friendshipService.findAll(userId);
    }
}
