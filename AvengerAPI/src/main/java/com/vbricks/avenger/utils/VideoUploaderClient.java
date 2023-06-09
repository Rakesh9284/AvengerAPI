 package com.vbricks.avenger.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

//import com.sun.media.jfxmedia.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;


public class VideoUploaderClient {
	private static Logger logger = Logger.getLogger(UploaderClient.class);
    private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;
 
 
    //    /**
    //     * This constructor initializes a new HTTP POST request with content type
    //     * is set to multipart/form-data
    //     * @param requestURL
    //     * @param charset
    //     * @throws IOException
    //     */
        public VideoUploaderClient(String requestURL, String charset, String authToken)
                throws IOException {
            this.charset = charset;
            ApiUtils apiutils=new ApiUtils();
            // creates a unique boundary based on time stamp
            boundary = "===" + System.currentTimeMillis() + "===";
             
            URL url = new URL(requestURL);
            if("http".equalsIgnoreCase(apiutils.sliptBaseURL(requestURL))){
            httpConn = (HttpURLConnection) url.openConnection();
           }else{
              httpConn = (HttpsURLConnection) url.openConnection();
            }
            //Gives IO exception if response takes more than a minute
            try {
            httpConn.setConnectTimeout(1*60*1000);
            httpConn.setReadTimeout(1*60*1000);
            }catch(Exception e){
            	e.printStackTrace();
            }
            
            httpConn.setRequestMethod("POST");
            httpConn.setUseCaches(false);
            httpConn.setDoOutput(true); // indicates POST method
            httpConn.setDoInput(true);
            httpConn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + boundary);
           
            httpConn.setRequestProperty("Authorization","VBrick "+authToken);
              httpConn.setRequestProperty("Connection", "keep-alive");
            outputStream = httpConn.getOutputStream();
            writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                    true);
        }
     
    //    /**
    //     * Adds a form field to the request
    //     * @param name field name
    //     * @param value field value
    //     */
        public void addFormField(String name, String value) {
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                    .append(LINE_FEED);
            writer.append("Content-Type: application/json; charset=" + charset).append(
                    LINE_FEED);
            writer.append(LINE_FEED);
            writer.append(value).append(LINE_FEED);
            writer.flush();
        }
 
        
        public void addFilePart(String fieldName, File uploadFile,String fileType)
                throws IOException {
            String fileName = uploadFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            logger.info("Upload File in the AddFilePart methods received fileType :::"+fileType);
            if(fileType.equalsIgnoreCase("video")){
            
                writer.append("Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
                writer.append("Content-Type: video/mp4").append(LINE_FEED);
                             
                writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
            }
            else  if(fileType.equalsIgnoreCase("image")){
            	
                writer.append("Content-Disposition: form-data; name=\"" + fieldName
                                + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
                writer.append("Content-Type: image/png").append(LINE_FEED);
                                     
                writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
            }
            
            else  if(fileType.equalsIgnoreCase("transcription")){
            	
                writer.append("Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
                writer.append("Content-Type: video/mp4").append(LINE_FEED);
                             
                writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
            }
            
            else  if(fileType.equalsIgnoreCase("supplemental")){
                writer.append("Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
               writer.append("Content-Type: Supplemental").append(LINE_FEED);
                             
                writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
            }else{
            	
            	logger.info("Not Matched file format Type :::"+fileType);
            }
            
            writer.append(LINE_FEED);
            writer.flush();
     
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
             
            writer.append(LINE_FEED);
            writer.flush();    
        }
     
    //    /**
    //     * Adds a header field to the request.
    //     * @param name - name of the header field
    //     * @param value - value of the header field
    //     */
        public void addHeaderField(String name, String value) {
            writer.append(name + ": " + value).append(LINE_FEED);
            writer.flush();
        }
         
    //    /**
    //     * Completes the request and receives response from the server.
    //     * @return a list of Strings as response in case the server returned
    //     * status OK, otherwise an exception is thrown.
    //     * @throws IOException
    //     */
        public List<String> finish() throws IOException {
            List<String> response = new ArrayList<String>();
     
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
     
            // checks server's status code first
            
            
            
            int status = httpConn.getResponseCode();
            try{
            if (status == HttpURLConnection.HTTP_OK) {
                response.add("HTTP StatusCode :" + status);
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        httpConn.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    response.add(line);
                }
                reader.close();
                httpConn.disconnect();
            } else {
                response.add("HTTP StatusCode :" + status);
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        httpConn.getErrorStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    response.add(line);

                }
              
            }
            }
            catch(Exception e){
            	e.printStackTrace();
            }
            return response;
        }
        
    	public static String parseJsonObject(String responsedata ) throws JSONException
    	{
    		JSONObject jsonObj = new JSONObject(responsedata);
    		String videoId=null;
		 
    	
            videoId = jsonObj.getString("videoId");
            System.out.println("response--------------------->"+videoId);
            return videoId;
		 

    	}
    	
    	public static String parseJsonObject1(String responsedata ) throws JSONException
    	{
    		JSONObject jsonObj = new JSONObject(responsedata);
    		String code=null;
		 
    	
            code = jsonObj.getString("code");
            System.out.println("response--------------------->"+code);
            return code;
		 

    	}
}
