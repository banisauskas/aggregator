package app.klix.aggregator.data;

public class Aggregate {
	private long id;
	/** Dynamically calculated */
	private boolean finalResult;
	private Application fastApplication;
	private Application solidApplication;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isFinalResult() {
		return finalResult;
	}

	public void setFinalResult(boolean finalResult) {
		this.finalResult = finalResult;
	}

	public Application getFastApplication() {
		return fastApplication;
	}

	public void setFastApplication(Application fastApplication) {
		this.fastApplication = fastApplication;
	}

	public Application getSolidApplication() {
		return solidApplication;
	}

	public void setSolidApplication(Application solidApplication) {
		this.solidApplication = solidApplication;
	}
}