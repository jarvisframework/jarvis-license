package com.jarvis.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Doug Wang
 * @since 1.8, 2024-04-15 16:24:02
 */
@SpringBootApplication
public class JarvisLicenseApplication {

    public static void main(String[] args) {
        SpringApplication.run(JarvisLicenseApplication.class, args);
        System.out.println("************系统启动完毕.************");
    }

}
