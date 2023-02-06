package dk.kea;

import java.net.InetSocketAddress;
import java.util.*;

public class Main {

    private static final int MAX_TAL = 50;
    private static final int ANTAL_VINDERTAL = 5;
    private static final int ANTAL_EKSTRATAL = 2;

    public static Set<Integer> genererLottoTal(int antal) {
        Set<Integer> talSet = new TreeSet<>();
        Random random = new Random();

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


        System.out.println("Check af kupon: " + kupon);
        System.out.println("Udtrukne tal "+ trukneTal + trukneEkstra);
        System.out.println("Vindertal: " + rigtigeTal.size() + ": " + rigtigeTal) ;
        System.out.println("Tillægstal: " + rigtigeEkstra.size() + ": " + rigtigeEkstra);
        System.out.println();
    }

    public static void main(String[] args) {
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

        for (int i=0;i<5;i++) {
            Set<Integer> vinderTal = genererLottoTal(ANTAL_VINDERTAL);
            Set<Integer> ekstraTal = genererLottoTal(ANTAL_EKSTRATAL);

            checkKupon(tkupon, vinderTal, ekstraTal);
        }

    }
}
