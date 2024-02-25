import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class MyTestListener extends TestListenerAdapter {
    private int count = 0;

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log(iTestResult.getName()+ "--Test method failed\n");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log(iTestResult.getName()+ "--Test method skipped\n");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log(iTestResult.getName()+ "--Test method success\n");
    }

    private void log(String string) {
        System.out.print(string);
        if (++count % 40 == 0) {
            System.out.println("");
        }
    }

}

