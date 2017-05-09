package me.jcala.jmooc.entity.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * 添加章节表单映射类
 */
@Data
public class ChpForm {

    @NotBlank(message = "章节名称")
    private String name;

    private int crs_id;//表示课程id

    @Min(0)
    private int pos;//表示第几章

}
