console.log('member.js');





//1. 회원가입
function signup(){
    //1. HTML 입력값 호출
    let id = document.querySelector('#id').value;
    let pw = document.querySelector('#pw').value;
    let name = document.querySelector('#name').value;
    let phone = document.querySelector('#phone').value;
    let email = document.querySelector('#email').value;
    let img = document.querySelector('#id').value;

    //2. 객체화
    let info= {id:id, pw:pw,name:name, phone:phone, email: email, img: img};
     //3. 객체를 배열에 저장 --> SPRING CONTROLLER 서버와 통신 [JQUERY AJAX]
    $.ajax({
        url:'/member/signup',
        method:'POST',
        data: info,
        success :function(result){
        console.log(result);
          //4. 결과
          if(result){
          alert('회원가입 성공');
          location.href='/member/login';
          }else{
            alert('회원가입 실패');
          }
        }
    })
}
//2. 로그인
function login(){
    console.log("login()")
    //1. HTML 입력값 호출
    let id = document.querySelector('#id').value; console.log(id);
    let pw = document.querySelector('#pw').value; console.log(pw);

    //2. 객체화
    let info = {id:id, pw:pw};

    //3. 서버와 통신
    $.ajax({
       url : '/member/login',                       // 어디에
       method : 'POST',                             // 어떻게
       data :  info  ,                              // 입력받은 값 보내기
       success : function ( result ){               // 통신후 응답받은 값
            console.log(result);
             //4. 결과
            if(result){alert('로그인성공')}
            else{alert('로그인실패');}
        }
    })
}





