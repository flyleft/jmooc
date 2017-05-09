package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Long>{

    User findUserByName(String name);

    User findUserByNameAndPasswordAndRole(@Param("name") String name,
                                      @Param("password") String password,
                                      @Param("role") int role);
}
