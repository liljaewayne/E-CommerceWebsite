package com.commerce.util;

import com.commerce.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonUtilTest {

    private User u1 = new User();
    private User u2 = new User();

    private String u1Json = "{\n" +
            "  \"id\" : 1,\n" +
            "  \"username\" : \"zhangsan\",\n" +
            "  \"password\" : null,\n" +
            "  \"email\" : \"zhangsan@qq.com\",\n" +
            "  \"phone\" : null,\n" +
            "  \"question\" : null,\n" +
            "  \"answer\" : null,\n" +
            "  \"role\" : null,\n" +
            "  \"createTime\" : null,\n" +
            "  \"updateTime\" : null\n" +
            "}";

    private String userListStr = "[ {\n" +
            "  \"id\" : 1,\n" +
            "  \"username\" : \"zhangsan\",\n" +
            "  \"password\" : null,\n" +
            "  \"email\" : \"zhangsan@qq.com\",\n" +
            "  \"phone\" : null,\n" +
            "  \"question\" : null,\n" +
            "  \"answer\" : null,\n" +
            "  \"role\" : null,\n" +
            "  \"createTime\" : null,\n" +
            "  \"updateTime\" : null\n" +
            "}, {\n" +
            "  \"id\" : 2,\n" +
            "  \"username\" : \"lisi\",\n" +
            "  \"password\" : null,\n" +
            "  \"email\" : \"lisi@qq.com\",\n" +
            "  \"phone\" : null,\n" +
            "  \"question\" : null,\n" +
            "  \"answer\" : null,\n" +
            "  \"role\" : null,\n" +
            "  \"createTime\" : null,\n" +
            "  \"updateTime\" : null\n" +
            "} ]";

    private List<User> userList = new ArrayList<>();

    @Before
    public void init() {
        u1.setId(1);
        u1.setUsername("zhangsan");
        u1.setEmail("zhangsan@qq.com");

        u2.setId(2);
        u2.setUsername("lisi");
        u2.setEmail("lisi@qq.com");

        userList.add(u1);
        userList.add(u2);
    }

    @Test
    public void testObjToString() {
        String u1Json = JsonUtil.objToString(u1);
        log.info("u1Json: {}", u1Json);
    }

    @Test
    public void testObjToStringPretty() {
        String u1JsonPretty = JsonUtil.objToStringPretty(u1);
        log.info("u1JsonPretty: {}", u1JsonPretty);
    }


    @Test
    public void testStringToObj() {
        String u1Json = "";
        User user = JsonUtil.stringToObj(u1Json, User.class);
    }

    @Test
    public void testListToStringPretty() {
        String userListStr = JsonUtil.objToStringPretty(userList);
        log.info("userListStr: {}", userListStr);

    }

    @Test
    public void testJsonToListWithObj() {
        List<User> deSerializeList1 = JsonUtil.stringToObj(userListStr, List.class);

        List<User> deSerializeList2 = JsonUtil.stringToObj(userListStr, new TypeReference<List<User>>() {
        });

        List<User> deSerializeList3 = JsonUtil.stringToObj(userListStr, List.class, User.class);

        log.info("{}", deSerializeList1);// bad

        log.info("{}", deSerializeList2);// good
        log.info("{}", deSerializeList3);// good

    }


}
