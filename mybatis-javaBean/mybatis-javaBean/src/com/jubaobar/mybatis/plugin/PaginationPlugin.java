/**
 * 
 */
package com.jubaobar.mybatis.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;



/**
 * @author sunjun
 * 
 */
public class PaginationPlugin extends PluginAdapter {

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		// add field, getter, setter for limit clause
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(PageInfo.class.getName());
		topLevelClass.setSuperClass(type);
		topLevelClass.addImportedType(type);
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}

	public boolean providerGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		// FullyQualifiedJavaType type = new
		// FullyQualifiedJavaType(PageInfo.class.getName());
		// topLevelClass.setSuperClass(type);
		return super.providerGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		XmlElement isParameterPresenteElemen = element;
		XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$  
		isNotNullElement.addAttribute(new Attribute("test", "offset != null and limit != null")); //$NON-NLS-1$ //$NON-NLS-2$  
		isNotNullElement.addElement(new TextElement("limit ${offset}, ${limit}"));
		isParameterPresenteElemen.addElement(isNotNullElement);
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	public boolean validate(List<String> warnings) {
		return true;
	}
}
