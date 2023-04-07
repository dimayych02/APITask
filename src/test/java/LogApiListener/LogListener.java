package LogApiListener;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;




public class LogListener implements ITestListener {

    private   ByteArrayOutputStream request = new ByteArrayOutputStream();
    private  ByteArrayOutputStream response = new ByteArrayOutputStream();
    private  PrintStream requestVar = new PrintStream(request,true);
    private  PrintStream responseVar = new PrintStream(response,true);

    public  void OnStart(ITestContext iTestContext){
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL,requestVar),
                new RequestLoggingFilter(LogDetail.ALL,requestVar));
    }
    public  void onTestSuccess(){
        logRequest(request);
        logResponse(response);
    }

    public  void onTestFailure(ITestResult iTestResult){
       onTestSuccess(iTestResult);
    }
    @Attachment(value="request")
    public byte[] logRequest(ByteArrayOutputStream stream){
        return  attach(stream);
    }

    @Attachment(value="response")
    public byte[] logResponse(ByteArrayOutputStream stream){
        return  attach(stream);
    }

    public  static byte[] attach(ByteArrayOutputStream log){
        byte[] array = log.toByteArray();
        log.reset();
        return array;
    }
}
