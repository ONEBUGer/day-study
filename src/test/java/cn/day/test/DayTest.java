package cn.day.test;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author ZhengChangBing
 * @Date 2022/11/4 10:06
 * @Description
 */
public class DayTest {

    @Test
    public void test(){
//        List<String> stringList = Arrays.asList("test1");
//        stringList.add("test2");
//        System.out.println(stringList);

        List<String> list = Collections.singletonList("test3");
        list.add("test4");
        System.out.println(list);

    }
}
