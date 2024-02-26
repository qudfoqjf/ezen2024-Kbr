package example.day11._2Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class RestController1 {

    //HTTP 이용한 매개변수 보내는 방법
        //1. 경로상의 변수            http://localhost/day11/black/value
        //2. 쿼리스트링 변수          http://localhost/day11/black?key=value


    //1. Get GET http://localhost:8080//day11/black
    @RequestMapping(value = "/day11/black", method = RequestMethod.GET)
    public void GetBlack(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        System.out.println("RestConroller1.GetBlack");
        // 요청
        String sendMSG= req.getParameter("msendMSG");
        System.out.println("sendMSG = " + sendMSG);
        // 응답
        resp.setContentType("text/html");
        resp.getWriter().println("클라이언트에게 안녕");
    }

    //2. Post   POST    http://localhost:8080//day11/black
    @RequestMapping(value = "/day11/black",method = RequestMethod.POST)
    public void postBlack(HttpServletRequest req,HttpServletResponse resp)throws IOException{
        System.out.println("RestConroller1.postBlack");
        //요청/응답
        String sendMSG = req.getParameter("sendMSG");
        System.out.println("sendMSG = " + sendMSG);
        resp.setContentType("text/html");
        resp.getWriter().println("클라이언트에게안녕");

    }
    //3. Put  PUT      http://localhost:8080//day11/black
    @RequestMapping(value = "/day11/black",method = RequestMethod.PUT)
    public void putBlack(HttpServletRequest req,HttpServletResponse resp)throws IOException{
        System.out.println("RestConroller1.putBlack");
        //요청/응답
        String sendMSG = req.getParameter("sendMSG");
        System.out.println("sendMSG = " + sendMSG);
        resp.setContentType("text/html");
        resp.getWriter().println("클라이언트에게안녕");
    }

    //4. Delete  DELETE   http://localhost:8080//day11/black
    @RequestMapping(value = "/day11/black",method = RequestMethod.DELETE)
    public void deleteBlack(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        System.out.println("RestConroller1.deleteBlack");
        //요청/응답
        String sendMSG = req.getParameter("sendMSG");
        System.out.println("sendMSG = " + sendMSG);
        resp.setContentType("text/html");
        resp.getWriter().println("클라이언트에게안녕");
    }

}
