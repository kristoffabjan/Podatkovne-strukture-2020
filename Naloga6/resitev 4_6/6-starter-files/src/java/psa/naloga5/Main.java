package psa.naloga5;

public class Main {
    public static void main(String[] args) {
        int n = 5;
        Prim p = new Prim(n);
        int[][] matrika = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j){
                    matrika[i][j] = 0;
                }else {
                    matrika[i][j] = i+j+10;
                }
            }
        }

        Prim graf = new Prim(matrika);
        for (int i = 0; i <n ; i++) {
            System.out.println(graf.prim(2)[i]);
        }
        System.out.println(graf.MSTcost() + " cena lol");
    }
}
