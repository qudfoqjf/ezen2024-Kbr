package example.day10._2example;

public class WorkObject {
    //1. 함수
    public synchronized void methodA(){
        //1. 현재 스레드객체 호출: .currentThread()
        //2. 스레드의 이름 호출: .getName()
        Thread thread =Thread.currentThread();
        System.out.println(thread.getName());
        notify();   //다른 스레드를 실행 대기 상태로
        try {       //다른 부분에서 오류가 날 수 있어서 예외처리 필수
            wait();     //현재 스레드를 일시정지 상태로
        }catch (InterruptedException e){}

    }
    //2. 함수2
    public synchronized void methodB(){
        Thread thread = Thread.currentThread();
        System.out.println("thread.getName() = " + thread.getName());

        notify();   //다른 스레드를 실행 대기 상태로
        try {       //다른 부분에서 오류가 날 수 있어서 예외처리 필수
            wait();     //현재 스레드를 일시정지 상태로
        }catch (InterruptedException e){}
    }


}
/*

    스레드란
    멀티스레드란
    동기화 비동기화
    스레드 상태 : 실행대기, 실행, 일시정지

*/
