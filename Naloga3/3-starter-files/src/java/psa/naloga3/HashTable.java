package psa.naloga3;

import java.util.Arrays;

/*
 * Razred mora imeplementirati podatkovno strukturo Razprsilne tabele (HashTable).
 * Za funkcijo uporabite: h(x) = x * 701 mod 2000
 * V primeru kolizij uporabite VERIZENJE in sicer kot Slovar uporabite podatkovno 
 * strukturo Razprsilne tabele, ki ga morate implementirati (razred HashTable2). 
 * Pazite, ker je lahko ključ tudi negativno število
 */
public class HashTable {
	HashTable2[] data;
	int length = 2000;

	public HashTable(){
		this.data = new HashTable2[length];
		Arrays.fill(data,new HashTable2());
	}

	public int hash_function(int key){
		int index = (Math.abs(key*701)) % length;
		return index;
	}

	public boolean insert(int key) {
		int index = hash_function(key);
		if (data[index].insert(key)){		//ce je bil uspesno vstavljen v HT2 -> true
			return true;
		}else{
			return false;					//else false
		}
	}

	public boolean search(int key) {
		int index = hash_function(key);
		if (data[index].search(key)){
			return true;
		}else{
			return false;
		}
	}

	public boolean delete(int key) {
		int index = hash_function(key);
		if (data[index].delete(key)){
			return true;
		}else {
			return false;
		}
	}
}
