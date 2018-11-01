import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Send_HTTP_Request2 {
	public static void main(String[] args) {
     try {
         Send_HTTP_Request2.call_me();
        } catch (Exception e) {
         e.printStackTrace();
       }
     }
	   
public static void call_me() throws Exception {
     String url = "https://api.cryptonator.com/api/full/btc-usd";
     URL obj = new URL(url);
     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
     // optional default is GET
     con.setRequestMethod("GET");
     //add request header
     con.setRequestProperty("User-Agent", "Mozilla/5.0");
     int responseCode = con.getResponseCode();
     //System.out.println("\nSending 'GET' request to URL : " + url);
     //System.out.println("Response Code : " + responseCode);
     BufferedReader in = new BufferedReader(
             new InputStreamReader(con.getInputStream()));
     String inputLine;
     StringBuffer response = new StringBuffer();
     while ((inputLine = in.readLine()) != null) {
     	response.append(inputLine);
     }
     in.close();
     //print in String
     //System.out.println(response.toString());
     //Read JSON response and print
     JSONObject myResponse = new JSONObject(response.toString());
  
     String loudScreaming = myResponse.getJSONObject("ticker").getString("base");	 
     String target= myResponse.getJSONObject("ticker").getString("target");
     String price = myResponse.getJSONObject("ticker").getString("price");
     String volume = myResponse.getJSONObject("ticker").getString("volume");
     String change = myResponse.getJSONObject("ticker").getString("change");

     
     System.out.println("From "+loudScreaming + " To "+target +" with price "+price+" with volume "+volume+ " and change"+change);
     
     
     JSONArray dataArray= myResponse.getJSONObject("ticker").getJSONArray("markets");     
     for(int n = 0; n < dataArray.length(); n++)
     {
    	 String market = dataArray.getJSONObject(n).getString("market");
    	 String priceX = dataArray.getJSONObject(n).getString("price");
    	 //String volumeX = dataArray.getJSONObject(n).getString("volume");
    
    	 System.out.println("    ");
    	 System.out.println("market- "+market);
         System.out.println("price- "+priceX);
         System.out.println("    ");
         //System.out.println("volumeX- "+volumeX);
    
     }
     
   }
}