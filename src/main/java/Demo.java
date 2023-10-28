import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.Credentials;
import org.apache.hc.client5.http.auth.CredentialsStore;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class Demo {
    public static void main(String[] args) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("param", "value");

        try {
            RestTemplate restTemplate = buildRestTemplate();
            String response = restTemplate.postForObject("http://localhost:8080/api.cgi", request, String.class);
            System.out.println(response);
        } catch (RestClientException exception) {
            exception.printStackTrace();
        }
    }

    private static RestTemplate buildRestTemplate() {
        Credentials credentials = new UsernamePasswordCredentials("user", "password".toCharArray());

        CredentialsStore credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(null, -1), credentials);

        HttpClient httpClient = HttpClients.custom()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(requestFactory);
    }
}