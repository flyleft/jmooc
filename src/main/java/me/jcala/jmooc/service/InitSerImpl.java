package me.jcala.jmooc.service;

import me.jcala.jmooc.entity.User;
import me.jcala.jmooc.repository.UserRepository;
import me.jcala.jmooc.service.inter.InitSer;
import me.jcala.jmooc.utils.EncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class InitSerImpl implements InitSer{

    private static final Logger logger= LoggerFactory.getLogger(InitSer.class);

    private UserRepository userRepository;

    @Autowired
    public InitSerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    @Transactional
    @Override
    public void executeInit() {
       logger.info("----------开始初始化数据-----------");
       initUserData();
    }


    private void initUserData(){

        User jcala=userRepository.findUserByName("jcala");
        if (jcala==null){
            String pass= EncryptUtils.EncoderByMd5("jcala");
            userRepository.save(new User("jcala",pass,1));
        }

        User tea=userRepository.findUserByName("tea");
        if (tea==null){
            String pass= EncryptUtils.EncoderByMd5("tea");
            userRepository.save(new User("tea",pass,2));
        }
    }


    /*
        public Problem(String title, String description, String input, String output,
                   int spj, String hint, String source, int time_limit,
                   int memory_limit, char defunct, int accepted, int solved,
                   int submit, int submit_user, String author, Date create_date,
                   float difficulty, float ratio, int contest_id,
                   String oj_name, int oj_pid, int isvirtual) {
    INSERT INTO `problem` VALUES ('1000', 'A+B Problem', 'Calculate A + B，and give me the answer! ', 'Input two integers A and B.(Watch the Sample Input) ', 'For each case, output A + B in one line..(Watch the Sample Output)', '1 2 ', '3 ', '0', 'Press the<span style=\"font-size:16px;\"> <strong>Submit</strong></span> to submit your code!<br />\r\n<span style=\"color:#ff0000;\">Q:</span> Where is the input and the output?<br />\r\n<span style=\"color:#ff0000;\">A:</span> Your program shall read input from stdin(&#39;<strong>Standard Input</strong>&#39;) and write output to stdout(&#39;<strong>Standard Output</strong>&#39;).<br />\r\nFor example, you can use &#39;scanf&#39; in C or &#39;cin&#39; in C++ to read from stdin, and use &#39;printf&#39; in C or &#39;cout&#39; in C++ to write to stdout. User programs are not allowed to open and read from/write to files, you will get a &quot;Restricted Function&quot; if you try to do so.<br />\r\nHere is a sample solution for problem 1000 using C:<br />\r\n<pre class=\"brush:java;\">\r\n#include &lt;stdio.h&gt;\r\nint main(void)\r\n{\r\n    int a,b;\r\n    while(scanf(&quot;%d%d&quot;, &amp;a,&amp;b) != EOF)\r\n        printf(&quot;%d\\n&quot;,a+b);\r\n    return 0;\r\n}</pre>\r\nHere is a sample solution for problem 1000 using C++:\r\n<pre class=\"brush:cpp;\">\r\n#include &lt;iostream&gt;\r\nusing namespace std;\r\nint main(void)\r\n{\r\n    int a,b;\r\n    while(cin &gt;&gt; a&gt;&gt;b)\r\n        cout &lt;&lt; a+b&lt;&lt;endl;\r\n    return 0;\r\n}</pre>\r\nHere is a sample solution for problem 1000 using Java:\r\n<pre class=\"brush:java;\">\r\nimport java.util.Scanner;\r\npublic class Main {\r\n public static void main(String[] args) {\r\n  Scanner in = new Scanner(System.in);\r\n  while (in.hasNextInt()) {\r\n   int a = in.nextInt();\r\n   int b = in.nextInt();\r\n   System.out.println(a + b);\r\n  }\r\n }\r\n}</pre>\r\n', ' ', '1000', '65535', 'N', '0', '0', '0', '0', 'ACSolo', null, '0', '0', '', '0', 'GUET', '0', '0');
     */

}
