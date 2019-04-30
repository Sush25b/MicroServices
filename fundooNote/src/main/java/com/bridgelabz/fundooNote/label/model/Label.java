package com.bridgelabz.fundooNote.label.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bridgelabz.fundooNotes.note.model.Note;
import com.bridgelabz.fundooNotes.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Label")
public class Label 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="label_id" )
	private Long labelId;
	
	@Column(name="label_title" )
	private String labelTitle;
	
//	@ManyToMany(mappedBy="Label",cascade = CascadeType.ALL)
//	@JsonIgnore
//	Set<Note> Note;
	
	//,
	
//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private User user;

//************************8********************
	@ManyToMany(mappedBy="label",cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JsonIgnore
	Set<Note> note;
	

//	@JoinTable(
//				name="note_label",
//				joinColumns=@JoinColumn(name="label_label_id"),
//				inverseJoinColumns=@JoinColumn(name="note_note_id")
//			  )
	
	
	
	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public String getLabelTitle() {
		return labelTitle;
	}

	public void setLabelTitle(String labelTitle) {
		this.labelTitle = labelTitle;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public Set<Note> getNote() {
		return note;
	}

	public void setNote(Set<Note> note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Label [labelId=" + labelId + ", labelTitle=" + labelTitle  + "]";
	}
}