package ru.dev.kiyari.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.dev.kiyari.auth.model.entity.User;


public interface UserService {
    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public User save(User user);


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public User create(User user);

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public User getByUsername(String username);

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService();

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public User getCurrentUser();


    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    @Deprecated
    public void getAdmin();
}
