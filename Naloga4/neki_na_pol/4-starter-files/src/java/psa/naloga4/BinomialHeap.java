package psa.naloga4;

import java.util.Arrays;
import java.util.Vector;

public class BinomialHeap {
	BinomialNode[] data;
	
	BinomialHeap(){
		data = new BinomialNode[1];
	}
	
	public boolean insert(int key) {
		if (numOfNullValues(data) == 0){	//ce ni vec praznega mesta, povecaj array
			//povecamo polje in damo elemente na konec polja
			data = resizeArray(data);
		}

		//vstavimo nov element na prvo mesto, ce je potem poln ga podvojimo
		//shiftArray(data);
		if (data.length < 2){
			data[0] = new BinomialNode(key);
			System.out.println(data[0].key + " je bila prva");
			return true;
		}else {
			if (numOfNullValues(data) < 2){
				data = resizeArray(data);
			}
			data = shiftArray(data);
			data[0]= new BinomialNode(key);
			if (data[1].childs.size()> 0){
			System.out.println(data[0].childs.elementAt(1));
			}
			data = mergeAllNodes(data);
			return true;
		}

	}
	
	public int getMin() {
		return getMinNode().key;
	}

	public BinomialNode getMinNode() {
		BinomialNode minimum = new BinomialNode(Integer.MAX_VALUE);
		for (int i = 0; i < data.length; i++) {
			if (data[i] == null){
				continue;
			}
			else if (data[i].key < minimum.key){
				minimum = data[i];
			}
		}
		return minimum;
	}

	public boolean delMin() {
		BinomialNode minimum = getMinNode();
		//stevilo otrok noda ki bo izbrisan
		int numOfChildren = minimum.childs.size();


		int nulls = numOfNullValues(data);
		while ( nulls < numOfChildren){
			resizeArray(data);
			nulls = numOfNullValues(data);
		}
		return true;
		//dodaj otroke ki so ostali brez starsa v data, in sortiraj data
		//za oboje uporabi mergeHeaps()
		/*for (int i = 0; i <intArray.length; i++) {
			for (int j = i+1; j <intArray.length; j++) {
				if(intArray[i] >intArray[j]) {      //swap elements if not in order
					temp = intArray[i];
					intArray[i] = intArray[j];
					intArray[j] = temp;
				}
			}
		}*/

	}

	public BinomialNode[] mergeHeaps(BinomialNode[] h1, BinomialNode[] h2){
		/*for (int i = 0; i <intArray.length; i++) {
			for (int j = i+1; j <intArray.length; j++) {
				if(intArray[i] >intArray[j]) {      //swap elements if not in order
					temp = intArray[i];
					intArray[i] = intArray[j];
					intArray[j] = temp;
				}
			}
		}*/
		return data;
	}

	public int numOfNullValues(BinomialNode[] arr){
		int counter = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null){
				counter++;
			}
		}
		return counter;
	}
	


	public BinomialNode[] resizeArray(BinomialNode[] arr) {
		BinomialNode[] newArray = new BinomialNode[2*(arr.length)];
		for (int i = 0; i < arr.length; i++) {
			newArray[i] = arr[i];
		}
		return newArray;
	}

	public BinomialNode[]  mergeAllNodes(BinomialNode[] arr){
		BinomialNode[] curr = arr;
		BinomialNode[] finalArr=new BinomialNode[arr.length];
		//Arrays.fill(finalArr,null);
		int counter=arr.length-1;

		for (int i = 0; i < curr.length - 1; i++) {
			if (curr[i] == null || curr[i+1] == null){
				continue;
			}
			else if (curr[i].childs.size() == curr[i+1].childs.size()){
				finalArr[counter] = merge(curr[i],curr[i+1] );
				counter--;
			}
		}

		return finalArr;
	}

	public BinomialNode[] shiftArray(BinomialNode[] arr){
		BinomialNode[] newArr = new BinomialNode[arr.length];
		for (int i = 0; i < arr.length-1; i++) {
			newArr[i+1] = arr[i];
		}
		newArr[0] = null;
		return newArr;
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

		//dodamo poddrevo
		root.addChild(bigger);
		return root;
	}

}

