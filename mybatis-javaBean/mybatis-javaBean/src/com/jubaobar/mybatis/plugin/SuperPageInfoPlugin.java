package com.jubaobar.mybatis.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

public class SuperPageInfoPlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		super.modelExampleClassGenerated(topLevelClass, introspectedTable);
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("com.jubaopen.bean.PageInfo");
		topLevelClass.setSuperClass(type);
		System.out.println(topLevelClass);
		return true;
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		XmlElement isGreaterThan = new XmlElement("isGreaterEqual");
		isGreaterThan.addAttribute(new Attribute("property", "totalCount"));
		isGreaterThan.addAttribute(new Attribute("compareValue", "0"));
		element.addElement(isGreaterThan);
		isGreaterThan.addElement(new TextElement(" limit #offset#,#limit#"));
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

}
