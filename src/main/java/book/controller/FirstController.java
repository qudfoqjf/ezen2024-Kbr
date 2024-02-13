package book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //  이 클래스가 컨트롤러임을 선언
public class FirstController {

    @GetMapping("/hi")
     public String niceToMeetyou(Model model){
         //return "머스테치파일명";
        model.addAttribute("username","홍팍");
        return "greetings";
        //서버가 알아서 templates 폴더에서 파일을 찾아 브라우저에게 전승.
     }

     @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","홍길동");
        return "goodbye";
    }
     // http://localhost:8080/greetings.mustache    [X] resources/templates
     // http://localhost:8080/hello.html            [O] resources/static
     // http://localhost:8080/hi                    [0] resources/static
     // http://localhost:8080/bye
}
/*
    HTTP: 이동식문서 교환 규약
        1. IP 주소:PORT 번호    .스프링            .   localhost:8080
        2.  /자원의 경로        .도서지급대장문서 요청.    /bookdocument.@GetMapping("/bookdocument") --->해당 함수로 이동

    브라우저[클라이언트]                                             스프링[서버] localhost:8080
    강호동                                                         신동엽
                    강호동이 신동엽에게 도서지급대장문서를 요청
                        http://localhost:8080/hi
                        ----------------------------------->       서랍=hi[도서지급대장문서=greetings.mustache]
   브라우저           신동엽이 강호동에게 도서지급대장문서를 제공
   html 렌더링기능    greetings.html[String]                         강호동은 템플릿을 모르기 때문에 HTML 렌더링하고 HTML로 반환.
                     <----------------------------------
*/