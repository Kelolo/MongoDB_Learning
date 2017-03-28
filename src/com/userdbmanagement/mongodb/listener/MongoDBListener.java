package com.userdbmanagement.mongodb.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mongodb.MongoClient;

@WebListener
public class MongoDBListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext ctx = sce.getServletContext();
		MongoClient mongo = new MongoClient(ctx.getInitParameter("MONGO_HOST"), Integer.parseInt(ctx.getInitParameter("MONGO_PORT")));
		sce.getServletContext().setAttribute("MONGO CLIENT", mongo);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		MongoClient mongo = (MongoClient) sce.getServletContext().getAttribute("MONGO CLIENT");
		mongo.close();
	}
	
}
