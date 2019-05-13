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
package es.ucm.fdi.gaia.recolibry.api;

import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.jiowa.codegen.JiowaCodeGeneratorEngine;
import com.jiowa.codegen.config.JiowaCodeGenConfig;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import es.ucm.fdi.gaia.recolibry.implementations.jcolibri.LocalSimilarityConfiguration;
import es.ucm.fdi.gaia.recolibry.utils.BeansFactory;
import es.ucm.fdi.gaia.recolibry.utils.ClassGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to build a recommender system using a configuration file.
 *
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class JsonRecSysConfiguration extends RecSysConfiguration {

    private JSONObject configObject;
    private JSONObject generateClass;
    private String generatedClassName;

    /**
     * Constructor of a {@link JsonRecSysConfiguration} object. It
     * needs the path of the configuration file.
     * @param jsonPath path where is the configuration file.
     */
    public JsonRecSysConfiguration(String jsonPath) {
        try {

            FileReader file = new FileReader(jsonPath);
            JSONParser parser = new JSONParser();
            configObject = (JSONObject) parser.parse(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void generateClass() {
        String packageName = (String) generateClass.get("packageName");
        String name = (String) generateClass.get("className");

        // Read Attributes
        JSONArray attJSON = (JSONArray) generateClass.get("attributes");
        List<String[]> attributes = getAttributes(attJSON);

        // Read Attributes List
        JSONArray attListJson = (JSONArray) generateClass.get("attributeList");
        List<String[]> attributesList = getAttributes(attListJson);

        // Make class generator
        JiowaCodeGenConfig config = new JiowaCodeGenConfig("codegen.properties");

        ClassGenerator classGenerator = new ClassGenerator(config);
        classGenerator.setClassName(name);
        classGenerator.setPackageName(packageName);
        classGenerator.setAttributes(attributes);
        classGenerator.setAttributesList(attributesList);

        // Build java file
        JiowaCodeGeneratorEngine engine = new JiowaCodeGeneratorEngine(classGenerator);
        engine.start();

        file = "./src/main/java/" + packageName.replace(".", "/") + "/" + name + ".java";
        file.replace("/", System.getProperty("path.separator"));

        generatedClassName = packageName + "." + name;
    }

    private List<String[]> getAttributes(JSONArray attJSON) {
        List<String[]> attributes = new ArrayList<>();

        for(int i = 0; i < attJSON.size(); i++) {
            String nameAtt = (String) ((JSONObject) attJSON.get(i)).get("name");
            String typeAtt = (String) ((JSONObject) attJSON.get(i)).get("type");

            String[] attr = new String[]{nameAtt, typeAtt};
            attributes.add(attr);
        }

        return attributes;
    }

    @Override
    protected void configure() {

        try {
            if (configObject.containsKey("generatedClass")) {
                generateClass = (JSONObject) configObject.get("generatedClass");

                generateClass();
                compile();

                // Create Bean Factory
                Class clazz = Class.forName(generatedClassName);
                BeansFactory factory = new BeansFactory(clazz);

                bind(BeansFactory.class)
                        .annotatedWith(Names.named("beansFactory"))
                        .toInstance(factory);

            }

            JSONArray injections = (JSONArray) configObject.get("injections");
            for (int i = 0; i < injections.size(); i++) {

                JSONObject inject = (JSONObject) injections.get(i);
                String type = (String) inject.get("type");
                String bind = (String) inject.get("bind");
                String annotated = (String) inject.get("annotated");
                Object to = inject.get("to");

                if (inject.containsKey("similarities")) {

                    JSONArray similarities = (JSONArray) inject.get("similarities");
                    List<LocalSimilarityConfiguration> simConfigurations = new ArrayList<>();

                    for(int j = 0; j < similarities.size(); j++) {
                        JSONObject sim = (JSONObject) similarities.get(j);

                        String attribute = (String) sim.get("attribute");
                        String clazzStr = (String) sim.get("class");
                        String functionObject = (String) sim.get("similarities");

                        Class clazz = Class.forName(clazzStr);
                        Class clazzFunction = Class.forName(functionObject);
                        Object simObject = clazzFunction.getConstructor().newInstance();

                        LocalSimilarityConfiguration conf = new LocalSimilarityConfiguration(attribute, clazz, (LocalSimilarityFunction) simObject);
                        simConfigurations.add(conf);
                    }

                    bind(new TypeLiteral<List<LocalSimilarityConfiguration>>(){}).toInstance(simConfigurations);

                } else {
                    Class fromClazz;
                    Class toClazz;

                    switch (type) {
                        case "Class":
                            fromClazz = Class.forName(bind);
                            toClazz = Class.forName((String) to);

                            bind(fromClazz).to(toClazz);

                            break;
                        case "Instance":
                            fromClazz = Class.forName(bind);

                            if (annotated != null)
                                bind(fromClazz).annotatedWith(Names.named(annotated)).toInstance(to);
                            else
                                bind(fromClazz).toInstance(to);

                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
