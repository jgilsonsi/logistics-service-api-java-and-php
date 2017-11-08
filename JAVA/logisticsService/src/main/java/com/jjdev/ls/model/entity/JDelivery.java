package com.jjdev.ls.model.entity;

import java.util.Date;

public class JDelivery {

	private int id;
	private int order;
	private String receiverName;
	private String receiverCpf;
	private Date dateTime;
	private int userId;

	public JDelivery() {
	}

	public JDelivery(int id, int order, String receiverName, String receiverCpf, Date dateTime, int userId) {
		super();
		this.id = id;
		this.order = order;
		this.receiverName = receiverName;
		this.receiverCpf = receiverCpf;
		this.dateTime = dateTime;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverCpf() {
		return receiverCpf;
	}

	public void setReceiverCpf(String receiverCpf) {
		this.receiverCpf = receiverCpf;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
