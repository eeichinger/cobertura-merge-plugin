package sample;

public class MyIntegrationTestSampleService {
    public void calledFromIntegrationTests() {
        System.out.println("FROM INTEGRATION TESTS:");
        new Exception().printStackTrace();
    }

    public void notCalledAtAll() {
        throw new UnsupportedOperationException("SHOULD NOT BE CALLED");
    }
}
