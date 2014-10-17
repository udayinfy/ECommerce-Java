package com.appdynamicspilot.rest;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/ping")
public class Ping {

	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response ping(@Context HttpServletResponse response) throws IOException {
		response.setStatus(204);
		response.flushBuffer();
        return Response.noContent().build();
	}
}
	