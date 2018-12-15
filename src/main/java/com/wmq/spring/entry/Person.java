package com.wmq.spring.entry;

import org.springframework.beans.factory.annotation.Value;

public class Person {
    /**
     * @Value
     * 可以写直接的数值
     * 也可以使用SPEL表达式#{ }
     * ${}去除配置文件中的值
     */
    @Value("${ZHANSAN.name}")
    private String name;
    @Value("10")
    private Integer age;
    //@Value("${ZHANSAN.SEX}")
    private Boolean sex;

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
