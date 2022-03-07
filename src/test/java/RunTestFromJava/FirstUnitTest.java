package RunTestFromJava;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;

public class FirstUnitTest {

    @Test
    public void whenThis_thenThat() {
        Assertions.assertTrue(true);
    }

    @Test
    public void whenSomething_thenSomething() {
        Assertions.assertTrue(false);
    }

    @Test
    public void whenSomethingElse_thenSomethingElse() {
        Assertions.assertTrue(true);
    }

    @Test
    public void miTest(){
        TestBuilder tb = new TestBuilder();
        String res = tb.getResponse();
        Assertions.assertEquals(ContentType.JSON, res);
    }
}