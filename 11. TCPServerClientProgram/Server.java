import java.io.*; 
import java.net.*;

public class Server 
{ 
	public static void main(String args[]) throws IOException 
	{ 
		String str="Since it is not possible to know with precision the extent to which something is 'secure' (and a measure of vulnerability is unavoidable), perceptions of security vary, often greatly.[3][17] For example, a fear of death by earthquake is common in the United States (US), but slipping on the bathroom floor kills more people;and in France, the United Kingdom and the US there are far fewer deaths caused by terrorism than there are women killed by their partners in the home.\r\n" + 
				"Another problem of perception is the common assumption that the mere presence of a security system (such as armed forces, or antivirus software) implies security. For example, two computer security programs installed on the same device can prevent each other from working properly, while the user assumes that he or she benefits from twice the protection that only one program would afford.\r\n" +  
				"Security theater is a critical term for measures that change perceptions of security without necessarily affecting security itself. For example, visual signs of security protections, such as a home that advertises its alarm system, may deter an intruder, whether or not the system functions properly. Similarly, the increased presence of military personnel on the streets of a city after a terrorist attack may help to reassure the public, whether or not it diminishes the risk of further attacks.\r\n"+
				"Computer security, also known as cybersecurity or IT security, refers to the security of computing devices such as computers and smartphones, as well as computer networks such as private and public networks, and the Internet. The field has growing importance due to the increasing reliance on computer systems in most societies.[9] It concerns the protection of hardware, software, data, people, and also the procedures by which systems are accessed. The means of computer security include the physical security of systems and security of information held on them.";
		int index=1;
		//System.out.println(str.length());
		ServerSocket ss = new ServerSocket(4444); 
		Socket s = ss.accept(); 
		System.out.println("connected with clinet\naddr-"+s.getInetAddress()+", port-"+s.getPort());
		int SEND_INTERVAL=1000;
		DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
		
		while (true) 
		{
			try {
				final long startTime = System.currentTimeMillis();
				dos.writeUTF((index++)+"."+str);
				final long endTime = System.currentTimeMillis();
				//System.out.println(endTime-startTime);
				Thread.sleep(SEND_INTERVAL-(endTime-startTime));
			}
			catch(Exception e) {}
		} 
	} 
} 
