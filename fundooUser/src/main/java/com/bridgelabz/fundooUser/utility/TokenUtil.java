package com.bridgelabz.fundooUser.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

public class TokenUtil 
{
	public static final String TOKEN_SECRET = "s4T2zOIWHMM1sxq";
	
	/**
	   * create token
	 * @param id
	 * @return token
	 */
	public static String createToken(Long id)
	{
			try 
			{
				// to set algorithm
				Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
	
				// payload
				String token = JWT.create().withClaim("userId", id).sign(algorithm);
				return token;
			}
			catch (JWTCreationException exception) 
			{
				exception.printStackTrace();
				// log Token Signing Failed
			}
			return null;
	}
	
	public static Long decodeToken(String token)
	{
//			try 
//			{
//				System.out.println(token);
//				Long userid;
//				
//				// to set algorithm
//	            Verification verification=JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
//	            JWTVerifier jwtverifier=verification.build();
//	            
//	            //to decode token
//	            DecodedJWT decodedjwt= JWT.decode(token);
//	        
//	            //retrive data
//	            Claim claim=decodedjwt.getClaim("userid");
//	            System.out.println(claim);
//	            userid=claim.asLong();
//	            
//	            System.out.println(userid);
//	            return userid;
//			    
//			} 
//			catch (JWTDecodeException exception)
//			{
//			    //Invalid token
//			}
//			return null;

		  Long userid;
	            //for verification algorithm
	            Verification verification=JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
	            JWTVerifier jwtverifier=verification.build();
	            //to decode token
	            DecodedJWT decodedjwt=jwtverifier.verify(token);
	            //retrive data
	            Claim claim=decodedjwt.getClaim("userId");
	            userid=claim.asLong();  
	            //System.out.println(userid);
	            return userid;

	}
}
