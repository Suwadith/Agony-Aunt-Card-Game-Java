package model;

import java.util.Map;

public class PenaltySquares {

    private String penaltySquareName;
	private Map<Counter, Integer> counters;

	public PenaltySquares(String penaltySquareName) {
		this.penaltySquareName = penaltySquareName;
	}

	public String getPenaltySquareName() {
		return penaltySquareName;
	}

	public Map<Counter, Integer> getCounters() {
		return counters;
	}

	public void setCounters(Map<Counter, Integer> counters) {
		this.counters = counters;
	}
}
