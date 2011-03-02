package sample;

import org.junit.Test;

public class MySampleServiceTest {

    @Test
    public void exerciseUnitTest() {
        MyUnitTestSampleService myService = new MyUnitTestSampleService();
        myService.calledFromUnitTests();
    }
}
