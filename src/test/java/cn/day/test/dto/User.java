package cn.day.test.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ZhengChangBing
 * @Date 2022/10/19 9:53
 * @Description 用户实体类
 */
@Data
@Accessors(chain = true)
public class User {

    private Integer id;

    private String name;

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    private transient String hobby;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
