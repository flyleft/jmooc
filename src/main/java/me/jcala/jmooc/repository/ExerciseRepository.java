package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ExerciseRepository  extends CrudRepository<Exercise,Long> {
}
