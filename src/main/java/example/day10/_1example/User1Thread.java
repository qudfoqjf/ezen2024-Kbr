package example.day10._1example;

public class User1Thread extends Thread {
    //extends Thread : 작업스레드를 생성하기 위해

    //1. 필드, 유저1객체가 가지고있는 계산기
    private  Calculator calculator;

    public User1Thread(){
        //setName: Thread 클래스로부터 상속받은 함수(작업스레드의 이름 변경)
        setName("User1Thread");
    }
    public  void setCalculator(Calculator calculator){
        this.calculator= calculator;
    }
    @Override
    public void run(){
        calculator.setMemory(100);
    }
}
