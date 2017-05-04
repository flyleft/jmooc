package me.jcala.jmooc.service;

import me.jcala.jmooc.entity.User;
import me.jcala.jmooc.repository.UserRepository;
import me.jcala.jmooc.service.inter.InitSer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InitSerImpl implements InitSer{

    private static final Logger logger= LoggerFactory.getLogger(InitSer.class);

    private UserRepository userRepository;

    @Autowired
    public InitSerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    @Override
    public void executeInit() {
       logger.info("----------开始初始化数据-----------");
       initUserData();
    }


    private void initUserData(){
        User jcala=new User(1,"jcala","jcala",1);
        User tea=new User(1,"tea","tea",2);
        userRepository.save(jcala);
        userRepository.save(tea);
    }

}
