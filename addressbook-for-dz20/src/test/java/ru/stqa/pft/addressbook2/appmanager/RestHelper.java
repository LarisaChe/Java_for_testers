package ru.stqa.pft.addressbook2.appmanager;

import java.io.IOException;
import java.util.Set;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import ru.stqa.pft.addressbook2.model.Issue;

public class RestHelper {

   private ApplicationManager app;
   private Executor executor;

   public RestHelper(ApplicationManager app) {
      this.app = app;
      executor = Executor.newInstance().auth(app.getProperty("rest.login"), "");
   }

   public Set<Issue> getIssue(int issueId) throws  IOException {
      //String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
      String json = executor.execute(
           Request.Get(String.format("%s/%s.json", app.getProperty("rest.url"), issueId)))//"https://bugify.stqa.ru/api/issues.json"))
                            .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
   }

  /* // RestAssured
  private Executor getExecutor() {
      //return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
     // return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
      return executor.auth(app.getProperty("rest.login"), "");

   } */
  /* public void init() {
      //RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
      RestAssured.authentication = RestAssured.basic(app.getProperty("rest.login"), "");
   }

   public Issue getIssue(int issueId) throws IOException { //Set<Issue>
      //String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
      init();
      String json = RestAssured.get(String.format("%s/%s.json", app.getProperty("rest.url"), issueId)).asString();
      System.out.println(json);
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
   } */

}
