package com.userdbmanagement.mongodb.dao;



import java.util.List;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.userdbmanagement.mongodb.converter.UserConverter;
import com.userdbmanagement.mongodb.model.User;

public class UserDAO {
	
	private DBCollection coll;
	
//	@SuppressWarnings("deprecation")
	public UserDAO(MongoClient mongo) {
		this.coll = mongo.getDB("usersDB").getCollection("users");
	}
	
	public User createUser(User u) {
		DBObject doc = UserConverter.toDBObject(u);
		this.coll.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		u.setId(id.toString());
		return u;
	}

	public List<User> readAllUsers() {
		// TODO Auto-generated method stub
		List<User> data = new ArrayList<>();
		DBCursor cursor = coll.find();			// pointer points to each doc in collection
		while(cursor.hasNext()) {
			DBObject doc = cursor.next();
			User u = UserConverter.toUser(doc);
			data.add(u);
		}
		return data;
	}
}
