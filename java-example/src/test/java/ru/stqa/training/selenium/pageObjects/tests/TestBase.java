package ru.stqa.training.selenium.pageObjects.tests;


import org.junit.After;
import org.junit.Before;
import ru.stqa.training.selenium.pageObjects.app.Application;

public class TestBase {
    protected static final Application app = new Application(org.openqa.selenium.remote.BrowserType.FIREFOX);

    @Before
    public void setUp() throws Exception {
        app.init();
    }

    @After
    public void tearDown() {
        app.stop();
    }
}
