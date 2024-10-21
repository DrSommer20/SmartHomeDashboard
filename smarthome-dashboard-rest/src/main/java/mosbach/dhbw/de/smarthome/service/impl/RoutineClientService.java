package mosbach.dhbw.de.smarthome.service.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import mosbach.dhbw.de.smarthome.dto.AllRoutines;
import mosbach.dhbw.de.smarthome.dto.RoutineDTO;

@Service
public class RoutineClientService {

    private static final String BASE_URL = "http://SmartHomeRoutines-optimistic-swan-pf.apps.01.cf.eu01.stackit.cloud/api/intern/routine";
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public boolean updateRoutine(String token, int id, RoutineDTO changeRequest) throws IOException {
        String url = BASE_URL + "/" + id;
        HttpPut request = new HttpPut(url);
        request.setHeader("Authorization", token);
        request.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(objectMapper.writeValueAsString(changeRequest));
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public RoutineDTO getRoutineById(String token, int id) throws IOException {
        String url = BASE_URL + "/" + id;
        HttpGet request = new HttpGet(url);
        request.setHeader("Authorization", token);

        return httpClient.execute(request, new MyResponseHandler<>(RoutineDTO.class));
    }

    public boolean deleteRoutine(String token, int id) throws IOException {
        String url = BASE_URL + "/" + id;
        HttpDelete request = new HttpDelete(url);
        request.setHeader("Authorization", token);

        HttpResponse response = httpClient.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public AllRoutines getAllRoutines(String token) throws IOException {
        String url = BASE_URL;
        HttpGet request = new HttpGet(url);
        request.setHeader("Authorization", token);

        return httpClient.execute(request, new MyResponseHandler<>(AllRoutines.class));
    }

    public boolean createRoutine(String token, RoutineDTO routine) throws IOException {
        String url = BASE_URL;
        HttpPost request = new HttpPost(url);
        request.setHeader("Authorization", token);
        request.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(objectMapper.writeValueAsString(routine));
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public boolean switchRoutine(String token, int id, String state) throws IOException {
        String url = BASE_URL + "/" + id + "/" + state;
        HttpPost request = new HttpPost(url);
        request.setHeader("Authorization", token);

        HttpResponse response = httpClient.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }

    public static void main(String[] args) {
        RoutineClientService routineClientService = new RoutineClientService();
        try {
            System.out.println(routineClientService.getAllRoutines("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXhAbXVzdGVybWFubi5kZSIsImlhdCI6MTcyOTU0NDAyOCwiZXhwIjoxNzI5NTUxMjI4fQ.uJ6_xvo9udNM0lmj_j_lXkrTnvgU1CvdiCsYUZRxtaI").getRoutines().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MyResponseHandler<T> implements ResponseHandler<T> {
        private final Class<T> responseType;

        public MyResponseHandler(Class<T> responseType) {
            this.responseType = responseType;
        }

        @Override
        public T handleResponse(HttpResponse response) throws IOException {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                String json = entity != null ? EntityUtils.toString(entity) : null;
                return objectMapper.readValue(json, responseType);
            } else {
                throw new IOException("Unexpected response status: " + status);
            }
        }
    }
}