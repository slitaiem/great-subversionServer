package com.pfe.serrvice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.pfe.buisiness.ServiceDAO;
import com.pfe.buisiness.ServiceDAORemote;
import com.pfe.buisiness.UserDAO;
import com.pfe.buisiness.UserDAORemote;
import com.pfe.domain.PrivateService;
import com.pfe.domain.Profile;
import com.pfe.domain.PublicService;
import com.pfe.domain.Service;
import com.pfe.domain.User;
import com.pfe.domain.UserInfo;

@Path("/ClientServiceWS")
@Stateless
public class ClientServiceWS {
	@EJB
	UserDAO userDAO;
	@EJB
	ServiceDAO serviceDAO;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/authenticateUser/")
	public UserInfo login(@QueryParam("login") String login, @QueryParam("password") String password) {
		User userOriginal = userDAO.loginUser(login, password);
		UserInfo userInfos = new UserInfo();
		
		if(userOriginal != null){
			userInfos = userOriginal.deepCopy();	
			
		}
		// TODO Auto-generated method stub
		return userInfos;

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllServices/{userId}")
	public List<Object> getAllServices(@PathParam("userId") String userId) {
		// TODO Auto-generated method stub
		List<Object> allServices = new ArrayList<Object>();
		allServices = serviceDAO.getAllServices(userId);
		return allServices;
	}

}
