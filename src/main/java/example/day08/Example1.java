package example.day08;

import java.awt.*;

public class Example1 {

    /*
        운영체제는 실행중인 프로그램을 프로세스로 관리
            -프로그램 1개당 프로세스 존재
            -멀티 태스킹: 두 가지 이상을 동시에 처리
               -프로그램 1개당 멀티 프로세스가 존재할 수 있다.
               -프로세스 1개당 멀티 스레드가 존재할 수 있다.
               -멀티 스레드: 하나의 프로세스가 두 가지 이상의 작업을 처리
        스레드: 코드의 실행 흐름
            -스레드마다 스택 할당
            -스레드끼리 자원 공유 안됨
            -하나의 스레드가 예외 발생시 전체 스레드가 예외 발생
        멀티 스레드: 여러 개의 코드 실행 흐름
            -생성 :main스레드가 추가 작업 스레드 생성
        JVM
        메소드영역       스택영역                힙영역
        -클래스 정보     -스레드마다 할당           - 인스턴스 할당
        -static        -함수실행{}지역변수할당
     */

    //1. 메인스레드(메인함수) 선언
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        for (int i = 1; i <= 5; i++) {
            toolkit.beep(); // 띵 소리 발생시키는 함수
            //for문이 5번 회전하는것보다 띵소리가 느리기 때문에
            //메인스레드 잠시 멈추기
            //Thread.sleep(밀리초): 밀리초 만큼 일시정지
            //예외처리: 혹시나 일시정지 도중에 다른쪽 스레드가 예외 발생시킬 수 있으니
            try {
                Thread.sleep(500);//메인스레드를 0.5초간 정지

            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        for (int i = 1; i <= 5; i++) {
            System.out.println("띵");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        //================================== 단일 스레드 일때 end==============================//

        //================================== 멀티 스레드 일때==================================//
        //1. Runnable 인터페이스 구현객체 필요
        //1. 구현 클래스
        //2. 익명 구현체: 인터페이스가 new 이용한 직접 추상메소드 재정의
        // new 인터페이스명(){}
        //2. 구현객체를 Thread 생성자에 대입
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Toolkit toolkit1 = Toolkit.getDefaultToolkit();
                for (int i = 1; i <= 5; i++) {
                    toolkit.beep();
                    try {
                        {
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
            //====================작업스레드 구현 end=====================//
        });
        thread.start(); //2. 작업스레드 실행;

        for (int i = 1; i <= 5; i++) {        //main 스레드가 실행
            System.out.println("띵");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        //===========================멀티 스레드2 일때[구현 클래스]==================================//

        //1. Runnable 객체
        Runnable runnable = new 작업스레드();
        //2. Thread 객체
        Thread thread1 = new Thread(runnable);
        //3. 작업 스레드 실행
        thread1.start();

        for (int i = 1; i <= 5; i++) {        //main 스레드가 실행
            System.out.println("띵");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        //===========================멀티 스레드2 일때[Thread 상속]==================================//
        작업스레드2 작업스레드2 = new 작업스레드2();
        작업스레드2.run();

        // vs
        //익명 자식 객체
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    toolkit.beep();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        };
    }
}
