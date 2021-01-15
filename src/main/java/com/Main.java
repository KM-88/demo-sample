package com;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import com.dao.StudentDAO;
import com.obj.Student;
import com.util.ProcessBuilderScriptInvoker;
import com.util.PythonScriptInvoker;
import com.util.intf.ScriptInvoke;

public class Main {

	/**
	* Code to test out Some hibernate code and invoking a python script from the code
	*/
	public static void main(String[] args) {
		
		//Create a Student
		StudentDAO studentDao = new StudentDAO();
		Student student = new Student("Tom", "Hanks", "tom.hanks@gmail.com");
		
		//save the student through DAO
		studentDao.saveStudent(student);
		
		//Fetch all Student details
		List<Student> students = studentDao.getStudents();
		//Print their first name
		students.forEach(s -> System.out.println(s.getFirstName()));
		
		//Get the path of the python script file
		Path currentRelativePath =  FileSystems.getDefault().getPath("testPython.py");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		
		ScriptInvoke scriptInvoker;
		
		//Invoke through ProcessBuilder API
		scriptInvoker = new ProcessBuilderScriptInvoker();
		System.out.println(scriptInvoker.invoke("python", s, ""));
		
		//Invoke through RunTime.exec() 
		scriptInvoker = new PythonScriptInvoker();
		scriptInvoker.invoke("python", s, "");
		
	}
}
