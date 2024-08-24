package app.klix.aggregator.services;

import app.klix.aggregator.data.Aggregate;
import app.klix.aggregator.data.Request;
import app.klix.aggregator.repositories.CreateRepository;
import app.klix.aggregator.repositories.ReadRepository;
import app.klix.aggregator.repositories.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AggregateService {

	@Autowired
	private FastService fastService;
	@Autowired
	private SolidService solidService;
	@Autowired
	private CreateRepository createRepo;
	@Autowired
	private ReadRepository readRepo;
	@Autowired
	private UpdateRepository updateRepo;

	public Aggregate findAggregate(long id) {
		Aggregate aggregate = readRepo.findAggregate(id);
		if (aggregate == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		boolean changed = fastService.refetchApplication(aggregate);
		if (changed) {
			updateRepo.updateApplication(aggregate.getFastApplication());
		}

		changed = solidService.refetchApplication(aggregate);
		if (changed) {
			updateRepo.updateApplication(aggregate.getSolidApplication());
		}

		updateFinalResult(aggregate);
		return aggregate;
	}

	public Aggregate createAggregate(Request request) {
		Aggregate aggregate = new Aggregate();

		if (fastService.validRequest(request)) {
			aggregate.setFastApplication(fastService.createApplication(request));
		}

		if (solidService.validRequest(request)) {
			aggregate.setSolidApplication(solidService.createApplication(request));
		}

		// succeeds if at least 1 bank request succeeds
		if (aggregate.getFastApplication() == null && aggregate.getSolidApplication() == null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		long id = createRepo.saveAggregate(aggregate);
		
		aggregate.setId(id);
		updateFinalResult(aggregate);
		return aggregate;
	}

	private void updateFinalResult(Aggregate aggregate) {
		boolean fastFinal = fastService.finalResult(aggregate.getFastApplication());
		boolean solidFinal = solidService.finalResult(aggregate.getSolidApplication());
		aggregate.setFinalResult(fastFinal && solidFinal);
	}
}