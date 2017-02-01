package DNS;

import java.util.*;
import java.net.*;
import java.io.*;

public class dnsServer {	


	public static void main (String args[])
	{
		try
		{


			DatagramSocket ServerSock = new DatagramSocket(1046);	//Creating a Datagram Socket for Server that has port number 1046	

			byte[] sendpack = new byte[1024];//byte arrays created to send and receive DataPackets
			byte[] recvpack = new byte[1024];

			DatagramPacket recieverPacket = new DatagramPacket (recvpack,recvpack.length); //Datagram packet created to receive the data from client 


			ServerSock.receive(recieverPacket);//the packet from the client is received in the socket

			InetAddress address = recieverPacket.getAddress(); // Saving the Port and Address of the receiver, so that it can used for replying back. 
			int port = recieverPacket.getPort();		
			String s = new String (recieverPacket.getData());
			String val= s.trim(); //trimming the string received 
			//String Arrays with HostName and corresponding IP address 
			String[] hname = {"www.google.com","www.amazon.com","www.facebook.com","www.twitter.com","www.youtube.com","www.tumblr.com","www.livejournal.com","www.dreamwidth.org",
					"www.newegg.com","www.frys.com","www.wikipedia.org","www.yahoo.com","www.toaboa.com","www.ebay.com","www.msn.com","www.bing.com", 
					"www.instagram.com","www.apple.com","www.reddit.com","www.paypal.com","www.microsoft.com","www.imgur.com","www.nextflix.com","www.stackoverflow.com",
					"www.craigslist.org","www.blogger.com","www.cnn.com","www.bbc.co.uk","www.flipkart.com"};

			String[] ipaddr = { "216.58.219.196","205.251.242.54","31.13.71.1","199.16.156.198","173.194.123.3","192.229.162.51","208.93.0.150","141.101.126.234",
					"184.26.44.105","107.162.132.86","208.80.154.224","98.139.183.24","173.192.64.147","23.66.223.239","204.79.197.203","204.79.197.200",
					"54.175.177.96","17.142.160.59","198.41.209.13","66.211.169.66","134.170.188.221","23.235.39.193","69.162.80.52","104.16.102.85",
					"208.82.238.129","216.58.217.137","157.166.226.25","212.58.244.18","163.53.78.58"};

			String str = new String();

			for (int i=0;i<hname.length;i++)
			{
				if(val.equals(hname[i]))   		//Returns the corresponding IP to HostName. Stores value in String str 

				{

					str = ipaddr[i];					

					break;
				}


				else if (val.equals(ipaddr[i])) 	//Returns the corresponding HostName to IPaddress. Stores value in String str.
				{

					str = hname[i];
					break;
				}

			}


			sendpack = str.getBytes();

			DatagramPacket senderPacket = new DatagramPacket(sendpack, sendpack.length, address, port);
			ServerSock.send(senderPacket);  //Sends the resolved HostName/IP Address


		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}

}
