package example.day01.webMvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController //해당 클래스를 스프링mvc 환경에 등록
public class TodoController {
    private TodoDao todoDao= new TodoDao();

    //2. 할일등록 함수
    @GetMapping("/todo/post.do")
    public boolean doPost(TodoDto todoDto){
        return todoDao.doPost(todoDto);
    }
    //Js[외부] 가 JAVA에게 요청하는 경로
    // http://localhost:8080/todo/post.do?content=안녕하세요&deadline=2024-11-21
    //3. 할일목록 함수
    @GetMapping("/todo/get.do")
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }
    //http://localhost:8080/todo/get.do
}
