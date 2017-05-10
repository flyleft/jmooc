package me.jcala.jmooc.entity.auxiliary;


import lombok.Data;

import java.io.Serializable;

@Data
public class UpFile implements Serializable{

    private static final long serialVersionUID = 2649804251374236606L;

    private String name;//文件名称
    private String url;//文件访问地址

}
