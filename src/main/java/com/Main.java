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

	public static void main(String[] args) {
		StudentDAO studentDao = new StudentDAO();
		Student student = new Student("Tom", "Hanks", "tom.hanks@gmail.com");
		studentDao.saveStudent(student);
		List<Student> students = studentDao.getStudents();
		students.forEach(s -> System.out.println(s.getFirstName()));
		Path currentRelativePath =  FileSystems.getDefault().getPath("testPython.py");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		
		ScriptInvoke scriptInvoker;
		
		scriptInvoker = new ProcessBuilderScriptInvoker();
		System.out.println(scriptInvoker.invoke("python", s, ""));
		
		scriptInvoker = new PythonScriptInvoker();
		scriptInvoker.invoke("python", s, "");
		
	}
}
