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
    private List<Subscriber> subscriberList = new ArrayList<Subscriber>();

    @Override
    public void subscribe(Subscriber sub) {
        subscriberList.add(sub);
        System.out.println("New subscriber added: " + sub.getName());
    }

    @Override
    public void unsubscribe(Subscriber sub) {
        subscriberList.remove(sub);
        System.out.println("Subscriber removed: " + sub.getName());
    }

    @Override
    public void update(float priceBTC, float priceETH, float priceDOGE, float priceBCC) {
        this.priceBTC = priceBTC;
        this.priceETH = priceETH;
        this.priceBCC = priceBCC;
        this.priceDOGE = priceDOGE;
        notifySubscribers();
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber sub : subscriberList) {
            sub.updateValue(priceBTC, priceETH, priceDOGE, priceBCC);
        }
    }

    public void updateOnline() {

        String[] coinIdentifiers = {"Qwsogvtv82FCd", "razxDUgYGNAdQ", "a91GCGd_u96cF", "yDaCLN1Y2kPKy"};

        for (String identifier : coinIdentifiers) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://coinranking1.p.rapidapi.com/coin/"+identifier+"/price?referenceCurrencyUuid=yhjMzLPhuIDl")
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
                    String price = data.get("price").toString().replace('"', ' ').trim();
                    int priceDotIndex = price.indexOf('.');
                    price = price.substring(0, priceDotIndex + 3);
                    float coinPrice = Float.parseFloat(price);
                    System.out.println(price);
                }
            } catch (IOException e) {
                System.out.println("Error occured");
            }
        }
    }


}
