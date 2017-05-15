package me.jcala.jmooc.service;

import me.jcala.jmooc.entity.Notice;
import me.jcala.jmooc.entity.User;
import me.jcala.jmooc.entity.auxiliary.UserAuxiliary;
import me.jcala.jmooc.repository.NoticeRepository;
import me.jcala.jmooc.repository.UserRepository;
import me.jcala.jmooc.service.inter.NoticeSer;
import me.jcala.jmooc.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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
    public void addComment(HttpServletRequest request, Notice notice,int type) {
        UserAuxiliary auxiliary= RequestUtils.getUserAuxiliaryFromReq(request);
        if (auxiliary==null) return;

        notice.setCreatedAt(new Date());
        notice.setType(type);
        notice.setFromUserId(auxiliary.getId());
        notice.setFromUserName(auxiliary.getName());
        notice.setOwner(new User(notice.getFrontOwnerId()));

        noticeRepository.save(notice);
        userRepository.noticeNumPlusOne(notice.getFrontOwnerId());
    }

    @Override
    public List<Notice> getCrsNotice(long fromInfoId) {
        return noticeRepository.findByTypeAndFromInfoId(1,fromInfoId);
    }

    @Override
    public List<Notice> getExeNotice(long fromInfoId) {
        return noticeRepository.findByTypeAndFromInfoId(2,fromInfoId);
    }
}
