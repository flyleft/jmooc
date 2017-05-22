package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.oj.SolutionSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SolutionSourceRepository extends CrudRepository<SolutionSource,Long>{
}
