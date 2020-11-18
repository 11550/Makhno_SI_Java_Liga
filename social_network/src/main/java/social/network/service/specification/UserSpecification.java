package social.network.service.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import social.network.domain.User;

public class UserSpecification {

    /**
     * Поиск по вхождению
     *
     * @param column колонка таблицы сущности T
     * @param value  значения для поиска
     * @return спецификация
     */
    public static Specification<User> like(final String column, final String value) {
        return StringUtils.isEmpty(column) ||  StringUtils.isEmpty(value)
                ? null
                : (root, query, cb) ->
                cb.like(root.get(column), "%" + value + "%");
    }

    /**
     * Поиск по эквивалентности полю
     *
     * @param column колонка таблицы сущности T
     * @param flag   значение поля
     * @return спецификация
     */
    public static <T> Specification<T> equal(final String column, final Object flag) {
        return  StringUtils.isEmpty(column) || ObjectUtils.isEmpty(flag)
                ? null
                : (root, query, cb) -> cb.equal(root.get(column), flag);
    }

    /**
     * Поиск по величине поля >= переданного значения
     *
     * @param column колонка таблицы сущности T
     * @param value  значения для поиска
     * @return спецификация
     */
    public static <T> Specification<T> greaterThanOrEqualTo(final String column, final Integer value) {
        return StringUtils.isEmpty(column)
                ? null
                : (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get(column), value);
    }

    /**
     * Поиск по величине поля < переданного значения
     *
     * @param column колонка таблицы сущности T
     * @param value  значения для поиска
     * @return спецификация
     */
    public static <T> Specification<T> lessThan(final String column, final Integer value) {
        return StringUtils.isEmpty(column)
                ? null
                : (root, query, cb) ->
                cb.lessThan(root.get(column), value);
    }
}
