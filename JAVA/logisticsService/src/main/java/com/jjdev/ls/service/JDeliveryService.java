package com.jjdev.ls.service;

import java.sql.Connection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jjdev.ls.model.JConnectionFactory;
import com.jjdev.ls.model.dao.JDeliveryDAO;
import com.jjdev.ls.model.entity.JDelivery;

@Path("/api/v1/delivery")
public class JDeliveryService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(JDelivery delivery) {
		try {
			Connection conn = JConnectionFactory.getInstance().getConnection();

			JDeliveryDAO deliveryDAO = new JDeliveryDAO();
			deliveryDAO.create(conn, delivery);

			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/order/{order}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readByOrder(@PathParam("order") int order) {
		try {
			Connection conn = JConnectionFactory.getInstance().getConnection();

			JDeliveryDAO deliveryDAO = new JDeliveryDAO();
			JDelivery delivery = deliveryDAO.read(conn, order);

			return Response.status(Response.Status.OK).entity(delivery).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
