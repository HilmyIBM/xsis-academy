package com.kel1.healthcare.healthcare_be.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kel1.healthcare.healthcare_be.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    //Hash SHA-256
	private static String stringToHex(String strInput) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashByte = digest.digest(strInput.getBytes(StandardCharsets.UTF_8));

	    StringBuilder hexString = new StringBuilder(2 * hashByte.length);
	    for (int i = 0; i < hashByte.length; i++) {
	        String hex = Integer.toHexString(0xff & hashByte[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}

	public List<Map<String, Object>> getAllNative() throws Exception {
        // TODO Auto-generated method stub
        return userRepo.findAllNative().get();   }
}
