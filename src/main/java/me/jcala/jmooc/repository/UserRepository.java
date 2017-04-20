package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User,String>{


}
