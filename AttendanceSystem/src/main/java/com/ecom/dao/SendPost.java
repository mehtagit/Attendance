package com.ecom.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.springframework.web.multipart.support.MultipartFilter;

public class SendPost {

	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/HMSServices/OrderService/create/HH5656565";
		String json = "{\"orederId\":\"10007\",\"waiterId\":\"HHHH\",\"tableId\":\"TAB1\",\"customerId\":\"8826053999\",\"totalPrice\":2000.0,\"status\":true,\"createdOn\":\"2017-03-25 21:07:59.0\",\"modifiedOn\":\"2017-03-25 21:07:59.0\",\"hotelId\":\"HH5656565\",\"orderList\":[{\"orderListId\":\"12123\",\"orderId\":\"10007\",\"itemId\":\"IT103\",\"itemName\":\"Samosa\",\"orderedQuantity\":3,\"halfQuantity\":0,\"price\":300.0,\"status\":true,\"preparedStatus\":0,\"createdOn\":\"2017-03-25 23:35:51.0\",\"modifiedOn\":\"2017-03-25 23:35:51.0\",\"hotelId\":\"HH5656565\"}]}";

		new SendPost().sendPost(url, json, 20000,20000);
	}

	public String sendPost(String url, String xml, int connTimeOut, int readTimeOut) {
		Exception E = null;
		DataOutputStream wr = null;
		BufferedReader in = null;
		int status = -10;
		String result = null;
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
			con.setRequestMethod("PUT");
			con.setRequestProperty("content-type", "application/json");
			con.setRequestProperty("Accept", "application/json;");
			con.setDoOutput(true);
			con.setReadTimeout(readTimeOut);
			con.setConnectTimeout(connTimeOut);
			con.connect();
			wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(xml);
			wr.flush();
			wr.close();

			status = con.getResponseCode();
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			result = response.toString();
		} catch (SocketTimeoutException e) {
			// Read Time Out
			status = -20;
			E = e;
		} catch (ConnectException e) {
			// Connection Time Out
			status = -30;
			E = e;
		} catch (SocketException e) {
			// Connection Reset
			status = -40;
			E = e;
		} catch (Exception e) {
			e.printStackTrace();
			E = e;
		} finally {
			try {
				if (wr != null) {
					wr.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (E != null)
			System.out.println("sendPost - Status:Exception" + E.getMessage() + ", URL:" + url + " XML:" + xml);
		else
			System.out
					.println("sendPost - Status:" + status + ", URL:" + url + ", XML:" + xml + ", Response: " + result);
		return result;
	}
}
