package com.jjdev.ls.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jjdev.ls.model.entity.JDelivery;

public class JDeliveryDAO {

	public void create(Connection conn, JDelivery delivery) throws SQLException {

		String sql = "INSERT INTO delivery (_order, user_id) VALUES (?, ?);";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, delivery.getOrder());
		pstm.setInt(2, delivery.getUserId());
		pstm.execute();
	}

	public JDelivery read(Connection conn, int order) throws SQLException {

		JDelivery delivery = null;

		String sql = "SELECT * FROM delivery WHERE _order = ?;";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, order);

		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			delivery = new JDelivery();
			delivery.setId(rs.getInt("id"));
			delivery.setOrder(rs.getInt("_order"));
			delivery.setReceiverName(rs.getString("receiver_name"));
			delivery.setReceiverCpf(rs.getString("receiver_cpf"));
			delivery.setDateTime(rs.getTimestamp("date_time"));
			delivery.setUserId(rs.getInt("user_id"));
		}
		return delivery;
	}

}
