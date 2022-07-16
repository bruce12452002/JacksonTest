package home.bruce.JacksonTest.entity;

import com.fasterxml.jackson.annotation.*;

import java.time.LocalTime;

@JsonPropertyOrder(alphabetic = true) // JSON 輸出時按字母排序
@JsonIgnoreType // 如果其他類別有這個類別，會忽略，KV 都不會有，但如果是直接轉成 JSON 不會有影響
public class Zoo {
     @JsonRawValue
    // @JsonValue // 轉成 JSON 時，只顯示 value，一個類別只能使用一個，如要顯示多個，可將註解寫在 toString 方法上
    private String address;
    private LocalTime openTime;
    private LocalTime closeTime;

    /**
     * 輸出時改成對應的值
     * JsonGetter 和 JsonSetter 寫了其中一個，另一個會自動填上，所以可以只寫一個
     * 但兩個都寫其中一個會是 null
     * 效果和 JsonProperty 一樣，但 JsonGetter 和 JsonSetter 不能寫在屬性上
     */
    // @JsonGetter("addr")
    public String getAddress() {
        return address;
    }

    // @JsonSetter("addr")
    public void setAddress(String address) {
        this.address = address;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    // @JsonValue
    @Override
    public String toString() {
        return address + openTime;
    }
}
