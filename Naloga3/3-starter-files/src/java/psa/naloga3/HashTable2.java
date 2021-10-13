package psa.naloga3;

import java.util.Arrays;

/*
 * Razred mora imeplementirati podatkovno strukturo Razprsilne tabele.
 * Za funkcijo uporabite: h(x) = x * 53 mod 100
 * V primeru kolizij uporabite LINEARNO NASLAVLJANJE.
 */
public class HashTable2 {

	int[] data;
	boolean[] is_deleted;
	boolean[] is_full;
	int length = 100;

	public HashTable2(){
		//this.data = new HashNode[length];
		this.data = new int[length];
		this.is_deleted = new boolean[length];
		Arrays.fill(is_deleted,false);
		this.is_full = new boolean[length];
		Arrays.fill(is_full,false);
	}


	public int hash_function(int key, int i){
		int index = (Math.abs(key*53)+i) % length;
		return index;
	}

	public boolean insert(int key) {
		int i=0;
		int index = hash_function(key,i);
		if (areAllTrue(is_full) || search(key)){				//ce je vse polno-false
			return false;
		}else{
			while (is_full[index]) {                //dokler je zasedeno mesto, gremo naprej
				i++;
				index = hash_function(key,i);
			}
			data[index] = key;
			is_full[index] = true;
			is_deleted[index] = false;
			return true;
		}
	}

	public boolean search(int key){
		if (getIndex(key) >= 0 && getIndex(key) < length ){
			return true;
		}else {
			return false;
		}
	}


	public boolean delete(int key) {
		if (!search(key)){					//ce ga ni, false
			return false;
		}else {
			int index = getIndex(key);
			data[index] = Integer.MIN_VALUE;
			is_deleted[index] = true;
			is_full[index] = false;
			return true;
		}
	}

	public int getIndex(int key) {
		int i=0;
		int index = hash_function(key,i);
		while (is_full[index] && !is_deleted[index]) {	//ce tam nismo brisali
			if (data[index] == key) {			//ce ga najdemo dokler hodimo naprej do
				return index;					//praznega
			} else {
				if (index<99){
				i++;
				index = hash_function(key,i);
				}
			}
		}
		return -1;
	}


	public static boolean areAllTrue(boolean[] array)
	{
		for(boolean b : array) if(!b) return false;
		return true;
	}
}
