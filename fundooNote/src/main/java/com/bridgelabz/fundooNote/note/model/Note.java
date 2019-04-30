package com.bridgelabz.fundooNote.note.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;


import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bridgelabz.fundooNote.label.model.Label;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Note")
public class Note
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="noteId" )
	private Long noteId;
	
	@Column(name ="title" )
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "is_pinned")
	private boolean isPinned;
	
	@Column(name = "is_trash")
	private boolean isTrash;
	
	@Column(name= "is_archive")
	private boolean isArchive;
	
	@Column(name = "reminder")
	private String reminder;
	
	@Column(name = "created_time")
	private LocalDateTime createdTime;
	
	@Column(name = "lastupdate_time")
	private LocalDateTime lastUpdateTime;
	
	@Column(name ="userId")
	private Long userId;
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private Set<Label> label;

//	@JoinTable(
//				name="note_label",
//				joinColumns=@JoinColumn(name="note_note_id"),
//				inverseJoinColumns=@JoinColumn(name="label_label_id")
//			  )
	
	
//	@ManyToMany(cascade=CascadeType.ALL)
//	List<Label> label;
	
//	@JsonIgnore
//	@ManyToMany(cascade = CascadeType.ALL)
//	private Set<Label> Label;
	
//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private User user;
	
//************************8********************
	
	public Long getNoteId() {
		return noteId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

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

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}

	public boolean isArchive() {
		return isArchive;
	}

	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", title=" + title + ", description=" + description + ", color=" + color
				+ ", isPinned=" + isPinned + ", isTrash=" + isTrash + ", isArchive=" + isArchive + ", reminder="
				+ reminder + ", createdTime=" + createdTime + ", lastUpdateTime=" + lastUpdateTime + ", userId="
				+ userId + ", label=" + label + "]";
	}
	
}



