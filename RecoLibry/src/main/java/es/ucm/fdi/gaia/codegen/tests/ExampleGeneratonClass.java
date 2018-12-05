package es.ucm.fdi.gaia.codegen.tests;

import java.util.List;

public class ExampleGeneratonClass {

	public static void printClasses(List<Object> parameters) {
		for(Object o : parameters)
			System.out.println(o.getClass().toString());
	}
	
	public static void main(String[] args) {

		/*String className = "MovieCase2";
		String packageName = "es.ucm.fdi.gaia.recolibry.examples.test1";
		
		String[] attr1 = new String[] {"id", "int"};
		String[] attr2 = new String[] {"tittle", "String"};
		String[] attr3 = new String[] {"genres", "String[]"};
		
		List<String[]> attributes = new ArrayList<String[]>();
		attributes.add(attr1);
		attributes.add(attr2);
		attributes.add(attr3);
		
		JiowaCodeGenConfig config = new JiowaCodeGenConfig("codegen.properties");
		
		ClassGenerator classGenerator = new ClassGenerator(config);
		classGenerator.setClassName(className);
		classGenerator.setPackageName(packageName);
		classGenerator.setAttributes(attributes);
		
		JiowaCodeGeneratorEngine engine = new JiowaCodeGeneratorEngine(classGenerator);
		engine.start();*/

		/*List<Object> parameters = new ArrayList<>();
		parameters.add(1);
		parameters.add("Hola");
		parameters.add(5.0);
		parameters.add(true);
		parameters.add(1L);

		ExampleGeneratonClass.printClasses(parameters);*/



       /*try {
            BeansFactory factory = new BeansFactory(MovieCase2.class);

            List<Object> parameters = new ArrayList<>();
            parameters.add(1);
            parameters.add("Esto es una peli");
            parameters.add(new String[]{"Adventure", "Horror"});

            MovieCase2 m = (MovieCase2) factory.getBeanWithParameters(parameters);

            System.out.println(m.getId());
            System.out.println(m.getTittle());
            System.out.println(m.getGenres());

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }*/

    }

}
