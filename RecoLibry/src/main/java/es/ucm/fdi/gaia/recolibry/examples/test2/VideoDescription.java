package es.ucm.fdi.gaia.recolibry.examples.test2;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;

public class VideoDescription implements CaseComponent {

    private Integer id;
    private Integer[] features;
    private String category;

    public VideoDescription(Integer id, Integer[] features, String category) {
        this.id = id;
        this.features = features;
        this.category = category;
    }

    @Override
    public Attribute getIdAttribute() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer[] getFeatures() {
        return features;
    }

    public void setFeatures(Integer[] features) {
        this.features = features;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
