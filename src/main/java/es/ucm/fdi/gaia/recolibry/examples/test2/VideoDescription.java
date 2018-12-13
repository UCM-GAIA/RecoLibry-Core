package es.ucm.fdi.gaia.recolibry.examples.test2;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindAndSplitByName;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

import java.util.LinkedList;
import java.util.List;

public class VideoDescription implements CaseComponent {

    @CsvBindByName
    public Integer id;
    @CsvBindByName
    public String category;

    @CsvBindAndSplitByName(elementType= Integer.class, splitOn="\\|")
    public List<Integer> features;
    
    /**
     * Constructor with all attributes.
     */
    public VideoDescription(Integer id, String category, List<Integer> features)  
    {
        this.id = id;
        this.category = category;
        this.features = features;
    }

    public VideoDescription() { }
    
    public Attribute getIdAttribute() {
        return new Attribute("id", VideoDescription.class);
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
     * Returns the value of type String for attribute category.
     * @return value of category
     */
    public String getCategory()   
    {
        return this.category;
    }

    /**
     * Returns the value of type Integer for attribute features.
     * @return value of features
     */
    public List<Integer > getFeatures()
    {
        return this.features;
    }
    
    // {{ProtectedRegionStart::ManuallyWrittenCode}}    
        // ...
        // insert your customized code here!        
        // ... 
    // {{ProtectedRegionEnd}}
    
}