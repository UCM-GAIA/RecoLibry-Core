package es.ucm.fdi.gaia.recolibry.implementations.jcolibri;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseBaseFilter;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.exception.InitializingException;
import es.ucm.fdi.gaia.recolibry.utils.BeansFactory;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Connector to read CSV file and use it in jCOLIBRI. It transforms the
 * information contained in a CSV file to a list of CBR cases.
 * 
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class CSVConnector implements Connector {
	
	private String fileName;
	private boolean existTitleRow;
	private BeansFactory beansFactory;
	
	private List<CBRCase> cases;
	
	@Inject
	public CSVConnector(@Named("beansFactory") BeansFactory beansFactory, @Named("fileName") String fileName, @Named("existTitleRow") boolean existTitleRow) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		super();
		this.fileName = fileName;
		this.existTitleRow = existTitleRow;
		this.beansFactory = beansFactory;
		init();
	}

	private void init() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, FileNotFoundException {

	    Class<?> clazz = beansFactory.getClazz();
	    List<CaseComponent> beans = new CsvToBeanBuilder(new FileReader("fileName")).withType(clazz).build().parse();

	    cases = new ArrayList<>();
	    for(CaseComponent c : beans) {
	        CBRCase cbrCase = new CBRCase();
	        cbrCase.setDescription(c);
	        cases.add(cbrCase);
        }

	    //try {

			// Open CSV file
			/*FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			CSVReader reader = new CSVReader(isr);
			
			// Create list of cases to save information
			cases = new ArrayList<>();
			
			// Read Cases
			// TODO - Generalizar
			String[] nextLine;
			
			if (existTitleRow)
				reader.readNext();
			
			while ((nextLine = reader.readNext()) != null) {
				//TODO - Estoy hay que darle una pensada de como puede hacerse para clases genéricas
				//TODO - Hay que saber los tipos de datos para castearlos
				List<Object> parameters = new ArrayList<>();
				parameters.add(Integer.valueOf(nextLine[0]));

//				Esto es para Movies

//				parameters.add(nextLine[1]);
//				parameters.add(nextLine[2].split("\\|"));


				Integer[] features = new Integer[28];
				for(int i = 1; i < nextLine.length - 1; i++)
					features[i - 1] = Integer.parseInt(nextLine[i]);

				parameters.add(features);
				parameters.add(nextLine[nextLine.length - 1]);

				CBRCase c = new CBRCase();
				c.setDescription((CaseComponent) beansFactory.getBeanWithParameters(parameters));

				cases.add(c);
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
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
