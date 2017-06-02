package me.jcala.jmooc.service;

import me.jcala.jmooc.conf.WebMvcConfig;
import me.jcala.jmooc.entity.Solution;
import me.jcala.jmooc.entity.auxiliary.CodeForm;
import me.jcala.jmooc.entity.auxiliary.CodeResult;
import me.jcala.jmooc.repository.SolutionRepository;
import me.jcala.jmooc.service.inter.CodeSer;
import me.jcala.jmooc.utils.JmoocBeanUtils;
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
    public Solution submitCode(String userId, CodeForm codeForm) {
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
        return back;
    }

    @Override
    public CodeResult getResult(long solution_id) {
        CodeResult result=new CodeResult();
        Solution solution=solutionRepository.findOne(solution_id);
        if (solution==null){
            result.setId(7);
            result.setInfo("系统错误");
            result.setData("该id不存在: "+solution_id);
            return result;
        }
        if (solution.getVerdict()==0){
            result.setInfo("正在运算队列");
            result.setData("请稍等...");
        }
        if (solution.getVerdict()==2){
            result.setInfo("编译出现异常");
            result.setData(solution.getCompile_err());
        }
        if (solution.getVerdict()==4){
            result.setInfo("代码正确，结果如下:");
            result.setData(solution.getResult());
        }
        if (solution.getVerdict()==5){
            result.setInfo("运行异常");
            result.setData("请检查代码...");
        }
        if (solution.getVerdict()==6){
           result.setInfo("超时");
           result.setData("运行超时，请检查代码...");
        }
        result.setId(solution.getVerdict());
        return result;
    }
}
