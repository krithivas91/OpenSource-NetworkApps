package TraceRt;


import java.util.*;
import java.io.*;
import java.net.*;
import javax.mail.*;

public class TraceRoute {
	

	public static void main(String args[])
	{
try
	{
		
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the address or domain name to be tracerouted:");  //Enter address or domain name
		String ab = sc.nextLine();
		
		Runtime run = Runtime.getRuntime();
		
		Process pro = run.exec("traceroute "+ab);  // Traceroute is done on runtime
		
	
		BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		String line = null;
		

		
	while ((line=in.readLine()) != null)
		{
			
		System.out.println(line);				//reads line by line and checks traceroute contains any of these error codes
		
		if(line.contains("!H"))
		{
			System.out.println("Host unreachable");   
			
			break;
		}
		else if (line.contains("!N"))
		{
			System.out.println("Network Unreachable");
		
			break;
		}
	
		else if (line.contains("!P"))
				{
			System.out.println("Protocol Unreachable");
		
				break;
				}
		
		else if (line.contains("!S"))
				{
			System.out.println("Source Route Failed");
		
				break;
				}
		
		else if (line.contains("!F"))
				{
			System.out.println("Fragmentation needed");
		
				break;
				}
		else if (line.contains("!X"))
				{
			System.out.println("Communication administratively prohibited");
	
				break;
				}
	
		
		}
	
	
	}

catch (Exception ex)
{
	System.out.println(ex);
}


}

	
}