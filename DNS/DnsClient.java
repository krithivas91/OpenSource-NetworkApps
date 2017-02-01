package DNS;

import java.util.*;
import java.io.*;
import java.net.*;


public class DnsClient {



	public static void main(String args[])
	{
		try
		{
			DatagramSocket client = new DatagramSocket(); // Creating a Client Socket
			InetAddress address = 	InetAddress.getByName("127.0.0.1"); // Declaring the server address as a loopback address

			byte[] sendBytes = new byte[1024];  //byte arrays created to send and receive DataPackets
			byte [] recvBytes = new byte[1024]; 

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter the IP address or Hostname:");

			String s = in.readLine();   // Gets the HostName/IP address from the user

			sendBytes = s.getBytes();


			DatagramPacket senderPacket = new DatagramPacket(sendBytes, sendBytes.length, address, 1046);

			client.send(senderPacket);  //Sends the Datagram off to the sever port 1046

			DatagramPacket recvPacket = new DatagramPacket ( recvBytes, recvBytes.length);
			client.receive(recvPacket);  // Client receives the resolved Hostname/Ip from the Server

			String serv = new String(recvPacket.getData()); // Byte to String
			serv.trim(); 	

			System.out.println("IP/HOSTNAME RETURNED FROM THE DNS SERVER");


			System.out.println(serv);   //Prints the resolved output to user




			client.close();   // Client Socket is closed


		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}