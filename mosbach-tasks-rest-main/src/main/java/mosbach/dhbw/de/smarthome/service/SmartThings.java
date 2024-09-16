package mosbach.dhbw.de.smarthome.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import mosbach.dhbw.de.smarthome.dto.smartthings.AllDevices;
import mosbach.dhbw.de.smarthome.dto.smartthings.Capability;
import mosbach.dhbw.de.smarthome.dto.smartthings.DeviceST;
import mosbach.dhbw.de.smarthome.dto.smartthings.GetStatusResponseHealthCheck;
import mosbach.dhbw.de.smarthome.dto.smartthings.GetStatusResponseSwitch;
import mosbach.dhbw.de.smarthome.dto.smartthings.SetStatusResponse;

public class SmartThings {

    private static final String API_URL = "https://api.smartthings.com/v1/devices";
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    public static AllDevices getAllDevices(String accessToken){
        final HttpGet httpGet = new HttpGet(API_URL);	
        httpGet.setHeader("Authorization", "Bearer " + accessToken);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String response = client.execute(httpGet, new MyResponseHandler());
            System.out.println(response);
            AllDevices deviceResponse = objectMapper.readValue(response, AllDevices.class);
            for(DeviceST deviceST : deviceResponse.getItems()){
                for(Capability capability :deviceST.getComponents().get(0).getCapabilities()){
                    System.out.println("Capabilities: " + capability.getId());
                }
            }
            
            return deviceResponse;
        }catch (Exception e) {
            return null;
        }
    }

    public static DeviceST getDevice(String deviceID, String accessToken){
        final HttpGet httpGet = new HttpGet(API_URL);	
        httpGet.setHeader("Authorization", "Bearer " + accessToken);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String response = client.execute(httpGet, new MyResponseHandler());
            DeviceST deviceResponse = objectMapper.readValue(response, DeviceST.class);
            System.out.println("Label: " + deviceResponse.getLabel());
            return deviceResponse;
        }catch (Exception e) {
            return null;
        }
    }

    public static boolean setDeviceStatus(String status, String deviceID, String capabilityId, String accessToken){
        final HttpPost httpPost = new HttpPost(API_URL + "/" + deviceID + "/commands");	

        final String json = "{\"commands\":[{\"capability\":\""+capabilityId+"\",\"command\":\""+status+"\"}]}";
        StringEntity entity = null;
        try {
            entity = new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(entity);
        httpPost.setHeader("Authorization", "Bearer " + accessToken);
        httpPost.setHeader("Content-type", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String response = client.execute(httpPost, new MyResponseHandler());
            SetStatusResponse devicResponse = objectMapper.readValue(response, SetStatusResponse.class);
            if(devicResponse.getResults().get(0).getStatus().equals("ACCEPTED")) return true;
            else return false;
        }catch (Exception e) {
            return false;
        }
    }   
    
    public static boolean isSwitchOn(String deviceID, String accessToken ){
        final HttpGet httpGet = new HttpGet(API_URL + "/" + deviceID + "/components/main/capabilities/switch/status");	
        httpGet.setHeader("Authorization", "Bearer " + accessToken);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String response = client.execute(httpGet, new MyResponseHandler());
            GetStatusResponseSwitch devicResponse = objectMapper.readValue(response, GetStatusResponseSwitch.class);
            if(devicResponse.getSwitch().getValue().equals("on")) return true;
            else return false;
        }catch (Exception e) {
            return false;
        }
    }

    public static boolean isOnline(String deviceID, String accessToken ){
        final HttpGet httpGet = new HttpGet(API_URL + "/" + deviceID + "/components/main/capabilities/healthCheck/status");	
        httpGet.setHeader("Authorization", "Bearer " + accessToken);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String response = client.execute(httpGet, new MyResponseHandler());
            System.out.println(response);
            GetStatusResponseHealthCheck devicResponse = objectMapper.readValue(response, GetStatusResponseHealthCheck.class);

            if(devicResponse.getDeviceWatchDeviceStatus().getValue().equals("online")) return true;
            else return false;
        }catch (Exception e) {
            return false;
        }
    }

    

}

class MyResponseHandler implements ResponseHandler<String>{
 
    public String handleResponse(final HttpResponse response) throws IOException{
 
       //Get the status of the response
       int status = response.getStatusLine().getStatusCode();
       if (status >= 200 && status < 300) {
          HttpEntity entity = response.getEntity();
          if(entity == null) {
             return "";
          } else {
             return EntityUtils.toString(entity);
          }
 
       } else {
          return ""+status;
       }
    }
 }
 