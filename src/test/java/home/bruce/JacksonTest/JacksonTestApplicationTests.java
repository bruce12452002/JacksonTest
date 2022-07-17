package home.bruce.JacksonTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import home.bruce.JacksonTest.entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

// @SpringBootTest
public class JacksonTestApplicationTests {
    private static final Animal animal = new Animal();

    private static final Zoo zoo = new Zoo();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        animal.setId(111);
        animal.setName("monkey");
        animal.setBirthday(new Date(90, Calendar.MAY, 6));
        animal.setDeadDay(LocalDateTime.of(2010, 7, 8, 13, 20));
        animal.setNickname(null);
        animal.setAge(20);
        animal.setMoney(100.56);

        zoo.setAddress("花巷草弄12號");
        zoo.setOpenTime(LocalTime.of(9, 0));
        zoo.setCloseTime(LocalTime.of(17, 0));
        animal.setZoo(zoo);

        /*
         * SNAKE_CASE 使用_分開
         * KEBAB_CASE 使用-分開
         * LOWER_DOT_CASE 使用.分開
         * 會影響轉成 json 和轉成物件
         */
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
        objectMapper.registerModule(new JavaTimeModule()); // 支援 java8 的時間

        /*
         * SerializationFeature 轉 json
         * DeserializationFeature 轉物件
         * 預設有不認識的屬性，轉成物件時會報錯，可以改成 false
         */
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    @Test
    public void testBasic() throws JsonProcessingException {
        String s = objectMapper.writeValueAsString(animal);
        System.out.println(s);

        Animal a = objectMapper.readValue(s, Animal.class);
        System.out.println(a.getId());
        System.out.println(a.getName());
        System.out.println(a.getBirthday());
        System.out.println(a.getDeadDay());
        System.out.println(a.getNickname());
        System.out.println(a.getAge());
        System.out.println(a.getMoney());

        Zoo zoo = a.getZoo();
        Optional.ofNullable(zoo).ifPresent(c -> {
            System.out.println(c.getAddress());
            System.out.println(c.getOpenTime());
            System.out.println(c.getCloseTime());
        });
    }

    @Test
    public void testAlias() throws JsonProcessingException {
//        String s1 = "{\"id\":111,\"name\":\"monkey\",\"birthday\":\"1990-06-05 16:00:00\",\"deadDay\":\"2010-07-08 13:20:00\",\"age\":20,\"money\":100.56}";
        String s2 = "{\"id\":111,\"name\":\"monkey\",\"birthday\":\"1990-06-05 16:00:00\",\"deadDay\":\"2010-07-08 13:20:00\",\"aaa\":20,\"money\":100.56}";
        String s3 = "{\"id\":111,\"name\":\"monkey\",\"birthday\":\"1990-06-05 16:00:00\",\"deadDay\":\"2010-07-08 13:20:00\",\"bbb\":20,\"money\":100.56}";
//        System.out.println(objectMapper.readValue(s1, Animal.class).getAge());
        System.out.println(objectMapper.readValue(s2, Animal.class).getAge());
        System.out.println(objectMapper.readValue(s3, Animal.class).getAge());
    }

    @Test
    public void testDifferentKey() throws JsonProcessingException {
        // 少了屬性不會報錯
//        String s1 = "{\"id\":111,\"name\":\"monkey\",\"birthday\":\"1990-06-05 16:00:00\",\"deadDay\":\"2010-07-08 13:20:00\",\"age\":20,\"money\":100.56}";
//        System.out.println(objectMapper.readValue(s1, Animal.class));

        // 多了屬性預設會報錯
        String s2 = "{\"id\":111,\"name\":\"monkey\",\"birthday\":\"1990-06-05 16:00:00\",\"deadDay\":\"2010-07-08 13:20:00\",\"age\":20,\"money\":100.56,\"xxx\":222}";
        System.out.println(objectMapper.readValue(s2, Animal.class));
    }

    @Test
    public void testJsonGetterSetter() throws JsonProcessingException {
        String z = objectMapper.writeValueAsString(zoo);
        System.out.println(z);

        Zoo zoo = objectMapper.readValue(z, Zoo.class);
        System.out.println(zoo.getAddress());
    }

    @Test
    public void testJsonPropertyOrder() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(zoo);
        System.out.println(json);
    }

    @Test
    public void testJsonValue() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(zoo);
        System.out.println(json);
    }

    @Test
    public void testJsonRawValue() throws JsonProcessingException {
        zoo.setAddress("{\"city\": \"taipei\"}");
        String json = objectMapper.writeValueAsString(zoo);
        System.out.println(json);

        /*
         * @JsonRawValue 將值本來就是 JSON 時，不做處理
         * 但轉後的值無法再轉成物件了，不用 @JsonRawValue 是可以轉的
         */
//        Zoo zoo = objectMapper.readValue(json, Zoo.class);
//        System.out.println(zoo.getAddress());
    }

    @Test
    public void testNoSetter() throws JsonProcessingException {
        People people = new People(1, "mary");
        String json = objectMapper.writeValueAsString(people);
        System.out.println(json);

        People p = objectMapper.readValue(json, People.class);
        System.out.println(p.getId());
        System.out.println(p.getName());
    }

    @Test
    public void testJsonAnyXxx() throws JsonProcessingException {
        MyMap m = objectMapper
                .readValue("{\"key\":\"value\", \"k1\":\"v1\"}", MyMap.class);
        System.out.println(m.getMap("key"));
        System.out.println(m.getMap("k1"));
    }

    @Test
    public void testJsonSerializer() throws JsonProcessingException {
        People people = new People(1, "mary");
        people.setGood(true);
        String json = objectMapper.writeValueAsString(people);
        System.out.println(json);

        People p = objectMapper.readValue(json, People.class);
        System.out.println(p.getId());
        System.out.println(p.getName());
        System.out.println(p.isGood());
    }

    @Test
    public void testJsonAutoDetect() throws JsonProcessingException {
        NoSetterGetter noSetterGetter = new NoSetterGetter();
        String json = objectMapper.writeValueAsString(noSetterGetter);
        System.out.println(json);
    }
}
