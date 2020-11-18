package social.network.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User extends Identifiable {

    /**
     * email
     */
    @Column(name = "EMAIL", nullable = false, unique = true)
    @NotEmpty
    private String email;

    /**
     * Имя пользователя
     */
    @Column(name = "FIRST_NAME", nullable = false)
    @NotEmpty
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "LAST_NAME", nullable = false)
    @NotEmpty
    private String lastName;

    /**
     * Возраст пользователя
     */
    private Integer age;

    /**
     * Гендерная идентичность пользователя
     */
    private String gender;

    /**
     * Интересы пользователя
     */
    private String interests;

    /**
     * Город пользователя
     */
    @Column(name = "CITY")
    private String city;

    /**
     * Друзья пользователя
     */
    @ManyToMany
    @JoinTable(
            name = "Friendships",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friends;
}
