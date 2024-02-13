package example.day02.webMvc;

import example.day02.consoleMvc.MainView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//1, 내장서버(?캣) 실행
//********* 2. 동등한 패키지 혹은 하위 패키지내
// @Controller @RestController 둘을 스캔
public class AppStart {
    public static void main(String[] args) {
        // *스프링 시작
        SpringApplication.run(AppStart.class);
    }
}
/*

 */
