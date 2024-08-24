package app.klix.aggregator.services;

import app.klix.aggregator.clients.SolidClient;
import app.klix.aggregator.data.Aggregate;
import app.klix.aggregator.data.Application;
import app.klix.aggregator.data.ApplicationStatus;
import app.klix.aggregator.data.Request;
import app.klix.aggregator.data.SolidRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SolidService {

	@Autowired
	private SolidClient solidClient;

	public Application createApplication(Request request) {
		SolidRequest solidReq = new SolidRequest();
		solidReq.setPhone(request.getPhone());
		solidReq.setEmail(request.getEmail());
		solidReq.setMonthlyIncome(request.getMonthlyIncome());
		solidReq.setMonthlyExpenses(request.getMonthlyExpenses());
		solidReq.setMaritalStatus(request.getMaritalStatus());
		solidReq.setAgreeToBeScored(request.getAgreeToBeScored());
		solidReq.setAmount(request.getAmount());

		return solidClient.addApplication(solidReq);
	}

	public boolean refetchApplication(Aggregate aggregate) {
		Application solidApp = aggregate.getSolidApplication();
		if (finalResult(solidApp)) {
			return false;
		}

		solidApp = solidClient.getApplicationById(solidApp.getId());
		if (solidApp == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		aggregate.setSolidApplication(solidApp);
		return true;
	}

	/** Additional request validation */
	public boolean validRequest(Request request) {
		return true; // always valid
	}

	public boolean finalResult(Application solidApp) {
		return solidApp == null || solidApp.getStatus() == ApplicationStatus.PROCESSED;
	}
}