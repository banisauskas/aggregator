package app.klix.aggregator.services;

import app.klix.aggregator.clients.FastClient;
import app.klix.aggregator.data.Aggregate;
import app.klix.aggregator.data.Application;
import app.klix.aggregator.data.ApplicationStatus;
import app.klix.aggregator.data.FastRequest;
import app.klix.aggregator.data.Request;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FastService {

	private static final Pattern PHONE_REGEXP = Pattern.compile("\\+371[0-9]{8}");

	@Autowired
	private FastClient fastClient;

	public Application createApplication(Request request) {
		FastRequest fastReq = new FastRequest();
		fastReq.setPhoneNumber(request.getPhone());
		fastReq.setEmail(request.getEmail());
		fastReq.setMonthlyIncomeAmount(request.getMonthlyIncome());
		fastReq.setMonthlyCreditLiabilities(request.getMonthlyExpenses());
		fastReq.setDependents(request.getDependents());
		fastReq.setAgreeToDataSharing(request.getAgreeToDataSharing());
		fastReq.setAmount(request.getAmount());

		return fastClient.addApplication(fastReq);
	}

	public boolean refetchApplication(Aggregate aggregate) {
		Application fastApp = aggregate.getFastApplication();
		if (finalResult(fastApp)) {
			return false;
		}

		fastApp = fastClient.getApplicationById(fastApp.getId());
		if (fastApp == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		aggregate.setFastApplication(fastApp);
		return true;
	}

	/** Additional request validation */
	public boolean validRequest(Request request) {
		// not all valid request phone numbers are accepted by FastBank
		return PHONE_REGEXP.matcher(request.getPhone()).matches();
	}

	public boolean finalResult(Application fastApp) {
		return fastApp == null || fastApp.getStatus() == ApplicationStatus.PROCESSED;
	}
}