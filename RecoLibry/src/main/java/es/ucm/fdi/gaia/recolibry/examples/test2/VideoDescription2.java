package es.ucm.fdi.gaia.recolibry.examples.test2;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class VideoDescription2 implements CaseComponent {

    public Integer id;
    public Integer[] features;
    public String category; 
    
    /**
     * Constructor with all attributes.
     */
    public VideoDescription2(Integer id, Integer[] features, String category)  
    {
        this.id = id;
        this.features = features;
        this.category = category;
    }
    
    public Attribute getIdAttribute() {
        return new Attribute("id", VideoDescription2.class);
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
     * Returns the value of type Integer[] for attribute features.
     * @return value of features
     */
    public Integer[] getFeatures()   
    {
        return this.features;
    }
    
    /**
     * Returns the value of type String for attribute category.
     * @return value of category
     */
    public String getCategory()   
    {
        return this.category;
    }
    
    // {{ProtectedRegionStart::ManuallyWrittenCode}}    
        // ...
        // insert your customized code here!        
        // ... 
    // {{ProtectedRegionEnd}}
    
}