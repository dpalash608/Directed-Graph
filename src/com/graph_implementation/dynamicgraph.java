package com.graph_implementation;

import java.util.ArrayList;
import java.util.Scanner;

class Edge
{
	int startVertex;
	int endVertex;
	double weight;
	public Edge(int startVertex, int endVertex, double weight) {
		super();
		this.startVertex = startVertex;
		this.endVertex = endVertex;
		this.weight = weight;
	}
}

public class dynamicgraph {
	
public static void main(String[] args) {
	

	Scanner sc = new Scanner(System.in);

	int vertex = 5;
	
	int[][] matrix = new int[vertex+1][vertex+1];
	ArrayList<Edge> edgelist = new ArrayList<Edge>();
	
	edgelist.add(new Edge(1,2,5.0));
	edgelist.add(new Edge(2,2,5.0));
	edgelist.add(new Edge(3,4,5.0));
	edgelist.add(new Edge(3,2,5.0));
	edgelist.add(new Edge(3,3,5.0));
	edgelist.add(new Edge(4,5,5.0));
	
	for(int i=0;i<edgelist.size();i++)
	{
		Edge currentEdge = edgelist.get(i);
		int startVertex = currentEdge.startVertex;
		int endVertex =currentEdge.endVertex;
		matrix[startVertex][endVertex]=1;
	}
	
	for(int i = 1;i<=vertex;i++)
	{
		for(int j=1;j<=vertex;j++)
		{
			System.out.print(matrix[i][j]+"");
		}
		System.out.println();
	}
	
	System.out.println("Enter the 2 nodes for which you want to delete the edge");
	int delnode1 = sc.nextInt();
	int delnode2 = sc.nextInt();
	for(int i=0;i<edgelist.size();i++)
	{
		Edge currentEdge = edgelist.get(i);
		int startVertex = currentEdge.startVertex;
		int endVertex =currentEdge.endVertex;
		
		if(currentEdge.startVertex == delnode1 && currentEdge.endVertex == delnode2)
		{
			edgelist.remove(i);
			matrix[startVertex][endVertex] = 0;
		}
	}
	System.out.println("Enter the 2 nodes for which you want to insert the edge");
	int addnode1 = sc.nextInt();
	int addnode2 = sc.nextInt();
	
	edgelist.add(new Edge(addnode1,addnode2,5.0));
	matrix[addnode1][addnode2]=1;
	
	
	System.out.println("TaDa----------------------------");
	for(int i = 1;i<=vertex;i++)
	{
		for(int j=1;j<=vertex;j++)
		{
			System.out.print(matrix[i][j]+"");
		}
		System.out.println();
	}
	
	}	

}
