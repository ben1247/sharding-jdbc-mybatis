package org.sjm.entry;

import java.io.Serializable;

/**
 * Component:
 * Description:
 * Date: 16/9/28
 *
 * @author yue.zhang
 */
public class Student implements Serializable{

    private static final long serialVersionUID = 8920597824668331209L;

    private Integer id ;

    private Integer studentId;

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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", id=" + id +
                ", studentId=" + studentId +
                ", name='" + name + '\'' +
                '}';
    }
}
