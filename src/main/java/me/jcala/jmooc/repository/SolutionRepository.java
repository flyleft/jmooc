package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.oj.Solution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SolutionRepository extends CrudRepository<Solution,Long>{
}
