package com.project.cache.annotation.resolver;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ognl.Ognl;
import ognl.OgnlContext;
import org.junit.Test;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/1/9.
 */
@Slf4j
public class TestAbstractSimpleResolver {

    class User {
        private int id;
        private String name;
        private Room room;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Room getRoom() {
            return room;
        }

        public void setRoom(Room room) {
            this.room = room;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class Room {
        private String root;

        public String getRoot() {
            return root;
        }

        public void setRoot(String root) {
            this.root = root;
        }
    }

    @Test
    public void testOgnl() throws Exception {

        User user = new User();
        user.setId(1111);
        user.setName("namedddddddddd");

        Room room = new Room();
        room.setRoot("rooteeee");
        user.setRoom(room);

        OgnlContext context = new OgnlContext();
        // OGNL实现了MAP接口
        context.put("user", user);
        context.put("room", room);
        Object object = Ognl.parseExpression("user.id == 11");
        Object result = Ognl.isConstant(object);

        log.info(result.toString());
    }
}
