###### 스프링 공부 정리
<u>공부했던 링크들</u>   
[Dispatcher-Servlet](https://mangkyu.tistory.com/18)   
[JSP 이란 무엇인가](https://javacpro.tistory.com/43)   
[Controller Vs RestController](https://mangkyu.tistory.com/49)  
[ResponseEntity 란?](https://devlog-wjdrbs96.tistory.com/182)   
[DI 란?](https://heosedev.tistory.com/entry/DI%EC%9D%98%EC%A1%B4%EC%84%B1-%EC%A3%BC%EC%9E%85%EC%9D%B4%EB%9E%80)   
[POJO](https://yoo11052.tistory.com/133)

- Dispatcher-Servlet
  Http 프로토콜로 들어오는 모든 요청을 가장 먼저 받아 적절한 컨트롤러에 연결해주는 용도로 사용된다.   
  > 장점: web.xml 의 역활을 없애면서 컨트롤러 구현이 매우 단순해짐   
  > 
  > 단점: 이미지, Html/css/js 요청까지 가로채서 대안이 필요해짐   
  > 단점 대응 1. 정적 자원 요청과 애플리케이션 요청을 분리   
  > 단점 대응 2. 애플리케이션 요청을 탐색하고 없으면 정적 자원 요청으로 처리  
  > 
  > 과정 1. 클라 요청 받음    
  > 과정 2. HandlerMapping 으로 어느 컨트롤러가 요청처리 할지 찾음    
  > 과정 3. 요청을 컨트롤러로 위임할 HandlerAdapter 을 찾음   
  > 과정 4. HandlerAdapter 가 Controller 로 요청을 위임함    
  > 과정 5. Controller 가 서비스를 호출하고 비즈니스 로직 처리   
  > 과정 6. Response Entity 를 반환함   
  > 과정 7. HandlerAdapter 가 반환값을 받고 내부적으로 직렬화,응답 상태 등등 처리한다.   
  > 과정 8. 클라에게 응답 
  
  
- Controller Vs RestController 의 차이  
Controller 는 ResponseEntity 를 반환하기 위해서 @RequestBody 를 붙여야하지만 RestController 는 그럴 필요가 없다.
  

- (기본 웹지식: 웹 어플리케이션이 기본적으로 갖춰야할 구조)   
  1. 웹 브라우저
  2. 웹 서버
  3. 웹 어플리케이션 서버
  4. 데이터베이스
    

- (기본 웹지식: JSP 와 서블릿)  
  jsp 는 자바 서버 페이지의 줄임말   
  클라이언트는 jsp 로 요청 -> jsp 는 서블릿으로 변환 -> 서블릿.java 는 class로 변환 ->   
  jsp 컨테이너로 전달 -> jsp 는 http 프로토콜을 이용해서 html 페이지를 클라이언트에게 전달
  

    
- DI 복습   

  DI 란 내부에서 객체를 직접 생성하지 않고 외부를 통해 객체를 주입함으로써,    
  클래스간의 의존 관계를 조절하거나 결합도를 낮출 수 있다.      
  
     
  1) 외부에서 어떠한 객체를 주입할 것인지 정하기 때문에,     
  내부 클래스에서 구체적으로 특정 클래스를 지정하지 않아서 확장성이 매우 높다.   
  2) 보통 DI를 사용할때 특정 구현 클래스를 직접 의존하지 않고 추상 클래스에 의존하기 
  때문에 의존 관계가 역전 된다.   
  3) 외부에서 주입하기 때문에 테스트에도 매우 용이하다. 
  4) DI 를 쓰면 보통 DI 라이브러리를 많이 활용하는데 최신 DI 라이브러리나    
   프레임 워크들은 외부에서 주입하는 컨테이너가 대부분 객체의 생명주기를 자동으로 관리해줘서 편리하다.
  5) 클래스간의 의존 관계가 매우 줄어든다. 
