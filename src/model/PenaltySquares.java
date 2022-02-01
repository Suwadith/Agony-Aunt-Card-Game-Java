package model;

import java.util.Map;

public class PenaltySquares {

    private String penaltyName;
	private Map<Counter, Integer> counters;

	public PenaltySquares(String penaltyName) {
		this.penaltyName = penaltyName;
	}

	public String getPenaltyName() {
		return penaltyName;
	}

	public Map<Counter, Integer> getCounters() {
		return counters;
	}

	public void setCounters(Map<Counter, Integer> counters) {
		this.counters = counters;
	}
}
