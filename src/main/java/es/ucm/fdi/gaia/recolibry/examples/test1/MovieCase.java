package es.ucm.fdi.gaia.recolibry.examples.test1;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindAndSplitByName;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

import java.util.LinkedList;
import java.util.List;

public class MovieCase implements CaseComponent {

    @CsvBindByName
    public Integer id;
    @CsvBindByName
    public String title;

    @CsvBindAndSplitByName(elementType= String.class, splitOn="\\|")
    public List<String> genres;
    
    /**
     * Constructor with all attributes.
     */
    public MovieCase(Integer id, String title, List<String> genres)  
    {
        this.id = id;
        this.title = title;
        this.genres = genres;
    }

    public MovieCase() { }
    
    public Attribute getIdAttribute() {
        return new Attribute("id", MovieCase.class);
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
     * Returns the value of type String for attribute genres.
     * @return value of genres
     */
    public List<String > getGenres()
    {
        return this.genres;
    }
    
    // {{ProtectedRegionStart::ManuallyWrittenCode}}    
        // ...
        // insert your customized code here!        
        // ... 
    // {{ProtectedRegionEnd}}
    
}