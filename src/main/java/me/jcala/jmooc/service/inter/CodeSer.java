package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.entity.Solution;
import me.jcala.jmooc.entity.auxiliary.CodeForm;
import me.jcala.jmooc.entity.auxiliary.CodeResult;

public interface CodeSer {

     Solution submitCode(String userId, CodeForm codeForm);

     CodeResult getResult(long solution_id);

}
