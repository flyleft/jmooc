package me.jcala.jmooc.entity.auxiliary;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class ExeForm {

    @NotEmpty
   private String title;
    @NotEmpty
   private String a;
    @NotEmpty
   private String b;
   private String c;
   private String d;
   private char answer;
   private String analysis;
    @NotEmpty
   private String type;
    @Min(0)
    @Max(5)
   private int difficulty;

    public ExeForm() {
    }
}
