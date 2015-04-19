
package au.edu.unsw.soacourse.marketservice;

import org.junit.Test;

import au.edu.unsw.soacourse.marketservice.HelloWorldImpl;
import static org.junit.Assert.assertEquals;

public class HelloWorldImplTest {

    @Test
    public void testSayHi() {
        HelloWorldImpl helloWorldImpl = new HelloWorldImpl();
        String response = helloWorldImpl.sayHi("Sam");
        assertEquals("HelloWorldImpl not properly saying hi", "Hello Sam", response);
    }
}