package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.service.FrontSerImpl;
import org.springframework.data.domain.Pageable;

public interface FrontSer {

    FrontSerImpl.ExeFront getExeFront(String param, Pageable pageable);

    FrontSerImpl.CrsFront getCrsFront(String param,Pageable pageable);

}
