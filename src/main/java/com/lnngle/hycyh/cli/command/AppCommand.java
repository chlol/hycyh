package com.lnngle.hycyh.cli.command;

import java.util.concurrent.Callable;

import cn.hutool.core.util.StrUtil;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParentCommand;

@Command(name = "app")
public class AppCommand implements Callable<Integer> {
	@Option(names = "--project-name")
	String projectName;
	@Option(names = "--template")
	String template;

	@ParentCommand
	HycyhCommand parent;

	public Integer call() throws Exception {
		while (StrUtil.isEmptyIfStr(projectName)) {
			parent.getOut().println("Enter project name for --project-name: ");
			projectName = parent.lineReader.readLine();
		}

		while (StrUtil.isEmptyIfStr(template)) {
			parent.getOut().println("Enter template for --template: ");
			template = parent.lineReader.readLine();
		}

		parent.getOut().println("project command projectName:" + this.projectName);
		parent.getOut().println("project command template:" + this.template);

		return 0;
	}

	
}
