package com.frostick.assetmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class AssetmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetmanagementApplication.class, args);
    }

}
