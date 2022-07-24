package me.whiteship.inflearnthejavatest.study;

import me.whiteship.inflearnthejavatest.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void create() {
        App app = new App();
        assertNotNull(app);
    }
}
