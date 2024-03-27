package com.ToDoApp.model;

public class User {
	private int userId;
	private String firstName;
	private String email;
	private String passWord;
	
	public User(String firstName, String email, String password) {
		this.firstName = firstName;
		this.email = email;
		this.passWord = password;
	}
	
	public User(int userId, String firstName, String email, String password) {
		this.userId = userId;
		this.firstName = firstName;
		this.email = email;
		this.passWord = password;
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String password) {
		this.passWord = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", email=" + email + ", passWord=" + passWord
				+ "]";
	}

	

}
