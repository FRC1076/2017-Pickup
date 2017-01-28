package org.usfirst.frc.team1076.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmarterDashboard extends SmartDashboard {
	public static boolean defaultNumber(String key, double value) {
		double realValue = getNumber(key, value);
		return putNumber(key, realValue);
	}
	
	public static boolean defaultString(String key, String value) {
		String realValue = getString(key, value);
		return putString(key, realValue);
	}

	public static boolean defaultBoolean(String key, boolean value) {
		boolean realValue = getBoolean(key, value);
		return putBoolean(key, realValue);
	}
}
