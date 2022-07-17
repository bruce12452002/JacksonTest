package home.bruce.JacksonTest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import home.bruce.JacksonTest.ser.MyDeSerializer;
import home.bruce.JacksonTest.ser.MySerializer;

public class People {
    private int id;
    private String name;

    /**
     * 如果有某個值想轉換某個值，如布林值轉數字，可以使用這兩個註解
     */
    @JsonSerialize(using = MySerializer.class)
    @JsonDeserialize(using = MyDeSerializer.class)
    private boolean good;

    public People(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }
}
