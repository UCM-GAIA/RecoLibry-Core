package es.ucm.fdi.gaia.recolibry.examples.test2;

import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.jiowa.codegen.JiowaCodeGeneratorEngine;
import com.jiowa.codegen.config.JiowaCodeGenConfig;
import es.ucm.fdi.gaia.recolibry.utils.ClassGenerator;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.GlobalSimilarityFunction;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecSysConfiguration;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.CSVConnector;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.LocalSimilarityConfiguration;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.QueryJColibri;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.RecommenderJColibri;
import es.ucm.fdi.gaia.recolibry.utils.BeansFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VideosRecSysConfiguration extends RecSysConfiguration {

    @Override
    protected void generateClass() {
        String packageName = "es.ucm.fdi.gaia.recolibry.examples.test2";

        String[] attr1 = new String[] {"id", "Integer"};
        String[] attr2 = new String[] {"features", "Integer"};
        String[] attr3 = new String[] {"category", "String"};

        List<String[]> attributes = new ArrayList<>();
        attributes.add(attr1);
        attributes.add(attr3);

        List<String[]> attributesList = new ArrayList<>();
        attributesList.add(attr2);

        JiowaCodeGenConfig config = new JiowaCodeGenConfig("codegen.properties");

        ClassGenerator classGenerator = new ClassGenerator(config);
        classGenerator.setClassName("VideoDescription");
        classGenerator.setPackageName("es.ucm.fdi.gaia.recolibry.examples.test2");
        classGenerator.setAttributes(attributes);
        classGenerator.setAttributesList(attributesList);

        JiowaCodeGeneratorEngine engine = new JiowaCodeGeneratorEngine(classGenerator);
        engine.start();

        file = System.getProperty("user.dir").replace("\\","/") + "/src/main/java/" + packageName.replace(".", "/") + "/VideoDescription.java";
        file.replace("/", System.getProperty("path.separator"));
    }

    @Override
    protected void configure() {

        try {
            bind(RecommenderAlgorithm.class).to(RecommenderJColibri.class);
            bind(Query.class).to(QueryJColibri.class);

            // Generar dinamicamente la clase para los casos

            generateClass();
            compile();
            Class clazz = null;

            clazz = Class.forName("es.ucm.fdi.gaia.recolibry.examples.test2.VideoDescription");

            BeansFactory factory = new BeansFactory(clazz);

            bind(BeansFactory.class).annotatedWith(Names.named("beansFactory")).toInstance(factory);
            bind(String.class).annotatedWith(Names.named("fileName")).toInstance(System.getProperty("user.dir") + "/csv/videos_features.csv");
            bind(Boolean.class).annotatedWith(Names.named("existTitleRow")).toInstance(true);

            // Make Local Similarity
            List<LocalSimilarityConfiguration> configurations = new ArrayList<>();
            LocalSimilarityConfiguration conf = new LocalSimilarityConfiguration("features", clazz, new FeaturesSimilarity());
           // conf.setWeight(0.5);
            configurations.add(conf);
            LocalSimilarityConfiguration conf2 = new LocalSimilarityConfiguration("category", clazz, new Equal());
           // conf2.setWeight(0.5);
            configurations.add(conf2);


            // Configuración de RecommenderJColibri
            bind(Connector.class).to(CSVConnector.class);
            bind(Integer.class).toInstance(10);
            bind(GlobalSimilarityFunction.class).to(Average.class);
            bind(new TypeLiteral<List<LocalSimilarityConfiguration>>() {}).toInstance(configurations);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
