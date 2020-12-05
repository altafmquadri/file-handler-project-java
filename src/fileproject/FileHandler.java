package fileproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class FileHandler {

	public static String filepath = "students.txt";
	Set<Student> studentSet = new LinkedHashSet<Student>();

	public void readData(String filepath) {

		try {
			BufferedReader reader;
			reader = new BufferedReader(new FileReader(filepath));
			String line;
			while ((line = reader.readLine()) != null) {
				parseText(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file " + filepath + " could not be found!");
		} catch (IOException e) {
			System.out.println("Something went wrong!");
		}
	}

	public void parseText(String line) {
		String data[] = line.split(",");
		Student student = new Student(Integer.parseInt(data[0]), data[1], data[2]);
		studentSet.add(student);
	}

	public void display() {
		studentSet.forEach(s -> System.out.println(s));
	}

	public void appendData(String filepath, Student student) {
		if (studentSet.contains(student))
			return;

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true));
			writer.newLine();
			writer.append(student.toString());
			writer.close();
			studentSet.add(student);
			System.out.println("\nStudent " + student.getStudentName() + " has been added\n");
		} catch (IOException e) {
			System.out.println("Something went wrong!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FileHandler myFile = new FileHandler();
		myFile.readData(filepath);
		
		myFile.appendData(filepath, new Student(6, "James Earl", "Accounting"));
		myFile.display();

	}
}
