package com.fengling.ecserver;

import com.fengling.ecserver.netty.client.EnableNettyClient;
import com.fengling.ecserver.netty.server.EnableNettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages = "com.fengling.ecserver")
@MapperScan("com.fengling.ecserver.mapper")
@EnableNettyServer
@EnableNettyClient
public class EcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcServerApplication.class, args);
    }

}
