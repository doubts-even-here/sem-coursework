import org.junit.jupiter.api.*;
import sem.App;

import static org.junit.jupiter.api.Assertions.*;


public class MyTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 10000);
    }

    @Test
    void getWorldPopulationTest() {
        long population = app.getWorldPopulation();
        assertNotEquals(0, population);
    }
}