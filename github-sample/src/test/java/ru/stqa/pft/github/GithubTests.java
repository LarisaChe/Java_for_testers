package ru.stqa.pft.github;

import java.io.IOException;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.RepoCommit;
import com.jcabi.github.RepoCommits;
import com.jcabi.github.RtGithub;

/**
 * Description.
 *
 * @author lchernaya
 */
public class GithubTests {

   @Test
   public void testCommits () throws IOException {
      Github github = new RtGithub("21be78403adb99a96844142521db137d273793ff");
      RepoCommits commits = github.repos().get(new Coordinates.Simple("LarisaChe","Java_for_testers")).commits();
      for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
         //System.out.println(commit);
         System.out.println(new RepoCommit.Smart(commit).message());
      }


   }

}
