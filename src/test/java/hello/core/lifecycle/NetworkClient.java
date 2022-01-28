package hello.core.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 가짜 네트워크 클라이언트
public class NetworkClient {

    // 접속할 url
    private String url;


    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출하는 메서드
    public void connect() {
        System.out.println("connect : " + url);
    }

    // 실제로 연결된 상태에서 호출이 가능한 메서드(연결이 되었기 때문에 url이 나온다)
    public void call(String message) {
        System.out.println("call : " + url + " message : " + message);

    }

    // 서비스 종료시 호출하는 메서드
    public void disconnect() {
        System.out.println("close : + " + url);
    }
}
