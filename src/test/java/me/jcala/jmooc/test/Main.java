package me.jcala.jmooc.test;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String args[]){
        List<Teacher> teachers=Arrays.asList(new Teacher("语文老师"),
                new Teacher("数学老师"),
                new Teacher("化学老师"));
        final StringBuffer buffer=new StringBuffer();
        teachers.forEach(e -> buffer.append(e));
        System.out.print(buffer);
    }
   static class  Teacher extends Peo{
        private String type;
        public Teacher(String type) {
            super("teacher");
            this.type = type;
        }
        @Override
        public String toString() {
            return "Teacher{" + "type='" + type + ' ' + ", job='" + super.job + ' ' + '}';
        }
    }
   static  class Peo{
        private String job;
        public Peo(String job) {
            this.job = job;
        }
    }
}
