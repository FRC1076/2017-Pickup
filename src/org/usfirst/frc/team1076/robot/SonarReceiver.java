package org.usfirst.frc.team1076.robot;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SonarReceiver {
	DatagramSocket socket;
	int sonarDistance;

	public SonarReceiver(String ip, int portNumber) throws SocketException {
		socket = new DatagramSocket(null);
		InetSocketAddress address = new InetSocketAddress(ip, portNumber);
		socket.bind(address);
	}

	public void receive() throws IOException {
		byte[] buffer = new byte[512];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.setSoTimeout(1);
		
    	while (true) {
    		try {
    		    socket.receive(packet);
            } catch (SocketTimeoutException e) {
                // Exit once we have received all the packets.
                return;
            }
    
    		try {
    		    String json = new String(packet.getData()).substring(0, packet.getLength());
    		    JSONTokener tokener = new JSONTokener(json);
    		    JSONObject data = new JSONObject(tokener);
    		    sonarDistance = data.getInt("left front");
    		} catch (JSONException e) {
    		    e.printStackTrace();
    		}
		}
	}

	public int distance() {
		return sonarDistance;
	}

}
