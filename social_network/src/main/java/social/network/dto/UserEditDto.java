package social.network.dto;

import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Dto для редактирования данных пользователя
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEditDto {

    /**
     * Поле Адрес почты
     */
    @Pattern(regexp = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}", message = "Expected correct email")
    private String email;

    /**
     * Поле Имя
     */
    private String firstName;

    /**
     * Поле Фамилия
     */
    private String lastName;

    /**
     * Поле возраст
     */
    @Size(min = 0, max = 120)
    private Integer age;

    /**
     * Поле пол
     */
    @Pattern(regexp = "^[M|F|O]$", message = "Expected 'M', 'F' or 'O'")
    private String gender;

    /**
     * Поле Интересы
     */
    private String interests;

    /**
     * Поле Город
     */
    @Size(min = 2, max = 25)
    private String city;
}
