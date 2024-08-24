package app.klix.aggregator.data;

import java.util.UUID;

public class Application {
	private UUID id;
	private ApplicationStatus status;
	/** Nullable */
	private Offer offer;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
}