package com.starchat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "my-config")
public class MyConfigProperties {
    private Admin admin;

    @Setter
    @Getter
    public static class Admin {
        private List<String> emails;
    }
}
