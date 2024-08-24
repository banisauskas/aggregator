package app.klix.aggregator.clients;

import app.klix.aggregator.data.Application;
import app.klix.aggregator.data.FastRequest;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class FastClient {

	private final RestClient restClient =
		RestClient.create("https://shop.uat.klix.app/api/FastBank");

	/**
	 * Find previously created application.
	 * Returns application with offer if already available.
	 * @param id Application ID
	 * @return null if not found   
	 * @throws RestClientException rest client error   
	 */
	public Application getApplicationById(UUID id) {
		try {
			return restClient.get()
				.uri("/applications/{id}", id)
				.retrieve()
				.body(Application.class);
		}
		catch (HttpClientErrorException.NotFound e) { // HTTP 404
			return null;
		}
	}

	/**
	 * Submit financing application.
	 * @return null if failed   
	 * @throws RestClientException rest client error   
	 */
	public Application addApplication(FastRequest request) {
		try {
			return restClient.post()
				.uri("/applications")
				.contentType(MediaType.APPLICATION_JSON)
				.body(request)
				.retrieve()
				.body(Application.class);
		}
		catch (HttpClientErrorException.BadRequest e) { // HTTP 400
			return null;
		}
	}
}