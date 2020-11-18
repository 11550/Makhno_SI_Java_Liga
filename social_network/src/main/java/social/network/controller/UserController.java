package social.network.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.network.dto.UserEditDto;
import social.network.dto.UserRegistrationDto;
import social.network.service.UserService;
import social.network.service.filters.UserFilter;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Контроллер для работы с пользователем
 */
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * Создание нового пользователя
     *
     * @param userDto Dto для регистрации пользователя
     * @return id пользователя и статус 200 при успехе, иначе RuntimeException и статус 404
     */
    @PostMapping
    public UUID create(@RequestBody @Valid UserRegistrationDto userDto) {
        log.info("Создание нового пользователя user={}", userDto.toString());
        return userService.create(userDto);
    }

    /**
     * Обновление данных пользователя
     *
     * @param userDto Dto c данными пользователя для изменения
     * @param id      Идентификатор обновляемого пользователя
     * @return id и статус 200 при успехе, иначе RuntimeException и статус 404
     */
    @PutMapping("{id}")
    public UUID update(@RequestBody @Valid UserEditDto userDto, @PathVariable UUID id) {
        log.info("Изменение данных пользователя user={}", userDto.toString());
        return userService.update(userDto, id);
    }

    /**
     * Получения списка пользователей с фильтром
     *
     * @param filter Фильтр для фильтрации списка пользователей
     * @return Статус 200 и список пользователей в формате Dto при успехе, иначе пустое тело ответа и статус 404
     */
    @GetMapping
    public List<UserEditDto> getUsersWithFilters(UserFilter filter) {
        return userService.findWithFilter(filter);
    }

    /**
     * Получение пользователя по id
     *
     * @param id Идентификатор пользователя
     * @return Dto с полными данными и статус 200 при успехе, иначе пустое тело ответа и статус 404
     */
    @GetMapping("{id}")
    public UserEditDto get(@PathVariable UUID id) {
        log.info("Поиск пользователя по идентификатору id={}", id);
        return userService.findById(id);
    }

    /**
     * Метод для удаления пользователя
     *
     * @param id Идентификатор пользователя
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        userService.delete(id);
        return new ResponseEntity<>(id.toString(), HttpStatus.OK);
    }
}
