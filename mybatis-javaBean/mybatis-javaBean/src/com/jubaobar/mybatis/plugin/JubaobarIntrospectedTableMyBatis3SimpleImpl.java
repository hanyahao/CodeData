package com.jubaobar.mybatis.plugin;

import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

/**
 * 聚宝吧生成mybatis的时候，数据库接口用DAO结尾
 * 
 * @author wuyajun
 */
public class JubaobarIntrospectedTableMyBatis3SimpleImpl extends IntrospectedTableMyBatis3Impl {

	@Override
	protected void calculateJavaClientAttributes() {
		if (context.getJavaClientGeneratorConfiguration() == null) {
			return;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(calculateJavaClientImplementationPackage());
		sb.append('.');
		sb.append(fullyQualifiedTable.getDomainObjectName());
		sb.append("DAOImpl"); //$NON-NLS-1$
		setDAOImplementationType(sb.toString());

		sb.setLength(0);
		sb.append(calculateJavaClientInterfacePackage());
		sb.append('.');
		sb.append(fullyQualifiedTable.getDomainObjectName());
		sb.append("DAO"); //$NON-NLS-1$
		setDAOInterfaceType(sb.toString());

		sb.setLength(0);
		sb.append(calculateJavaClientInterfacePackage());
		sb.append('.');
		sb.append(fullyQualifiedTable.getDomainObjectName());
		sb.append("DAO"); //$NON-NLS-1$
		setMyBatis3JavaMapperType(sb.toString());

		sb.setLength(0);
		sb.append(calculateJavaClientInterfacePackage());
		sb.append('.');
		sb.append(fullyQualifiedTable.getDomainObjectName());
		sb.append("SqlProvider"); //$NON-NLS-1$
		setMyBatis3SqlProviderType(sb.toString());
	}

}
