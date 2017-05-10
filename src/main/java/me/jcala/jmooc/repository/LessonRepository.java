package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Lesson;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface LessonRepository extends CrudRepository<Lesson,Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update lesson les set les.video =?1 where les.id = ?2",nativeQuery = true)
    void updateVideoById(String video,long lesId);

}
