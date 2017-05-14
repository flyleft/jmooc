package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ExerciseRepository  extends CrudRepository<Exercise,Long> {

    List<Exercise> findByType(@Param("type") String type, Pageable pageable);

    List<Exercise> findByDifficulty(@Param("difficulty") int def, Pageable pageable);

    Page<Exercise> findAll(Pageable pageable);

    int countExerciseByType(@Param("type") String type);

    int countExerciseByDifficulty(@Param("difficulty") int def);


}
