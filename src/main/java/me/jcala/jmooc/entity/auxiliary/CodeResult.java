package me.jcala.jmooc.entity.auxiliary;

import lombok.Data;

import java.io.Serializable;

@Data
public class CodeResult implements Serializable{
    private static final long serialVersionUID = -8612040549262912515L;
    private int id;
    private String info;
    private String data;
}
