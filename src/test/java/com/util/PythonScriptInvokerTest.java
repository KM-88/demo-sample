package com.util;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.util.intf.ScriptInvoke;

public class PythonScriptInvokerTest {

	ScriptInvoke scriptInvoker = new PythonScriptInvoker();

	@Test
	public void invokeTestSuccess() {
		Path currentRelativePath =  FileSystems.getDefault().getPath("testPython.py");
		String s = currentRelativePath.toAbsolutePath().toString();
		String retrunMessage = scriptInvoker.invoke("python", s, "");
		Assertions.assertNotNull(retrunMessage);
		Assertions.assertTrue(retrunMessage.contains("Welcome to Script!It does nothing important"));
	}
	
	@Test
	public void invokeTestFailure() {
		Path currentRelativePath =  FileSystems.getDefault().getPath("test1Python.py");
		String s = currentRelativePath.toAbsolutePath().toString();
		String retrunMessage = scriptInvoker.invoke("python", s, "");
		Assertions.assertNotNull(retrunMessage);
		Assertions.assertTrue(retrunMessage.isBlank());
	}
}
