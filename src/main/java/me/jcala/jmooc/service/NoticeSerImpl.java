package me.jcala.jmooc.service;

import me.jcala.jmooc.entity.Notice;
import me.jcala.jmooc.entity.auxiliary.UserAuxiliary;
import me.jcala.jmooc.repository.NoticeRepository;
import me.jcala.jmooc.repository.UserRepository;
import me.jcala.jmooc.service.inter.NoticeSer;
import me.jcala.jmooc.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class NoticeSerImpl implements NoticeSer{

    private NoticeRepository noticeRepository;
    private UserRepository userRepository;

    @Autowired
    public NoticeSerImpl(NoticeRepository noticeRepository, UserRepository userRepository) {
        this.noticeRepository = noticeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addCrsComment(HttpServletRequest request, Notice notice) {
        UserAuxiliary auxiliary= RequestUtils.getUserAuxiliaryFromReq(request);
        if (auxiliary==null) return;

        notice.setCreatedAt(new Date());
        notice.setType(1);
        notice.setFromUserId(auxiliary.getId());
        notice.setFromUserName(auxiliary.getName());

        noticeRepository.save(notice);
        userRepository.noticeNumPlusOne(notice.getOwnerId());
    }

    @Override
    public void addExeComment(HttpServletRequest request,Notice notice) {

    }
}
