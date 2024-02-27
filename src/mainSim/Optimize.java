package mainSim;
import snabbköp.SnabbköpTillstånd;

import java.util.Random;



public class Optimize {
    public int metod1(int antalKassor, int maxAntalKunder, double ankomstRate, long frö, double minKassaTid, double maxKassaTid, double minPlockTid, double maxPlockTid, double tidenSnabbköpetStänger) {
        SnabbköpTillstånd tillstånd = new SnabbköpTillstånd(antalKassor, maxAntalKunder, ankomstRate, frö, minKassaTid, maxKassaTid, minPlockTid, maxPlockTid, tidenSnabbköpetStänger);
        @SuppressWarnings("unused")
        Main main = new Main();
        return tillstånd.getTotaltAntalMissadeKunder();
    }



    public int metod2(long frö){
        int maxAntalKunder = 10;
        double stängSnabbköp = 10.0;
        double maxPlockTid = 1.0;
        double minPlockTid = 0.5;
        double maxKassaTid = 3.0;
        double minKassaTid = 2.0;
        @SuppressWarnings("unused")
        double lambda = 1.0;

        int antalKassor = maxAntalKunder;

        int totaltAntalKunderSomFörsöktHandlat = metod1(antalKassor, maxAntalKunder, antalKassor, frö, minKassaTid, maxKassaTid, minPlockTid, maxPlockTid, stängSnabbköp);

        while(antalKassor >= 1){
            int nyaMissadeKunder = metod1(antalKassor, maxAntalKunder, antalKassor, frö, minKassaTid, maxKassaTid, minPlockTid, maxPlockTid, stängSnabbköp);

            if(totaltAntalKunderSomFörsöktHandlat != nyaMissadeKunder){
                return antalKassor + 1;
            }
            antalKassor -= 1;
        }
        return maxAntalKunder;
    }

    public Object[] metod3(long frö){
        Random rand = new Random(frö);
        int counter = 0;
        int maxAntalKassor = 0;
        long nextSeed = 0L;

        while(counter < 100){
            int nyAntalKassor = metod2(rand.nextLong());
            nextSeed = rand.nextLong();
            System.out.println("Next Seed: "+nextSeed);
            if(maxAntalKassor != Math.max(maxAntalKassor, nyAntalKassor)){
                counter = 0;
                maxAntalKassor = Math.max(maxAntalKassor, nyAntalKassor);
            }
            else{
                counter += 1;
            }
            System.out.println(counter);
        }
        return new Object[]{maxAntalKassor, nextSeed};
    }
    public static void main(String[] args) {
        Optimize optimize = new Optimize();
        System.out.println(optimize.metod2(1234L));
        Object[] hehe = optimize.metod3(1234L);
        int maxKassor = (int) hehe[0];
        long seed = (long) hehe[1];
        System.out.println(maxKassor);
        System.out.println(seed);
    }
}

