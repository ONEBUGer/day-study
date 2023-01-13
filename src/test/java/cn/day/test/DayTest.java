package cn.day.test;

import cn.day.test.dto.User;
import cn.day.test.elasticsearch.dto.TestDTO;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author ZhengChangBing
 * @Date 2022/11/4 10:06
 * @Description
 */
public class DayTest {

    @Test
    public void test() throws Exception {
//        List<String> stringList = Arrays.asList("test1");
//        stringList.add("test2");
//        System.out.println(stringList);

//        List<String> list = Collections.singletonList("test3");
//        list.add("test4");
//        System.out.println(list);

//        System.out.println(UUID.randomUUID().toString().replace("-", "").length());

//        ApplicationHome applicationHome = new ApplicationHome();
//        String dir = applicationHome.getDir().getParent();
//        System.out.println(dir);

//        String str = "[[733,-67123], [4601,-70369], [6137,-68538], [2269,-65292]]";
//        List<PointCoordinate> pointCoordinates = JSONObject.parseArray(str, PointCoordinate.class);
//        System.out.println(pointCoordinates);

//        String str = "733,-67123;4601,-70369;6137,-68538;2269,-65292";
//        String[] split = str.split(";");
//        for (String s : split) {
//            String[] split1 = s.split(",");
//            PointCoordinate pointCoordinate = new PointCoordinate();
//            pointCoordinate.setX(split1[0]);
//            pointCoordinate.setY(split1[1]);
//
//        }

//        HashMap<String, Object> map = new HashMap<>();
//        map.put("test1", 1);
//        map.put("test2", 2);
//        System.out.println(map.values());

//        List<Integer> list = Arrays.asList(1, 2, 3, 4);
//        List<Integer> list1 = Arrays.asList(1, 2);
//        List<Integer> collect = list.stream().filter(item -> !list1.contains(item)).collect(Collectors.toList());
//        System.out.println(collect);

//        ArrayList<Object> list = new ArrayList<>();
//        for (Object o : list) {
//            System.out.println("111");
//        }

//        ArrayList<User> users = new ArrayList<>();
//        User user = new User();
//        user.setId(1);
//        user.setName("1");
//        User user1 = new User();
//        user1.setId(2);
//        user1.setName("2");
//        User user3 = new User();
//        user3.setId(2);
//        user3.setName("new2");
//        users.add(user);
//        users.add(user1);
//        users.add(user3);
//
//        List<Integer> collect = users.stream().map(user2 -> user2.getId()).distinct().collect(Collectors.toList());
//        System.out.println(collect);

//        String urlStr = "https://www.baidu.com";

//        String urlStr = "https://www.baidu.com";
//        String urlStr = "https://find-car-admin-test.keytop.cn";
//        testUrlWithTimeOut(urlStr, 1000);

//        List<Integer> list = Arrays.asList(1, 2, 3, 4);
//        System.out.println(list.get(0));

//        AsyncLoadingCache<Object, String> async = Caffeine.newBuilder().maximumSize(1000).buildAsync(key -> slowMethod(key.toString()));
//        CompletableFuture<String> test = async.get("test");
//        System.out.println(test.get());

//        Cache<Object, Object> build = Caffeine.newBuilder().build();


//        String x = "-2323.44";
//        double v = Double.parseDouble(x);
//        System.out.println(v);

//        String fileName = "D:\\fileTest";
//        //读取方式1
//        FileReader fileReader = new FileReader(fileName);
//        Scanner scanner = new Scanner(fileReader);
        //读取方式2
//        Path path = Paths.get(fileName);
//        Stream<String> lines = Files.lines(path);
        //读取方式3

//        Double test = 3.2344324;
//        System.out.println(test / 1000);

//        HashSet<Integer> hashSet = new HashSet<>();
//        hashSet.add(1);
//        hashSet.add(2);
//
//        ArrayList<Integer> list = new ArrayList<>(hashSet);
//        System.out.println(list);
//
//        Base64.Encoder encoder = Base64.getEncoder();
//
//        HttpRequest head = HttpRequest.head("http://www.baidu.com");
//        head.basicAuth("id", "password");

//        String str = "true";
//        if (str.equals(true)){
//            System.out.println("111");
//        }

//        TestDTO testDTO = new TestDTO();
//        testDTO.setCheck(true);
//
//        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(testDTO));
//        String check = jsonObject.getString("check");
//        System.out.println(check);
//
//        if (check.equals("true")){
//            System.out.println("111");
//        }

//        TestDTO testDTO = new TestDTO();
//        testDTO.setCheck(true);
//        testDTO.setGroupId(1);
//        TestDTO dto = new TestDTO();
//        dto.setGroupId(1);
//        dto.setCheck(false);
//        TestDTO test = new TestDTO();
//        test.setCheck(false);
//        test.setGroupId(2);
//
//        ArrayList<TestDTO> testDTOS = new ArrayList<>();
//        testDTOS.add(test);
//        testDTOS.add(testDTO);
//        testDTOS.add(dto);
//
//        HashMap<Integer, List<TestDTO>> map = new HashMap<>();
//        for (TestDTO testDTO1 : testDTOS) {
//            map.computeIfAbsent(testDTO1.getGroupId(), k -> new ArrayList<>()).add(testDTO1);
//        }
//        System.out.println(map);

//        //密码校验是否季度更新提示
//        Boolean checkPasswordUpdate = false;
//        Date date = new Date();
//        String str = "2023-01-10 16:09:43";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date parse = simpleDateFormat.parse(str);
//        if ((System.currentTimeMillis() - parse.getTime()) > 90 * 24 * 60 * 60 * 1000L){
//            checkPasswordUpdate = true;
//        }
//        long i = 1839838;
//
//        System.out.println(checkPasswordUpdate);

//        String str = "null";
//        String[] split = str.split(",");
//        System.out.println(split.length);

//        List<Integer> list = Arrays.asList(1, 2, 3);
//        List<Integer> list1 = list.subList(0, 2);
//        String join = Joiner.on(",").join(list1);
//        System.out.println(join);

//
        String ip = "192.168.25.31";
        String[] split = ip.split("\\.");
        System.out.println(Arrays.asList(split));
    }

    public static String slowMethod(String key) throws InterruptedException {
        Thread.sleep(1000);
        return key + ".result";
    }

    public static void testUrlWithTimeOut(String urlString, int timeOutMillSeconds) {
        long lo = System.currentTimeMillis();
        URL url;
        try {
            url = new URL(urlString);
            URLConnection co = url.openConnection();
            co.setConnectTimeout(timeOutMillSeconds);
            co.connect();
            System.out.println("连接可用");
        } catch (Exception e1) {
            System.out.println("连接打不开!");
            url = null;
        }
        System.out.println(System.currentTimeMillis() - lo);
    }

    private static void streamTest(){
        List<String> stringList = Arrays.asList("1", "2", "3");

        Set<String> collect1 = Stream.of(stringList).flatMap(Collection::stream).collect(Collectors.toSet());
        System.out.println(collect1);


    }


}
