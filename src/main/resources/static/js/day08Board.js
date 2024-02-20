console.log("day08Board");

//1. 저장 메소드 : 실행조건 : 등록 버튼 클릭시
function doCreate(){
    console.log("doCreate()");
}

//2. 전체 호출 메소드 : 실행조건: 페이지 열릴때, 변화(자장,수정,삭제)가 있을때(새로고침) 매개변수X, 리턴X)
doRead();   // JS 열릴때 최초 실행;
function doRead(){
    console.log("doRead()");
}

//3. 수정 메소드 : 실행조건 : 수정 버튼 클릭시 매개변수: 수정할식별할키 bno, 리턴X
function doUpdate(){
    console.log("doUpdate()"+bno);
}

//4. 삭제 메소드: 실행조건: 삭제 버튼 클릭시 매개변수: 삭제할 식별키 bno , 리턴X
function doDelete(){
    console.log("doDelete()"+bno);
}