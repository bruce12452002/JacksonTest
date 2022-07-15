package home.bruce.JacksonTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import home.bruce.JacksonTest.entity.Animal;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

//@SpringBootTest
public class JacksonTestApplicationTests {
    private static final Animal animal = new Animal();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        animal.setId(111);
        animal.setName("monkey");
        animal.setBirthday(new Date(90, 5, 6));
        animal.setDeadDay(LocalDateTime.of(2010, 7, 8, 13, 20));
        animal.setNickname(null);
        animal.setAge(20);
        animal.setMoney(100.56);

        /**
         * SNAKE_CASE 使用_分開
         * KEBAB_CASE 使用-分開
         * LOWER_DOT_CASE 使用.分開
         * 會影響轉成 json 和轉成物件
         */
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
        objectMapper.registerModule(new JavaTimeModule()); // 支援 java8 的時間
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
    }

    @Test
    public void testAlias() throws JsonProcessingException {
        String s1 = "{\"id\":111,\"name\":\"monkey\",\"birthday\":\"1990-06-05 16:00:00\",\"deadDay\":\"2010-07-08 13:20:00\",\"age\":20,\"money\":100.56}";
        String s2 = "{\"id\":111,\"name\":\"monkey\",\"birthday\":\"1990-06-05 16:00:00\",\"deadDay\":\"2010-07-08 13:20:00\",\"aaa\":20,\"money\":100.56}";
        String s3 = "{\"id\":111,\"name\":\"monkey\",\"birthday\":\"1990-06-05 16:00:00\",\"deadDay\":\"2010-07-08 13:20:00\",\"bbb\":20,\"money\":100.56}";
        System.out.println(objectMapper.readValue(s1, Animal.class).getAge());
        System.out.println(objectMapper.readValue(s2, Animal.class).getAge());
        System.out.println(objectMapper.readValue(s3, Animal.class).getAge());
    }

    @Test
    public void test() throws JsonProcessingException {
        // 少或多了屬性並不會報錯
        String s1 = "{\"xxx\":111,\"name\":\"monkey\",\"birthday\":\"1990-06-05 16:00:00\",\"deadDay\":\"2010-07-08 13:20:00\",\"age\":20,\"money\":100.56}";
        System.out.println(objectMapper.readValue(s1, Animal.class));

        String s2 = "{\"id\":111,\"name\":\"monkey\",\"birthday\":\"1990-06-05 16:00:00\",\"deadDay\":\"2010-07-08 13:20:00\",\"age\":20,\"money\":100.56},\"xxx\":20";
        System.out.println(objectMapper.readValue(s2, Animal.class));
    }

}
