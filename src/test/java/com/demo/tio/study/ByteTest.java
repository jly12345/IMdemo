package com.demo.tio.study;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

/**
 * @Auther: lingyun.jiang
 * @Date: 2019/10/25 14:33
 * @Description:
 */
public class ByteTest {
    @Test
    public void test01() throws UnsupportedEncodingException {
//        byte[] bytes = {0, 3, 26, 64, 1, 0, 0, 0, 9, 117, 110, 100, 101, 102, 105, 110, 101, 100};
        byte[] byte1= {3, 26, 64};
        String s1 = new String(byte1);
        System.out.println(s1);


    }
}
