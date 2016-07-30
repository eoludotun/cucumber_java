package com.CommonAction.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class RESTEasyClientGet {

	public static void main(String[] args) {
	  try {

		ClientRequest request = new ClientRequest(
				"http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false");
		ClientResponse<String> response = request.get(String.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			new ByteArrayInputStream(response.getEntity().getBytes())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

	  } catch (ClientProtocolException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  } catch (Exception e) {

		e.printStackTrace();

	  }

	}

}