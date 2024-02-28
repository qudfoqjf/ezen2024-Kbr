package ezenweb.service;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MemberService {
    @Autowired
    private FileService fileService;    // 외부 서비스
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private EmailService emailService;
    //1. 회원가입 서비스
    public boolean doPostSignup(MemberDto memberDto){

        /*
            만약에
                1. 첨부파일 있다 vs 업다
                    있다[업로드 성공했다 vs 실패했다]
                        성공 db처리
                        실패 무산 false
                    없다
                        db처리
         */
        //1. 파일처리
        // 만약에 첨부파일이 존재하면
        String fileName= "default.jpg";
        if(!memberDto.getImg().isEmpty()){
            fileName = fileService.fileUpload(memberDto.getImg());
            if(fileName==null){ // 업로드 성공했으면
                return false;
            }
        }
        memberDto.setUuidFile(fileName);
        boolean result=memberDao.doPostSignup(memberDto);
        if(result){emailService.send();}
        return result;

    }// end
    //2. 로그인 서비스

    //3. 회원정보 요청 서비스
    public MemberDto doGetLoginInfo(String id){
        // 1. DAO 호출
        return memberDao.doGetLoginInfo(id);

    }

    //4. 아이디 중복 체크 요청
    public boolean doGetFinIdCheck( String id){
        return false;
    }
}

