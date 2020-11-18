package social.network.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Dto для регистрации пользователя
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

    /**
     * email пользователя
     */
    @NotNull
    private String email;

    /**
     * Имя пользователя
     */
    @Size(min = 2, max = 50)
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Size(min = 2, max = 50)
    private String lastName;
}
