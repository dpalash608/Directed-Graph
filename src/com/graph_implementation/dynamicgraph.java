package com.graph_implementation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

class Edge {
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

	static int[][] adjacency_matrix = null;
	static ArrayList<Edge> edgelist = new ArrayList<Edge>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("Enter 1. Add nodes 2. Add edges 3. Delete Edge  4. Display the graph 5.Exit");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("Enter number of nodes");
				int nodes = sc.nextInt();
				add_nodes(nodes);
				break;
			case 2:
				System.out.println("Enter the start node , end node and weight of the edge ");
				int start_node = sc.nextInt();
				int end_node = sc.nextInt();
				double weight = sc.nextDouble();
				add_edge(start_node, end_node, weight);
				break;
			case 3:
				System.out.println("Enter the 2 nodes for which you want to delete the edge");
				int delnode1 = sc.nextInt();
				int delnode2 = sc.nextInt();
				delete_node(delnode1, delnode2);
				break;
			case 4:
				print_node();
				break;
			case 5:
				sc.close();
				System.exit(1);
			case 6:				
				dijkstra_GetMinDistances(1);
			break;

			}

		}
	}
	
	static int vertices =5 ;
	
	 //get the vertex with minimum distance which is not included in SPT
    static int getMinimumVertex(boolean [] mst, int [] key){
        int minKey = Integer.MAX_VALUE;
        int vertex = 1;
        for (int i = 1; i <vertices ; i++) {
            if(mst[i]==false && minKey>key[i]){
                minKey = key[i];
                vertex = i;
            }
        }
        return vertex;
    }

    public static void dijkstra_GetMinDistances(int sourceVertex){
        boolean[] spt = new boolean[vertices];
        int [] distance = new int[vertices];
        int INFINITY = Integer.MAX_VALUE;

        //Initialize all the distance to infinity
        for (int i = 1; i <vertices ; i++) {
            distance[i] = INFINITY;
        }

        //start from the vertex 0
        distance[sourceVertex] = 0;

        //create SPT
        for (int i = 1; i <vertices ; i++) {

            //get the vertex with the minimum distance
            int vertex_U = getMinimumVertex(spt, distance);

            //include this vertex in SPT
            spt[vertex_U] = true;

            //iterate through all the adjacent vertices of above vertex and update the keys
            for (int vertex_V = 0; vertex_V <vertices ; vertex_V++) {
                //check of the edge between vertex_U and vertex_V
                if(adjacency_matrix[vertex_U][vertex_V]>0){
                    //check if this vertex 'vertex_V' already in spt and
                    // if distance[vertex_V]!=Infinity

                    if(spt[vertex_V]==false && adjacency_matrix[vertex_U][vertex_V]!=INFINITY){
                        //check if distance needs an update or not
                        //means check total weight from source to vertex_V is less than
                        //the current distance value, if yes then update the distance

                        int newKey =  adjacency_matrix[vertex_U][vertex_V] + distance[vertex_U];
                        if(newKey<distance[vertex_V])
                            distance[vertex_V] = newKey;
                    }
                }
            }
        }
        //print shortest path tree
        printDijkstra(sourceVertex, distance);
    }

    public static void printDijkstra(int sourceVertex, int [] key){
        System.out.println("Dijkstra Algorithm: (Adjacency Matrix)");
        for (int i = 1; i <vertices ; i++) {
            System.out.println("Source Vertex: " + sourceVertex + " to vertex " +   + i +
                    " distance: " + key[i]);
        }
    }

	private static void print_node() {

		for (int i = 1; i < adjacency_matrix.length; i++) {
			for (int j = 1; j < adjacency_matrix.length; j++) {
				if(adjacency_matrix[i][j]>0)
				System.out.print("\t" + i + "->" + j + "\t" +adjacency_matrix[i][j] +"\t");
			}
			System.out.println();
		}
		
		for (int i = 1; i < adjacency_matrix.length; i++) {
			for (int j = 1; j < adjacency_matrix.length; j++) {
				System.out.print(adjacency_matrix[i][j]+"\t");
			}
			System.out.println();
		}
	}

	private static void delete_node(int delnode1, int delnode2) {
		for (int i = 0; i < edgelist.size(); i++) {
			Edge currentEdge = edgelist.get(i);
			int startVertex = currentEdge.startVertex;
			int endVertex = currentEdge.endVertex;

			if (currentEdge.startVertex == delnode1 && currentEdge.endVertex == delnode2) {
				edgelist.remove(i);
				adjacency_matrix[startVertex][endVertex] = 0;
			}
		}

	}

	private static void add_edge(int start_node, int end_node, double weight) {
		edgelist.add(new Edge(start_node, end_node, weight));
		adjacency_matrix[start_node][end_node] = (int) weight ;

	}

	private static void add_nodes(int nodes) {

		if (adjacency_matrix == null) {
			adjacency_matrix = new int[nodes + 1][nodes + 1];
		} else {
			int[][] temp = new int[adjacency_matrix.length+nodes + 1][adjacency_matrix.length+nodes + 1];

			for (int i = 1; i < adjacency_matrix.length; i++) {
				for (int j = 1; j < adjacency_matrix.length; j++) {
					temp[i][j] = adjacency_matrix[i][j];

				}

			}
			adjacency_matrix = temp;
			
		}
	}
}
