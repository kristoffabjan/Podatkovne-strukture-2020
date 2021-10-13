package psa.naloga3;

import java.util.Arrays;

public class NodeSkipList {
    int key;
    NodeSkipList[] node_array;
    int array_actual_fill;          //how many indexes in array are full, 0=just initial vals, 1=just one...

    public NodeSkipList(int key, int height){
        this.key = key;
        this.node_array = new NodeSkipList[height];
        Arrays.fill(node_array,null);               //fill pointers array w null values
    }

    public NodeSkipList(int key, int height,NodeSkipList fill){
        this.key = key;
        this.node_array = new NodeSkipList[height];
        Arrays.fill(node_array,fill);               //fill pointers array w null values
    }
}
