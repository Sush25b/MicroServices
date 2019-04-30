package com.bridgelabz.fundooNote.label.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.bridgelabz.fundooNotes.label.dto.LabelDto;
import com.bridgelabz.fundooNotes.label.model.Label;
import com.bridgelabz.fundooNotes.note.dto.NoteDto;
import com.bridgelabz.fundooNotes.note.model.Note;
import com.bridgelabz.fundooNotes.user.response.UserResponse;

public interface LabelServices 
{
	public ResponseEntity<UserResponse> createLabel(@RequestBody LabelDto labelDto,@RequestHeader(value="jwtToken") String jwtToken); 
	public ResponseEntity<UserResponse> LabelCreate(@RequestParam String LabelTitle,@RequestHeader(value="jwtToken") String jwtToken); 
	
	public ResponseEntity<UserResponse> updateLabel(@RequestParam("labelId") Long labelId,@RequestBody LabelDto labelDto,@RequestHeader String token); // UserException2 
	
	public ResponseEntity<UserResponse> deleteLabelNote(@RequestParam Long noteId,@RequestParam("labelId") Long labelId,@RequestHeader String token);
	
	public Set<Label> getAllLabels(@RequestHeader(value="jwtToken") String token);

	public ResponseEntity<UserResponse> deleteLabelFromNote(@RequestParam Long labelId,@RequestHeader String token);

	public ResponseEntity<UserResponse> deleteLabelNote(Long labelId, String token);
	
	public ResponseEntity<UserResponse> addLabelToNote(Long labelId, String labelTitle, String token);

	public Set<Note> getLabelNote(Long labelId, String token);
	
	public ResponseEntity<UserResponse> deleteLabel(Long labelId, String token);

}
