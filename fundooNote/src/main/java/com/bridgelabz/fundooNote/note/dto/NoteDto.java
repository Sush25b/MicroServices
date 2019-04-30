package com.bridgelabz.fundooNote.note.dto;

import javax.persistence.Column;

public class NoteDto 
{
	private String title;

	private String description;

	private String color;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "NoteDto [title=" + title + ", description=" + description + ", color=" + color + "]";
	}
	
}
