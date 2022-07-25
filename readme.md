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

## JUnit 5 테스트 이름 표시하기

### @DisplayName

테스트 이름을 쉽게 표현할 수 있는 어노테이션

권장됨

### @DisplayNameGeneration

전략에 따라 DisplayName 을 생성하는 어노테이션

전략을 담은 클래스를 임포트

메소드에서 control + shift + r 누르면 테스트 실행 가능 

## JUnit 5 Assertion

assertion 의 왼쪽이 기대값, 오른쪽이 실제값

```java
assertEquals(expected, actual);
```

마지막에는 Supplier 클래스가 들어갈 수 있음

```java
assertEquals(expected, actual, new Supplier<String>() {
    @Override
    public String get() {
        return "스터디를 처음 만들면 상태값이 DRAFT 여야 한다.";
    }
})
```

람다식으로 줄일 수 있음

```java
assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT 여야 한다.");
```

그냥 문자열로 써도 되는데 람다식으로 쓰는 이유는? 오류발생 시에만 문자열 연산을 해주기 위해

| 비교항목 | assert |
|---|---|
|실제 값이 기대한 값과 같은지 확인|assertEqulas(expected, actual)|
|값이 null이 아닌지 확인|assertNotNull(actual)|
|다음 조건이 참(true)인지 확인|assertTrue(boolean)|
|모든 확인 구문 확인|assertAll(executables...)|
|예외 발생 확인|assertThrows(expectedType, executable)|
|특정 시간 안에 실행이 완료되는지 확인|assertTimeout(duration, executable)|

연관된 테스트를 한번에 하는 방법 assertAll 에 포함시킴

```java
assertAll(
    () -> assertNotNull(study),
    () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT 여야 한다."),
    () -> assertTrue(study.getLimit() > 0, "스터디 최대참석인원은 0보다 커야 한다.");
);
```

### assertThrows 예외처리를 확인하는 방법

```java
assertThrows(IllegalArgumentException.class, () -> new Study(-10));

// 메시지 확이 가능
IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
String message = exception.getMessage();
assertEquals("limit 는 0 보다 커야 한다.", message);
```

### assertTimeout

```java
assertTimeout(Duration.ofMillis(100), () -> {
    new Study(10);
    Thread.sleep(300);
});
```

타임아웃이 되면 바로 테스트종료 시키기

```java
assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
    new Study(10);
    Thread.sleep(300);
});
```

코드블럭은 ThreadLocal 에서 동작?

jupiter Assertion 외에 AssertJ, Hemcrest, Truth 등의 라이브러리를 사용할 수도 있다.

asertj assertThat 예시

```java
import static org.assertj.core.api.Assertions.assertThat;
...
void create_new_study(){
    Study actual=new Study(10);
    assertThat(actual.getLimit()).isGreaterThan(0);
}
```

## JUnit 5 조건에 따라 테스트 실행하기

### assumeTrue

특정한 버전이나 OS나 환경에 따라 다르게 테스트를 실행하는 방법

```java
assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")));
```

assumeTrue 의 결과가 true 인 경우에만 이 후 테스트 실행함

System.getenv 의 값은 ~/.zshrc 등에서 `export TEXT_ENV=LOCAL` 식으로 설정할 수 있음

### assumingThat()

```java
assumingThat("LOCAL".equalsIgnoreCase("TEST_ENV"), () -> {
    Study actual = new Study(10);
    assertThat(actual.getLimit()).isGreaterThan(0);
});
```

테스트 위에 어노테이션으로 실행가능

### @Enabled, @Disabled

@EnabledOnOS OS 에 따라 실행

@DisabledOnOS OS 에 따라 실행안함

```java
@EnabledOnOs({OS.MAC, OS.LINUX})
```

@EnabledOnJre 특정 버전에 따라 실행

```java
@EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9})
```

@EnabledIfSystemProperty 시스템속성에 따라 실행

@EnabledIfEnvironmentVariable 환경변수에 따라 실행

@EnabledIf -> deprecated

## JUnit 5: 태깅과 필터링

### @Tag

여러 개의 테스트를 그룹화하는 기능

intelliJ 에서는 configuration 에서 tags 를 설정하여 실행가능

메이븐에서 테스트 필터링 하는 방법. profile 을 설정 후 테스트

```xml
<!-- profiles 에서 -->
<profiles>
    <profile>
        <id>default</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <build>
            <plugins>
                <plugin>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <configuration>
                        <groups>fast</groups>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </profile>
    <profile>
        <id>ci</id>
        <build>
            <plugins>
                <plugin>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <configuration>
                        <groups>fast | slow</groups>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </profile>
</profiles>
```

테스트 실행

```bash
# maven 예시
$ ./mvnm test -P default
```

메이븐 profile 과 표현식

- https://maven.apache.org/guides/introduction/introduction-to-profiles.html
- https://junit.org/junit5/docs/current/user-guide/#running-tests-tag-expressions

## JUnit 5 커스텀 태그

JUnit 5 어노테이션을 조합해서 커스텀 태그 생성 가능

커스텀 태그인 FastTest, SlowTest 예시 참조

테스트에 어노테이션을 줄줄이 달 필요 없이 커스텀 태그를 만들어서 사용가능 

## JUnit 5 테스트 반복하기 1부

### @RepeatedTest

테스트를 반복시키는 어노테이션

어노테이션에서 cmd + p 입력하면 파라미터 확인가능

### @ParameterizedTest

테스트에 파라미터를 넣고 파라미터만큼 테스트를 반복시키는 어노테이션


## JUnit 5 테스트 반복하기 2부


참고

- https://www.inflearn.com/course/the-java-application-test/


