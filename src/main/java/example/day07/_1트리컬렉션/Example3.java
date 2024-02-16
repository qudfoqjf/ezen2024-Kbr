package example.day07._1트리컬렉션;

import java.util.TreeMap;
import java.util.TreeSet;

public class Example3 {
    public static void main(String[] args) {
        //1. TreeSet 컬랙션 생성
            // - 필수: 정력기준 필요(Integer, String, Double 등 타입)
        TreeSet<Person> treeSet=new TreeSet<Person>();

        //2. 객체 저장
        treeSet.add(new Person("홍길동",15));
        treeSet.add(new Person("감자바",25));
        treeSet.add(new Person("박지원",31));
        System.out.println("treeSet = " + treeSet);

        String str="유재석";
        System.out.println(str.compareTo("유재석"));
        System.out.println(str.compareTo("강호동"));
        System.out.println(str.compareTo("홍길동"));

    }
}
