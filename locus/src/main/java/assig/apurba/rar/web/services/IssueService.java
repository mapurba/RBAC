package assig.apurba.rar.web.services;

import java.util.List;

import assig.apurba.rar.web.model.Issue;
import assig.apurba.rar.web.model.Project;

public interface IssueService {
	public List<Issue> getIssues(Project project);
	public Issue getIssue(Integer id);
	public void createIssue(Issue issue);
	public void updateIssue(Issue issue);
	public void deleteIssue(Issue issue);
}
