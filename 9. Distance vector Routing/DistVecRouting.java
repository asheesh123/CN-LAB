import java.util.*;

public class DistVecRouting {
	int graph[][];
	int adj[][];
	int copy[][];
	int nextHop[][];
	int V;
	int E;
	public static void main(String args[]){
	     Scanner sc = new Scanner(System.in);
	     DistVecRouting ob = new DistVecRouting();
	     System.out.print("enter number of routers");
	     ob.V=sc.nextInt();
	     System.out.print("enter number of edges");
	     ob.E=sc.nextInt();
	     ob.graph=new int[ob.V][ob.V];
	     ob.copy=new int[ob.V][ob.V];
	     ob.adj=new int[ob.V][ob.V];
	     ob.nextHop=new int[ob.V][ob.V];
	     for(int i=0;i<ob.V;i++){
	    	 for(int j=0;j<ob.V;j++){
	    		 ob.copy[i][j]=0;
	    		 ob.adj[i][j]=0;
	    		 ob.nextHop[i][j]=0;
	    		 if(i!=j)
	    		   ob.graph[i][j]=1000000;
	    		 else
	    			ob.graph[i][j]=0; 
	    	 }
	     }
	     for(int i=0;i<ob.E;i++){
	    	 int s,d,cost;
	    	 System.out.println("enter: source destination cost separared by spaces");
	    	 s=sc.nextInt();s--;
	    	 d=sc.nextInt();d--;
	    	 cost=sc.nextInt();
	    	 ob.graph[s][d]=cost;
	    	 ob.graph[d][s]=cost;
	    	 ob.adj[s][d]=1;
	    	 ob.adj[d][s]=1;
	    	 ob.nextHop[s][d]=d+1;
	    	 ob.nextHop[d][s]=s+1;
	     }
	     for(int i=0;i<ob.V-1;i++){
	    	 ob.algorithm();
	    	 for(int j=0;j<ob.V;j++){
	    		 for(int k=0;k<ob.V;k++){
	    			 if(ob.copy[j][k]>0){
	    				 ob.graph[j][k]=ob.copy[j][k];
	    				 ob.copy[j][k]=0;
	    			 }
	    		 }
	    	 }
	     }
	     System.out.println("Graph matrix:");
	     for(int i=0;i<ob.V;i++){
	    	 for(int j=0;j<ob.V;j++){
	    		 System.out.print(ob.graph[i][j]+" ");
	    	 }
	    	 System.out.println();
	     }
	     System.out.println("Next hop matrix:");
	     for(int i=0;i<ob.V;i++){
	    	 for(int j=0;j<ob.V;j++){
	    		 System.out.print(ob.nextHop[i][j]+" ");
	    	 }
	    	 System.out.println();
	     } 
		sc.close();
	}
	void algorithm(){
		for(int i=0;i<V;i++){
			int adjIndex[]=new int[V];
			int adjLength[]=new int[V];
			int index=0;
			for(int j=0;j<V;j++){
				if(adj[i][j]==1){
					adjIndex[index++]=j;
				}
			}
			int index1=0;
			for(int j=0;j<index;j++){
				adjLength[index1++]=graph[i][adjIndex[j]];
			}
			for(int j=0;j<V;j++){	
				int min=1000,minIndex=0;
				if(i!=j){
					for(int k=0;k<index;k++){
						if(min>(adjLength[k]+graph[adjIndex[k]][j])){
							min=adjLength[k]+graph[adjIndex[k]][j];
							minIndex=adjIndex[k];
						}
					}
					copy[i][j]=min;
					nextHop[i][j]=minIndex+1;
				}
			}
		}	
	}
}
