package ru.stqa.pft.rest;

import java.io.IOException;
import java.util.Set;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import ru.stqa.pft.model.Issue;

public class RestAssuredTests {

   @BeforeClass
   public void init() {
      RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
   }

   @Test
   public void testCreateIssue() throws IOException {
      long now = System.currentTimeMillis();
      Set<Issue> oldIssues = getIssues();
      Issue newIssue = new Issue().withSubject("Test issue L"+now).withDescription("Description test issue L"+now) ;
      int issueId = createIssue(newIssue);
      Set<Issue> newIssues = getIssues();
      oldIssues.add(newIssue.withId(issueId));
      System.out.println(newIssues.toString());
      Assert.assertEquals(newIssues, oldIssues);
   }

   private Set<Issue> getIssues() throws IOException {
      String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
     // String json = RestAssured.get(String.format("https://bugify.stqa.ru/api/issues/%s.json", 369)).asString();
      System.out.println(json);
           JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
   }

   private int createIssue(Issue newIssue) throws IOException {
      String json = RestAssured.given()
                 .parameter("subject", newIssue.getSubject())
                 .parameter("description", newIssue.getDescription())
                 .post("https://bugify.stqa.ru/api/issues.json").asString();



      JsonElement parsed = new JsonParser().parse(json);
      System.out.println(parsed.toString());
      System.out.println(parsed.getAsJsonObject().get("issue_id").getAsInt());
      return parsed.getAsJsonObject().get("issue_id").getAsInt();
   }

}
