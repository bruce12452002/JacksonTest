package home.bruce.JacksonTest.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Date;

// @JsonIgnoreProperties({"id", "name"}) // @JsonIgnore 比較麻煩，可以一次輸入不想轉成json或轉成物件的屬性
public class Animal {
    // @JsonIgnore // 轉 json 和轉物件都不會使用
    private int id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadDay;

    @JsonInclude(JsonInclude.Include.NON_NULL) // 不要null，轉 json 時，如果 value 是 null 就不輸出
    private String nickname;

    @JsonAlias({"aaa", "bbb"}) // 轉物件時，aaa、bbb、age 都能轉成 age 物件
    private int age;
    private double money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getDeadDay() {
        return deadDay;
    }

    public void setDeadDay(LocalDateTime deadDay) {
        this.deadDay = deadDay;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
