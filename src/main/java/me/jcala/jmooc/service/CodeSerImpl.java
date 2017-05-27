package me.jcala.jmooc.service;

import me.jcala.jmooc.conf.WebMvcConfig;
import me.jcala.jmooc.entity.oj.Solution;
import me.jcala.jmooc.entity.oj.SolutionSource;
import me.jcala.jmooc.entity.auxiliary.CodeForm;
import me.jcala.jmooc.repository.SolutionRepository;
import me.jcala.jmooc.repository.SolutionSourceRepository;
import me.jcala.jmooc.service.inter.CodeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class CodeSerImpl implements CodeSer{

    private SolutionRepository solutionRepository;

    private SolutionSourceRepository solutionSourceRepository;

    @Autowired
    public CodeSerImpl(SolutionRepository solutionRepository, SolutionSourceRepository solutionSourceRepository) {
        this.solutionRepository = solutionRepository;
        this.solutionSourceRepository = solutionSourceRepository;
    }

    @Override
    public void submitCode(String userId, CodeForm codeForm) {

        Solution solution=new Solution();
        solution.setUser_id(userId);
        solution.setLanguage(codeForm.getLanguage());
        solution.setSubmit_date(new Date());
        Solution back=solutionRepository.save(solution);

        SolutionSource source=new SolutionSource(back.getSolution_id(),codeForm.getSource());
        solutionSourceRepository.save(source);

        String[] cmd={WebMvcConfig.OJ_PATH+"Client.exe",back.getSolution_id()+"",back.getLanguage()+"",WebMvcConfig.OJ_INI_PATH};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
