package es.ucm.fdi.gaia.recolibry;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.jiowa.codegen.JiowaCodeGeneratorEngine;
import com.jiowa.codegen.config.JiowaCodeGenConfig;
import es.ucm.fdi.gaia.codegen.tests.ClassGenerator;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.GlobalSimilarityFunction;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import es.ucm.fdi.gaia.recolibry.api.Query;
import es.ucm.fdi.gaia.recolibry.api.RecommenderAlgorithm;
import es.ucm.fdi.gaia.recolibry.examples.test1.GenresSimilarity;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.CSVConnector;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.LocalSimilarityConfiguration;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.QueryJColibri;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.RecommenderJColibri;
import es.ucm.fdi.gaia.recolibry.utils.BeansFactory;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class to make the recommender configuration. It reads a JSON file
 * and make the configuration of the recommender system.
 * 
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class RecommenderSystemConfiguration extends AbstractModule {

    private String file = "";

    private void generateClass() {
        String packageName = "es.ucm.fdi.gaia.recolibry.examples.test1";

        String[] attr1 = new String[] {"id", "Integer"};
        String[] attr2 = new String[] {"title", "String"};
        String[] attr3 = new String[] {"genres", "String[]"};

        List<String[]> attributes = new ArrayList<>();
        attributes.add(attr1);
        attributes.add(attr2);
        attributes.add(attr3);

        JiowaCodeGenConfig config = new JiowaCodeGenConfig("codegen.properties");

        ClassGenerator classGenerator = new ClassGenerator(config);
        classGenerator.setClassName("MovieCase2");
        classGenerator.setPackageName("es.ucm.fdi.gaia.recolibry.examples.test1");
        classGenerator.setAttributes(attributes);

        JiowaCodeGeneratorEngine engine = new JiowaCodeGeneratorEngine(classGenerator);
        engine.start();

        setFile(System.getProperty("user.dir") + "\\src\\main\\java\\" + packageName.replace(".", "\\") + "\\MovieCase2.java");

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
		//TODO - Estoy hay que convertirlo en un fichero JSON que se pueda leer.
        try {
            // Configuración del sistema recomendador
            bind(RecommenderAlgorithm.class).to(RecommenderJColibri.class);
            bind(Query.class).to(QueryJColibri.class);

            generateClass();
            compile();
            Class clazz = Class.forName("es.ucm.fdi.gaia.recolibry.examples.test1.MovieCase2");
            BeansFactory factory = new BeansFactory(clazz);

            bind(BeansFactory.class).annotatedWith(Names.named("beansFactory")).toInstance(factory);
            bind(String.class).annotatedWith(Names.named("fileName")).toInstance("c:\\movies.csv");
            bind(Boolean.class).annotatedWith(Names.named("existTitleRow")).toInstance(true);

            // Make Local Similarity Function
            List<LocalSimilarityConfiguration> configurations = new ArrayList<LocalSimilarityConfiguration>();
            LocalSimilarityConfiguration conf = new LocalSimilarityConfiguration("genres", clazz, new GenresSimilarity());
            configurations.add(conf);

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
