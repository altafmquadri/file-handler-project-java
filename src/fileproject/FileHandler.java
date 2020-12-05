package fileproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;


public class FileHandler {
	
	Set <Student> studentList = new LinkedHashSet<Student>();
	File file; 

	public void readData(String f) {
		try {
			//create a file
			file = new File(f);
			//instantiate the buffered 
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			
			while((line = reader.readLine()) != null) {
				parseText(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void parseText(String line) {
		String [] student = line.split(",");
		Student s = new Student(Integer.parseInt(student[0]), student[1], student[2]);
		studentList.add(s);
	}
	
	public void display() {
		studentList.forEach(s -> System.out.println(s));
	}
	
	public void appendData(String f, Student s) {
		file = new File(f);
		if (studentList.contains(s)) return;
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			writer.newLine();
			writer.append(s.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FileHandler myFile = new FileHandler();
		myFile.readData("students.txt");

		Student st = new Student(4, "Mo Dna", "Genetics");
		myFile.appendData("students.txt", st);
		myFile.readData("students.txt");
		
		Student st1 = new Student(5, "Vivica", "Sports");
		myFile.appendData("students.txt", st1);
		myFile.readData("students.txt");
		
		myFile.display();


	}
}
