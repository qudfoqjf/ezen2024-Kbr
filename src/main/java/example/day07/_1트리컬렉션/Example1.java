package example.day07._1트리컬렉션;

import java.util.NavigableSet;
import java.util.TreeSet;

public class Example1 {
    public static void main(String[] args) {

        //1. TreeSet 컬렉션 생성
        TreeSet<Integer> scores = new TreeSet<>();

        //2. TreeSet 컬렉션 객체에 객체 추가
        scores.add(87);
        scores.add(98);
        scores.add(75);
        scores.add(95);
        scores.add(80);
        System.out.println(scores);

        /*

            컬렉션 프레임워크 : 널리 알려진 자료구조 기반으로 미리 만들어진 클래스/인터페이스를
            자료구조: 자료(데이터)를 저장하는 방법론
            이진 트리: 여러 자료구조 중에 하나의 방법


        */

        //3. 순회
        for(Integer i: scores){System.out.println("i = " + i);}
        System.out.println();
        scores.forEach(i-> System.out.println("i = " + i));
        System.out.println();

        //4. HashSet 보다 추가적인 메소드 제공.
        System.out.println("가장 낮은 점수:"+scores.first());
        System.out.println("가장 높은 점수:"+scores.last());
        System.out.println("95점보다 낮은 점수:"+scores.lower(95));
        System.out.println("95점보다 높은 점수:"+scores.higher(95));
        System.out.println("95점이거나 바로 아래 점수:"+scores.floor(95));
        System.out.println("85점이거나 바로 위의 점수:"+scores.ceiling(85));

        //5. 내림차순
        NavigableSet<Integer> descending=scores.descendingSet();
        System.out.println("descending = " + descending);

        //6. 범위검색
        System.out.println("scores.tailSet(80,true) ="+scores.tailSet(80,true));

        //(80<= <90)80~89사이
        System.out.println("scores ="+ scores.subSet(80,true,90,false));



    }


}
