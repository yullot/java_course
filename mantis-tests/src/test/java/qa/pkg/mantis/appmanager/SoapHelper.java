package qa.pkg.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import qa.pkg.mantis.model.Issue;
import qa.pkg.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
  private final ApplicationManager app;
  private final String adminLogin;
  private final String adminPassword;
  private final String soapUrl;


  public SoapHelper(ApplicationManager app) {
    this.app = app;
    this.adminLogin=app.getProperty("soap.adminLogin");
    this.adminPassword=app.getProperty("soap.adminPassword");
    this.soapUrl=app.getProperty("soap.soapURL");
  }

  public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible(adminLogin, adminPassword);

    return Arrays.asList(projects).stream()
            .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
  }

  public MantisConnectPortType getMantisConnect() throws MalformedURLException, ServiceException {
    return new MantisConnectLocator()
            .getMantisConnectPort(new URL(soapUrl));

  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {

    MantisConnectPortType mc = getMantisConnect();
    String [] categories=mc.mc_project_get_categories(adminLogin, adminPassword, BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setCategory(categories[0]);
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    BigInteger issueId = mc.mc_issue_add(adminLogin, adminPassword, issueData);
    IssueData createdIssueData =mc.mc_issue_get(adminLogin, adminPassword, issueId);
  return new Issue().withId(createdIssueData.getId().intValue())
          .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
          .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
          .withName(createdIssueData.getProject().getName()));
  }

  public String getIssueStatus(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc =getMantisConnect();
    IssueData issueData =mc.mc_issue_get(adminLogin, adminPassword, BigInteger.valueOf(issueId));
    String issueStatus= issueData.getStatus().getName();
    return issueStatus;
  }
}
