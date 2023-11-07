###### 스프링 공부 정리
<u>공부했던 링크들</u>   
[스프링 정리](https://heosedev.tistory.com/entry/Spring-%EA%B0%9C%EB%85%90-%EC%A0%95%EB%A6%AC)   
[Catsbi 님의 노션](https://catsbi.oopy.io/)
- Dispatcher-Servlet
  Http 프로토콜로 들어오는 모든 요청을 가장 먼저 받아 적절한 컨트롤러에 연결해주는 용도로 사용된다.   
  > 장점: web.xml 의 역활을 없애면서 컨트롤러 구현이 매우 단순해짐   
  > 
  > 단점: 이미지, Html/css/js 요청까지 가로채서 대안이 필요해짐   
  
- Spring IOC  
객체 생성 -> 의존성 객체 주입(스프링에게 권한을 줌) -> 의존성 객체의 메소드 호출   
Spring 에게 의존 위임을 준 객체들은 Ioc 컨테이너에서 객체의 생명주기를 관리해준다.
   

- Spring 의 뼈대 BeanFactory 와 ApplicationContext
1. BeanFactory 는 Bean 객체를 관리하고 조회하는 역활을 하며, 최상위 인터페이스
2. ApplicationContext 는 BeenFactory 의 기능을 상속받은 인터페이스로 BeanFactory 의 기능들을 사용하며,  
Transaction, AOP 등 부가기능도 포함되어 있어서 Spring 프로젝트에서 자주 사용한다.   
3. Bean 객체는 Spring 의 컨테이너가 관리하도록 위임된 객체를 뜻한다. Ioc 컨테이너에서 Bean 객체들을 싱글톤으로 관리한다.

    
- Spring의 의존성 주입 3가지
```
//1. 필드 인젝션
//단점으로 객체가 변할 수 있고, 필드 주입시 외부에서 수정이 불가해서 Test 가 불가하다.
public class DIController {
	@Autowired 
	private DIService DIService;
}
```

```
//2. 세터 인젝션
//단점으로 setter 를 쓰기전에 객체를 사용하면 NPE 위험이 있다.
public class DIController {
	
	private DIService diService;
    
	@Autowired 
	public void setDIService(DIService diService){
		this.DIService = diService;
	}  
       
}

```

```
//3. 생성자 주입
//NPE 에도 위험이 없으며 final 키워드로 객체의 불변성을 확보한다.
public class DIController {

	private final DIService diService;

	@Autowired
	public DIService(DIService diService){
		this.DIService = diService;
	}

}
```   

- 스프링의 AOP   
관점 지향 프로그래밍 사용하는 공통 기능들을 따로 추출해서 사용하는 것    
  (공부 추가 필요, 아직 스프링 입문 단계임으로 일단 보류)


- 스프링의 배치    
Application, Batch Core ,Batch Infrastructure 3가지로 구성 된다.
  1) Application   
  스프링 배치 프레임워크를 통해 커스텀한 Job 과 커스텀 코드를 포함해서 개발자는 비즈니스 로직을 집중하도록 한다.
  2) Batch Core   
  Job 에 관한 핵심 API 를 가지고 있다. JobLauncher, Job, Step, Flow 등이 속한다.   
  3) Batch Infrastructure   
  Application 과 Core 모두 이곳에서 빌드하며, Job 실행의 흐름을 처리할 틀을 제공한다. Reader, Process Writer, Skip, Retry 등등..
   

- 스프링의 배치 아키텍처 디테일   
  Job, Step, Job Launcher, Job Repository, ItemReader/ItemProcessor/ItemWriter 으로 구성 되어 있다.   
  - 보통 JobBuilderFactory, StepBuilderFactory 써서 Job -> Step -> Tasklet 순으로 작업을 하는 코드를 짜면 된다. 
  

- @Bean vs @Component   
  1) @Bean 은 반환 타입을 보고 Bean 객체를 만든다. 주로 외부 라이브러리 클래스를 Bean 객체로 만들 때 사용한다.
  2) Component 는 개발자가 직접 만든 클래스 자체를 Bean 객체로 만들때 사용한다.
  3) @Autowired 는 객체의 타입을 보고 주입하기 때문에 겹치는게 있다면, @Qualifier(bean 이름) 을 통해 명시를 해야한다.   
  4) @Bean 을 사용하려면 @Configuration 를 쓰는 Config 클래스를 임의로 써서 @Bean 클래스를 감싸고 main() 에서 등록해야한다.   
  4) @Component 클래스는 자동으로 Bean 객체로 등록 되지만, 안될땐 xml 설정이 필요하다.
  
  
- 스프링의 순환참조 3가지 경우
```
//1. 생성자 주입 방식 
// A 와 B 는 생성부에서 서로를 필요로 하는 순환 참조가 발생한다.
@Component
public class BeanA {
	private BeanB beanB;

	public void BeanA(BeanB beanB){
		this.beanB = beanB;
	}
}

@Component
public class BeanB {
	private BeanA beanA;

	public void BeanB(BeanA beanA){
		this.beanA = beanA;
	}
}

```

```
//2. 필드 주입 방식  
// 생성자 주입과 달리 런타임 발생한다.
@Component
@Slf4j
public class BeanA {
	@Autowired
	private BeanB beanB;

	public void run(){
		beanB.run();
	}

	public void call(){
		log.info("called BeanA");
	}
}

@Component
@Slf4j
public class BeanB {
	@Autowired
	private BeanA beanA;

	public void run(){
		log.info("Called BeanB");
		beanA.call();
	}
}
```

```
//3. 세터 주입 방식  
// 마찬가지로 런타임에서 발생한다.
@Component
@Slf4j
public class BeanA {
	private BeanB beanB;

	@Autowired
	public void setBeanB(BeanB beanB){
		this.beanB = beanB;
	}

	public void run(){
		beanB.run();
	}

	public void call(){
		log.info("called BeanA");
	}
}

@Component
@Slf4j
public class BeanB {
	private BeanA beanA;

	@Autowired
	public void setBeanA(BeanA beanA){
		this.beanA = beanA;
	}

	public void run(){
		log.info("Called BeanB");
		beanA.call();
	}
}

```