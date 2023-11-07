###### 스프링 공부 정리
<u>공부했던 링크들</u>   
[Kross Bow](https://joffrey-bion.github.io/krossbow/stomp/getting-started/)   
[NaikSoftware 의 Stomp 라이브러리](https://github.com/NaikSoftware/StompProtocolAndroid)   
[NaikSoftware 의 코드를 가공한 코틀린 라이브러리](https://ardor-dev.tistory.com/64)
[Scarlet](https://github.com/Tinder/Scarlet)
[Jeremy 의 Thunder](https://medium.com/@wodbs135/%EF%B8%8Fthunder-websockets-have-come-through-the-clouds-53224f330f9b)

__STOMP 란?__  
소켓 프로토콜 방식중 하나로, 퍼블리셔와 구독 형태의 소통 방식이다.   
소비자와 공급 주체가 있고 어디서 소통할지 결정한다.  
HTTP 의 모델링을 가졌고 헤더를 통해서 연결, 구독,해제 등등을 요청할 수 있다.   
또한 메시지 브로커라는 개념으로 클라이언트 간의 메시지를 주고 받을 수 있도록 중개한다는 개념이 있다.

(예외로 안드로이드는 공식적으로 지원되는 라이브러리는 따로 없고,   
개인이 만들었거나 큰 소켓 라이브러리의 일부로 사용해야함)  

__라이브러리 비교__   
회사 개발 일정에 STOMP 소켓 연결이 필요해서 안드로이드에서 사용할 라이브러리가 필요하다.   
기존 안드로이드 프로젝트에 사용하고 있던 Flow를 가장 잘 활용하고, 사용하려는 페이지가 많은 것들이 엉켜있어서,   
추가적으로 많은 요구 사항을 반영해야 한다.

NaikSoftware
-
가장 인기가 많은 개인 라이브러리 
하지만 Java 와 RX Java로 이루어져 있어서  
현대 안드로이드와 동떨어진 느낌이 들지만 크게 필요한 기능은 모두 충족한다.


Kross Bow  
- 
최신 코틀린 라이브러리라서, 매우 심플한 코드를 보여준다,    
하지만 많은 상태관리가 지원되는지 확인이 필요하다.  
소켓이 끊기고 재연결 될때의 컨트롤의 관한 레퍼런스가 부족하다.    
   
```
//간단 샘플 

val client = StompClient(WebSocketClient.builtIn()) 
val session: StompSession = client.connect(url) 
 
val subscription: Flow<String> = session.subscribeText("/some/topic/destination")
val collectorJob = launch {
    subscription.collect { msg ->
        println("Received: $msg")
    }
}
delay(3000)
collectorJob.cancel()
session.disconnect()

```

Scarlet
-
매우 유명한 현대적 소켓 라이브러리  
레트로핏에서 영감을 받아서 어노테이션을 이용해서 Send, event, Response 크게 3가지를 받는다.   
그래서 리액티브하게 값을 받아 처리할 수 있으며, Flow 도 어댑터를 사용해서 사용이 가능하다.   
무엇보다 현대적인 설계에 응용하기 좋은 구조로 가지고 있다.   
문제는 2021년에 마지막 릴리스로 멈춰있고, STOMP 를 2.X 대에서  공식지원을 한다 했으나,   
아직 소식이 없다.   
