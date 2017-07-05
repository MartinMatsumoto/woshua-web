package com.woshua.structure.user.repository;

import com.woshua.structure.user.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Acer on 2017/3/24.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByAccountAndPassword(String account, String password);

    User findByEmailOrAccount(String email, String account);
}
