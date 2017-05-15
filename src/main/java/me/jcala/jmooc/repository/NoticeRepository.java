package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Notice;
import org.springframework.data.repository.CrudRepository;

public interface NoticeRepository extends CrudRepository<Notice,Long> {
}
