package example.day01.webMvc;

import example.day01.consoleMvc.MainView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AppStart { //스프링 부트 주입
    public static void main(String[] args) {

        // 스프링 시작
        SpringApplication.run(AppStart.class);
        //http://localhost:8080

    }
}
