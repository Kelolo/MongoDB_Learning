package com.userdbmanagement.mongodb.dao;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.userdbmanagement.mongodb.converter.UserConverter;
import com.userdbmanagement.mongodb.model.User;

public class UserDAO {
	
	private DBCollection coll;
	
	public UserDAO(MongoClient mongo) {
		this.coll = mongo.getDB("usersDB").getCollection("users");
	}
	
	public User createUser(User u) {
		DBObject doc = UserConverter.toDBObject(u);
		return u;
	}
}
