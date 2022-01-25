package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 선언힌다
    // 1. static 영역에 객체 instance를 미리 하나 생성해서 올려둡니다.
    private static final SingletonService instance = new SingletonService();

    // 2. SingletonService 인스턴스가 필요하다면 getInstance() 메서드를 통해서만 조회가 가능하다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 인스턴스는 딱 하나만 존재해야하므로 외부에서 생성하지 못하도록 private으로 성생자를 지정한다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글토 객체 로직 호출");
    }

}
