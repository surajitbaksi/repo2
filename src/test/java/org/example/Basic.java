package org.example;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basic
{
    public static void main(String[] args)
    {
    String response =   given().baseUri("https://rahulshettyacademy.com").log().all().queryParam("key","qaclick123").
                header("Content-Type","application/json").body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}\n").
                when().post("/maps/api/place/add/json").
                then().assertThat().log().all().statusCode(200).body("scope",equalTo("APP")).extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String place_id = js.getString("place_id");
        String new_address = "Bangalore";

        //update place

        given().baseUri("https://rahulshettyacademy.com").log().all().queryParam("key","qaclick123").
                header("Content-Type","application/json").body("{\n" +
                        "\"place_id\":\""+place_id+"\",\n" +
                        "\"address\":\""+new_address+", USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").

        when().put("maps/api/place/update/json").

        then().assertThat().log().all().statusCode(200);
    }

}
