package org.example;

public class Subscriber implements Observer {

    private String name;

    //Although there is really only one crypto market  - so property market would be excess

    public Subscriber(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public void notifyUpdated(CryptoMarket market) {
        System.out.println("User: "+getName());
        System.out.println("Bitcoin price: "+market.getPriceBTC()+"\nEthereum price: "+market.getPriceETH()+"" +
                "\nDoge price: "+market.getPriceDOGE()+ "\nBitconnect price: "+market.getPriceBCC()+"\n");
    }


}
