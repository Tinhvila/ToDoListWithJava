package com.ToDoApp.model;

public class Todolist {
	private int id;
	private int userID;
	private String task;
	private int status;
	
	public Todolist(int id, int userID, String task, int status) {
		this.id = id;
		this.userID = userID;
		this.task = task;
		this.status = status;
	}
	
	public Todolist(int userID, String task, int status) {
		this.userID = userID;
		this.task = task;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Todolist [id=" + id + ", userID=" + userID + ", task=" + task + ", status=" + status + "]";
	}
	
}
