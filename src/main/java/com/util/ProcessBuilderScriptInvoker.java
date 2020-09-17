package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.util.intf.ScriptInvoke;

public class ProcessBuilderScriptInvoker implements ScriptInvoke {

	@Override
	public String invoke(String cmdName, String filePath, String args) {
		ProcessBuilder processBuilder = new ProcessBuilder(cmdName, filePath);
		processBuilder.redirectErrorStream(true);

		Process process;
		try {
			process = processBuilder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		String line = null, paragraph = "";
		InputStream inputStream = process.getInputStream();
		BufferedInputStream bufferStream = new BufferedInputStream(inputStream);
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			while ((line = bufReader.readLine()) != null) {
				System.out.println(line);
				paragraph = paragraph.concat(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			int exitCode = process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return paragraph;
	}

}
