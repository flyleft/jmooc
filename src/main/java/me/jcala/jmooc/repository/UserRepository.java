package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User,Long>{

    User findUserByName(String name);

    User findUserByNameAndPasswordAndRole(@Param("name") String name,
                                      @Param("password") String password,
                                      @Param("role") int role);

    @Modifying(clearAutomatically = true)
    @Query(value = "update users u set u.join_courses =?1 where u.id = ?2",nativeQuery = true)
    void updateJoinCourses(String crsStr,long userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update users u set u.notice_num = u.notice_num + 1 where u.id = ?1",nativeQuery = true)
    void noticeNumPlusOne(long userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update users u set u.col_exercises = ?1 where u.id = ?2",nativeQuery = true)
    void updateColExe(String colExercises,long userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update users u set u.notice_num = 0 where u.id = ?1",nativeQuery = true)
    void clearNoticeNum(long userId);
}
