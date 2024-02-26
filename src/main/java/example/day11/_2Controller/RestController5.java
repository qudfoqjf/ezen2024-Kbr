package example.day11._2Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/day11")
public class RestController5 {
    //--------------------경로상의 변수(쿼리스트링): <POST, PUT,GET,DELETE> ==//
        // 글등록,글조회,글삭제,글수정 => Get 다 가능
        // 쿼리스트링: URL 상에 데이터/매개변수가 표시됨
        // 캐시(기록) 남기 => 장: 기록된 데이터 재사용으로 빠름 단점: 노출

    //==================contentType: form <POST, PUT> ====================//
        // URL 상에 데이터/매개변수 표시안함. => HTTP body(본문) 이용, POST/PUT 가능

    //1.
   /* @PostMapping
    public String ajax5(int id, @RequestParam String content){
        System.out.println("RestController5.ajax5");
        System.out.println("id = " + id + ", content = " + content);
        return "응답5";
    }*/
    //2.
  /*  @PostMapping("/ajax5")
    public String ajax5(@RequestParam Map<String,String> map){
        System.out.println("RestController5.ajax5");
        System.out.println("map = " + map);
        return "응답5";
    }*/
    //3.
    @PostMapping("/ajax5")
    public String ajax5(AjaxDto ajaxDto){
        System.out.println("RestController5.ajax5");
        System.out.println("ajaxDto = " + ajaxDto);
        return "응답5";
    }

    //-----------------------------------------------------------//
    @PostMapping("/ajax6")
    public String ajax6(@RequestBody AjaxDto ajaxDto){
        System.out.println("RestController5.ajax6");
        System.out.println("ajaxDto = " + ajaxDto);
        return "응답6";
    }
    @PostMapping("/ajax6")
    public String ajax6(@RequestParam Map<String,String> map){
        System.out.println("RestController5.ajax6");
        System.out.println("map = " + map);

        return "응답6";
    }





}
