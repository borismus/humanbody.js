package com.smus;

import SimpleOpenNI.SimpleOpenNI;

public class Body {
	Joint head = new Joint(SimpleOpenNI.SKEL_HEAD, this);
	Joint neck = new Joint(SimpleOpenNI.SKEL_NECK, this);
	Joint leftShoulder = new Joint(SimpleOpenNI.SKEL_LEFT_SHOULDER, this);
	Joint rightShoulder = new Joint(SimpleOpenNI.SKEL_RIGHT_SHOULDER, this);
	Joint leftElbow = new Joint(SimpleOpenNI.SKEL_LEFT_ELBOW, this);
	Joint rightElbow = new Joint(SimpleOpenNI.SKEL_RIGHT_ELBOW, this);
	Joint leftHand = new Joint(SimpleOpenNI.SKEL_LEFT_HAND, this);
	Joint rightHand = new Joint(SimpleOpenNI.SKEL_RIGHT_HAND, this);
	Joint torso = new Joint(SimpleOpenNI.SKEL_TORSO, this);
	Joint leftHip = new Joint(SimpleOpenNI.SKEL_LEFT_HIP, this);
	Joint rightHip = new Joint(SimpleOpenNI.SKEL_RIGHT_HIP, this);
	Joint leftKnee = new Joint(SimpleOpenNI.SKEL_LEFT_KNEE, this);
	Joint rightKnee = new Joint(SimpleOpenNI.SKEL_RIGHT_KNEE, this);
	Joint leftFoot = new Joint(SimpleOpenNI.SKEL_LEFT_FOOT, this);
	Joint rightFoot = new Joint(SimpleOpenNI.SKEL_RIGHT_FOOT, this);
	
	transient SimpleOpenNI context;
	
	Body(SimpleOpenNI context) {
		this.context = context;
	}
}
