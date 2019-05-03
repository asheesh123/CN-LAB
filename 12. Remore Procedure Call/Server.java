import java.net.*;
import java.text.*;
import java.io.*;
import java.util.*;
public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket ss=new ServerSocket(5056);
		while(true) {
			Socket s=null;
			try {
				s=ss.accept();
				System.out.println("A new client is connected:"+s);
				DataInputStream dis=new DataInputStream(s.getInputStream());
				DataOutputStream dos=new DataOutputStream(s.getOutputStream());
				System.out.println("Assigning new thread to this client");
				Thread t=new ClientHandler(s,dis,dos);
				t.start();
			}
			catch(Exception e) {
				System.out.println("Error occured!");
			}
		}
	}
}
class ClientHandler extends Thread{
	final Socket s;
	final DataOutputStream dos;
	final DataInputStream dis;
	public ClientHandler(Socket s,DataInputStream dis,DataOutputStream dos) {
		this.s=s;
		this.dis=dis;
		this.dos=dos;
	}
	int compute(int op1,int op2,String op){
		int res=0;
		switch(op) {
			case "+":
				res=op1+op2;
				break;
			case "-":
				res=op1-op2;
				break;
			case "*":
				res=op1*op2;
				break;
			case "/":
				res=op1/op2;
				break;
			default:
				System.out.println("Invalid operation");
		}
		return res;
	}
    @Override
    public void run()  
    { 
        String received; 
        String toreturn; 
        while (true)  
        { 
            try {
                dos.writeUTF("Enter the simple expression to be computed. eg. 2 + 3 must be seperated with space");
                received = dis.readUTF(); 
                if(received.equals("Exit")) 
                {  
                    System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 

                String s[]=received.split(" ");
                if(s.length<3)
                	dos.writeUTF("invalid input format");
                else{
                	int op1=Integer.parseInt(s[0]);
                	int op2=Integer.parseInt(s[2]);
                    toreturn = compute(op1, op2, s[1])+"";
                    dos.writeUTF(toreturn);
                }
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        }  
        try
        {
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    } 
}
