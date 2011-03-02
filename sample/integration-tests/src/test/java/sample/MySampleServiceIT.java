package sample;

import net.sourceforge.cobertura.coveragedata.CoverageDataFileHandler;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

public class MySampleServiceIT {

//    @BeforeClass
//    public static void initCob() {
//        File f = CoverageDataFileHandler.getDefaultDataFile();
//        Assert.assertEquals("false", f.getAbsolutePath());
//    }

    @Test
    public void exerciseMySampleService() {
        MyIntegrationTestSampleService myService = new MyIntegrationTestSampleService();
        myService.calledFromIntegrationTests();
//        Assert.fail("OK");
    }
}
