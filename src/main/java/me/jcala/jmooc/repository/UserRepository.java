package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer>{

    User findUserByName(String name);
}
