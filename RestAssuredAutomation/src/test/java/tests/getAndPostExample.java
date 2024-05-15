package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class getAndPostExample {
	
	@Test
	public void testGet() {
	
		baseURI = "https://reqres.in/api";
		given()
		.get("/users?page=2").
		then()
		.statusCode(200)
		.body("data[2].first_name", equalTo("Tobias"))
		.body("data.first_name", hasItems("Tobias","Georgei"))
		
		;
		
		
	}
	
	@Test
	public void testPost() {
		
		/*
		 * Map<String, Object> map=new HashMap<String,Object>(); map.put("name",
		 * 
		 * "Kuhu"); map.put("job", "Playing"); System.out.println(map);
		 */
		
		JSONObject request=new JSONObject();
		request.put("name", "Kuhu");
		request.put("job", "Playing");
		//System.out.println(request.toJSONString());
		baseURI = "https://reqres.in/api";
		given()
		.header("Content-Type","application/json")
		.contentType(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.post("/users").
		then().assertThat()
		.statusCode(201)
		//.log().all()
		;
		
		
		
	}

}
