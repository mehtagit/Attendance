package com.ecom.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class Utility {
	
	public Gson gson = new Gson();
	
    public String callPost(String url, String json) {
        HttpURLConnection con = null;
        BufferedReader reader = null;
        String output=null;
        try{
            URL obj = new URL(url);

            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("content-type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setReadTimeout(20*1000);
            con.setConnectTimeout(20*1000);
            con.connect();

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(json);
            wr.flush();
            wr.close();

            int retVal = con.getResponseCode();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            output = response.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
            // output = e.getMessage();
        }
        finally {
            if(con != null)
                con.disconnect();
            try {
                if(reader != null)
                    reader.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    public String callGet(String Url) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(Url);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
            try {
                if (reader != null)
                    reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
}
