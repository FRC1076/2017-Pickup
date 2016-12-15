package org.usfirst.frc.team1076.robot;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import org.json.JSONObject;
import org.json.JSONTokener;

public class SonarReciever {
	DatagramSocket socket;
	int left;
	int right;

	public SonarReciever() throws SocketException {

		socket = new DatagramSocket(null);
		InetSocketAddress address = new InetSocketAddress("10.10.76.2", 4243);
		socket.bind(address);
	}

	public void recieve() throws IOException {

		byte[] buffer = new byte[512];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.receive(packet);
		JSONTokener tokener = new JSONTokener(new String(packet.getData()));
		JSONObject data = new JSONObject(tokener);
		left = data.getInt("left front");
		right = data.getInt("right front");

	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;

	}

}
