package UdpPinger;

import java.util.*;
import java.net.*;
import java.io.*;

public class PingerServer {
	
     	public static void main (String args[])
		{
			while(true)
			{
     		try
			{

				DatagramSocket ServerSock = new DatagramSocket(12345);	//Creating a Datagram Socket for Server that has port number 12345	

				byte[] sendpack = new byte[1024];//byte arrays created to send and receive DataPackets
				byte[] recvpack = new byte[1024];
				System.out.println("ready to receive...");
				DatagramPacket recieverPacket = new DatagramPacket (recvpack,recvpack.length); //Datagram packet created to receive the data from client 


				ServerSock.receive(recieverPacket);//the packet from the client is received in the socket

				InetAddress address = recieverPacket.getAddress(); // Saving the Port and Address of the receiver, so that it can used for replying back. 
				int port = recieverPacket.getPort();		
				String s = new String (recieverPacket.getData());
				String val= s.trim(); //trimming the string received 
			
				if (val.equals("ping"))
				{
					
					String str = "pong";
					sendpack = str.getBytes();
					DatagramPacket senderPacket = new DatagramPacket(sendpack, sendpack.length, address, port);
					ServerSock.send(senderPacket);  
				}
				ServerSock.close();
					
			}
				


			
			catch (Exception ex)
			{
				System.out.println(ex);
			}
			}
			}

	}



