package example.day01.consoleMvc;

import java.util.ArrayList;

public class TodoController {
    private TodoDao todoDao= new TodoDao();

    //2. 할일등록 함수
    public boolean doPost(TodoDto todoDto){
        return todoDao.doPost(todoDto);
    }

    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }
}
