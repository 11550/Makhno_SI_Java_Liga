package social.network.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Dto для регистрации пользователя
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAsListDto {

    /**
     * Возраст пользователя
     */
    @NotEmpty
    private Integer age;

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
