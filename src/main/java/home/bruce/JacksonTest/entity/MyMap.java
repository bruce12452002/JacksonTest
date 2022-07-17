package home.bruce.JacksonTest.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class MyMap {
    private Map<String, String> map = new HashMap<>(4);

    @JsonAnySetter
    public void setMap(String k, String v) {
        this.map.put(k, v);
    }

    @JsonAnyGetter
    public String getMap(String k) {
        return this.map.get(k);
    }
}
