package com.bridgelabz.fundooNotes.user.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundooNotes.user.dto.LoginDto;
import com.bridgelabz.fundooNotes.user.dto.UserDto;
import com.bridgelabz.fundooNotes.user.exception.UserException;
import com.bridgelabz.fundooNotes.user.exception.UserException2;
import com.bridgelabz.fundooNotes.user.model.User;
import com.bridgelabz.fundooNotes.user.response.UserResp;
import com.bridgelabz.fundooNotes.user.response.UserResponse;
import com.bridgelabz.fundooNotes.user.service.UserService;
import com.bridgelabz.fundooNotes.user.service.UserServicesImpl;
import com.bridgelabz.fundooNotes.utility.TokenUtil;

@RestController
@RequestMapping("/fundooNotes")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*",exposedHeaders={"jwtTokens"})
public class UserRestController
{
	@Autowired
	private UserService userservice;

	@Autowired(required=true) 
	UserServicesImpl userServices;
	
	private final Path pathlocation = Paths.get("/home/admin1/Documents/sushantproject/fundooNotes/src/assets/profilepic");
	

	@GetMapping("/usersget")
	public List<User> findAll() 
	{
		return userservice.findAll();
	}

	@PostMapping("/users")
	public UserDto addUser(@RequestBody UserDto userDTO) 
	{
		// this to save of new item ... instead of update
		System.out.println("User  : "+userDTO);

		userservice.save(userDTO);	

		return userDTO;
	}
	
	//, HttpServletResponse  response
	//successfully
	@PostMapping("/login") 
	public ResponseEntity<UserResp> Login(@RequestBody LoginDto loginDto) // throws UnsupportedEncodingException
	{ 		
		System.out.println("a");
		System.out.println(loginDto);
		return userServices.onLogin(loginDto); 
	}

	//successfully
	@PostMapping("/register") 
	public ResponseEntity<UserResponse> registerUser(@RequestBody UserDto userDtO) // UserException2 
	{ 
		return userServices.onRegister(userDtO); 
	}
	
	  //successfully
	  @PostMapping(value="/forgetpassword") 
	 // public ResponseEntity<UserResponse> forgotPassword(@RequestBody String emailid) //throws UnsupportedEncodingException 
	  public ResponseEntity<UserResponse> forgotPassword(@RequestBody UserDto userDto) //throws UnsupportedEncodingException 
	  {
		  System.out.println(userDto+"@@@@@@@@@@@");
		 return userServices.forgetPassword(userDto);
	  }
	 
	  //successfully
	  @GetMapping(value="user/resetPassword/{token}") 
	  public ResponseEntity<UserResponse> resetPassword(@PathVariable("token") String token,@RequestParam("password") String password) throws UnsupportedEncodingException
	  {
		  	System.out.println("token:"+token+" ,  password:"+password); 
	  
	  		return userServices.resetPassword(token,password);
	  }
	  
	  //successfully
	  @GetMapping(value="user/{token}/validate") 
	  public ResponseEntity<UserResponse> validEmail(@PathVariable("token") String token) //throws UnsupportedEncodingException
	  {
		  System.out.println("token "+token); 
		  
		  	return  userServices.validEmail(token); 
	  }
	  
	  //wrong
	  @GetMapping(value="user/profilepic")
	  public ResponseEntity<UserResponse> setProfilePic(@RequestParam MultipartFile picture,@RequestHeader(value="jwtToken") String jwtToken) 
	  {
		  System.out.println("#####"+picture);
		  
		return  userServices.setProfilePic(jwtToken,picture);
	  }
	  
	  @RequestMapping(value = "user/profileupload", method = RequestMethod.PUT,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	  public ResponseEntity<UserResponse> imageUploads(@RequestHeader(value="jwtToken") String token,@RequestParam("File") MultipartFile file) throws IOException 
	  {
		  System.out.println("77777");
		  System.out.println(file);
			UUID uuid = UUID.randomUUID();
			
			String uuidString = uuid.toString();
			System.out.println(uuidString);
			
					//File convertFile=new File(this.pathlocation+"/"+file.getOriginalFilename());
					//System.out.println(convertFile);
			        // convertFile.createNewFile();
					//FileOutputStream fout=new FileOutputStream(convertFile);
			     	//	fout.write(file.getBytes());
					//fout.close();
			 Files.copy(file.getInputStream(), this.pathlocation.resolve(uuidString) ,StandardCopyOption.REPLACE_EXISTING);
			return userServices.imageUpload(token,uuid.toString());
	  } 
		
	
	   @GetMapping("/user/getProfile/{token}")
	   public Resource getImageAll(@PathVariable("token") String token) 
	   {
	    	
			// encode user
			Long userid = TokenUtil.decodeToken(token);
			
	    	String filename=userServices.getImage(userid);
	    	System.out.println(filename);
	    	
	    	Path file = this.pathlocation.resolve(filename);
	    	System.out.println(file);
	        
	    	try {
	          
	            Resource resource = new UrlResource(file.toUri());
	            
	            if (resource.exists() || resource.isReadable()) 
	            {
	                return resource;
	            } 
	            else 
	            {
	                throw new UserException( "Could not read file: " + filename);
	
	            }
	        } 
	    	catch (MalformedURLException e)
	    	{
	    		throw new UserException( "Could not read file: " + filename);
	        }
	    }
   

	  
	 
	// add mapping for GET
		/*
		 * @GetMapping("/users/{employeeId}") public Employee getEmployee(@PathVariable
		 * int employeeId) {
		 * 
		 * Employee theEmployee = UserService.findById(employeeId);
		 * 
		 * if (theEmployee == null) { throw new
		 * RuntimeException("Employee id not found - " + employeeId); }
		 * 
		 * return theEmployee; }
		 */
		// add mapping for POST /employees - add new employee

		// add mapping for PUT /Users - update existing User

		/*
		 * @PutMapping("/usersupdate") public UserDto updateUser(@RequestBody UserDto
		 * userDTO) { userservice.save(userDTO);
		 * 
		 * return userDTO; }
		 * 
		 * // add mapping for DELETE /Users/{UserId} - delete User
		 * 
		 * //successfully
		 * 
		 * @DeleteMapping("/usersdelete/{userid}") public String
		 * deleteUser(@PathVariable Long userid) { User tempUser =
		 * userservice.findById(userid);
		 * 
		 * // throw exception if null if (tempUser == null) //no need of checking it
		 * again { System.out.println("invalid user"); }
		 * 
		 * userservice.deleteById(userid);
		 * 
		 * return "Deleted User id - " + userid; }
		 */
}
