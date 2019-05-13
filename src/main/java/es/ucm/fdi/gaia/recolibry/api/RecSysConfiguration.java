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

import com.google.inject.AbstractModule;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that defines the minimum functions used to configure a recommender system.
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 * @see AbstractModule
 */
public abstract class RecSysConfiguration extends AbstractModule {

    protected String file;

    /**
     * Funtion to make a new class in the recommender system.
     */
    protected abstract void generateClass();

    /**
     * Function to compile the class make by the generateClass function.
     * @throws IOException
     */
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
