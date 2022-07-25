package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("새로운 스터디 만들기")
    void create_new_study() {
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);

//        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
//            new Study(10);
//            Thread.sleep(300);
//        });

//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
//        String message = exception.getMessage();
//        assertEquals("limit 는 0 보다 커야 한다.", message);

//        Study study = new Study(-10);
//        assertAll(
//            () -> assertNotNull(study),
//            () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT 여야 한다."),
//            () -> assertTrue(study.getLimit() > 0, "스터디 최대참석인원은 0보다 커야 한다.")
//        );
    }

    @Test
    void create_new_study_again() {
        System.out.println("create1");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each");
    }

}