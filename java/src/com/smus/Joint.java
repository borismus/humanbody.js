package com.smus;

import processing.core.PVector;

public class Joint {
	private int jointId;
	private Body body;

	Joint(int jointId, Body body) {
		this.jointId = jointId;
		this.body = body;
	}
	
	public PVector getJointPosition() {
		PVector position = new PVector();
		this.body.context.getJointPositionSkeleton(1, this.jointId, position);
		return position;
	}

}
