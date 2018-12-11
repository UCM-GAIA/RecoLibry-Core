package es.ucm.fdi.gaia.recolibry.examples.test2;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.jiowa.codegen.JiowaCodeGeneratorEngine;
import com.jiowa.codegen.config.JiowaCodeGenConfig;
import es.ucm.fdi.gaia.codegen.tests.ClassGenerator;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.GlobalSimilarityFunction;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.CSVConnector;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.LocalSimilarityConfiguration;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.QueryJColibri;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.RecommenderJColibri;
import es.ucm.fdi.gaia.recolibry.utils.BeansFactory;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideosRecSysConfiguration extends AbstractModule {

    private String file = "";

    private void generateClass() {
        String packageName = "es.ucm.fdi.gaia.recolibry.examples.test2";

        String[] attr1 = new String[] {"id", "Integer"};
        String[] attr2 = new String[] {"features", "Integer[]"};
        String[] attr3 = new String[] {"category", "String"};

        List<String[]> attributes = new ArrayList<>();
        attributes.add(attr1);
        attributes.add(attr2);
        attributes.add(attr3);

        JiowaCodeGenConfig config = new JiowaCodeGenConfig("codegen.properties");

        ClassGenerator classGenerator = new ClassGenerator(config);
        classGenerator.setClassName("VideoDescription2");
        classGenerator.setPackageName("es.ucm.fdi.gaia.recolibry.examples.test2");
        classGenerator.setAttributes(attributes);

        JiowaCodeGeneratorEngine engine = new JiowaCodeGeneratorEngine(classGenerator);
        engine.start();

        setFile(System.getProperty("user.dir") + "\\src\\main\\java\\" + packageName.replace(".", "\\") + "\\VideoDescription2.java");

    }

    private void compile(){
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            File output = new File("target/classes");
            //output.mkdirs(); // Creo que no hace falta porque ya existe el target/classes :)

            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(output));
            List<File> classesToCompile = new ArrayList<>();
            classesToCompile.add(new File(getFile()));
            Iterable<? extends JavaFileObject> compilationUnits1 = fileManager.getJavaFileObjectsFromFiles(classesToCompile);
            compiler.getTask(null, fileManager, null, null, null, compilationUnits1).call();
        } catch (IOException e) {
            System.out.println("----> Error compilando ficheros");
        }
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

            clazz = Class.forName("es.ucm.fdi.gaia.recolibry.examples.test2.VideoDescription2");

            BeansFactory factory = new BeansFactory(clazz);

            bind(BeansFactory.class).annotatedWith(Names.named("beansFactory")).toInstance(factory);
            bind(String.class).annotatedWith(Names.named("fileName")).toInstance("c:\\videos_features.csv");
            bind(Boolean.class).annotatedWith(Names.named("existTitleRow")).toInstance(true);

            // Make Local Similarity
            List<LocalSimilarityConfiguration> configurations = new ArrayList<>();
            LocalSimilarityConfiguration conf = new LocalSimilarityConfiguration("features", VideoDescription.class, new FeaturesSimilarity());
            configurations.add(conf);
            LocalSimilarityConfiguration conf2 = new LocalSimilarityConfiguration("category", VideoDescription.class, new Equal());
            configurations.add(conf2);


            // Configuración de RecommenderJColibri
            bind(Connector.class).to(CSVConnector.class);
            bind(Integer.class).toInstance(10);
            bind(GlobalSimilarityFunction.class).to(Average.class);
            bind(new TypeLiteral<List<LocalSimilarityConfiguration>>() {}).toInstance(configurations);
        } catch (ClassNotFoundException e) {
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
