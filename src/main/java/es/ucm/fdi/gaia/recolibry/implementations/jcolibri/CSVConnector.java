/**
 * RecoLibry-Core Source Code
 * Copyright (C) 2019  Jose L. Jorro-Aragoneses
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package es.ucm.fdi.gaia.recolibry.implementations.jcolibri;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.opencsv.bean.CsvToBeanBuilder;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseBaseFilter;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.exception.InitializingException;
import es.ucm.fdi.gaia.recolibry.utils.BeansFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
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
	public CSVConnector(@Named("beansFactory") BeansFactory beansFactory, @Named("fileName") String fileName, @Named("existTitleRow") boolean existTitleRow) throws FileNotFoundException {
		super();
		this.fileName = fileName;
		this.existTitleRow = existTitleRow;
		this.beansFactory = beansFactory;
		init();
	}

	private void init() throws FileNotFoundException {

	    Class<?> clazz = beansFactory.getClazz();
	    List<CaseComponent> beans = new CsvToBeanBuilder(new FileReader(fileName))
				.withType(clazz)
				.build()
				.parse();

	    cases = new ArrayList<>();
	    for(CaseComponent c : beans) {
	        CBRCase cbrCase = new CBRCase();
	        cbrCase.setDescription(c);
	        cases.add(cbrCase);
        }
	}

	@Override
	public void initFromXMLfile(URL file) throws InitializingException {}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public void storeCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub
	}

	@Override
	public Collection<CBRCase> retrieveAllCases() {
		return cases;
	}

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter filter) {
		return null;
	}
	
}
