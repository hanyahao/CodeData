/**
 * 
 */
package com.jubaobar;

import org.mybatis.generator.api.ShellRunner;

import com.jubaobar.mybatis.plugin.PaginationPlugin;

/**
 * @author sunjun
 * 
 */
public class Main {

	public static void generate() {
		String config = PaginationPlugin.class.getClassLoader().getResource("generatorConfig.xml").getFile();
		String[] arg = { "-configfile", config, "-overwrite" };
		ShellRunner.main(arg);
	}

	public static void main(String[] args) {
		generate();
	}
}