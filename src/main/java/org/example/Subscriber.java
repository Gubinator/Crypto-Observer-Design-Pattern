package org.example;

public class Subscriber implements Observer {

    private String name;

    //Since there is only one crypto market additional property market would be excess
    //Although it would be appropriate for more channels or cases where subscriber
    //can be subscribed to more than one channel like youtube

    public Subscriber(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public void notifyUpdated(CryptoMarket market) {
        System.out.println("User: "+getName()+"\n"+"Bitcoin price: "+market.getPriceBTC()+"\nEthereum price: "
        +market.getPriceETH()+"" + "\nDoge price: "+market.getPriceDOGE()+ "\nBitconnect price: "+market.getPriceBCC()+"\n");
    }


}
