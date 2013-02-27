package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;

public class MultipleChoice implements Question {

	public static final int type=3;
	private String statement;
	private Set<String> wrongAnswers;
	private String answer;
	private int qID;
	
	public MultipleChoice(String question, Set<String> wrong, String ans, int id) {
		statement = question;
		
		for(String s : wrong) {
			wrongAnswers.add(s);
		}
		
		answer = ans;
		qID = id;
	}
	
	public MultipleChoice(Integer id, Connection con) throws SQLException {
		this.qID = id;
		
		PreparedStatement ps = con.prepareStatement("select * from multiple_choice_question where question_id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		String wrong = new String();
		while(rs.next()) {
			statement = rs.getString("statement");
			answer = rs.getString("answer");
			wrong = rs.getString("wrong_answers");
		}
		
		StringTokenizer tokenizer = new StringTokenizer(wrong, "&&&");
		while(tokenizer.hasMoreTokens()) {
			wrongAnswers.add(tokenizer.nextToken());
		}
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public Set<String> getWrongAnswers() {
		return wrongAnswers;
	}

	public void setWrongAnswers(Set<String> wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}
	

	@Override
	public int solve(ArrayList<String> answer) {

		// TODO Auto-generated method stub
		return 0;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toHTMLString() {
		StringBuilder html = new StringBuilder();
		
        html.append(statement);
        html.append("<br />");
        
        for(String s : wrongAnswers) {
                html.append("<input type=\"radio\" name=\""+ qID + "\" value=\"");
                html.append(s + "\"> " + s);
                html.append("<br />");       
        }
        
        html.append("<input type=\"radio\" name=\""+ qID + "\" value=\"");
        html.append(answer + "\"> " + answer);
        html.append("<br />");
        
		return html.toString();
	}

	@Override
	public void generate(int id, Connection con) {
		// TODO Auto-generated method stub
		
	}

}
