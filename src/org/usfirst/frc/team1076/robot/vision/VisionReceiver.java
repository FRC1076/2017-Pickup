package org.usfirst.frc.team1076.robot.vision;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * This class reads from an IP address and port 
 * It expects to receive data packets relating to vision.
 */
public class VisionReceiver {
	DatagramSocket socket;
	VisionData data;

	
	public VisionReceiver(String ip, int port) throws SocketException {
		socket = new DatagramSocket(null);
		InetSocketAddress address = new InetSocketAddress(ip, port);
		socket.bind(address);
	}

	/**
	 * Receives and processes a vision packet.
	 */
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
	            String json = new String(packet.getData()).substring(0, packet.getLength());
	            this.data = new VisionData(json);
	        }
	}
	
	/**
	 * Return most recently received packet 
	 */
	public VisionData getData() {
		return data;
	}
}
