import org.example.Main;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;


public class MainTest {

    @BeforeAll
    public static void setup() {
        Main.main(null);
    }

    @Test
    void testCurrentDatePass() {
        Calendar calendar = Calendar.getInstance();
        when().
                get("/currentdate").
                then().
                statusCode(200).body("date",equalTo(calendar.get(Calendar.DATE)));

    }

    @Test
    void testCurrentDateFail() {
        Calendar calendar = Calendar.getInstance();
        when().
                get("/currentdte").
                then().
                statusCode(404);

    }

    @Test
    void testCurrentDayPass() {
        Calendar calendar = Calendar.getInstance();
        when().
                get("/currentday").
                then().
                statusCode(200).body("day",equalTo(calendar.get(Calendar.DAY_OF_WEEK)));

    }

    @Test
    void testCurrentDayFail() {
        Calendar calendar = Calendar.getInstance();
        when().
                get("/currentdy").
                then().
                statusCode(404);

    }
    @Test
    void testCurrentMonthPass() {
        Calendar calendar = Calendar.getInstance();
        when().
                get("/currentmonth").
                then().
                statusCode(200).body("month",equalTo(calendar.get(Calendar.MONTH)));

    }

    @Test
    void testCurrentMonthFail() {
        Calendar calendar = Calendar.getInstance();
        when().
                get("/currentmnth").
                then().
                statusCode(404);

    }

    @Test
    void testCurrentYearPass() {
        Calendar calendar = Calendar.getInstance();
        when().
                get("/currentyear").
                then().
                statusCode(200).body("year",equalTo(calendar.get(Calendar.YEAR)));

    }

    @Test
    void testCurrentYearFail() {
        Calendar calendar = Calendar.getInstance();
        when().
                get("/currentyr").
                then().
                statusCode(404);

    }

    @Test
    void testPostEventPass() {
        JSONObject parameters = new JSONObject();
        parameters.put("date", "9");
        parameters.put("month", "10");
        parameters.put("year", "2022");
        parameters.put("patientId", "1");
        parameters.put("doctorId", "1");
        parameters.put("EventID", "1");
        given().
                body(parameters.toString()).
        when().post("/event").then().statusCode(201);

    }

    @Test
    void testPostEventFail() {
        JSONObject parameters = new JSONObject();
        parameters.put("date", "9");
        parameters.put("month", "10");
        parameters.put("year", "2022");
        parameters.put("patientId", "1");
        parameters.put("doctorId", "1");
        parameters.put("EventID", "1");
        given().
                body(parameters.toString()).
        when().
                post("/evnt").then().statusCode(404);

    }


    @Test
    void testPutEventPass() {
        JSONObject parameters = new JSONObject();
        parameters.put("date", "9");
        parameters.put("month", "10");
        parameters.put("year", "2022");
        parameters.put("patientId", "1");
        parameters.put("doctorId", "2");
        parameters.put("EventID", "1");
        given().
                body(parameters.toString()).when().
                put("/event/{eventID}", 1).then().
                statusCode(200).body("success",equalTo("Updated the Event!"));

    }

    @Test
    void testPutEventFail() {
        JSONObject parameters = new JSONObject();
        parameters.put("date", "9");
        parameters.put("month", "10");
        parameters.put("year", "2022");
        parameters.put("patientId", "1");
        parameters.put("doctorId", "1");
        parameters.put("EventID", "1");
        given().
                body(parameters.toString()).
        when().
                put("/evnt/{eventID}",1).
        then().
                statusCode(404);

    }

    @Test
    void testDeleteEventPass() {

        when().
                delete("/event/{eventID}", 1).then().
                statusCode(200).body("success",equalTo("Deleted the Event!"));

    }
    @Test
    void testDeleteEVentFail() {

        when().
                delete("/evnt/{eventID}",2).then().
                statusCode(404);

    }

    @Test
    void testGetEventsforDatePass() {
        given().
                param("date", "9").
                param("month", "10").
                param("year", "2022").

                when().get("/event").
                then().statusCode(200);

    }

    @Test
    void testGetEventsforDateFail() {
        given().
                param("date", "9").
                param("month", "10").
                param("year", "2022").

                when().get("/evnt").
                then().statusCode(404);

    }

}
