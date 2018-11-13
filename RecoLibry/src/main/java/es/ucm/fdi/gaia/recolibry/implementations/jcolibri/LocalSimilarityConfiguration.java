package es.ucm.fdi.gaia.recolibry.implementations.jcolibri;

import com.google.inject.Inject;

import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

/**
 * Class to define the local similarity function of a specific
 * attribute in a class. It is used in recommender systems make
 * with jCOLIBRI.
 * 
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class LocalSimilarityConfiguration {
	
	private String attributeName;
	private Class<?> clazz;
	private LocalSimilarityFunction similarityFunction;
	
	@Inject
	public LocalSimilarityConfiguration(String attributeName, Class<?> clazz, LocalSimilarityFunction similarityFunction) {
		super();
		this.attributeName = attributeName;
		this.clazz = clazz;
		this.similarityFunction = similarityFunction;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public LocalSimilarityFunction getSimilarityFunction() {
		return similarityFunction;
	}

}
