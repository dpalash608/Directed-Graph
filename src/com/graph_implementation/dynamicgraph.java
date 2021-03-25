package com.graph_implementation;

import java.util.ArrayList;
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
				System.exit(1);

			}

		}
	}

	private static void print_node() {

		for (int i = 1; i < adjacency_matrix.length; i++) {
			for (int j = 1; j < adjacency_matrix.length; j++) {
				if(adjacency_matrix[i][j]==1)
				System.out.print("\t" + i + "->" + j + "\t");
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
		adjacency_matrix[start_node][end_node] = 1;

	}

	private static void add_nodes(int nodes) {

		if (adjacency_matrix == null) {
			adjacency_matrix = new int[nodes + 1][nodes + 1];
		} else {
			int[][] temp = new int[nodes + 1][nodes + 1];

			for (int i = 1; i < adjacency_matrix.length; i++) {
				for (int j = 1; j < adjacency_matrix.length; j++) {
					temp[i][j] = adjacency_matrix[i][j];

				}

			}
			adjacency_matrix = temp;
		}
	}
}
