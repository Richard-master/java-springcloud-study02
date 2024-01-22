package cn.itcast.bean;

import org.springframework.context.annotation.Bean;

/**
 * 没有spring注解
 */
public class UserConfiguration {
    @Bean
    public User getUser() {
        User user = new User();
        user.setAge(12);
        user.setUsername("测试");
        return user;
    }
}
