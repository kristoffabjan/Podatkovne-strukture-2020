package psa.naloga5;

import java.util.Comparator;

public class Prim {
	int[][] data;
	int n;
	Vertex[] vertexArray;

	public Prim(int n) {
		data = new int[n][n];
		this.n = n;
		this.vertexArray = new Vertex[n-1];
	}

	public Prim(int[][] d) {
		this.data = d;
		this.n = d.length;
		this.vertexArray = new Vertex[n-1];
	}

	public void addEdge(int i, int j, int d) {
		this.data[i][j] = d;
		this.data[j][i] = d;
	}

	public int MSTcost() {
		int minCost = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int tempMin = calculateTree(i).minCost;
			if ( tempMin < minCost){
				minCost = tempMin;
			}
		}
		return minCost;
	}

	public int[] prim(int s) {
		return calculateTree(s).tree;
	}

	public TreeData calculateTree(int s){
		int[] tree=new int[n];
		int cost = 0;
		int current = s;

		//dodamo vsa vozlisca razen s
		int cntr = 0;
		for (int i = 0; i < n; i++) {
			if (i != s) {
				vertexArray[cntr] = new Vertex(i, Integer.MAX_VALUE);
				cntr++;
			}
		}

		tree[current] = 0;
		//dokler obstaja neobiskano vozlisce delaj
		while (!allVerticesUsed(vertexArray)){
			for (int i = 0; i < n; i++) {
				if (data[current][i] != 0){
					for (int j = 0; j < vertexArray.length; j++) {
						if (i == vertexArray[j].vertex){
							//posodobimo teže sosednjih vozlisc
							if (vertexArray[j].weight > data[current][i]){
								vertexArray[j].weight = data[current][i];
							}
						}
					}
				}
			}
			Vertex next = findMinUnusedVertex(vertexArray);
			//ce se ni bil uporabljen ga dodamo
			if (!next.isUsed){
				tree[next.vertex] = current;
				cost =cost +  next.weight;
				next.isUsed = true;
				current = next.vertex;
			}
		}

		return new TreeData(tree,cost);
	}

	public Vertex findMinUnusedVertex(Vertex[] vertices){
		Vertex min = new Vertex(Integer.MAX_VALUE, Integer.MAX_VALUE);
		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i].weight < min.weight && !vertices[i].isUsed){
				min = vertices[i];
			}
			else if (vertices[i].weight == min.weight && !vertices[i].isUsed){
				if (vertices[i].vertex < min.vertex){
					min = vertices[i];
				}
			}
		}
		return min;
	}

	public boolean allVerticesUsed(Vertex[] vertices){
		for (int i = 0; i < vertices.length ; i++) {
			if (!vertices[i].isUsed){
				return false;
			}
		}
		return true;
	}

}

class TreeData{
	int[] tree;
	int minCost;

	public TreeData(int[] tree, int minCost){
		this.tree = tree;
		this.minCost = minCost;
	}

}

class Vertex {
	int vertex;
	int weight;
	boolean isUsed;

	public Vertex(int v, int w){
		this.vertex = v;
		this.weight = w;
		this.isUsed = false;
	}
}

