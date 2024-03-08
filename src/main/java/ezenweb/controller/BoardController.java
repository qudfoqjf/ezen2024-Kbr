package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.BoardPageDto;
import ezenweb.service.BoardService;
import ezenweb.service.FileService;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// @ResponseBody  응답데이터를 객체로 하겠다.
// @RequestParam
@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    //세션 불러오기위함
    @Autowired
    private HttpServletRequest request;
    // mid로 mno 불러오기
    @Autowired
    private MemberService memberService;
    @Autowired
    private FileService fileService;
    //1. 글쓰기 처리             "/board/write"        POST      Dto
    @PostMapping("/write.do")
    @ResponseBody
    public long doPostBoardWrite(BoardDto boardDto){
        System.out.println("BoardController.doPostBoardWrite");
        //1. 현재 로그인된 세션 호출(톰캣서버(자바프로그램) 메모리(JVM) 저장소)
        Object object=request.getSession().getAttribute("loginDto");
        if(object==null) return -2;
        //2. 형 변환
        String mid= (String) object;
        //3. mid 로 mno가져오기
        long mno = memberService.doGetLoginInfo(mid).getNo();
        //4. 작성자 번호 대입
        boardDto.setMno(mno);
        return boardService.doPostBoardWrite(boardDto);
    }
    //2. 전체 글 출력 호출        "/board/"            GET        x, 페이징처리, 검색
    @GetMapping("/do")
    @ResponseBody                           //@RequestParam: 쿼리스트링
    public BoardPageDto dogetBoardViewList(
            @RequestParam int page,@RequestParam int pageBoardSize,
            @RequestParam int bcno, @RequestParam("key") String field,
            @RequestParam("keyword") String value
            ){
        System.out.println("BoardController.dogetBoardViewList");
        return boardService.dogetBoardViewList(page,pageBoardSize,bcno,field,value);
    }
    //3. 개별 글 출력 호출        "/board/view"        GET        게시물번호
    @GetMapping("/view.do")
    @ResponseBody
    public BoardDto doGetBoardView(@RequestParam int bno){
        System.out.println("BoardController.doGetBoardView");
        return boardService.doGetBoardView(bno);
    }

    //4. 글 수정 처리             "/board/update.do"   put       dto
    @PutMapping("/update.do")
    @ResponseBody
    public Boolean doPutBoard(BoardDto boardDto) {
        System.out.println("BoardController.doPutBoard");
        //유효성 검사. //1. 현재 로그인된 아이디(세션)
        Object object = request.getSession().getAttribute("loginDto");
        if (object != null) {
            String mid = (String) object;

            boolean result = boardService.boardWriterAuth(boardDto.getBno(), mid);


            if (result) {
                return boardService.doPutBoard(boardDto);
            }
            //2. 현재 수정할 게시물의 작성자 아이디(DB);

        }
        return false;
    }
    //5. 글 삭제 처리            "/board/delete.do"    delete    게시물번호
    @DeleteMapping("/delete.do")
    @ResponseBody
    public boolean doDeleteBoard(@RequestParam int bno){System.out.println("BoardController.doDeleteBoard");
        //유효성 검사.
        //1. 현재 로그인된 아이디(세션)
        Object object=request.getSession().getAttribute("loginDto");
        if(object != null ){
            String mid=(String)object;

            boolean result=boardService.boardWriterAuth(bno,mid);

            if(result){
                return boardService.doDeleteBoard(bno);//2. 현재 수정할 게시물의 작성자 아이디(DB)
            }//2. 현재 수정할 게시물의 작성자 아이디(DB)
        }
        return false;
    }
    //6. 다운로드 처리(함수만들때 고민할점 1.매개변수: 파일명  2. 반환 3. 사용처:get http 요청)
    @GetMapping("/file/download")
    @ResponseBody
    public void getBoardFileDownload(@RequestParam String bfile){
        System.out.println("BoardController.getBoardFileDownload"); System.out.println("bfile = " + bfile);

        fileService.fileDownload(bfile);// autowired 사용
        return;
    }

    //7. 댓글 작성 (brcontent, brindex, mno, bno)
    @PostMapping("/replay/write.do")
    @ResponseBody
    public boolean postReplyWrite(@RequestParam Map<String,String>map){System.out.println("BoardController.postReplyWrite");

        //1. 현재 로그인된 세션(톰캣서버(자바프로그램), 메모리(jvm)저장소) 호출
        Object object = request.getSession().getAttribute("loginDto");
        if(object==null)return false;   //세션없다( 로그인 안했다)
        //2. 형변환
        String mid=(String) object;
        //3. mid를 mno 찾아오기
        long mno= memberService.doGetLoginInfo(mid).getNo();
        //4. map에 mno 넣기
        map.put("mno",mno+"");

        System.out.println("map = " + map);

        return boardService.postReplyWrite(map);
    }
    //8. 댓글 출력 (brno, brcontent,brdate,mno)
    @GetMapping("/reply/do")
    @ResponseBody
    public List<Map<String,String>> getReplyDo(int bno){ System.out.println("BoardController.getReplyDo");
        return null;
    }
    //------------------------------------------------------//

    //1. 글쓰기 페이지 이동       "/board/write"    GET
    @GetMapping("/write")
    public String getBoardWrite(){
        return "ezenweb/board/write";
    }

    //2. 게시판 페이지 이동       "/board"          GET
    @GetMapping("/")
    public String getBoard(){
        return "ezenweb/board/board";
    }

    //3. 게시판 상세 페이지 이동  "/board/view"     GET
    @GetMapping("/view")
    public String getBoardView(int bno){
        return "ezenweb/board/view";
    }

    //4. 글수정 페이지 이동       "/board/update"   GET
    @GetMapping("/update")
    public String getBoardUpdate(){return "ezenweb/board/update";}

}
