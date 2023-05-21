package com.vbrick.avenger.funUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class BuildNo {
	public static void main(String[] args) {
	//	System.out.println("The arguments"+args[0]);
		String urlText = args[0] + "js/version.js";
		//System.out.println("URL Text"+urlText);
		String sbuildNo = null;
		BufferedReader in = null;
		try {
			URL url = new URL(urlText);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			int i = 0;
			while ((inputLine = in.readLine()) != null) {
				if (i == 3) {
					sbuildNo = inputLine;
				}
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(sbuildNo.replace("'", "").replace(":", "").replace("buildNumber", "").trim());
		
	}


}
