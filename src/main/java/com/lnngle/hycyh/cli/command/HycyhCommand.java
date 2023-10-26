package com.lnngle.hycyh.cli.command;

import java.io.PrintWriter;
import java.util.concurrent.Callable;

import org.jline.reader.LineReader;
import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;

@Component 
@Command(name = "", subcommands = {AppCommand.class})
public class HycyhCommand implements Callable<Integer> {
	LineReader lineReader;
	
	public Integer call() throws Exception {
		this.getOut().println("Hycyh Command");
		return 0;
	}
	
	public void setReader(LineReader lineReader) {
		this.lineReader = lineReader;
    }
	
	public PrintWriter getOut() {
		return this.lineReader.getTerminal().writer();
	}
}
