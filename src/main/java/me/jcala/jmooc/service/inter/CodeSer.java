package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.entity.auxiliary.CodeForm;

public interface CodeSer {

     void submitCode(String userId, CodeForm codeForm);

}
