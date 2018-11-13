package es.ucm.fdi.gaia.recolibry.implementations.jcolibri;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;

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
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.api.RecommenderResult;
import es.ucm.fdi.gaia.recolibry.examples.test1.GenresSimilarity;
import es.ucm.fdi.gaia.recolibry.examples.test1.MovieCase;

/**
 * Class that implements a content-based recommender algorithm using
 * jCOLIBRI framework. In this class is necessary inject the connector
 * (is the knowledge source of the recommender system).
 * 
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 * TODO - Hay que generalizarlo. Ahora mismo es sólo un ejemplo.
 */
public class RecommenderJColibri implements RecommenderAlgorithm, StandardCBRApplication {
	
	private CBRCaseBase casebase;
	private Connector connector;
	private int kNeighbourhood = 5; // TODO - Esto se injecta también.
	
	private List<RecommenderResult> recommenderResults;

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
		//TODO - Esto tiene que ser inyectado. El connector es una fuente de conocimiento.
		connector = new CSVConnector("src/main/resources/movies.csv", true);
		casebase = new LinealCaseBase();
	}

	public void cycle(CBRQuery query) throws ExecutionException {
		// TODO - En este ejemplo solo se comparan géneros. Hay que buscar una fórmula
		// para generalizar las funciones de similitud.
		NNConfig config = new NNConfig();
		
		config.setDescriptionSimFunction(new Average());
		config.addMapping(new Attribute("genres", MovieCase.class), new GenresSimilarity());
		
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
