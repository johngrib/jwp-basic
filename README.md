#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.

1. 지정한 webappDirLocation 경로를 탐색한다.
1. JSP 파일은 컴파일해서 WEB-INF 로 배포한다.
1. 리스너를 띄우고 요청을 기다린다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.

1. `WebServlet`이 `/ dispatcher` 로 요청을 돌린다.
1. `/ dispatcher`의 `init` 메서드가 호출된다.
1. `RequestMap`이 초기화된다.
1. `service` 메서드가 호출된다.
1. request에 맞는 controller를 탐색한다.
1. `HomeController`로 요청을 처리한다.
    1. JSP로 `home.jsp`를 지정한다.
    1. 모든 질문을 조회하여 `model`에 입력한다.
1. HTML 코드를 렌더링하여 클라이언트로 전송한다.

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* Controller는 각각 하나의 인스턴스가 생성되어 `RequestMap`에 보관되고 있다.
Controller가 필요할 때마다 `RequestMap`에서 꺼내어 사용하는 방식인데,
`ShowController`는 `Question` 객체와 `Answer` 객체의 리스트를 멤버 변수로 갖고 있으므로
멀티 스레드 상황에서 두 변수에 대해 경쟁상태가 벌어질 가능성이 매우 높다.

```
private Question question;
private List<Answer> answers;
```

* 이에 대해서는 두 변수를 삭제하여, `ShowController`를 Stateless 하게 바꿔주는 방식으로 문제를 해결하면 된다.
