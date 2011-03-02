package sample;

public class MyUnitTestSampleService {

    public void calledFromUnitTests() {
        System.out.println("FROM UNIT TESTS:");
        new Exception().printStackTrace();
    }
}
