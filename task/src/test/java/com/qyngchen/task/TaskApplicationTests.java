package com.qyngchen.task;

import org.apache.commons.lang3.StringUtils;

public class TaskApplicationTests {


    public static void main(String[] args) {

        boolean notBlank = StringUtils.isNotBlank("sss");
        System.out.println(notBlank);
    }

}
