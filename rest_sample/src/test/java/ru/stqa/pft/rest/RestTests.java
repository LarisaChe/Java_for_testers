package ru.stqa.pft.rest;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import ru.stqa.pft.model.Issue;

public class RestTests {
   @Test
   public void testCreateIssue() throws IOException {
      long now = System.currentTimeMillis();
      // String.format("test %s ", now);
      Set<Issue> oldIssues = getIssues();
      Issue newIssue = new Issue().withSubject("Test issue L"+now).withDescription("Description test issue L"+now) ;
      int issueId = createIssue(newIssue);
      Set<Issue> newIssues = getIssues();
      oldIssues.add(newIssue.withId(issueId));
      System.out.println(newIssues.toString());
      Assert.assertEquals(newIssues, oldIssues);
   }

   @Test
   public void testChangeIssueState() throws IOException {
      int issueId = 369;
      String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues/369.json")
                                                 .bodyForm(new BasicNameValuePair("method", "update"))
                                                 .bodyForm(new BasicNameValuePair("state", "3")))
                                 .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      System.out.println(parsed.toString());
      System.out.println(parsed.getAsJsonObject().get("issue_id").getAsInt());
      //return parsed.getAsJsonObject().get("issue_id").getAsInt();
   }

   private Set<Issue> getIssues() throws IOException {
      //String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
      String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
                                 .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
   }

   private Executor getExecutor() {
      //return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
      return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
   }

   private int createIssue(Issue newIssue) throws IOException {
      String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
               .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                         new BasicNameValuePair("description", newIssue.getDescription())))
                                 .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      System.out.println(parsed.toString());
      System.out.println(parsed.getAsJsonObject().get("issue_id").getAsInt());
      return parsed.getAsJsonObject().get("issue_id").getAsInt();
   }

}
