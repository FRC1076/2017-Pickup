package org.usfirst.frc.team1076.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.strongback.components.Motor;
import org.strongback.mock.Mock;
import org.usfirst.frc.team1076.robot.subsystems.DoorPneumatic;

public class TestRobot {

    @Test
    public void test() {
        assertTrue(true);
    }
    
    @Test(expected = AssertionError.class)
    public void testFail() {
        assertTrue(false);
    	Motor motor = Mock.stoppedMotor();
    	DoorPneumatic test = new DoorPneumatic(null);
    }

}
