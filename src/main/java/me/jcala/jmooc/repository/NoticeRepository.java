package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Notice;
import me.jcala.jmooc.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice,Long> {

    List<Notice> findByTypeAndOwnerAndFromInfoId(int type, User owner,long fromInfoId);
}
