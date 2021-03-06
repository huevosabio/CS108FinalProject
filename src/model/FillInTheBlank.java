package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class FillInTheBlank implements Question {

	public static final int type=2;
	private String statement;
	private Set<String> answers;
	private int qID;
	
	public void pushToDB(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement("insert into fill_in_the_blank_question values(null, ?, ?)");
		
		ps.setString(1, statement);
		
		StringBuilder ans = new StringBuilder();
		for (String string : answers) {
			ans.append(string);
			ans.append(" &&& ");
		}
		
		ans.replace(ans.length()-5, ans.length(), "");
		ps.setString(2, ans.toString());
		
		System.out.print(ans.toString());
		ResultSet resultSet = ps.executeQuery();
		
		PreparedStatement getID = con.prepareStatement("select * from fill_in_the_blank_question where statement = ?");
		getID.setString(1, statement);
		
		System.out.print(getID.toString());
		resultSet = getID.executeQuery();
		
		
		while (resultSet.next()) {
			this.qID = resultSet.getInt("question_id"); // will always be the last one
		}
	}
	
	public FillInTheBlank(String question, Set<String> ans) { 
		this.statement = question; // this should have the ________ in it already
		this.answers = ans; // need to add the &&&
	}

	public FillInTheBlank(int id, Connection con) { // pulls from database
		generate(id, con);
	}
	
	@Override
	public void generate(int id, Connection con) {
		this.qID = id;
		try {
			
			PreparedStatement ps = con.prepareStatement("select * from fill_in_the_blank_question where question_id = ?");
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			
			
			String ans = new String();
			while (resultSet.next()) {
				statement = resultSet.getString("statement");
				ans = resultSet.getString("answer");
				
			}

			String[] strings = ans.split(Pattern.quote(" &&& "));
			answers = new HashSet<String>();
			for (String string : strings) {
				answers.add(string);
			}
				
			
		}catch(Exception e){
			
		}
		
	}
	
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public Set<String> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<String> answers) {
		this.answers = answers;
	}
	
	public int solve(ArrayList<String> ans) {

		if (ans.size()!=1) {
			return 0; // input cleansing
		}
		for (String a : ans) {
			
			if (answers.contains(a)) {
				return 1;
			}
		
		}
		return 0;	
	}

	
	@Override
	public String toHTMLString() {
		int index = this.statement.indexOf("__________");
		StringBuilder html = new StringBuilder();
		html.append(this.statement.substring(0, index));
		html.append("<input type=\"text\" name=\"");
		html.append(type);
		html.append("_");
		html.append(qID);
		html.append("\" />");
		html.append(this.statement.substring(index + 10));
		
		return html.toString();
	}

	@Override
	public int getqID() {
		return qID;
	}

	@Override
	public String getCorrectAnswers() {
		StringBuilder correctAnswers = new StringBuilder();
		
		for(String s : answers) {
			correctAnswers.append(s);
			correctAnswers.append(" ");
		}
		
		return correctAnswers.toString();
	}

	public int getType(){
		return type;
	}
	
}
