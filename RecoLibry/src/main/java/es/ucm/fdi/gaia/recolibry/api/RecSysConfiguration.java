package es.ucm.fdi.gaia.recolibry.api;

import com.google.inject.AbstractModule;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RecSysConfiguration extends AbstractModule {

    protected String file;

    protected abstract void generateClass();

    protected void compile() throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        File output = new File("target/classes");
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(output));

        List<File> classesToCompile = new ArrayList<>();
        classesToCompile.add(new File(file));

        Iterable<? extends JavaFileObject> compilationUnits1 = fileManager.getJavaFileObjectsFromFiles(classesToCompile);
        compiler.getTask(null, fileManager, null, null, null, compilationUnits1).call();

    }

}
