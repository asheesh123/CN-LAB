import java.util.*;

public class SunettingAndSupernetting{
	public void calDetails(int t_hosts,int n)
	{
		int temp =  t_hosts+(2*n);
		double throughput=0;
		if(temp<=256){
			throughput=temp/256.0;
			System.out.println("Class C is suitable");
			System.out.println("Throughput:"+throughput*100+" %");
		}
		if(temp>257&&temp<=Math.pow(2, 16)){
			throughput=temp/Math.pow(2, 16);
			System.out.println("Class B is suitable");
			System.out.println("Throughput:"+throughput*100+" %");
			if(temp/Math.pow(2, 16)<0.5){
				int k=2;
				while(temp>k*256){
					k++;
				}
				throughput=temp/(k*Math.pow(2, 8));
				System.out.println("Alt: we can go with "+k+" class C networks");
				System.out.println("Throughput:"+throughput*100+" %");
				//System.out.println("number of supernet bits:"+getNoOfSubnetBits(k));
			}
			else {
				System.out.println("Use subnetting concept");
				System.out.println("number of subnet bits:"+getNoOfSubnetBits(n));
			}
		}
		if(temp>257&&temp>Math.pow(2, 16)&&temp<=Math.pow(2, 24)){
			throughput=temp/Math.pow(2, 24);
			System.out.println("Class A is suitable");
			System.out.println("Throughput:"+throughput*100+" %");
			if(temp/Math.pow(2, 24)<0.5){
				int k=2;
				while(temp>k*Math.pow(2, 16)){
					k++;
				}
				throughput=temp/(k*Math.pow(2, 16));
				System.out.println("Alt: we can go with "+k+" class B networks ");
				System.out.println("Throughput:"+throughput*100+" %");
				//System.out.println("number of supernet bits:"+getNoOfSubnetBits(k));
			}
			else {
				System.out.println("Use subnetting concept");
				System.out.println("number of subnet bits:"+getNoOfSubnetBits(n));
			}
		}
		
	}
	public String getClass(int noh) {
		int incr=8;
		int i=0;
		while((int)Math.pow(2, i)-2<noh) {
			i+=incr;
		}
		if(i==8) 
			return "C";
		else if(i==16)
			return "B";
		else if(i==24)
			return "A";
		else
			return "";
	}
	int getNoOfSubnetBits(int nop) {
		int incr=1;
		int i=0;
		while((int)Math.pow(2, i)<nop) {
			i+=incr;
		}
		return i;
	}
	int getNoOfSubnets(int nosb) {
		return (int)Math.pow(2, nosb);
	}
	int getNoOfHostsPerSubnet(int nosb,String cls) {
		if(cls.contentEquals("A")) {
			return ((int)Math.pow(2, 24)-(int)Math.pow(2,nosb))/(int)Math.pow(2,nosb);
		}
		else if(cls.equals("B")) {
			return ((int)Math.pow(2, 16)-(int)Math.pow(2,nosb))/(int)Math.pow(2,nosb);
		}
		else
			return ((int)Math.pow(2, 8)-(int)Math.pow(2,nosb))/(int)Math.pow(2,nosb);
	}
	int getNoSubnetmastBits(int nosb,String cls) {
		if(cls.contentEquals("A")) {
			return nosb+8;
		}
		else if(cls.equals("B")) {
			return nosb+16;
		}
		else
			return nosb+24;
	}
	String getSubnetMastAddr(int n) {
		int x=n/8;
		String s="";
		while(x--!=0) {
			s+="255.";
		}
		int y=n%8;
		int val=0;
		for(int i=0;i<y;i++) {
			val+=(int)Math.pow(2, i);
		}
		val=val<<y;
		s+=val+"";
		StringTokenizer st=new StringTokenizer(s,".");
		while(st.countTokens()<4) {
			s+=".0";
			st=new StringTokenizer(s,".");
		}
		return s;
	}
	float throughPut(int noh,String cls) {
		int div=1;
		if(cls.contentEquals("A")) {
			div=(int)Math.pow(2, 24);
		}
		else if(cls.equals("B")) {
			div=(int)Math.pow(2, 16);
		}
		else
			div=(int)Math.pow(2, 8);
		return ((float)noh)/div*100;
			
	}
	public static void main(String[] args) {
		ForwardingTable f=new ForwardingTable();
		Scanner sc=new Scanner(System.in);
		System.out.print("enter no. of physical addresses:");
		int nop=sc.nextInt();
		System.out.print("enter number of hosts per network:");
		int noh=sc.nextInt();
		int total_hosts=nop*noh;
		String cls=f.getClass(noh);
		f.calDetails(total_hosts, nop);
		System.out.println();
		System.out.println();
		System.out.println("class: "+cls);
		int nosb=f.getNoOfSubnetBits(nop);
		System.out.println("no. of subnet bits: "+nosb);
		int nos=f.getNoOfSubnets(nosb);
		System.out.println("no. of subnets : "+nos);
		int nohpsubnet=f.getNoOfHostsPerSubnet(nosb, cls);
		if(nohpsubnet<noh) 
			System.out.println("number of class C networks:"+nop);
		
		else
			System.out.println("no. of hosts per subnetwork: "+nohpsubnet);
		float throughtput=f.throughPut(nohpsubnet, cls);
		System.out.println("through put: "+throughtput+" %");
		int numsub=f.getNoSubnetmastBits(nosb,cls);
		System.out.println("number of subnet mask bits:"+numsub);
		System.out.println("subnet mask address: "+f.getSubnetMastAddr(numsub));
	}

}
