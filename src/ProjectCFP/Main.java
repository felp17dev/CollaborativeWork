/** This program consists of a menu in which you can choose 
 * the .txt document to display.
 *Authors:
 *Santiago Aranda Hurtado
 *Andres David Arias Combita
 *Luis Felipe Ayala Fernández
 *Leidy Johana Lopez Rincon
 *Santiago Valencia Marin
 *Subgrupo 14
 */

package ProjectCFP;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {

	/**
	 * The main method, which serves as the entry point to the program. It creates
	 * an instance of the GenerateInfoFiles class and calls the Start method.
	 *
	 * @param args command line arguments (not used) @
	 */

	public static void main(String[] args) {

		try {
			String productsFilePath = "src/formats/Product Information File Format.csv";
			String sellersFilePath = "src/formats/Seller Information File Format.csv";
			String inputFolderPath = "src/inputs";
			String outputFolderPath = "src/outputs";
			String outputSellerFilePath = "src/outputs/SalesReport.csv";
			String outputProductFilePath = "src/outputs/ProductReport.csv";

			SalesProcessor salesProcessor = new SalesProcessor();
			salesProcessor.processSales(productsFilePath, sellersFilePath, inputFolderPath, outputSellerFilePath);
			ProductSalesProcessor productProcessor = new ProductSalesProcessor();
			productProcessor.processProductSales(productsFilePath, inputFolderPath, outputProductFilePath);
			fileReader reader = new fileReader();
			reader.readCSVFiles(outputFolderPath);
			System.out.println("Main has finished");
			System.out.println("-------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static class SalesProcessor {

		private Map<String, Double> sellerSalesMap = new HashMap<>();

		public void processSales(String productsFilePath, String sellersFilePath, String inputFolderPath,
				String outputFilePath) throws IOException {
			Map<String, Double> productPrices = readProductPrices(productsFilePath);
			Map<String, String> sellerNames = readSellerNames(sellersFilePath);
			Files.list(Paths.get(inputFolderPath)).filter(Files::isRegularFile).forEach(file -> {
				try {
					processSalesFile(file.toString(), productPrices, sellerNames);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			generateSalesReport(outputFilePath, sellerNames);
			System.out.println("      *****************************************************");
			System.out.println("      *                   SALES REPORT                    *");
			System.out.println("      *****************************************************");
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Sales report file was created.");
			System.out.println("-------------------------------------------------------");
		}

		private Map<String, Double> readProductPrices(String filePath) throws IOException {
			Map<String, Double> productPrices = new HashMap<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
				String line;
				reader.readLine();
				reader.readLine();
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(";");
					String productId = parts[0];
					double price = Double.parseDouble(parts[2]);
					productPrices.put(productId, price);
				}
			}
			return productPrices;
		}

		private Map<String, String> readSellerNames(String filePath) throws IOException {
			Map<String, String> sellerNames = new HashMap<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
				String line;
				reader.readLine();
				reader.readLine();
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(";");
					String documentNumber = parts[1];
					String sellerFullName = parts[2] + " " + parts[3];
					sellerNames.put(documentNumber, sellerFullName);
				}
			}
			return sellerNames;
		}

		private void processSalesFile(String filePath, Map<String, Double> productPrices,
				Map<String, String> sellerNames) throws IOException {
			try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
				String line = reader.readLine();
				String[] header = line.split(";");
				String sellerId = header[1];
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(";");
					String productId = parts[0];
					int quantity = Integer.parseInt(parts[1]);
					double productPrice = productPrices.getOrDefault(productId, 0.0);
					double totalSale = productPrice * quantity;
					sellerSalesMap.put(sellerId, sellerSalesMap.getOrDefault(sellerId, 0.0) + totalSale);
				}
			}
		}

		private void generateSalesReport(String outputFilePath, Map<String, String> sellerNames) throws IOException {
			List<Map.Entry<String, Double>> sortedSales = new ArrayList<>(sellerSalesMap.entrySet());
			sortedSales.sort((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()));
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
				writer.write("Sales Report\n");
				writer.write("Seller Name;Total Sold\n");
				for (Map.Entry<String, Double> entry : sortedSales) {
					String sellerId = entry.getKey();
					double totalSold = entry.getValue();
					String fullName = sellerNames.get(sellerId);
					writer.write(fullName + ";" + String.format("%.2f", totalSold) + "\n");
				}
			}
		}
	}

	private static class ProductSalesProcessor {

		private Map<String, Integer> productSalesMap = new HashMap<>();

		public void processProductSales(String productsFilePath, String inputFolderPath, String outputFilePath)
				throws IOException {
			Map<String, String> productNames = readProductNames(productsFilePath);
			Files.list(Paths.get(inputFolderPath)).filter(Files::isRegularFile).forEach(file -> {
				try {
					processSalesFile(file.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			generateProductReport(outputFilePath, productNames);

			System.out.println("      *****************************************************");
			System.out.println("      *           PRODUCT SALES REPORT                    *");
			System.out.println("      *****************************************************");
			System.out.println("Product sales report file was created.");
			System.out.println("-------------------------------------------------------");
		}

		private Map<String, String> readProductNames(String filePath) throws IOException {
			Map<String, String> productNames = new HashMap<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
				String line;
				reader.readLine();
				reader.readLine();
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(";");
					String productId = parts[0];
					String productName = parts[1];
					productNames.put(productId, productName);
				}
			}
			return productNames;
		}

		private void processSalesFile(String filePath) throws IOException {
			try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
				String line = reader.readLine();
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(";");
					String productId = parts[0];
					int quantity = Integer.parseInt(parts[1]);
					productSalesMap.put(productId, productSalesMap.getOrDefault(productId, 0) + quantity);
				}
			}
		}

		private void generateProductReport(String outputFilePath, Map<String, String> productNames) throws IOException {
			List<Map.Entry<String, Integer>> sortedProducts = new ArrayList<>(productSalesMap.entrySet());
			sortedProducts.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
				writer.write("Product Sales Report\n");
				writer.write("Product Name;Quantity Sold\n");
				for (Map.Entry<String, Integer> entry : sortedProducts) {
					String productId = entry.getKey();
					int totalQuantity = entry.getValue();
					String productName = productNames.get(productId);
					writer.write(productName + ";" + totalQuantity + "\n");
				}
			}
		}
	}

	private static class fileReader {

		private void readCSVFiles(String directoryPath) {
			try {
				Files.list(Paths.get(directoryPath)).filter(Files::isRegularFile)
						.forEach(file -> readCSV(file.toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void readCSV(String filePath) {
			try {
				List<String> lines = Files.readAllLines(Paths.get(filePath));
				System.out.println("Reading file: " + filePath);
				for (String line : lines) {
					System.out.println(line);
					
				}
				
	
				System.out.println("-------------------------------------------------------");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}