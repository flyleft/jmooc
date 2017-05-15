package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Notice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice,Long> {

    List<Notice> findByTypeAndFromInfoId(int type,long fromInfoId);
}
