package com.example.bean;

import com.example.JacksonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void test() throws JsonProcessingException {
        User user = new User(42L, "John", "Doe", "password123");

        String serialized = JacksonUtils.wrappingMapper.writeValueAsString(user);

        assertThat(serialized).isEqualTo("{\"user\":{\"id\":42,\"name\":\"John Doe\"}}");
    }
}