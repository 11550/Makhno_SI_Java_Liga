package social.network.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import social.network.domain.Friendship;
import social.network.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий сущности Friendship
 */
@Repository
public interface FriendshipRepository extends CrudRepository<Friendship, UUID>, JpaSpecificationExecutor<Friendship> {

    /**
     * Метод для получения списка друзей
     *
     * @param user Пользователь
     * @return Список друзей пользователя
     */
    @Query("SELECT f.friend FROM Friendship f WHERE f.user = ?1")
    List<User> findFriendsByUser(User user);

    /**
     * Метод для получения дружбы между двумя пользователя
     *
     * @param user   Пользователь пригласивший
     * @param friend Пользователь приглашенный
     * @return Optional дружбы
     */
    Optional<Friendship> findByUserAndFriend(User user, User friend);

    /**
     * Метод для удаления дружбы между двумя пользователями
     *
     * @param user   Пользователь пригласивший
     * @param friend Пользователь приглашенный
     */

    void deleteByUserAndFriend(User user, User friend);
}
