package psa.naloga0;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.BitSet;

//import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class Naloga {
	boolean[] podatki;
	String hashName = "MD5";
	final MessageDigest digestFunc;
	int k;
	int velikost;


	public Naloga(int velikost, int k) {
		MessageDigest tmp;
		try {
			tmp = java.security.MessageDigest.getInstance(hashName);

		} catch (NoSuchAlgorithmException e) {
			tmp = null;
		}
		digestFunc = tmp;
		this.velikost=velikost;
		this.k=k;
		podatki = new boolean[this.velikost];
	}

	public void clear() {
		Arrays.fill(podatki,false);
		//Arrays.fill(podatki, false);
	}

	public void add(String input) {
		byte[] bytes= input.getBytes();
		int[] hashes=createHashes(bytes, k);
		for (int i=0;i<hashes.length;i++){
			podatki[hashes[i]] = true;	//na k mest v 'podatki' zapisemo true
		}
	}

	public boolean contains(String input) {
		byte[] bytes= input.getBytes();
		int[] hashes=createHashes(bytes, k);
		for (int i = 0; i < hashes.length; i++) {
			if (!podatki[hashes[i]]) {
				return false;
			}
		}
		return true;
	}

	public int[] createHashes(byte[] data, int hashes) {
		int[] result = new int[hashes];

		int k = 0;
		while (k < hashes) {
			byte[] digest;
			digest = digestFunc.digest(data);

			for (int i = 0, j = 0; i < digest.length && k < hashes; i+=2, j++) {
				result[j] = Math.abs(((int)digest[i] << 8) | ((int)digest[i+1] & 0xFF))%velikost;
				k++;
			}
		}
		return result;
	}}
