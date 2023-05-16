package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CryptoMarket implements Subject {

    private float priceBTC, priceETH, priceDOGE, priceBCC;
    private List<Subscriber> subscriberList = new ArrayList<>();

    public float getPriceBTC() {
        return priceBTC;
    }

    public float getPriceETH() {
        return priceETH;
    }

    public float getPriceDOGE() {
        return priceDOGE;
    }

    public float getPriceBCC() {
        return priceBCC;
    }

    //All setters notify users when coin value is changed
    public void setPriceBTC(float priceBTC) {
        this.priceBTC = priceBTC;
        notifySubscribers();
    }

    public void setPriceETH(float priceETH) {
        this.priceETH = priceETH;
        notifySubscribers();
    }

    public void setPriceDOGE(float priceDOGE) {
        this.priceDOGE = priceDOGE;
        notifySubscribers();
    }

    public void setPriceBCC(float priceBCC) {
        this.priceBCC = priceBCC;
        notifySubscribers();
    }

    @Override
    public void subscribe(Subscriber sub) {
        subscriberList.add(sub);
        System.out.println("New subscriber added: " + sub.getName()+"\n");
    }

    @Override
    public void unsubscribe(Subscriber sub) {
        subscriberList.remove(sub);
        System.out.println("Subscriber removed: " + sub.getName()+"\n");
    }

    //In a case if you want to set all values eg. when you start
    //Although it's not necessary method
    @Override
    public void updateAll(float priceBTC, float priceETH, float priceDOGE, float priceBCC) {
        this.priceBTC = priceBTC;
        this.priceETH = priceETH;
        this.priceBCC = priceBCC;
        this.priceDOGE = priceDOGE;
        notifySubscribers();
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber sub : subscriberList) {
            sub.notifyUpdated(this);
        }
    }

    public void getPricesOnline() {

        // Hardcoded for 4 coins, starting with BTC
        String[] coinIdentifiers = {"Qwsogvtv82FCd", "razxDUgYGNAdQ", "a91GCGd_u96cF", "yDaCLN1Y2kPKy"};

        for (String identifier : coinIdentifiers) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://coinranking1.p.rapidapi.com/coin/"+identifier+"/")
                    .get()
                    .addHeader("X-RapidAPI-Key", "783700f945mshd75725904a54db7p116b52jsn283a26d1fefb")
                    .addHeader("X-RapidAPI-Host", "coinranking1.p.rapidapi.com")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    JsonObject json = new JsonParser().parse(responseBody).getAsJsonObject();
                    JsonObject data = json.getAsJsonObject("data");
                    JsonObject coin = data.getAsJsonObject("coin");
                    String name = coin.get("name").toString();
                    //Returned string contains " ", for further operations it's removed
                    name = name.replace('"', ' ').trim();
                    String price = coin.get("price").toString().replace('"', ' ').trim();
                    int priceDotIndex = price.indexOf('.');
                    price = price.substring(0, priceDotIndex + 3);
                    float coinPrice = Float.parseFloat(price);

                    switch (name){
                        case ("Bitcoin"):
                            setPriceBTC(coinPrice);
                            break;
                        case("Dogecoin"):
                            setPriceDOGE(coinPrice);
                            break;
                        case("Ethereum"):
                            setPriceETH(coinPrice);
                            break;
                        case("BitConnect"):
                            setPriceBCC(coinPrice);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error occured");
            }
        }
        notifySubscribers();
    }


}
