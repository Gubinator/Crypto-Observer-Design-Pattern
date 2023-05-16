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
    public void updateValue(float priceBTC, float priceETH, float priceDOGE, float priceBCC) {
        System.out.println("User: "+getName());
        System.out.println("Bitcoin price: "+priceBTC+"\nEtherium price: "+priceETH+"\nDoge price: "+priceDOGE+
                "\nBitconnect price: "+priceBCC);
    }


}
