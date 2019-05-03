import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
 
class ARPProgram
{
	public static void main(String []args)throws Exception	
	{
		ARPProgram arp=new ARPProgram();
		//arp.fun();
		FileReader fr=new FileReader("map.txt");
		BufferedReader br=new BufferedReader(fr);
		HashMap<String, String> hmap=new HashMap<String, String>();
		String str="";
		while((str=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(str," ");
			String ip=st.nextToken();
			String mac=st.nextToken();
			hmap.put(ip, mac);
		}
		br.close();
		for(String s:hmap.keySet()) {
			System.out.println(s+"     "+hmap.get(s));
		}
		System.out.print("\n\nenter ip address:");
		Scanner sc=new Scanner(System.in);
		String ip=sc.next();
		if(hmap.get(ip)!=null) {
			System.out.println("mapping mac address is:"+hmap.get(ip));
		}
		else{
			System.out.println("not found");
		}
		
	}
	void fun()throws Exception {
		ProcessBuilder pb=new ProcessBuilder("arpe.bat");
		pb.redirectErrorStream();
		Process p=pb.start();
		BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
		String str="";
		br.readLine();
		br.readLine();
		br.readLine();
		br.readLine();
		System.out.println(br.readLine());
		HashMap<String, String> map=new HashMap<String, String>();
		while((str=br.readLine())!=null) {
			System.out.println(str);
			StringTokenizer st=new StringTokenizer(str," ");
			String arr[]=new String[10];
			int index=0;
			while(st.hasMoreElements()) {
				arr[index++]=st.nextElement().toString();
				System.out.println(arr[index-1]);
			}
			map.put(arr[0], arr[1]);
		}
		for(String key:map.keySet()) {
			System.out.println(map.get(key)+"::::::"+key);
		}
	}
}