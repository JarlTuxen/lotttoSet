package dk.kea;

import java.net.InetSocketAddress;
import java.util.*;

public class Main {

    private static final int MAX_TAL = 36;
    private static final int ANTAL_VINDERTAL = 5;
    private static final int ANTAL_EKSTRATAL = 2;

    public static Set<Integer> genererLottoTal(int antal) {
        Set<Integer> talSet = new TreeSet<>();
        Random random = new Random();

        //bliv ved til der er trukket antal unikke tal
        while (talSet.size() < antal) {
            int tal = random.nextInt(MAX_TAL) + 1;
            talSet.add(tal);
        }

        return talSet;
    }

    public static void checkKupon(Set<Integer> kupon, Set<Integer> trukneTal, Set<Integer> trukneEkstra){
        TreeSet<Integer> rigtigeTal = new TreeSet<>(kupon); //lav en kopi
        rigtigeTal.retainAll(trukneTal); //behold kun trukne tal
        TreeSet<Integer> rigtigeEkstra = new TreeSet<>(kupon); //lav en kopi
        rigtigeEkstra.retainAll(trukneEkstra); //behold kun trukne tillægstal

        //udskriv check af kupon
        System.out.println("Check af kupon: " + kupon);
        System.out.println("Udtrukne tal "+ trukneTal + trukneEkstra);
        System.out.println("Vindertal: " + rigtigeTal.size() + ": " + rigtigeTal) ;
        System.out.println("Tillægstal: " + rigtigeEkstra.size() + ": " + rigtigeEkstra);
        System.out.println();
    }

    public static void main(String[] args) {
        //check af metode med identiske kuponer
        Set<Integer> tvinderSet = new TreeSet<>();
        tvinderSet.add(1);
        tvinderSet.add(2);
        tvinderSet.add(3);
        tvinderSet.add(4);
        tvinderSet.add(5);
        Set<Integer> tekstraSet = new TreeSet<>();
        tekstraSet.add(6);
        tekstraSet.add(7);

        Set<Integer> tkupon = new TreeSet<>();
        tkupon.add(1);
        tkupon.add(2);
        tkupon.add(3);
        tkupon.add(4);
        tkupon.add(5);
        tkupon.add(6);
        tkupon.add(7);
        System.out.println("Test checkmetode med rigtig kupon");
        checkKupon(tkupon, tvinderSet, tekstraSet);

        //lav 5 kuponer og træk vindertal/ekstratal lige så mange gange
        for (int i=0;i<5;i++) {
            //lav tilfældig kupon med 7 tal
            tkupon = genererLottoTal(7);
            //træk 5 vindertal og 2 tillægstal
            Set<Integer> vinderTal = genererLottoTal(ANTAL_VINDERTAL);
            Set<Integer> ekstraTal = genererLottoTal(ANTAL_EKSTRATAL);
            //check for duplikat mellem vinderTal og ekstraTal
            //kan udføres ved at checke om kopiAfVinderTal.retainsAll(ekstraTal).size()==0
            boolean duplikat = true;
            while (duplikat){
                Set<Integer> checkDuplikat = new TreeSet<>(vinderTal);
                checkDuplikat.retainAll(ekstraTal);
                if (checkDuplikat.size()>0){
                    ekstraTal = genererLottoTal(ANTAL_EKSTRATAL);
                }
                else{
                    duplikat = false;
                }
            }

            checkKupon(tkupon, vinderTal, ekstraTal);
        }

    }
}
