package es.ucm.fdi.gaia.recolibry.api;

/**
 * Class that contains the result of a recommendation. Its minimum attributes
 * are the recommended object, for example a movie, and a recommended Value,
 * for example a predicted rating.
 *
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class RecommenderResult {
	
	private Object item;
	private double recommendedValue;

	/**
	 * Build a {@link RecommenderResult} based on a recommended object and
	 * its recommended value.
	 * @param item recommended object.
	 * @param recommendedValue recommended value.
	 */
	public RecommenderResult(Object item, double recommendedValue) {
		super();
		this.item = item;
		this.recommendedValue = recommendedValue;
	}

	/**
	 * Return the recommended object from the recommender system.
	 * @return recommended object.
	 */
	public Object getItem() {
		return item;
	}

	/**
	 * Return the recommended value of the object.
	 * @return recommended value.
	 */
	public double getRecommendedValue() {
		return recommendedValue;
	}

	@Override
	public String toString() {
		return "RecommenderResult [item=" + item + ", recommendedValue=" + recommendedValue + "]";
	}

}
