package com.vbricks.avenger.serviceimpl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.vbricks.avenger.service.RevbaseAPIURLs;
import com.vbricks.avenger.utils.ApiResources;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetVideoDetailsAPI {

	public Client client;
	public ClientResponse response;
	WebResource webResource;
	HashMap<String, JsonElement> getvideoapiresponse = new HashMap<String, JsonElement>();

	public HashMap<String, JsonElement> getVideoDetails(HashMap<String, String> apiresponse) {

		String responseFlag = null;

		try {

			client = Client.create();
			webResource = client.resource(apiresponse.get("baseurl"))
					.path(RevbaseAPIURLs.GETVIDEOURL + apiresponse.get("videoId") + RevbaseAPIURLs.DETAILSURL);
			response = webResource.accept(ApiResources.Content_Type).type(ApiResources.Content_Type)
					.header("Authorization", "VBrick " + apiresponse.get("accesstoken")).get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			responseFlag = response.getEntity(String.class);
			System.out.println(" Video Details Response HTTP Code --->" + response.getStatus());
			System.out.println(" Video Details Response JSON --->" + responseFlag);

			JsonParser parser = new JsonParser();
			JsonObject json = (JsonObject) parser.parse(responseFlag);
			for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
				System.out.println("Key-----------" + entry.getKey());
				System.out.println("Value--------------------" + entry.getValue());

				getvideoapiresponse.put(entry.getKey(), entry.getValue());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvideoapiresponse;

	}

	public static void main(String[] args) {

	}

}
