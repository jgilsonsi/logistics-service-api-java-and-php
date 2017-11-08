package com.jjdev.ls.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jjdev.ls.model.entity.JUser;

public class JUserDAO {

	public JUser read(Connection conn, String username) throws SQLException {

		JUser user = null;

		String sql = "SELECT * FROM users WHERE username = ?;";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);

		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			user = new JUser();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
		}
		return user;
	}

}
