package org.usfirst.frc.team1076.test.mock;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.strongback.mock.Mock;
import org.strongback.mock.MockMotor;
import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;

public class TestLeftRightMotors {

	private static final double EPSILON = 1E-10;
	MockMotor left = Mock.stoppedMotor(); 
	MockMotor right = Mock.stoppedMotor(); 
	LeftRightMotors leftRight = new LeftRightMotors(left, right); 
	
	@Before
	public void reset() {
		left.setSpeed(0); 
	    right.setSpeed(0);
	    leftRight.leftFactor = 1;
	    leftRight.rightFactor = 1;
	}
	
	@Test
	public void testSetLeftSpeed() {
		leftRight.setLeftSpeed(0.7);
		assertEquals("Left speed should match exactly when the leftFactor is 1",
				0.7, left.getSpeed(), EPSILON);
		
		leftRight.leftFactor = 0.5;
		leftRight.setLeftSpeed(0.7);
		assertEquals("Left speed should be scaled by leftFactor",
				0.7 * 0.5, left.getSpeed(), EPSILON);
	}

	@Test
	public void testSetRightSpeed() {
		leftRight.setRightSpeed(0.7);
		assertEquals("Right speed should match exactly when the rightFactor is 1",
				0.7, right.getSpeed(), EPSILON);
		
		leftRight.rightFactor = 0.5;
		leftRight.setRightSpeed(0.7);
		assertEquals("Right speed should be scaled by rightFactor",
				0.7 * 0.5, right.getSpeed(), EPSILON);
	}

	@Test
	public void testSetSpeed() {
		leftRight.setSpeed(1.0);
		assertEquals("Left and right motor speeds should be equal",
				left.getSpeed(), right.getSpeed(), EPSILON);
		
		leftRight.leftFactor = 0.6;
		leftRight.rightFactor = 0.4;
		leftRight.setSpeed(1.0);
		assertEquals("Left motor speeds should scale with leftFactor independent of rightFactor",
				0.6, left.getSpeed(), EPSILON);
		assertEquals("Right motor speeds should scale with rightFactor independent of leftFactor",
				0.4, right.getSpeed(), EPSILON);
	}

	@Test
	public void testStop() {
		leftRight.setSpeed(1.0);
		assertTrue("Motors should not be stopped before stopping",
				left.getSpeed() > 0 && right.getSpeed() > 0);
		leftRight.stop();
		assertEquals("Left motor should be stopped",
				0.0, left.getSpeed(), EPSILON);
		assertEquals("Right motor should be stopped",
				0.0, right.getSpeed(), EPSILON);
	}

}
