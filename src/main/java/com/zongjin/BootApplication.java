package com.zongjin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot启动类
 *
 * @Author Tommy
 * @Date 2021/11/10
 */
@SpringBootApplication // 默认是扫描当前包以及子包的所有类
public class BootApplication {

    public static void main(String[] args) {

        SpringApplication.run(BootApplication.class, args);

        System.out.println(" TyCode 启动成功 \n" +
                " _________    ___    ___ \n" +
                "|\\___   ___\\ |\\  \\  /  /|\n" +
                "\\|___ \\  \\_| \\ \\  \\/  / /\n" +
                "     \\ \\  \\   \\ \\    / / \n" +
                "      \\ \\  \\   \\/  /  /  \n" +
                "       \\ \\__\\__/  / /    \n" +
                "        \\|__|\\___/ /     \n" +
                "            \\|___|/      \n");
    }
}
