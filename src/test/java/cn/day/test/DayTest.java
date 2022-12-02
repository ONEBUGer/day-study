package cn.day.test;

import cn.day.test.dto.User;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
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


        String x = "-2323.44";
        double v = Double.parseDouble(x);
        System.out.println(v);
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
