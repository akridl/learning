package com.example.bean;

import com.example.JacksonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BeanWithMapTest {

    @Test
    void testEmptyMap() throws JsonProcessingException {
        BeanWithMap b = new BeanWithMap(42L, "beanie");

        String serialized = JacksonUtils.commonMapper.writeValueAsString(b);

        assertThat(serialized).isEqualTo("{\"beanName\":\"beanie\",\"id\":42}");
    }

    @Test
    void testFilledMap() throws JsonProcessingException {
        BeanWithMap b = new BeanWithMap(1L, "beanie");
        b.map.put("key1", "value");
        b.map.put("key2", "value");

        String serialized = JacksonUtils.commonMapper.writeValueAsString(b);

        assertThat(serialized).isEqualTo("{\"beanName\":\"beanie\",\"id\":1,\"key1\":\"value\",\"key2\":\"value\"}");
    }
}