package example.day11._2Controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController //@Controller *@ResponseBody
@RequestMapping("/day11")
public class RestController4 {
    //1.
    @GetMapping("/ajax1")
    public String ajax1(){
        System.out.println("RestController4.ajax1");
        return "응답1";
    }
    //2. 경로상의 변수를 활용한 매개변수 요청 받기
    @GetMapping("/ajax2/{id}/{content}")
    public String ajax2(@PathVariable int id,@PathParam("content") String content2){
        System.out.println("RestController4.ajax2");
        System.out.println("id = " + id+", content"+content2);
        return "응답2";
    }

    //3. 쿼리스트링



    //3-1. @RequestParam
  /*  @GetMapping("/ajax3")
    public String ajax3(int id, @RequestParam("content") String content2){
        System.out.println("RestController4.ajax3");
        System.out.println("id = " + id+",content="+ content2);

        return "응답3";
    }*/
    //3-2.HttpServletRequest 객체
  /*  @GetMapping("/ajax3")
    public String ajax3(int id, @RequestParam("content") String content2){
        System.out.println("RestController4.ajax3");
        System.out.println("id = " + id+",content="+ content2);

        return "응답3";
    }
*/
    //3-3.@RequestParam Map
    /*@GetMapping("/ajax3")
    public String ajax3(@RequestParam Map<String,String> map){
        System.out.println("RestController4.ajax3");
        System.out.println("map = " + map);

        return "응답3";
    }*/
    //3-4. DTO
    @GetMapping("/ajax3")
    public String ajax3(AjaxDto ajaxDto){
        System.out.println("RestController4.ajax3");
        System.out.println("ajaxDto = " +ajaxDto);

        return "응답3";
    }

    //4. HTTP BODY 본문
    // -1.
/*    @GetMapping("/ajax4")
    public  String ajax4(int id,@RequestParam("content") String content){
        System.out.println("RestController4.ajax4");
        System.out.println("id = " + id + ", content = " + content);
        return "응답4";
    }*/

    @GetMapping("/ajax4")
    public  String ajax4(Map<String,String> map){
        System.out.println("RestController4.ajax4");
        System.out.println("map = " + map);
        return "응답4";
    }

}
