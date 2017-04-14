package me.jcala.jmooc.entity;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ListTest {

    @Test
    public void testListToString(){
        List<Integer> integers= Arrays.asList(new Integer[]{1,34,45,56});
        System.out.print(integers);
    }

}
