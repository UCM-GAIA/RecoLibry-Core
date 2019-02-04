package es.ucm.fdi.gaia.recolibry.api;

/**
 * Interface to define methods necessary in a {@link Query} object.
 *
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public interface Query {

	//TODO - Tiene que haber las siguientes funciones como mínimo (lo dejo para más adelante)
	// getHtmlForm
	// getJsonForm

    /**
     * Method to set the bean object in a query.
     * @param bean object to set in a query.
     */
    void setBean(Object bean);

}
