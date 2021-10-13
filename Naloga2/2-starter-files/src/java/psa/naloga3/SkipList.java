package psa.naloga3;

import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.Random;

public class SkipList {

	NodeSkipList head;
	int max_height;
	NodeSkipList min_guard;
	NodeSkipList max_guard;
	int highest_array_index = 0;

	/*
	 * Tvoritelj sprejme kot parameter stevilo elementov, ki jih je sposoben obdelati
	 */
	//base level is level 0, height is h, arr len is h
	public SkipList(long maxNodes) {
		this.max_height= (int)(Math.log(maxNodes) / Math.log(2));
		this.min_guard = new NodeSkipList(Integer.MIN_VALUE,max_height);	//both guards, min has only pointer to max
		this.max_guard = new NodeSkipList(Integer.MAX_VALUE,max_height);	//max has null pointer
		Arrays.fill(this.min_guard.node_array,max_guard);                    //min points to max at beginning
		min_guard.array_actual_fill = 0;						//min is empty with pointers except max
		Arrays.fill(this.max_guard.node_array,null);					//max points to null
		max_guard.array_actual_fill = max_height;						//max will be filled to the top
	}

	public int flipCoin(){			//0 = grb , 1 je cifra, returns num of 0, before 1 falls
		Random random = new Random();
		int counter = 0;
		for (int i = 0; i < max_height -1; i++) {	//+1 so we dont overshoot level
			if (random.nextInt(2) == 0){
				counter++;
			}else {
				break;
			}

		}
		return counter;
	}

	/*
	 * Metoda sprejme stevilo in ga vstavi v preskocni seznam. Ce element ze
	 * obstaja v podatkovni strukturi, vrne false. Metoda vrne true, ce je bil
	 * element uspesno vstavljen in false sicer.
	 */
	public boolean insert(int searchKey) {
		if (search(searchKey)){					//if key already exist return false and quit
			return false;
		}else {
			int newNodeHeight = flipCoin();		//val between 0 and max_haight		//new node array filled with max nodes
			if (newNodeHeight > highest_array_index){
				highest_array_index = newNodeHeight;
			}
			NodeSkipList newNode = new NodeSkipList(searchKey,max_height,max_guard); //height is same, some null values
			newNode.array_actual_fill = newNodeHeight;		//0 to h-1				//the useful new height of new node
			NodeSkipList current = searchNode(searchKey);	//find node before one to be inserted
			int level = max_height-1 ;
			while (level >= 0){
					if (level <= (newNodeHeight)){					//when we arrive at level=useful height of next
						newNode.node_array[level] = current.node_array[level];
						current.node_array[level] = newNode;
						if (current == min_guard){
							max_guard.array_actual_fill++;			//increase useful height of min_guard
						}
					}else {
						//copy values higher than new node level, from previous to new
						newNode.node_array[level] = current.node_array[level];
					}
				//}
				level--;
			}
			return true;
		}
	}

	public void update_arrays(){

	}

	/*
	 * Metoda sprejme stevilo in poisce element v preskocnem seznamu. Metoda
	 * vrne true, ce je bil element uspesno najden v podatkovni strukturi, in
	 * false sicer
	 */

	public boolean search(int searchKey) {
		return searchNode(searchKey).key == searchKey;		//if keys are same -> true, else false
	}


	//returns node thats right before the one that we'll insert
	public NodeSkipList searchNode(int searchKey) {
		NodeSkipList current = min_guard;
		int level = max_height - 1;
		while (level>=0){
				if (current.node_array[level].key <= searchKey){
					current = current.node_array[level];		//if searchkey is bigger than right.key go right
				}else {
					level--;
				}
		}
		return current;
	}

	/*
	 * Metoda sprejme stevilo in izbrise element iz preskocnega seznama. Metoda
	 * vrne true, ce je bil element uspesno izbrisan iz podatkovne strukture, in
	 * false sicer
	 */
	public boolean delete(int key) {
		if (!search(key)){
			return false;	//return false if key doesnt exists
		}else {
			NodeSkipList current = searchNodeBeforeSame(key);		//node before one to be deleted
			NodeSkipList toBeDeleted = current.node_array[0];
			int level = max_height - 1;
			while (level >= 0){
				current.node_array[level] = toBeDeleted.node_array[level];	//copy from next to current
				level--;
			}
			return true;
		}
	}

	public NodeSkipList searchNodeBeforeSame(int searchKey) {
		NodeSkipList current = min_guard;
		int level = max_height - 1;
		while (level>=0){
			if (current.node_array[level].key < searchKey){
				current = current.node_array[level];		//if searchkey is bigger than right.key go right
			}else {
				level--;
			}
		}
		return current;
	}


}
