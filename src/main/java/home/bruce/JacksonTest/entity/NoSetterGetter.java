package home.bruce.JacksonTest.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * 沒有 setter 和 getter 時，預設只會抓 public 屬性
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NoSetterGetter {
    private int i1 = 11;
    int i2 = 22;
    protected int i3 = 33;
    public int i4 = 44;
}
