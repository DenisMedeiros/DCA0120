package dca0120.utils;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
	
	 public static String plainToSHA256(String passwordToHash, byte[] salt) {
	        String generatedPassword = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            md.update(salt);
	            byte[] bytes = md.digest(passwordToHash.getBytes());
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            generatedPassword = sb.toString();
	        } 
	        catch (NoSuchAlgorithmException e) 
	        {
	            e.printStackTrace();
	        }
	        return generatedPassword;
	 }
	 
	   private static byte[] createChecksum(InputStream is) throws Exception {

	       byte[] buffer = new byte[1024];
	       MessageDigest complete = MessageDigest.getInstance("MD5");
	       int numRead;

	       do {
	           numRead = is.read(buffer);
	           if (numRead > 0) {
	               complete.update(buffer, 0, numRead);
	           }
	       } while (numRead != -1);

	       return complete.digest();
	   }
	   
	   public static String getMD5Checksum(InputStream is) throws Exception {
	       byte[] b = createChecksum(is);
	       String result = "";

	       for (int i=0; i < b.length; i++) {
	           result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
	       }
	       
	       return result;
	   }
}


