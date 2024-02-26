package example.day11._2Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/day11")
public class RestController3 {

    //HTTP 이용한 매개변수 보내는 방법
        //1. 경로상의 변수            http://localhost/day11/black/value
        //2. 쿼리스트링 변수          http://localhost/day11/black?key=value


    //1. Get GET http://localhost:8080//day11/red
    @GetMapping("/red")
    public String getRed(HttpServletRequest req)throws IOException {
        // 요청
        String sendMSG= req.getParameter("msendMSG");
        System.out.println("sendMSG = " + sendMSG);
        // 응답
        return "안녕(클라이언트)";
    }

    //2. Post   POST    http://localhost:8080//day11/red

    @PostMapping("/red")
    public Map<String,String> postRed(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        //요청
        String sendMSG = req.getParameter("sendMSG");System.out.println("sendMSG = " + sendMSG);
        //응답

        // 배열
        //String[] strArray=new String[2];
        //strArray[0] = "안녕"; strArray[1] = "클라이언트";

        // List
        /*List<String> strArray= new ArrayList<>();
        strArray.add("안녕"); strArray.add("클라이언트");*/

        // map
        Map<String,String> strArray = new HashMap<>();
        strArray.put("안녕","클라이언트");
        return strArray;

    }
    //3. Put  PUT      http://localhost:8080//day11/red

    @PutMapping("/red")
    public int putRed(HttpServletRequest req,HttpServletResponse resp)throws IOException{
        System.out.println("RestConroller1.putBlack");
        //요청/응답
        String sendMSG = req.getParameter("sendMSG");   System.out.println("sendMSG = " + sendMSG);

        return 10;
    }

    //4. Delete  DELETE   http://localhost:8080//day11/red
    @DeleteMapping("/red")
    public boolean deleteRed(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        System.out.println("RestConroller1.deleteBlack");
        //요청/응답
        String sendMSG = req.getParameter("sendMSG");   System.out.println("sendMSG = " + sendMSG);

        return true;
    }

}
