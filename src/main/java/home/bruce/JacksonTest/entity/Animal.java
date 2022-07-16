package home.bruce.JacksonTest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Date;

// @JsonIgnoreProperties({"id", "name"}) // 可以一次忽略很多不想轉成 JSON 的屬性
public class Animal {
    // @JsonIgnore // 轉 JSON 和轉物件都不會使用
    private int id;

    private String name;

    private Zoo zoo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadDay;

    /**
     * 不要null，轉 json 時，如果 value 是 null 就不輸出，也可放在類別上
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nickname;

    // @JsonAlias({"aaa", "bbb"}) // 轉物件時，aaa、bbb、age 都能轉成 age 物件
    @JsonProperty("aaa") // 只有 aaa 能轉成物件
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

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }
}
