package psa.naloga4;

public class Main {
    public static void main(String[] args) {

        BinomialHeap heap = new BinomialHeap();

        heap.insert(2);
        heap.insert(7);
        System.out.println("sizee  ");
        System.out.println(heap.data[1].childs.size());
        System.out.println(heap.data[1].childs.elementAt(0).key);
        System.out.println("Dolzina polja: " + heap.data.length);
        //
        System.out.println("----------------");
        for (int i = 9; i < 20; i++) {
            System.out.println(heap.insert(i));
        }

        System.out.println("watrok");
        System.out.println(heap.data[2].childs);
        System.out.println(heap.data[2].childs.size());


        }
}
