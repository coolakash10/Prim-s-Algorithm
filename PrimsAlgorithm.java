/*********************************************************************************************************************
     **
     **  Prim's Algorithm
     **  list to store graph & priority queue to store according minimum weight
     **  Input in the form of array
     **  Output is minimum spanning tree
     
     **  Written By:    Akash Vishwas Londhe
     **
*********************************************************************************************************************/

//import package
import java.util.*;
import java.lang.*;
import java.io.*;

//main class
class Main
{
    //array list to store graph with edge as a element
    static LinkedList<Edge>[]list;
    
    //arraylist to store minimum spanning tree path
    static ArrayList<Node> arr=new ArrayList<>();
    
    static long ans=0;
    
    //edge class to store edge with specified weight
    static class Edge
    {
        
        int vertex;
        int weight;
        Edge(int v,int w)
        {
            this.vertex=v;
            this.weight=w;
        }
        
    }
    
    //node class to store sorted edge
    static class Node
    {
        
        int weight;
        int curr;
        int parent;
        Node(int weight,int curr,int parent)
        {
            this.weight=weight;
            this.curr=curr;
            this.parent=parent;
        }
        
    }
    
    //function to calculate minimum spanning tree 
    public static void mst(int n)
    {
        
        boolean[]visited=new boolean[n+1];
        
        //sort queue with considering weight as a key
        PriorityQueue<Node>queue=new PriorityQueue<>(new Comparator<Node>(){
            public int compare(Node n1,Node n2)
            {
                return n1.weight-n2.weight;
            }
        });
        
        visited[1]=true;
        
        for(Edge e:list[1])
        {
            queue.add(new Node(e.weight,e.vertex,1));
        }
        
        while(queue.size()!=0)
        {
            Node node=queue.poll();
            
            int weight=node.weight;
            int curr=node.curr;
            int parent=node.parent;
            
            if(visited[curr])
                continue;
                
            visited[curr]=true;
            arr.add(new Node(weight,curr,parent));
            ans+=weight;
            
            for(Edge e:list[curr])
            {
                queue.add(new Node(e.weight,e.vertex,curr));
            }
            
        }
        
    }
    
    //main function
	public static void main (String[] args) throws java.lang.Exception
	{
	    
		//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//String[]s=br.readLine().split(" ");
		int n=6; //Integer.parseInt(s[0]);
		int m=9; //Integer.parseInt(s[1]);
		
		list=new LinkedList[n+1];
		
		for(int i=1;i<=n;i++)
		    list[i]=new LinkedList<Edge>();
		    
		    int s[][]={{1, 2, 3},{1, 4, 1},{2, 3, 1},{2, 4, 3},{3, 4, 1},{3, 5, 5},{3, 6, 4},{4, 5, 6},{5, 6, 2}} ;
		for(int i=0;i<m;i++)
		{
		    //s=br.readLine().split(" ");
    		int a=s[i][0]; //Integer.parseInt(s[0]);
    		int b=s[i][1]; //Integer.parseInt(s[1]);
    		int c=s[i][2]; //Integer.parseInt(s[2]);
    		list[a].add(new Edge(b,c));
    		list[b].add(new Edge(a,c));
		}
		
		mst(n);
		
		System.out.println("Weight of Minimum spanning tree : "+ ans);
		System.out.println("\nPath between vertices with weight are : \n");
		for(Node node:arr)
		{
		    System.out.println("Node "+node.curr+" to "+node.parent+" with weight : "+node.weight);
		}
		
	}
}