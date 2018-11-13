package es.ucm.fdi.gaia.recolibry.api;

public class RecommenderResult {
	
	private Object item;
	private double recommendedValue;
	
	public RecommenderResult(Object item, double recommendedValue) {
		super();
		this.item = item;
		this.recommendedValue = recommendedValue;
	}

	public Object getItem() {
		return item;
	}

	public double getRecommendedValue() {
		return recommendedValue;
	}

	@Override
	public String toString() {
		return "RecommenderResult [item=" + item + ", recommendedValue=" + recommendedValue + "]";
	}

}
