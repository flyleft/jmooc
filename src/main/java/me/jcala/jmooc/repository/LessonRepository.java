package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Lesson;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LessonRepository extends CrudRepository<Lesson,Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "update lesson les set les.video =?1 where les.id = ?2",nativeQuery = true)
    void updateVideoById(String video,long lesId);


    @Modifying(clearAutomatically = true)
    @Query(value = "update lesson les set les.file_json =?1 where les.id = ?2",nativeQuery = true)
    void updateFileById(String file,long lesId);

}
