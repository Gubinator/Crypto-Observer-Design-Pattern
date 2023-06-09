package org.example;

public interface Subject {
    public void subscribe(Subscriber sub);

    public void unsubscribe(Subscriber sub);

    public void updateAll(float priceBTC, float priceETH, float priceDOGE, float priceBCC);

    public void notifySubscribers();
}
