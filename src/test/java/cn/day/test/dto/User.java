package cn.day.test.dto;

import lombok.Data;

/**
 * @author ZhengChangBing
 * @Date 2022/10/19 9:53
 * @Description 用户实体类
 */
@Data
public class User {

    private Integer id;

    private String name;

    public User(String name) {
        this.name = name;
    }

    public User(){
        this.id = id;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
