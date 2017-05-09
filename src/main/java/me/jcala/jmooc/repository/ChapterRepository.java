package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Chapter;
import org.springframework.data.repository.CrudRepository;


public interface ChapterRepository extends CrudRepository<Chapter,Long>{

    Chapter findById(long id);

}
