package ezenweb.controller;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.rmi.server.ExportException;
import java.util.UUID;

@Controller
public class MemberController {
    //Http 요청 객체
    @Autowired
    private HttpServletRequest request;
    //Dao 객체
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberService memberService;

    // 1단계. V<---->C 사이의 HTTP 통신 방식 설계
    // 2단계. Controller mapping 함수 선언 하고 통신 체크 ( API Tester )
    // 3단계. Controller request 매개변수 매핑
    // -------------- Dto , Service ---------------//
    // 4단계. 응답 : 1.뷰 반환 : text/html;  VS  2. 데이터/값 : @ResponseBody : Application/JSON

    // 1.=========== 회원가입 처리 요청 ===============
    @PostMapping("/member/signup") // http://localhost:80/member/signup
    @ResponseBody // 응답 방식 application/json;
    public boolean doPostSignup( MemberDto memberDto ){
        return memberService.doPostSignup(memberDto); // Dao 요청후 응답 결과를 보내기.
    }



    // 2. =========== 로그인 처리 요청 ===============
    @PostMapping("/member/login") // http://localhost:8080/member/login
    @ResponseBody  // 응답 방식 application/json;
    public boolean doPostLogin( LoginDto loginDto ){
        /* params    { id ="아이디" , pw ="비밀번호"  }   */
        boolean result = memberDao.doPostLogin(loginDto); // Dao처리
        // *로그인 성공시
            // 세션 저장소: 톰캣서버에 *브라우저마다 메모리 할당
            // 세션 객체 타입: object (여러가지 타입들을 저장하기 위해서)
            // 1. Http 세션 저장소:
            // 2. Http 세션 객체 호출 .getSession()
            // 3. Http 세션 데이터 저장 .setAttribute("세션명", 데이터);
        request.getSession().setAttribute("loginDto",loginDto.getId());    //loginDto: 3

        return result; // Dao 요청후 응답 결과를 보내기
    }// f end

    //2-2 ===========로그인 여부 확인 요청==================
    @GetMapping("/member/login/check")
    @ResponseBody
    public String doGetLogincheck(){
        // * 로그인 여부 확인 =세션이 있다 없다 확인
            //1 ->http 요청 객체 호출 , 2-> http 세션 객체 호출 -> 3. http 세션 데이터 호출
        // null 은 형변환이 불가능하기 때문에 유효성검사
        String loginDto=null;
        Object sessionObj = request.getSession().getAttribute("loginDto");
        if(sessionObj !=null){loginDto=(String) sessionObj;}
        return loginDto;
    }

    //2-3 ======= 로그인 로그아웃/ 세션 초기화==========
    @GetMapping("/member/logout")
    @ResponseBody   //응답받을 대상이 JS ajax
    public boolean doGetLogOut(){
        //1. 로그인 관련 세션 초기화
            //1. 모든 세션 초기화(모든 세션의 속성이 초기화-> 로그인 세션외 다른 세션도 고려)
            request.getSession().invalidate(); //현재 요청을 보낸 브라우저의 모든 세션 초기화
            //2. 특정 세션속성 초기화 => 동일한 세션속성명에 null 대입한다.
            //request.getSession().setAttribute("loginDto",null);
        return true;
    }
    //3. =================회원정보 요청(로그인된 회원 요청)===================
    @GetMapping("/member/login/info")
    @ResponseBody
    public MemberDto doGetLoginInfo(String id){



        return memberDao.doGetLoginInfo(id);
    }







    {}    // 3. =========== 회원가입 페이지 요청 ===============
    @GetMapping("/member/signup")
    public String viewSignup(){
        System.out.println("MemberController.viewSignup");
        return "ezenweb/signup";
    }




    // 4. =========== 로그인 페이지 요청 ===============
    @GetMapping("/member/login")
    public String viewLogin(){
        System.out.println("MemberController.viewLogin");
        return "ezenweb/login";
    }



    //5. =============== 회원 수정 페이지 =================
    @GetMapping("/member/edit")
    public String edit(@PathVariable int no){
        return "ezenweb/edit";
    }




    //6. ================= 회원 수정 처리 ==================
    @PostMapping("member/update")
    public String update(MemberDto memberDto){

        return "redirect:/login";
    }





    //7. ================ 회원 삭제 처리====================
    @GetMapping("member/delete")
    public String update(@PathVariable int no){

        return "redirect:/login";
    }



}