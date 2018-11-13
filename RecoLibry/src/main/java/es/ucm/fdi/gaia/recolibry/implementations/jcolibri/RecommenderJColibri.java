package es.ucm.fdi.gaia.recolibry.implementations.jcolibri;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;

import com.google.inject.Inject;

import es.ucm.fdi.gaia.jcolibri.casebase.LinealCaseBase;
import es.ucm.fdi.gaia.jcolibri.cbraplications.StandardCBRApplication;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCaseBase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.RetrievalResult;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.GlobalSimilarityFunction;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.api.RecommenderResult;

/**
 * Class that implements a content-based recommender algorithm using
 * jCOLIBRI framework. In this class is necessary inject the connector
 * (is the knowledge source of the recommender system).
 * 
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class RecommenderJColibri implements RecommenderAlgorithm, StandardCBRApplication {
	
	private Connector connector;
	private int kNeighbourhood;
	private GlobalSimilarityFunction globalSimFunction;
	private List<LocalSimilarityConfiguration> configurations;
	
	private CBRCaseBase casebase;
	
	private List<RecommenderResult> recommenderResults;

	@Inject
	public RecommenderJColibri(Connector connector, int kNeighbourhood, GlobalSimilarityFunction globalSimFunction, List<LocalSimilarityConfiguration> configurations) {
		this.connector = connector;
		this.kNeighbourhood = kNeighbourhood;
		this.globalSimFunction = globalSimFunction;
		this.configurations = configurations;
	}
	
	public boolean init() {
		try {
			configure();
			preCycle();
			return true;
		} catch (ExecutionException e) {
			LogManager.getLogger(RecommenderJColibri.class).error(e);
			return false;
		}
	}

	public List<RecommenderResult> recommend(Query query) {
		try {
			cycle((CBRQuery) query);
			return recommenderResults;
		} catch (ExecutionException e) {
			LogManager.getLogger(RecommenderJColibri.class).error(e);
			return null;
		}
	}

	public boolean close() {
		try {
			postCycle();
			return true;
		} catch (ExecutionException e) {
			LogManager.getLogger(RecommenderJColibri.class).error(e);
			return false;
		}
		
	}
	
	/* jCOLIBRI Methods */
	
	public void configure() throws ExecutionException {
		casebase = new LinealCaseBase();
	}

	public void cycle(CBRQuery query) throws ExecutionException {
		NNConfig config = new NNConfig();
		
		config.setDescriptionSimFunction(globalSimFunction);
		
		for (LocalSimilarityConfiguration conf : configurations)
			config.addMapping(new Attribute(conf.getAttributeName(), conf.getClazz()), conf.getSimilarityFunction());
		
		List<RetrievalResult> rr = NNScoringMethod.evaluateSimilarity(casebase.getCases(), query, config);
		
		recommenderResults = new ArrayList<RecommenderResult>();
		for (int i=0; i < kNeighbourhood; i++) {
			RecommenderResult result = new RecommenderResult(rr.get(i).get_case().getDescription(), rr.get(i).getEval());
			recommenderResults.add(result);
		}
	}

	public void postCycle() throws ExecutionException {
		casebase.close();
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		casebase.init(connector);
		return casebase;
	}

}
