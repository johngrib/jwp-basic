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
* 
