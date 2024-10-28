package mosbach.dhbw.de.smarthome.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

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
import mosbach.dhbw.de.smarthome.model.Routine;

@Service
public class RoutineClientService {

    private static final String BASE_URL = "https://SmartHomeRoutines-optimistic-swan-pf.apps.01.cf.eu01.stackit.cloud/api/intern/routine";
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public boolean updateRoutine(String token, int id, Routine changeRequest) throws IOException {
        RoutineDTO routine = RoutineDTO.convertToDTO(changeRequest);
        String url = BASE_URL + "/" + id;
        HttpPut request = new HttpPut(url); 
        request.setHeader("Authorization", token);
        request.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(objectMapper.writeValueAsString(routine));
        request.setEntity(entity);

        int statuscode = httpClient.execute(request).getStatusLine().getStatusCode() ;
        System.out.println(statuscode);
        return statuscode == 200;
    }

    public Routine getRoutineById(String token, int id, int userId) throws IOException {
        String url = BASE_URL + "/" + id;
        HttpGet request = new HttpGet(url);
        request.setHeader("Authorization", token);

        return RoutineDTO.convertToModel(httpClient.execute(request, new MyResponseHandler<>(RoutineDTO.class)), userId);
    }

    public boolean deleteRoutine(String token, int id) throws IOException {
        String url = BASE_URL + "/" + id;
        System.out.println(url);
        HttpDelete request = new HttpDelete(url);
        request.setHeader("Authorization", token);

        return httpClient.execute(request).getStatusLine().getStatusCode() == 200;
    }

    public List<Routine> getAllRoutines(String token, int userId) throws IOException {
        String url = BASE_URL;
        HttpGet request = new HttpGet(url);
        request.setHeader("Authorization", token);

        List<Routine> routines = new ArrayList<>();
        for(RoutineDTO routine : httpClient.execute(request, new MyResponseHandler<>(AllRoutines.class)).getRoutines()) {
            routines.add(RoutineDTO.convertToModel(routine, userId));
        }
        return routines;
    }

    public boolean createRoutine(String token, Routine routine) throws IOException {
        RoutineDTO routineDTO = RoutineDTO.convertToDTO(routine);
        String url = BASE_URL;
        HttpPost request = new HttpPost(url);
        request.setHeader("Authorization", token);
        request.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(objectMapper.writeValueAsString(routineDTO));
        request.setEntity(entity);

        return httpClient.execute(request).getStatusLine().getStatusCode() == 200;
    }

    public boolean switchRoutine(String token, int id, String state) throws IOException {
        String url = BASE_URL + "/" + id + "/" + state;
        System.out.println(url);
        HttpPost request = new HttpPost(url);
        request.setHeader("Authorization", token);
        int statuscode = httpClient.execute(request).getStatusLine().getStatusCode();
        System.out.println(statuscode);
        return statuscode == 200;
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