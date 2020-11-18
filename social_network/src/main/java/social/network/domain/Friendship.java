package social.network.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность Дружба
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Friendships")
public class Friendship extends Identifiable {

    /**
     * Пользователь пригласивший
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    /**
     * Пользователь приглашенный
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id", nullable = false, updatable = false)
    private User friend;


    public Friendship(User user, User friend) {
        this.user = user;
        this.friend = friend;
    }
}
