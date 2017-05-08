package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Chapter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ChapterRepository extends CrudRepository<Chapter,Integer>{

    @Query("select lessons from Chapter where id =?1")
    Chapter findLessonListById(int id);
}
