package org.sjm.entry;

import java.io.Serializable;

/**
 * Component:
 * Description:
 * Date: 16/9/28
 *
 * @author yue.zhang
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    private String name;

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
