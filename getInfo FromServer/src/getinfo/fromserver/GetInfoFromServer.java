/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getinfo.fromserver;


import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

/**
 *
 * @author user
 */

public class GetInfoFromServer {

    private static HttpURLConnection Connection;
    
    public static void main(String[] args) {
        
        BufferedReader redaer;
        String line;
        StringBuffer responesConnect = new StringBuffer();
        
        try{
            URL url = new URL("http://localhost:8080/mavenproject3/resources/generic");
            Connection = (HttpURLConnection)url.openConnection();
            
            //requset connection
            Connection.setRequestMethod("GET");
            Connection.setConnectTimeout(5000);             
            Connection.setReadTimeout(5000);
            
            
            
            int status = Connection.getResponseCode();
            System.out.println(status);
            if(status > 299){
                redaer = new BufferedReader(new InputStreamReader(Connection.getErrorStream()));
                while((line = redaer.readLine()) != null){
                    responesConnect.append(line);
                }
                redaer.close();
            }
            else{
                redaer = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
                while((line = redaer.readLine()) != null){
                    responesConnect.append(line);
                }
                redaer.close();
            }
            System.out.println(responesConnect.toString());
            
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            Connection.disconnect();
        }
    }
    
}
