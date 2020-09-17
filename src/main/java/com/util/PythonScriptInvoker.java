package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.util.intf.ScriptInvoke;

public class PythonScriptInvoker implements ScriptInvoke {

	Process mProcess;
	
	@Override
	public String invoke(String cmdName, String filePath, String args) {
		Process process;
	       try{
	             process = Runtime.getRuntime().exec((new String[]{cmdName,filePath, args}));
	             mProcess = process;	
	       }catch(Exception e) {
	          System.out.println("Exception Raised" + e.toString());
	       }
	       InputStream stdout = mProcess.getInputStream();
	       BufferedReader reader = new BufferedReader(new InputStreamReader(stdout,StandardCharsets.UTF_8));
	       String line, paragraph = "";
	       try{
	          while((line = reader.readLine()) != null){
	               System.out.println(line);
	               paragraph = paragraph.concat(line);
	          }
	       }catch(IOException e){
	             System.out.println("Exception in reading output"+ e.toString());
	             return e.getMessage();
	       }
		return paragraph;
	}

}
