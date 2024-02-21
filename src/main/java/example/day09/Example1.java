package example.day09;

public class Example1 {
    // * main 함수 안에는 main 스레드 포함
    public static void main(String[] args) {
        //1. 현재 코드의 스레드객체 호출
            //Thread.currentThread();
        Thread thread = Thread.currentThread();
        //2. 현재 코드를 실행하는 스레드 객체의 이름
        System.out.println("1thread.getname()=" +thread.getName());

        //3. 작업스레드 생성 4가지 방법
            //자식 익명 객체:  부모타입 변수명 = new 부모타입(){재정의;}
        Thread threadA =new Thread(){
            @Override
            public void run(){// - 작업 스레드가 실행할 때 최초로 실행되는 함수.
                Thread thread1 = Thread.currentThread();
                System.out.println("2작업스레드 A :" +getName());

            }
        };
        // *2 작업스레드 실행
        threadA.start();
        System.out.println("threadA = " + threadA);
    }
}
