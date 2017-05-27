package me.jcala.jmooc.entity.auxiliary;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class CodeForm {
    @Min(1)
    @Max(5)
    private int language;

    @NotBlank(message = "提交的代码")
    @Length(min = 10,max = 65535)
    private String source;
}
