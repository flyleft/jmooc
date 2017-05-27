package me.jcala.jmooc.service;

import me.jcala.jmooc.conf.WebMvcConfig;
import me.jcala.jmooc.entity.Solution;
import me.jcala.jmooc.entity.auxiliary.CodeForm;
import me.jcala.jmooc.repository.SolutionRepository;
import me.jcala.jmooc.service.inter.CodeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class CodeSerImpl implements CodeSer{

    private SolutionRepository solutionRepository;

    @Autowired
    public CodeSerImpl(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    @Override
    public void submitCode(String userId, CodeForm codeForm) {

        Solution solution=new Solution();
        solution.setUser_id(userId);
        solution.setLanguage(codeForm.getLanguage());
        solution.setSubmit_date(new Date());
        solution.setSource(codeForm.getSource());
        Solution back=solutionRepository.save(solution);

        String[] cmd={WebMvcConfig.OJ_PATH+"Client.exe",back.getSolution_id()+"",back.getLanguage()+"",WebMvcConfig.OJ_INI_PATH};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
