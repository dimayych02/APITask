package LogApiListener;


import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.AfterMethod;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



public class ApiListener  {

    private  ByteArrayOutputStream request = new ByteArrayOutputStream();
    private  ByteArrayOutputStream response = new ByteArrayOutputStream();
    private  PrintStream requestVar = new PrintStream(request,true);
    private  PrintStream responseVar = new PrintStream(response,true);


    @AfterMethod
    public  void logAfterTests(){
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL,responseVar),
                new RequestLoggingFilter(LogDetail.ALL,requestVar));
       logRequest(request);
       logResponse(response);
    }



    @Attachment(value="request")
    public byte[] logRequest(ByteArrayOutputStream stream){
        return  attach(stream);
    }

    @Attachment(value="response")
    public byte[] logResponse(ByteArrayOutputStream stream){
        return  attach(stream);
    }

    public   byte[] attach(ByteArrayOutputStream log){
        byte[] array = log.toByteArray();
        log.reset();
        return array;
    }
}



