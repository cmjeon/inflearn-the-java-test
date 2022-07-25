# 개요

더 자바, 애플리케이션을 테스트하는 다양한 방법 학습하기

# 1부 JUnit 5

## JUnit 5 소개

자바 개발자가 가장 많이 사용하는 테스팅 프레임워크

JUnit 5 의 세부모듈

- Jupiter : TestEngine API 구현체로 JUnit 5 를 제공 -> 주로 사용할 구현체
- JUnit Platform : 시행가능한 런처 제공, TestEngine API 제공
- Vintage : JUnit 4, 3 를 지원하느 구현체

## JUnit 5 시작하기

스프링부트 프로젝를 만들 JUnit 5 의존성이 추가됨

기본적으로 제공되는 파일들 삭제

테스트하고자 하는 파일에서 cmd + shift + t 로 테스트파일 생성 가능

JUnit 5 부터는 클래스, 메소드가 Public 이지 않아도 됨

### 스프링부터 프로젝트가 아니라면?

의존성 추가

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.5.2</version>
    <scope>test</scope>
</dependency>
```

### @BeforeAll 

모든 테스트를 실행하기 전에 딱 한번 실행됨

메소드를 static void 로 작성

### @AfterAll

모든 테스트가 실행되고 난 후 딱 한번 실행

메소드를 static void 로 작성

### @BeforeEach, AfterEach

각 테스트가 실행 전, 실행 후에 실행

### @Disabled

메소드에 선언하면 테스트 실행안함

주석말고 사용하자