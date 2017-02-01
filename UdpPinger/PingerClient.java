package UdpPinger;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PingerClient {
	
	
	public static void main(String args[])
	{
		for(int i=0;i<10;i++)   //Calculates RTT for 10 packets 
		{
		try
		{
			DatagramSocket client = new DatagramSocket(); 
			InetAddress address = 	InetAddress.getByName("127.0.0.1"); 
			byte[] sendBytes = new byte[1024];  
			byte [] recvBytes = new byte[1024]; 

	
			String s = "ping";
			sendBytes = s.getBytes();
			
			DatagramPacket senderPacket = new DatagramPacket(sendBytes, sendBytes.length, address, 12345);

			client.send(senderPacket);  //Sends the Datagram off to the sever port 1234
			long milli = System.currentTimeMillis() ;  //notes down the time when the packet is sent
			
			System.out.println("CLIENT : SENT PING TO SERVER AT " +milli+ " milliseconds");
			
			DatagramPacket recvPacket = new DatagramPacket ( recvBytes, recvBytes.length);
			client.receive(recvPacket);  

			String serv = new String(recvPacket.getData()); // Byte to String
			serv.trim(); 	
			
			
			if (serv.contains("pong"))
			{
				long delli = System.currentTimeMillis();  //Notes down the time when the packet is received
				System.out.println("SERVER : SENT BACK PONG TO CLIENT AT " +delli+ " milliseconds");
				long rtt = delli-milli;   // calculates roundtrip time 
				System.out.println("Round trip time is " +rtt+ " milliseconds");
				System.out.println();
				System.out.println();
			
			}
			
			
		client.close();   


		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		}
		}

}
