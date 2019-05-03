import java.util.*;
import java.net.*;
public class DomainNameToIP {

	public static void main(String[] args) throws UnknownHostException, SocketException {
		DomainNameToIP d=new DomainNameToIP();
		Scanner sc=new Scanner(System.in);
		System.out.print("enter domain name:");
		String domainName=sc.next();
		InetAddress addr[]=InetAddress.getAllByName(domainName);
		for(int i=0;i<addr.length;i++)
			System.out.println(addr[i].toString());
		InetAddress ip = addr[0];
		System.out.println("MAC:"+d.getMAC());
	}
	public static String getMAC(){
        try{
           InetAddress inetaddress=InetAddress.getLocalHost(); //Get LocalHost refrence
           NetworkInterface network = NetworkInterface.getByInetAddress(inetaddress); 
           byte[] macArray = network.getHardwareAddress();  //get Harware address Array
           StringBuilder str = new StringBuilder();
           // Convert Array to String 
           for (int i = 0; i < macArray.length; i++) {
                   str.append(String.format("%02X%s", macArray[i], (i < macArray.length - 1) ? "-" : ""));
           }
           String macAddress=str.toString();
           return macAddress; //return MAc Address
       }
       catch(Exception E){
           E.printStackTrace();  //print Exception StackTrace
           return null;
       } 
        //C0-B6-F9-A8-D3-78 mine mac
        //02-00-4C-4F-4F-50 
   }
}
