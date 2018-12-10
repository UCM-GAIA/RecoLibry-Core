package es.ucm.fdi.gaia.recolibry.examples.test1;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class MovieCase2 implements CaseComponent {

    public Integer id;
    public String title;
    public String[] genres;
 
    
    /**
     * Constructor with all attributes.
     */
    public MovieCase2(Integer id, String title, String[] genres)  
    {
        this.id = id;
        this.title = title;
        this.genres = genres;

    }

    
    public Attribute getIdAttribute() {
        return new Attribute("id", MovieCase2.class);
    }

    /**
     * Returns the value of type Integer for attribute id.
     * @return value of id
     */
    public Integer getId()   
    {
        return this.id;
    }
    
    /**
     * Returns the value of type String for attribute title.
     * @return value of title
     */
    public String getTitle()   
    {
        return this.title;
    }
    
    /**
     * Returns the value of type String[] for attribute genres.
     * @return value of genres
     */
    public String[] getGenres()   
    {
        return this.genres;
    }

    
    // {{ProtectedRegionStart::ManuallyWrittenCode}}    
        // ...
        // insert your customized code here!        
        // ... 
    // {{ProtectedRegionEnd}}
    
}