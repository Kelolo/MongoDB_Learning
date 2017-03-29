package com.userdbmanagement.mongodb.converter;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.userdbmanagement.mongodb.model.User;

public class UserConverter {
	
	public static DBObject toDBObject(User u) {
		
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append("name", u.getName())
				.append("email", u.getEmail())
				.append("password", u.getPassword())
				;
		if (u.getId() != null) {
			builder = builder.append("_id",  new ObjectId(u.getId()));
		}
		
		return builder.get() ;
	}

	public static User toUser(DBObject doc) {
		// TODO Auto-generated method stub
		User u = new User();
		u.setEmail(doc.get("email").toString());
		u.setId(doc.get("_id").toString());
		u.setName(doc.get("name").toString());
		u.setPassword(doc.get("password").toString());
	
		return u;
	}

}
