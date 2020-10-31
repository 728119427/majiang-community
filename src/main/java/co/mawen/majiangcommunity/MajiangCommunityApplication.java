package co.mawen.majiangcommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("co.mawen.majiangcommunity.mapper")
public class MajiangCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MajiangCommunityApplication.class, args);
    }

}
