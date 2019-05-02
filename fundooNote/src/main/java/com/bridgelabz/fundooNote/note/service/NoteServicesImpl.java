package com.bridgelabz.fundooNote.note.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooNote.exception.UserException;
import com.bridgelabz.fundooNote.note.dto.NoteDto;
import com.bridgelabz.fundooNote.note.model.Note;
import com.bridgelabz.fundooNote.note.repository.NoteRepository;
import com.bridgelabz.fundooNote.response.ResponseSender;
import com.bridgelabz.fundooNote.response.UserResponse;
import com.bridgelabz.fundooNote.utility.TokenUtil;


@PropertySource("classpath:message.properties")
@Service
public class NoteServicesImpl implements NoteServices
{

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	Environment environment;

	@Autowired
	NoteRepository noteRepository;

	/**
	 * to get current date and time
	 */
	LocalDateTime currDateTime = LocalDateTime.now();
//	
//	DateFormat dateandtime =new SimpleDateFormat("yyyy-MM-dd HH:mm")
//	

	/**
	 * Create Note
	 */
	public ResponseEntity<UserResponse> createNote(NoteDto noteDto, String token)
	{
		Note note = modelMapper.map(noteDto, Note.class);

		Long userId = TokenUtil.decodeToken(token);
		//User user = userRepository.findById(userId).orElseThrow(() -> new UserException(environment.getProperty("user.search")));

		if(userId!=null)
		{
				note.setCreatedTime(currDateTime);
				note.setLastUpdateTime(currDateTime);
				note.setUserId(userId);
		}
		else
		{
			System.out.println("user not found");
		}
		
		noteRepository.save(note);

		return ResponseSender.sendUserResponse("Note is created", 200);
	}

	/**
	 * trash note
	 * @throws UserException 
	 */
	public ResponseEntity<UserResponse> trashNote(Long noteId, String token) 
	{
		//the note is not going to DELETE 
		//in Note===>table the (column--->trash is going to be set as TRUE/False)
		System.out.println(noteId+" "+token);
		Long userid = TokenUtil.decodeToken(token);

		//get particular user--->from database
		//User user = userRepository.findById(userid).orElseThrow( () -> new UserException(environment.getProperty("user.search")) );

		//get all notes-->of that user
		List<Note> notes=noteRepository.findAll();
		
		//from the Notes List-->filter to get particular note by noteid
		Note filteredNote = notes.stream().filter(data -> data.getNoteId().equals(noteId)).findFirst().orElseThrow(() -> new UserException(environment.getProperty("user.note")));

		//check particular note Pinned STATUS  
		if(filteredNote.isTrash()==true)
		{
			filteredNote.setTrash(false);
			filteredNote.setLastUpdateTime(currDateTime);    
			//add the changed note to-->User Notes List
			noteRepository.save(filteredNote);
			return ResponseSender.sendUserResponse("Note is untrashed",200);
		}
		else
		{
			filteredNote.setTrash(true);
			filteredNote.setLastUpdateTime(currDateTime);    
			//add the changed note to-->User Notes List
			noteRepository.save(filteredNote);
			return ResponseSender.sendUserResponse("Note moved to trash",200);
		}
	}

	//Read //get
	/**
	 * Read notes
	 */
	public List<Note> getUserNote(String token) 
	{
		Long userId = TokenUtil.decodeToken(token);
//		User user = userRepository.findById(userId).orElseThrow(() -> new UserException("invalid user"));
//		List<Note> userNote = user.getNote();

		//get all notes-->of that user
		List<Note> notes=noteRepository.findAll();
				
		//from the Notes List-->filter to get particular note by noteid
		List<Note> filteredNote = notes.stream().filter(data -> data.getUserId().equals(userId)).collect(Collectors.toList());

		return filteredNote;
	}

	
	//Read //get
	/**
	 * Read notes
	 */
	public List<Note> getUserNotes(String token,boolean trash,boolean archive) 
	{

		Long userId = TokenUtil.decodeToken(token);
		
		//get all notes-->of that user
		List<Note> notes=noteRepository.findAll();
				
		//from the Notes List-->filter to get particular note by noteid
		List<Note> filteredNote = notes.stream().filter(data -> data.getUserId().equals(userId)).collect(Collectors.toList());

		List<Note> userNote = filteredNote.stream().filter(data -> (data.isTrash()==trash && data.isArchive()==archive)).collect(Collectors.toList()); 
		
		return userNote;
	}


	//update
	/**
	 * Update notes
	 */
	@Override
	public ResponseEntity<UserResponse> updateNote(Long noteId,NoteDto noteDto, String token)
	{
		System.out.println(noteId+" "+noteDto+" "+token);
		Long userid = TokenUtil.decodeToken(token);

		//get all notes-->of that user
	    List<Note> notes=noteRepository.findAll();
					
       //from the Notes List-->filter to get particular note by noteid
	   List<Note> filterNote = notes.stream().filter(data -> data.getUserId().equals(userid)).collect(Collectors.toList());

		//from the Notes List-->we filter to get particular noteid
		Note filteredNote = filterNote.stream().filter(data -> data.getNoteId().equals(noteId)).findFirst().orElseThrow(() -> new UserException(0, environment.getProperty("user.note")));
		filteredNote.setTitle(noteDto.getTitle());
		filteredNote.setDescription(noteDto.getDescription());
		filteredNote.setColor(noteDto.getColor());
		filteredNote.setLastUpdateTime(currDateTime);    
		
		System.out.println(filteredNote);
		//add the new note to-->User Notes List

		noteRepository.save(filteredNote);
		
		return ResponseSender.sendUserResponse("note updated successfully", 200);
	}

	
	//ispinned
	/**
	 * pin notes
	 */
	@Override
	public ResponseEntity<UserResponse> isPinned(Long noteId, String token)
	{
		System.out.println(noteId+" "+token);
		Long userid = TokenUtil.decodeToken(token);

		//get all notes-->of that user
	    List<Note> notes=noteRepository.findAll();
					
       //from the Notes List-->filter to get particular note by noteid
	   List<Note> filterNote = notes.stream().filter(data -> data.getUserId().equals(userid)).collect(Collectors.toList());

		//from the Notes List-->we filter to get particular noteid
		Note filteredNote = filterNote.stream().filter(data -> data.getNoteId().equals(noteId)).findFirst().orElseThrow(() -> new UserException(0, environment.getProperty("user.note")));
		
		//check note Pinned STATUS  
		if(filteredNote.isPinned()==true)
		{
			filteredNote.setPinned(false);
			filteredNote.setLastUpdateTime(currDateTime);    
			//add the changed note to-->User Notes List
			
			noteRepository.save(filteredNote);
			
			return ResponseSender.sendUserResponse("note unpinned successfully", 200);
		}
		else
		{	filteredNote.setPinned(true);
			filteredNote.setLastUpdateTime(currDateTime);    
			//add the changed note to-->User Notes List
			noteRepository.save(filteredNote);
			
			return ResponseSender.sendUserResponse("note pinned successfully", 200);
		}

//		user.setNote(notes);
//		userRepository.save(user);

//		return ResponseSender.sendUserResponse("note pinned successfully", 200);
	}


	//isArchieve
	/**
	 * Archive notes
	 */
	@Override
	public ResponseEntity<UserResponse> isArchieve(Long noteId, String token)
	{
		System.out.println(noteId+" "+token);
		Long userid = TokenUtil.decodeToken(token);

		//get all notes-->of that user
	    List<Note> notes=noteRepository.findAll();
					
	       //from the Notes List-->filter to get particular note by noteid
		   List<Note> filterNote = notes.stream().filter(data -> data.getUserId().equals(userid)).collect(Collectors.toList());

		//from the Notes List-->we filter to get particular noteid
		Note filteredNote = filterNote.stream().filter(data -> data.getNoteId().equals(noteId)).findFirst().orElseThrow(() -> new UserException(0, environment.getProperty("user.note")));
		
		//check note Archieve STATUS  
		if(filteredNote.isArchive()==true)
		{
			filteredNote.setArchive(false);
			filteredNote.setLastUpdateTime(currDateTime);    
			//add the changed note to-->User Notes List
			noteRepository.save(filteredNote);
			
			return ResponseSender.sendUserResponse("note unarchive successfully", 200);
		}
		else
		{
			filteredNote.setArchive(true);
			filteredNote.setLastUpdateTime(currDateTime);   
			//add the changed note to-->User Notes List
			noteRepository.save(filteredNote);
			
			return ResponseSender.sendUserResponse("note archive successfully", 200);
		}
	}

	//wrong
	//addReminder
		/**
		 * set reminder of a  notes
		 */
		@Override
		public ResponseEntity<UserResponse> addreminder(Long noteId,String reminder, String token)
		{
			return null;
		}
	
	
	//delete
	/**
	 * delete note
	 */
	@Override
	public ResponseEntity<UserResponse> deleteNote(Long noteId, String token) 
	{
		System.out.println(noteId+" "+token);
		Long userid = TokenUtil.decodeToken(token);

		//get particular user--->from database
		
		//get all notes-->of that user
	    List<Note> notes=noteRepository.findAll();
					
	       //from the Notes List-->filter to get particular note by noteid
		   List<Note> filterNote = notes.stream().filter(data -> data.getUserId().equals(userid)).collect(Collectors.toList());

		//from the Notes List-->we filter to get particular noteid
		Note filteredNote = filterNote.stream().filter(data -> data.getNoteId().equals(noteId)).findFirst().orElseThrow(() -> new UserException(0, environment.getProperty("user.note")));
		
		if(filteredNote.getNoteId()==noteId)
		{
			noteRepository.deleteById(noteId);
		}
//		List<Note> notes = user.getNote();
//
//		//for loop to search note
//		for(Note n:notes)
//		{
//			if(n.getNoteId()==noteId )
//			{
//				notes.remove(noteId);
//				System.out.println(notes);
//				System.out.println("note:"+" "+noteId+" "+"successfully delted");
//			}
//			else
//			{
//				
//			}
//		}
//		
//		user.setNote(notes);
//		userRepository.save(user);

		//				Optional<Note> notePresent=noteRepository.findById(noteId);
		//				
		//				if (notePresent.isPresent())
		//				{
		//					notes.remove(noteId);
		//					System.out.println("note:"+" "+noteId+" "+"successfully delted");
		//				}
		//				else
		//					System.out.println("note:"+" "+noteId+" "+" not successfully delted");

		return ResponseSender.sendUserResponse("note deleted successfully", 200);
	}

//	@Override
//	public ResponseEntity<UserResponse> addReminder(Long noteid, String reminder, String jwtToken) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	/**
	 * To add reminder to a particular note 
	 */
	public ResponseEntity<UserResponse> addReminder(Long noteid, String reminder, String token)
	{
		return null;
//		Long userid = TokenUtil.decodeToken(token);
//		User user = userRepository.findById(userid).orElseThrow(() -> new UserException(404, "userid not found"));
//		
//		
//		DateFormat dateAndTimeFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss");
//		Date date = null;
//		try {
//			date = dateAndTimeFormat.parse(reminder);
//			System.out.println(date);
//			SimpleDateFormat sdf=new SimpleDateFormat("MMM-dd HH:mm a");
//			
//			Note note = user.getNote().stream().filter(data ->data.getNoteId().equals(noteid)).findFirst().get();
//
//			note.setReminder(sdf.format(date));
//			//note.setReminder(reminder);
//			System.out.println(date);
//			
//			note.setLastUpdateTime(LocalDateTime.now());
//			user.getNote().add(note);
//			userRepository.save(user);
//			return ResponseSender.sendUserResponse("reminder successfully", 200);
//			
//		}
//		catch (ParseException e) 
//		{
//			e.printStackTrace();
//		}
//		return ResponseSender.sendUserResponse("reminder not added", 200);
		
	}
	
	
	public ResponseEntity<UserResponse> deleteReminder( Long noteid,String token) 
	{
		System.out.println(token+" "+noteid);
		Long userid = TokenUtil.decodeToken(token);
		
		//get all notes-->of that user
	    List<Note> notes=noteRepository.findAll();
					
	       //from the Notes List-->filter to get particular note by noteid
		   List<Note> filterNote = notes.stream().filter(data -> data.getUserId().equals(userid)).collect(Collectors.toList());
		
			Note reminderNote = filterNote.stream().filter(data ->data.getNoteId().equals(noteid)).findFirst().orElseThrow(()-> new UserException(404, "note not found"));
				
			    reminderNote.setReminder(null);
				reminderNote.setLastUpdateTime(LocalDateTime.now());
				
				noteRepository.save(reminderNote);
				System.out.println("done");
				return ResponseSender.sendUserResponse("reminder deleted", 200);
	}
}





