package org.example;

import java.util.ArrayList;
import java.util.List;

public class CryptoMarket implements Subject {

    private float priceBTC, priceETH, priceDOGE, priceBCC;
    private List<Subscriber> subscriberList = new ArrayList<Subscriber>();


    @Override
    public void subscribe(Subscriber sub) {
        subscriberList.add(sub);
        System.out.println("New subscriber added: " + sub.getName());
    }

    @Override
    public void unsubscribe(Subscriber sub) {
        subscriberList.remove(sub);
        System.out.println("Subscriber removed: "+ sub.getName());
    }

    @Override
    public void update(float priceBTC, float priceETH, float priceDOGE, float priceBCC) {
        this.priceBTC=priceBTC;
        this.priceETH=priceETH;
        this.priceBCC=priceBCC;
        this.priceDOGE=priceDOGE;
        notifySubscribers();
    }

    @Override
    public void notifySubscribers() {
        for(Subscriber sub : subscriberList){
            sub.updateValue(priceBTC, priceETH, priceDOGE, priceBCC);
        }
    }
}
