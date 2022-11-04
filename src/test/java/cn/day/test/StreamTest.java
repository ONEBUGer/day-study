package cn.day.test;

import cn.day.test.dto.User;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.DateUtil;
import org.json.JSONString;
import org.json.JSONStringer;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ZhengChangBing
 * @Date 2022/10/18 18:06
 * @Description
 */
@Slf4j
public class StreamTest {

    @Test
    public void streamTest1() {
//        String str = "this is a stream test";
//        String[] strings = str.split(" ");
//
//        List<String> collect = Arrays.stream(strings).filter(s -> s.length() > 2).sorted((i, j) -> i.length() - j.length()).limit(3).collect(Collectors.toList());
//        System.out.println(collect);

//        List<Integer> stringList = Arrays.asList(100, 101, 102, 103);
//        List<User> userList = stringList.stream().map(id -> {
//            User user = new User();
//            user.setId(id);
//            return user;
//        }).collect(Collectors.toList());
//
//        System.out.println(userList);

//        List<String> stringList = Arrays.asList("hello flatMap", "java stream test");
//        List<String> strings = stringList.stream()
//                .flatMap(string -> Arrays.stream(string.split(" ")))
//                .collect(Collectors.toList());
//
//        System.out.println(strings);

//        List<String> stringList = Arrays.asList("hello flatMap", "java stream test");
//        System.out.println("peek before");
//        stringList.stream().peek(string -> System.out.println(string));
//        System.out.println("peek after");
//        System.out.println("-----------");
//        System.out.println("foreach before");
//        stringList.stream().forEach(string -> System.out.println(string));
//        System.out.println("foreach after");
//        System.out.println("-----------");
//
//        System.out.println("peek plus before");
//        stringList.stream().peek(string -> System.out.println(string)).collect(Collectors.toList());
//        System.out.println("peek plus after");
//        System.out.println("-----------");

//        List<String> stringList = Arrays.asList("98", "99", "100", "101", "102", "102");
//        List<User> userList = stringList.stream().filter(string -> string.length() > 2).map(Integer::valueOf).distinct().limit(2).sorted(Comparator.comparingInt(string -> string)).map(string -> new User(string.toString())).collect(Collectors.toList());
//        System.out.println(userList);

//        List<String> stringList = Arrays.asList("98", "99", "100", "101", "102", "102");
//        System.out.println(stringList.stream().filter(string -> string.length() > 2).count());
//        System.out.println(stringList.stream().filter(string -> string.length() > 2).anyMatch("100"::equals));
//        stringList.stream().filter(string -> string.length() > 3).findFirst().ifPresent(s -> System.out.println(s));


//        List<User> userList = Arrays.asList(new User(99), new User(100), new User(101), new User(101));
//        System.out.println(userList.stream().filter(user -> user.getId() > 99).collect(Collectors.toList()));
//        System.out.println(userList.stream().filter(user -> user.getId() > 99).collect(Collectors.toSet()));
//        System.out.println(userList.stream().filter(user -> user.getId() > 99).distinct().collect(Collectors.toMap(User::getId, user -> user)));

//        List<String> stringList = Arrays.asList("98", "99", "100", "101", "102", "102");
//        System.out.println(stringList.stream().collect(Collectors.joining(",")));
//
//        System.out.println(String.join(",", stringList));

//        List<Integer> stringList = Arrays.asList(100, 101, 102, 103);
//        System.out.println(stringList.stream().collect(Collectors.averagingInt(value -> value)));
//        System.out.println(stringList.stream().collect(Collectors.summarizingInt(value -> value)));

//        List<User> userList = Arrays.asList(new User(99), new User(100), new User(101), new User(101));
//        System.out.println(userList.stream().filter(user -> 101 != user.getId()).collect(Collectors.groupingBy(User::getId)));


//        HashMap<Integer, List<User>> map = new HashMap<>();
//        for (User user : collect) {
//            List<User> users = map.computeIfAbsent(user.getId(), k -> new ArrayList<>());
//            users.add(user);
//        }
//        System.out.println(map);

//        Integer i = null;
//        if (ObjectUtil.isNull(i)){
//            System.out.println("111");
//        }
//        if (ObjectUtil.isEmpty(i)){
//            System.out.println("222");
//        }
//
//        User user = new User();
//        if (ObjectUtil.isEmpty(user)){
//            System.out.println("333");
//        }
//
//        List<User> users = new ArrayList<>();
//        users.add(user);
//        if (CollectionUtils.isEmpty(users)){
//            System.out.println("444");
//        }
//
//        Optional<User> user1 = Optional.ofNullable(user);
//        boolean present = user1.isPresent();
//        System.out.println(present);

//        String str = "-1";
//        try {
//            System.out.println(Integer.valueOf(str));
//        }catch (Exception e){
//            log.info("string转integer类型失败信息：{}", e);
//        }

//        File file = new File("D:\\keytop\\FreeLots", "new.txt");//在D:\pro11\src\te\Test2文件目录下建立new.txt文件
//        if (!file.exists()) {
//            try {
//                FileInputStream fileInputStream = new FileInputStream(file);
//                file.createNewFile();//创立文件
//                System.out.println("在当前目录下创建新文件：" + file.getName());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }

        String directoriesUrl = "D:\\keytop\\FreeLots";
        Path path = Paths.get(directoriesUrl);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                log.info("创建文件夹异常：{}", e);
            }
        }
        String prefixFileName = "GBTB-";
        String suffixFileName = ".txt";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
        String format = simpleDateFormat.format(date);

        String fileName = directoriesUrl + "\\" + prefixFileName + format + suffixFileName;
        File file = new File(fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String body = "区域1 区域2 区域3 \r\n" +
                    "100 200 300";
            fileOutputStream.write(body.getBytes("UTF-8"));
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            log.info("生成txt文档失败：{}", e);
        } catch (UnsupportedEncodingException e) {
            log.info("文件格式转换异常：{}", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("区域1");
        arrayList.add("区域2");
        arrayList.add("区域3");
        String join = Joiner.on(" ").join(arrayList);
        System.out.println(join);
    }

    @Test
    public void  deleteMethod() throws ParseException, IOException {
        File file = new File("D:\\keytop\\FreeLots");
        File[] deleteFiles = file.listFiles();
        for (File deleteFile : deleteFiles) {
            Path path = Paths.get(deleteFile.getAbsolutePath());
            BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            long millis = basicFileAttributes.creationTime().toMillis();
            if (new Date().getTime() - millis > 1*1000){
                deleteFile.deleteOnExit();
            }
        }
    }

    public boolean createTxtFile(JSONArray jsonArray, String path, String filename) {
        // 标记文件生成是否成功
        boolean flag = true;
        try {
            // 含文件名的全路径
//            String[] strings = {path, filename, ".txt"};
//            String fullPath = StrUtils.strSplice(strings);
            String fullPath = "";
            File file = new File(fullPath);
            File folder = new File(path);
            if (!folder.exists() && !folder.isDirectory()) {
                // 如果不存在,创建文件夹
                folder.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            // 格式化浮点数据
            NumberFormat formatter = NumberFormat.getNumberInstance();
            // 设置最大小数位为10
            formatter.setMaximumFractionDigits(10);
            // 格式化日期数据
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            // 遍历输出每行
            PrintWriter pfp = new PrintWriter(new FileOutputStream(file, true));
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.isEmpty()) {
                    break;
                }
                StringBuilder thisLine = new StringBuilder("");
                for (Iterator<String> iterator = jsonObject.keySet().iterator(); iterator.hasNext(); ) {
                    // 当前字段
                    String key = iterator.next();
                    Object obj = jsonObject.get(key);
                    // 格式化数据
                    String field = "";
                    if (null != obj) {
                        if (obj.getClass() == String.class) {
                            // 如果是字符串
                            field = (String) obj;
                        } else if (obj.getClass() == Double.class || obj.getClass() == Float.class) {
                            // 格式化浮点数,使浮点数不以科学计数法输出
                            field = formatter.format(obj);
                        } else if (obj.getClass() == Integer.class || obj.getClass() == Long.class
                                || obj.getClass() == Short.class || obj.getClass() == Byte.class) { // 如果是整形
                            field += obj;
                        } else if (obj.getClass() == Date.class) {
                            // 如果是日期类型
                            field = sdf.format(obj);
                        }
                    } else {
                        // null时给一个空格占位
                        field = " ";
                    }
                    // 拼接所有字段为一行数据，用tab键分隔
                    // 不是最后一个元素
                    if (iterator.hasNext()) {
                        thisLine.append(field).append("\t");
                    } else {
                        // 是最后一个元素
                        thisLine.append(field);
                    }
                }
                pfp.print(thisLine.toString() + "\n");
            }
            pfp.close();
        } catch (Exception e) {
            flag = false;
            log.error("生成txt文件失败", e);
        }
        return flag;
    }
}
