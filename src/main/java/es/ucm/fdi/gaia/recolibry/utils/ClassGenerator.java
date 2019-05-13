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
package es.ucm.fdi.gaia.recolibry.utils;

import java.util.ArrayList;
import java.util.List;

import com.jiowa.codegen.config.JiowaCodeGenConfig;
import com.jiowa.codegen.generator.AbstractGenerator;
import com.model.codegen.beans.AttributeList_jgt;
import com.model.codegen.beans.Attribute_jgt;
import com.model.codegen.beans.CaseComponent_jCOLIBRI_jgt;

/**
 * @author Jose L. Jorro-Aragoneses
 * @version 1.0
 */
public class ClassGenerator extends AbstractGenerator{
	
	private String className;
	private String packageName;
	private List<String[]> attributes; // Position 0 is name, position 1 is type
	private List<String[]> attributesList;
	
	public ClassGenerator(JiowaCodeGenConfig config) {
		super(config);
	}

	@Override
	public void generate() {
		// Create class
		CaseComponent_jCOLIBRI_jgt cbr_class = new CaseComponent_jCOLIBRI_jgt();
		cbr_class.setPackageName(packageName);
		cbr_class.setClassName(className);
		
		// Add attributes
		List<Attribute_jgt> attributes_jgt = new ArrayList<>();
		
		for(String[] a : attributes) {
			Attribute_jgt attr = cbr_class.foreachAttribute.append_Attribute_jgt().setDataType(a[1]).setAttributeName(a[0]);
			attributes_jgt.add(attr);
		}

		// Add attributes List
		List<AttributeList_jgt> attributesList_jgt = new ArrayList<>();

		for(String[] a : attributesList) {
			AttributeList_jgt attr = cbr_class.foreachAttributeList.append_AttributeList_jgt().setDataType(a[1]).setAttributeName(a[0]);
			attributesList_jgt.add(attr);
		}
		
		// Constructor arguments
		for(Attribute_jgt attr : attributes_jgt)
			cbr_class.foreachAttribute.append_Argument_jgt(attr);

		for(AttributeList_jgt attr : attributesList_jgt)
			cbr_class.foreachAttributeList.append_ArgumentList_jgt(attr);

        // Constructor arguments init
        for(Attribute_jgt attr : attributes_jgt)
            cbr_class.foreachAttribute.append_AttributeInit_jgt(attr);

		for(AttributeList_jgt attr : attributesList_jgt)
			cbr_class.foreachAttributeList.append_AttributeListInit_jgt(attr);
		
		// Getters
		for(Attribute_jgt attr : attributes_jgt)
			cbr_class.foreachAttribute.append_Getter_jgt(attr);

		// Getters List
		for(AttributeList_jgt attr : attributesList_jgt)
			cbr_class.foreachAttributeList.append_GetterList_jgt(attr);
		
		// Generate source file
		updateSourceFile("java/" + cbr_class.getPackageName().replace('.', '/') + "/" + cbr_class.getClassName() + ".java", cbr_class);                        

        System.out.println("Finish!!");
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setAttributes(List<String[]> attributes) {
		this.attributes = attributes;
	}

	public void setAttributesList(List<String[]> attributesList) {
		this.attributesList = attributesList;
	}
}
