package com.libraryevent.kafkademo.domain;

public class Customer {

	
	private int custId;
	public int getCustId() {
		return custId;
	}



	public void setCustId(int custId) {
		this.custId = custId;
	}
	private String first_name;
	private String last_name;
	private int age;
	private float height;
	private float weight;
    private boolean automated_email;
    
    
    
    
    
	public Customer() {
		
	}
	
	
	
	public Customer(String first_name, String last_name, int age, float height, float weight, boolean automated_email,int custId) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.automated_email = automated_email;
		this.custId=custId;
	}



	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public boolean isAutomated_email() {
		return automated_email;
	}
	public void setAutomated_email(boolean automated_email) {
		this.automated_email = automated_email;
	}
    
    
    
}