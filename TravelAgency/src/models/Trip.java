package models;

import java.io.Serializable;

public class Trip implements Serializable{
	private int id;
	private String title;
	private int capacity;
	private int occupation;
	
	public Trip (){
		super();
	}
	
	
	
	public Trip(int id, String title, int capacity, int occupation) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.occupation = occupation;
		this.title = title;
	}
	
	public Trip(String title, int capacity, int occupation) {
		super();
		this.capacity = capacity;
		this.occupation = occupation;
		this.title = title;
	}



	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getOccupation() {
		return occupation;
	}
	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}
	
	



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return title + ", capacity=" + capacity + ", occupation=" + occupation;
	}
	
	
}
