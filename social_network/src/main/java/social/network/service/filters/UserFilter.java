
package social.network.service.filters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import social.network.domain.User;
import social.network.service.specification.UserSpecification;

import static org.springframework.data.jpa.domain.Specification.where;

/**
 * Фильтр для реестра пользователей
 */
@Getter
@Setter
@NoArgsConstructor
public class UserFilter implements Filter<User> {

    private String firstNameLike;
    private String lastNameLike;
    private Integer minAge;
    private Integer maxAge;
    private String genderEqual;
    private String interestsLike;
    private String cityLike;

    @Override
    public Specification<User> toSpecification() {
        return where(UserSpecification.<User>like("firstName", firstNameLike))
                .and(UserSpecification.like("lastName", lastNameLike))
                .and(UserSpecification.greaterThanOrEqualTo("age", minAge))
                .and(UserSpecification.lessThan("age", maxAge))
                .and(UserSpecification.equal("gender", genderEqual))
                .and(UserSpecification.like("interests", interestsLike))
                .and(UserSpecification.like("city", cityLike));
    }
}
