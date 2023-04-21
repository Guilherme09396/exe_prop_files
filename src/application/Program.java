package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Product;
//home/guilhermegom/ws-eclipse/exe_pro_files/in.csv

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Product> list = null;
		
		String path = sc.nextLine();
		try {
			list = getInfo(path);
			setInfo(path, list);
			System.out.println("Summary.csv created!");
		} 
		catch(IOException e ) {
			System.out.println("Error :" + e.getMessage());
		}
	}
	
	public static List<Product> getInfo(String path) throws IOException {
		List<Product> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while(line != null) {
				String[] test = line.split(",");
				String name = test[0];
				double price = Double.valueOf(test[1]).doubleValue();
				int quantity = Integer.parseInt(test[2]);
				list.add(new Product(name, price, quantity));
				line = br.readLine();
			}
		}
		return list;
	}
	
	public static void setInfo(String path, List<Product> products) {
		File file = new File(path);
		new File(file.getParent() + "//out").mkdir();
		path = file.getParent() + "//out/summary.csv";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (Product prod : products) {
				bw.write(prod.toString());
				bw.newLine();
			}
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
