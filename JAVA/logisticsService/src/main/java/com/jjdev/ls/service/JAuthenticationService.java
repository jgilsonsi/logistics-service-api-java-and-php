package com.jjdev.ls.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.Base64;
import java.util.StringTokenizer;

import org.mindrot.jbcrypt.BCrypt;

import com.jjdev.ls.model.JConnectionFactory;
import com.jjdev.ls.model.dao.JUserDAO;
import com.jjdev.ls.model.entity.JUser;

public class JAuthenticationService {

	public boolean authenticate(String authCredentials) {
		if (null == authCredentials)
			return false;

		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		
		boolean authenticationStatus = false;
		try {
			Connection conn = JConnectionFactory.getInstance().getConnection();

			JUserDAO userDAO = new JUserDAO();
			JUser user = userDAO.read(conn, username);

			if(user != null && user.getUsername().equals(username) && BCrypt.checkpw(password, user.getPassword().replace("$2y$","$2a$"))){
				authenticationStatus = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR" + e);
		}		
		return authenticationStatus;
	}
}