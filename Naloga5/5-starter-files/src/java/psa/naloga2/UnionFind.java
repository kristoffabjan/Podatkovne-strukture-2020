package psa.naloga2;

public class UnionFind {
	int[] id;

	public UnionFind(int N) {
		this.id = new int[N];
		for (int i = 0; i < N; i++) {
			this.id[i] = i;
		}
	}

	/*
	 * Metoda sprejme index in vrne predstavnika mnozice, katere clan je index.
	 */
	public int find(int i) {

		if (id[i] == i){
			return i;
		}else{
			return id[i] = find(id[i]);
			//return compress(id[i]);
		}
	}

	public void setRepresentative(int i) {
		if (id[i] == i){
		}else{
			id[i] = find(id[i]);
			//return compress(id[i]);
		}
	}


	/*
	 * Metoda sprejme da indexa in naredi unijo
	 */
	public void unite(int p, int q) {
		id[id[q]] = id[p];
		for (int i = 0; i < id.length; i++) {
			find(i);
		}
	}
	
	/*
	 * Metoda vrne true, ce sta p in q v isti mnozici
	 */
	public boolean isInSameSet(int p, int q) {
		return (id[p] == id[q]);
	}
}
