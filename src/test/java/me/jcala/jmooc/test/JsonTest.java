package me.jcala.jmooc.test;

import me.jcala.jmooc.entity.Exercise;
import me.jcala.jmooc.utils.JsonUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JsonTest {

    private String jsonStr;
    @Before
    public void outputJson(){
        //(String title, String content, int difficulty, String chooses, char answer, String analysis, String type
        Set<Exercise> exercises=new HashSet<>();

        Map<Character,String> mapOne=new HashMap<>();
        mapOne.put('A',"double d=Math.cos（42）");
        mapOne.put('B',"double d=Math.cosine（42）");
        mapOne.put('C',"double d=Math.cos（Math.toRadians（42））");
        mapOne.put('D',"double d=Math.cos（Math.toDegrees（42））");
        String chooseOne= JsonUtils.instance.toJson(mapOne);
        Exercise one=new Exercise(
           "下列哪个选项是正确计算42度（角度）的余弦值？",null,2,chooseOne,'C',"计算余弦值使用Math类的cos()方法,toRadians()是将角度转换为弧度,toDegrees()是将弧度转换为角度","java"
        );
        exercises.add(one);


        mapOne.clear();
        mapOne.put('A',"342");
        mapOne.put('B',"3423");
        mapOne.put('C',"34234");
        mapOne.put('D',"323");
        String chooseTwo= JsonUtils.instance.toJson(mapOne);
        Exercise two=new Exercise(
                "下面程序的输出是什么？","package algorithms.com.guan.javajicu;" +
                "ee",
                2,
                chooseTwo,
                'B',
                "1.执行foo(0)时，不满足try语句块中的if语句，所以不会抛出异常，执行finally语句\n" +
                "2.执行foo(1)时，满足try中的If语句，抛出异常，在catch中进行异常处理，虽然有return语句，但是finally中的内容必须执行，也就是说要先执行了finally才进行return操作，return后  output += “4”将不会再执行.",
                "java"
        );
        exercises.add(two);

        jsonStr=JsonUtils.instance.toJson(exercises);
        System.out.println(jsonStr);
    }

    //[{"id":0,"title":"下列哪个选项是正确计算42度（角度）的余弦值？","content":null,"difficulty":2,"chooses":"{\"A\":\"double d=Math.cos（42）\",\"B\":\"double d=Math.cosine（42）\",\"C\":\"double d=Math.cos（Math.toRadians（42））\",\"D\":\"double d=Math.cos（Math.toDegrees（42））\"}","answer":"C","score":0,"analysis":"计算余弦值使用Math类的cos()方法,toRadians()是将角度转换为弧度,toDegrees()是将弧度转换为角度","type":"java"},{"id":0,"title":"下面程序的输出是什么？","content":"package algorithms.com.guan.javajicu;ee","difficulty":2,"chooses":"{\"A\":\"342\",\"B\":\"3423\",\"C\":\"34234\",\"D\":\"323\"}","answer":"B","score":0,"analysis":"1.执行foo(0)时，不满足try语句块中的if语句，所以不会抛出异常，执行finally语句\n2.执行foo(1)时，满足try中的If语句，抛出异常，在catch中进行异常处理，虽然有return语句，但是finally中的内容必须执行，也就是说要先执行了finally才进行return操作，return后  output += “4”将不会再执行.","type":"java"}]
    @Test
    public void testJackSon(){
        Set<Exercise> exercises=JsonUtils.instance.readJsonToExeSet(jsonStr);
        for (Exercise exercise:exercises){
            System.out.println(exercise);
        }
    }



}
