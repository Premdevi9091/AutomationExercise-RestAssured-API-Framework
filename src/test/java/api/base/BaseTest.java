package api.base;

import api.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setup(){

        RestAssured.baseURI = ConfigReader.get("base.url");
        RestAssured.defaultParser = Parser.JSON;

    }
}
