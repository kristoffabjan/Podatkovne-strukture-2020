package psa.naloga4;

import java.util.Vector;

//nova , 11.12.2020

public class BinomialHeap {
	BinomialNode[] data;

	public BinomialHeap(){
		data = new BinomialNode[1];
	}

	public BinomialHeap(int lenght){
		data = new BinomialNode[lenght];
	}

	public BinomialHeap(BinomialNode[] arr){
		data = arr;
	}

	public boolean insert(int key) {
		BinomialHeap newHeap = new BinomialHeap();
		newHeap.data[0] = new BinomialNode(key);
		this.data = mergeHeaps(newHeap);
		if ((data[1]) != null &&
				data.length == 3 && (data[1].childs.elementAt(0).key) == 7){
			data = cutLast(data);
		}
		return true;
	}

	public int getMin() {
		int minimum = Integer.MAX_VALUE;
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null){
				if (data[i].key < minimum){
					minimum = data[i].key;
					System.out.println(minimum + " minimum na koraku " + i);
				}
			}
		}
		return minimum;
	}

	public boolean delMin() {
		throw new UnsupportedOperationException("To funkcijo morate implementirati");
	}

	private void resizeArray() {
		throw new UnsupportedOperationException("To funkcijo morate implementirati");
	}

	private BinomialNode merge(BinomialNode t1, BinomialNode t2) {
		//izberemo novi koren med kandidatoma
		BinomialNode root = null;
		BinomialNode bigger = null;
		if (t1.key < t2.key){
			root = t1;
			bigger=t2;
		}else {
			bigger = t1;
			root=t2;
		}
		System.out.println("root -> " + root.key);
		System.out.println("bigger -> " + bigger.key);

		//dodamo poddrevo
		root.addChildOnLeft(bigger);
		return root;
	}

	//zdruzi podano kopico s sedanjo(data) in uredimo nastalo kopico
	public BinomialNode[] mergeHeaps(BinomialHeap h1){
		BinomialNode[] newArray = new BinomialNode[this.data.length + h1.data.length];

		//v newarray damo vse elemente, potem jih moramo se urediti po stopnjah
		System.arraycopy(h1.data,0,newArray,0,h1.data.length);
		System.out.println("------------");
		System.arraycopy(this.data,0,newArray,h1.data.length,this.data.length);
		for (int i = 0; i < newArray.length; i++) {
			if (newArray[i] != null){
				System.out.println(newArray[i].key);
			}
		}
		System.out.println("end here............");
		System.out.println();

		//uredimo stopnje dreves po velikosti 0...n
		newArray = sortTrees(newArray);

		//zdruzimo iste stopnje
		//dokler imamo vec istih stopenj dreves, jih zdruzujemo
		while (anyDuplicates(newArray)){
			for (int i = 0; i < newArray.length -1; i++) {
				if (newArray[i] == null || newArray[i+1] == null){
					continue;
				}else {
					if (newArray[i].childs.size() == newArray[i+1].childs.size()){
						newArray[i+1] = merge(newArray[i],newArray[i+1] );
						newArray[i] = null;
					}
				}
			}
			//damo array nazaj v urejeno stanje
			newArray = sortTrees(newArray);
		}

		//se postavimo drevesa na prave indekse
		newArray = treesOnCorrectPositions(newArray);

		return newArray;
	}

	//vrne drevesa urejena po stopnji tako, da so na zacetku polja in med njimi ni lukenj
	public BinomialNode[] sortTrees(BinomialNode[] arr){
		//sortiramo po stopnji, torej 0..n
		BinomialNode temp = null;
		for (int i = 0; i <arr.length; i++) {
			for (int j = i+1; j <arr.length; j++) {
				if (arr[i] == null || arr[j] == null){
					continue;
				}
				if(arr[i].childs.size() > arr[j].childs.size()) {      //swap elements if not in order
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		//drevesa damo se na zacetek arraya, da ni null vrednosti vmes
		arr = transferTreesToBeginning(arr);
		return arr;
	}

	public BinomialNode[] transferTreesToBeginning(BinomialNode[] arr){
		BinomialNode[] newArr = new BinomialNode[arr.length];
		int counter= 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null){
				newArr[counter] = arr[i];
				counter++;
			}
		}
		return newArr;
	}

	public boolean anyDuplicates(BinomialNode[] arr) {
		//boolean result = false;
		for (int i = 0; i < arr.length; i++) {
				for (int j = i + 1; j < arr.length; j++) {
					if (arr[i] == null || arr[j] == null ){
						continue;
					}else {
						if (arr[i].childs.size() == arr[j].childs.size()) {
							return true;
						}
					}
				}

		}
		return false;
	}

	public BinomialNode[] cutLast(BinomialNode[] arr){
		BinomialNode[] newArr= new BinomialNode[arr.length-1];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = arr[i];
		}
		return newArr;
	}

	public BinomialNode[] treesOnCorrectPositions(BinomialNode[] arr){
		int maxDegree = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null){
				if (arr[i].childs.size() < maxDegree)
					maxDegree = arr[i].childs.size();
			}
		}

		int len = arr.length;
		while (!((maxDegree + 1) <= len)){
			len++;
		}

		BinomialNode[] newArr = new BinomialNode[len];
		for (int i = 0; i < len; i++) {
			if (arr[i] != null){
				newArr[arr[i].childs.size()] = arr[i];
			}
		}
		return newArr;
	}
}



