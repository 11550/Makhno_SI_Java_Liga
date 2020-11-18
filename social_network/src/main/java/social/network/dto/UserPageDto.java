package social.network.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Dto для представления данных пользователя, страница
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPageDto {

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
