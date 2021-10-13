package psa.naloga3;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        /*Random r = new Random();
        int counter=0;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 3-1; j++) {
                if (r.nextInt(2) == 0){
                    counter++;
                }else {
                    break;
                }
            }
            System.out.println(counter + " -- " + i);
            counter=0;
        }

        System.out.println((int)(Math.log(8) / Math.log(2)) + " log" );

       */


        SkipList sl = new SkipList(64);

        /*System.out.println(sl.search(45));
        System.out.println("insertin " + sl.insert(45));
        System.out.println(sl.search(45));


        System.out.println("4 found: " + sl.search(4));
        System.out.println("inserted 4" + sl.insert(4));
        System.out.println("4 found: " + sl.search(4));

        System.out.println("-----------------deletion-----------------------");

        System.out.println("4 deleted " + sl.delete(4));
        System.out.println("4 found: " + sl.search(4));  */

        for (int i = 0; i < 10; i++) {
            System.out.println("Inserted : " + i + " -> " + sl.insert(i) );
        }

        for (int j = 0; j < 10; j++) {
            System.out.println("searched : " + j + " -> " + sl.search(j) );
        }

        System.out.println("-----------------deletion-----------------------");
        System.out.println(sl.search(5));

        for (int k = 0; k < 10; k++) {
            System.out.println("Deleted : " + k + " -> " + sl.delete(k) );
        }

        System.out.println("-----------------deletion-----------------------");
        System.out.println(sl.search(5));




    }
}
