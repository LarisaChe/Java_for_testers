package ru.stqa.pft.mantis.appmanager;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.rpc.ServiceException;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

public class SoapHelper {

   private ApplicationManager app;

   public SoapHelper(ApplicationManager app) {
      this.app = app;
   }

   public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
      MantisConnectPortType mc = getMantisConnect();
      final ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
      return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
   }

   private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
      MantisConnectPortType mc = new MantisConnectLocator()
           .getMantisConnectPort(new URL("http://localhost/mantisbt-2.24.3/api/soap/mantisconnect.php"));
      return mc;
   }

   public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
      MantisConnectPortType mc = getMantisConnect();
      String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
      IssueData issueData = new IssueData();
      issueData.setSummary(issue.getSummary());
      issueData.setDescription(issue.getDescription());
      issueData.setCategory(categories[0]);
      issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
      BigInteger issueId =  mc.mc_issue_add("administrator", "root", issueData);
      IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
      return new Issue().withId(createdIssueData.getId().intValue())
                        .withSummary(createdIssueData.getSummary())
                        .withDescription(createdIssueData.getDescription())
                        .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                                  .withName(createdIssueData.getProject().getName()));
   }
}
