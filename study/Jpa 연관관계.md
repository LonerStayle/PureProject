###### 스프링 공부 정리
<u>공부했던 링크들</u>   
[Jpa 연관관계 정리](https://jeong-pro.tistory.com/231)   

연관 관계 생각 3가지 
-

- 방향 : 단방향, 양방향 / 연관관계 주인 / 다중성: 1:1, 1:N, N:1, N:M  

###단방향,양방향   
DB의 테이블은 왜래키 하나로 양쪽 테이블 조인이 가능함,    
_하지만 객체는 참조용 필드가 있는 객체만 다른 객체참조 하는것이 가능함_   
   
Jpa 는 데이터베이스와 객체지향 관점을 맞추기 위해서 결국 방향이 중요하다.   
두 객체 사이에 하나의 객체만 참조용 필드를 사용하면 __단방향__,   
두 객체가 각각 참조용 필드를 사용하면 __양방향__ 이라고 한다.  

양방향: 두 개의 단방향이 서로를 향하고 있으면 __양방향__ 이라고 한다.
```
//예시 코드 (id는 생략)
@Entity
data class Author(
    @OneToMany(mappedBy = author)
    val books:List<Books> = emptyList() 
)

@Entity
data class Book { 
    @ManyToOne
    @JoinColumn(name = "author_id")
    val author:Author
}
```  
위 예제 처럼 반드시 주체가 아닌 객체에서 mappedBy로 주인을 지정해야한다.   
항상 __외래 키__ 가 있는 쪽이 주체이다.


####양방향의 단점
테이블의 연관 관계가 모두 양방향으로 이루어진다면, 연관관계가 매우 복잡해서 식별하기 어려워짐   
__기본적으로 단방향 매핑으로 하고 나중에 역방향으로 객체 탐색이 꼭 필요하면 추가할때 권장__   


###다중성   
방향성과 달리 다중성은 DB 테이블 기준으로 설계가 된다. 

#####1:N

_N:1 요구 사항_   
하나의 게시판(1)에는 여러 게시글(N)을 작성할 수 있습니다.   
하나의 게시글(N)은 하나의 게시판(1)에만 작성할 수 있다.   
   
또한, jpa entity 에서 N을 연관관계 주인으로 두는 것  
```
/*N:1 단방향*/

@Entity
data class Post( 
    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    val id: Long,
    
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    val board: Board? = null
)

@Entity
data class Board(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val title: String? = null
)

```

```
/*N:1 양방향*/
@Entity
data class Post(
    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    val id: Long,
    
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    val board: Board? = null
) 

@Entity
data class Board(
    @Id
    @GeneratedValue
    val id: Long,
    
    @OneToMany(mappedBy = "board")
    val posts: List<Post> = ArrayList()
)  

```


#####1:1 양방향
1:1 은 주 테이블과 부 테이블이 있음   
1:1 단방향은 공식적으로 jpa 에서 지원을 하지 않음 

```
@Entity
data class Post(
    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    val id: Long? = null,

  

    @OneToOne
    @JoinColumn(name = "ATTACH_ID")
    val attach: Attach? = null
)

@Entity
data class Attach(
    @Id
    @GeneratedValue
    @Column(name = "ATTACH_ID")
    val id: Long? = null,

    @OneToOne(mappedBy = "attach")
    val name: String? = null
)
```   

이럴 때는 어차피 양 쪽이 일대일이기 때문에 위에서 정의한 대로 처리하면 됩니다.

그러나 논란의 여지가 있습니다.

외래 키를 Post에서 관리하는 게 좋을 것인지, Attach에서 관리하는 게 좋을 것인지 생각을 해봐야합니다. 즉 테이블에 어디에 둘 것 인지를 생각해야합니다.

테이블은 한 번 생성되면 보통 굳어집니다. 변경이 어렵다는 얘기입니다.

그러나 비즈니스는 언제든 바뀔 수 있습니다.

게시글이 여러 개의 첨부파일을 첨부할 수 있도록 비즈니스가 변경되면 어떨까요?

그러면 다(N)쪽인 Attach테이블에 외래 키가 있는 것이 변경에 유연합니다.

그러면 다(N)가 될 확률이 높은 테이블에 외래 키를 놓는게 무조건 좋을까요?

그건 또 아닙니다.

객체 입장에서 Post쪽(1)에서 외래 키를 갖게되면 Post를 조회할 때마다 이미 Attach의 참조를 갖고 있기 때문에 성능상 이득이 있습니다.

※ 결론

종합적으로 판단하고 결정해야하는데 단순화해서, 보통 일대일이라고 정할 때도 아주 신중하게 정했다고 가정한다면 주 테이블(Post)에 외래 키를 두는 것이 더 낫습니다.

다시 말씀드리지만 논쟁이 있고 의견일 뿐입니다.