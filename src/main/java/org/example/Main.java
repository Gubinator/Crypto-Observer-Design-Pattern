package org.example;

public class Main {
    public static void main(String[] args) {

        CryptoMarket market = new CryptoMarket();

        Subscriber t1  = new Subscriber("filip", market);
        Subscriber t2  = new Subscriber("lovro", market);
        Subscriber t3  = new Subscriber("annon", market);

        market.subscribe(t1);
        market.subscribe(t2);
        market.subscribe(t3);
        market.update(32,334,3,4);


    }
}