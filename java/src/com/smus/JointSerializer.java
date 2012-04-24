package com.smus;

import java.lang.reflect.Type;

import processing.core.PVector;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JointSerializer implements JsonSerializer<Joint> {

	@Override
	public JsonElement serialize(Joint joint, Type type,
			JsonSerializationContext context) {
		JsonObject jo = new JsonObject();
		PVector coords = joint.getJointPosition();
		jo.addProperty("x", coords.x);
		jo.addProperty("y", coords.y);
		jo.addProperty("z", coords.z);
		return jo;
	}

}
