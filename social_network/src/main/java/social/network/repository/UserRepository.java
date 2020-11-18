package social.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import social.network.domain.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий сущности User
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

    /**
     * Метод для получения пользователя по email
     *
     * @param email email
     * @return Optional Пользователя
     */
    Optional<User> findByEmail(String email);
}
