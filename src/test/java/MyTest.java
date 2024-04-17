import org.junit.jupiter.api.*;
import sem.App;

import static org.junit.jupiter.api.Assertions.*;


class MyTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void displayCitiesTestNull()
    {
        app.displayCities(null);
    }
}