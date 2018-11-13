package es.ucm.fdi.gaia.recolibry.implementations.jcolibri;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.opencsv.CSVReader;

import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseBaseFilter;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.exception.InitializingException;
import es.ucm.fdi.gaia.recolibry.examples.test1.MovieCase;

/**
 * Connector to read CSV file and use it in jCOLIBRI. It transforms the
 * information contained in a CSV file to a list of CBR cases.
 * 
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 * TODO - Hay que generalizarlo. Ahora mismo es sólo un ejemplo.
 */
public class CSVConnector implements Connector {
	
	private String fileName;
	private boolean existTitleRow;
	
	private List<CBRCase> cases;
	
	@Inject
	public CSVConnector(@Named("fileName") String fileName, @Named("existTitleRow") boolean existTitleRow) {
		super();
		this.fileName = fileName;
		this.existTitleRow = existTitleRow;
		init();
	}

	private void init() {
		try {
			// Open CSV file
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			CSVReader reader = new CSVReader(isr);
			
			// Create list of cases to save information
			cases = new ArrayList<CBRCase>();
			
			// Read Cases
			// TODO - Generalizar
			String[] nextLine;
			
			if (existTitleRow)
				reader.readNext();
			
			while ((nextLine = reader.readNext()) != null) {
				MovieCase movie = new MovieCase(Integer.valueOf(nextLine[0]), nextLine[1], nextLine[2].split("\\|"));
				CBRCase c = new CBRCase();
				c.setDescription(movie);
				
				cases.add(c);
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void initFromXMLfile(URL file) throws InitializingException {}

	public void close() {
		// TODO Auto-generated method stub
	}

	public void storeCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub
	}

	public void deleteCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub
	}

	public Collection<CBRCase> retrieveAllCases() {
		return cases;
	}

	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter filter) {
		return null;
	}
	
}
