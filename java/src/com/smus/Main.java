package com.smus;

import java.net.InetSocketAddress;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import SimpleOpenNI.*;
import processing.core.*;

public class Main extends PApplet {

	public SimpleOpenNI context;
	public GestureWebSocketServer server;

	public void setup()
	{
		InetSocketAddress addr = new InetSocketAddress("localhost", 1357);
		server = new GestureWebSocketServer(addr);
		server.start();
		
		context = new SimpleOpenNI(this,SimpleOpenNI.RUN_MODE_MULTI_THREADED);

		// enable depthMap generation
		context.enableDepth();

		// enable skeleton generation for all joints
		context.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL);

		background(200,0,0);

		stroke(0,0,255);
		strokeWeight(3);
		smooth();

		size(context.depthWidth(), context.depthHeight());
	}

	public void draw()
	{
		// update the cam
		context.update();

		// draw depthImageMap
		image(context.depthImage(),0,0);

		// draw the skeleton if it's available
		if(context.isTrackingSkeleton(1)) {
			drawSkeleton(1);
			sendJoints();
		}
	}
	
	/**
	 * Broadcasts join information serialized in JSON form via the websocket server.
	 * @param userId
	 */
	public void sendJoints() {
		// Compute all of the relevant joint positions.
		Body body = new Body(context);
		// Generate the JSON for the data to broadcast.
		GsonBuilder gson = new GsonBuilder();
		gson.registerTypeAdapter(Joint.class, new JointSerializer());
		String json = gson.create().toJson(body);  
		// Broadcast the data through the websocket server.
		try {
			server.sendToAll(json);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// draw the skeleton with the selected joints
	public void drawSkeleton(int userId)
	{
		context.drawLimb(userId, SimpleOpenNI.SKEL_HEAD, SimpleOpenNI.SKEL_NECK);

		context.drawLimb(userId, SimpleOpenNI.SKEL_NECK, SimpleOpenNI.SKEL_LEFT_SHOULDER);
		context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER, SimpleOpenNI.SKEL_LEFT_ELBOW);
		context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_ELBOW, SimpleOpenNI.SKEL_LEFT_HAND);

		context.drawLimb(userId, SimpleOpenNI.SKEL_NECK, SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER, SimpleOpenNI.SKEL_RIGHT_ELBOW);
		context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_ELBOW, SimpleOpenNI.SKEL_RIGHT_HAND);

		context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER, SimpleOpenNI.SKEL_TORSO);
		context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER, SimpleOpenNI.SKEL_TORSO);

		context.drawLimb(userId, SimpleOpenNI.SKEL_TORSO, SimpleOpenNI.SKEL_LEFT_HIP);
		context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_HIP, SimpleOpenNI.SKEL_LEFT_KNEE);
		context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_KNEE, SimpleOpenNI.SKEL_LEFT_FOOT);

		context.drawLimb(userId, SimpleOpenNI.SKEL_TORSO, SimpleOpenNI.SKEL_RIGHT_HIP);
		context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_HIP, SimpleOpenNI.SKEL_RIGHT_KNEE);
		context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_KNEE, SimpleOpenNI.SKEL_RIGHT_FOOT);  
	}

	// -----------------------------------------------------------------
	// SimpleOpenNI events

	public void onNewUser(int userId)
	{
		println("onNewUser - userId: " + userId);
		println("  start pose detection");

		context.requestCalibrationSkeleton(userId, true);
	}

	public void onLostUser(int userId)
	{
		println("onLostUser - userId: " + userId);
	}

	public void onStartCalibration(int userId)
	{
		println("onStartCalibration - userId: " + userId);
	}

	public void onEndCalibration(int userId, boolean successfull)
	{
		println("onEndCalibration - userId: " + userId + ", successfull: " + successfull);

		if (successfull)
		{
			println("  User calibrated !!!");
			context.startTrackingSkeleton(userId);
		}
		else
		{
			println("  Failed to calibrate user !!!");
			println("  Start pose detection");
			context.startPoseDetection("Psi",userId);
		}
	}

	public void onStartPose(String pose,int userId)
	{
		println("onStartPose - userId: " + userId + ", pose: " + pose);
		println(" stop pose detection");

		context.stopPoseDetection(userId);
		context.requestCalibrationSkeleton(userId, true);

	}

	public void onEndPose(String pose,int userId)
	{
		println("onEndPose - userId: " + userId + ", pose: " + pose);
	}
}
