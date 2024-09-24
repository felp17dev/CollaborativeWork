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
import javax.swing.JOptionPane;


/**
 * This class handles the generation and display of information files.
 * It provides options to see a list of vendors, a product listing, 
 * and a sales report.
 */

/**
 * 
 */
public class GenerateInfoFiles {

	/**
	 * A static instance of Scanner to receive user input from the keyboard.
	 */

	static Scanner keyBoard = new Scanner(System.in);

	/**
	 * Starts the main menu where the user can select different options to view
	 * vendor information, product information, or sales reports. The menu runs in a
	 * loop until the user chooses to exit.
	 */

	public void Start() {

		int opcion = 0, subopcion = 0;
		int IDSeller = 0;

		do {
			System.out.println("----------Menu---------- ");
			System.out.println("1. Sellers List");
			System.out.println("2. Products List");
			System.out.println("3. Sales");
			System.out.println("4. Exit");
			System.out.println("Pick your choise");
			System.out.println();

			opcion = keyBoard.nextInt();

			switch (opcion) {

			case 1:

				ArchiveSellerInfo();
				break;

			case 2:

				ArchiveProductInfo();
				break;

			case 3:
				do {
					System.out.println("----------Sales---------- ");
					System.out.println("1. Sales x Seller");
					System.out.println("2. Sales x Product");
					System.out.println("3. Sales Report");
					System.out.println("4. Back");
					System.out.println("Pick your choise");
					System.out.println();
					subopcion = keyBoard.nextInt();

					switch (subopcion) {

					case 1:
						SalesSeller();
						break;
					case 2:
						SalesProduct();
						break;
					case 3:
						SalesReport();
						break;
					case 4:
						break;
					}

				} while (subopcion != 4);

				break;

			case 4:
				break;
			}

		} while (opcion != 4);

		System.out.println("----------Sesion finished----------");
	}

	/**
	 * Reads and displays the contents of the SellerInfo.txt file. If the file is
	 * not found or cannot be loaded, an error message is displayed.
	 */

	public void ArchiveSellerInfo() {

		int opcion = 0;

		Path path = Path.of("SellerInfo.txt");
		try (Scanner scanner = new Scanner(path)) {

			System.out.println("      *****************************************************");
			System.out.println("      *           ARCHIVO INFORMACIÓN VENDEDORES          *");
			System.out.println("      *****************************************************");
			System.out.println("-------------------------------------------------------------------");
			System.out.println("TipoDocumento;NúmeroDocumento;NombresVendedor;ApellidosVendedor");
			System.out.println("-------------------------------------------------------------------");

			while (scanner.hasNextLine()) {
				String ArchiveSellerInfo = scanner.nextLine();
				System.out.println(ArchiveSellerInfo);
			}
			System.out.println("-------------------------------------------------------------------");

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "El archivo no pudo ser cargado" + e);
		}
		System.out.println();
	}

	/**
	 * Reads and displays the contents of the ProductInfo.txt file. If the file is
	 * not found or cannot be loaded, an error message is displayed.
	 */

	public void ArchiveProductInfo() {

		int opcion = 0;

		Path path = Path.of("ProductInfo.txt");
		try (Scanner scanner = new Scanner(path)) {

			System.out.println("   *****************************************************");
			System.out.println("   *           ARCHIVO INFORMACIÓN PRODUCTOS           *");
			System.out.println("   *****************************************************");
			System.out.println("------------------------------------------------------------");
			System.out.println("IDProducto;NombreProducto;PrecioPorUnidadProducto");
			System.out.println("------------------------------------------------------------");

			while (scanner.hasNextLine()) {
				String ArchiveProductInfo = scanner.nextLine();
				System.out.println(ArchiveProductInfo);

			}
			System.out.println("------------------------------------------------------------");

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "El archivo no pudo ser cargado" + e);
		}
		System.out.println();
	}

	/**
	 * Reads and displays the contents of the seller1Report.txt file. If the file is
	 * not found or cannot be loaded, an error message is displayed.
	 */

	// Lista se ventas por vendedor

	public void SalesSeller() {

		int opcion = 0, subopcion = 0;
		int IDSeller;

		System.out.println("-------Buscar vendedor-----");
		System.out.println("Ingrese ID del vendedor: ");
		IDSeller = keyBoard.nextInt();

		Path path = Path.of("Seller" + IDSeller + ".txt");
		File archivo = new File("Seller" + IDSeller + ".txt");

		if (!archivo.exists()) {
			System.out.println();
			System.out.println("ERROR!! No existe información de ventas del vendedor ingresado");
			System.out.println();
		} else {
			try (Scanner scanner = new Scanner(path)) {

				System.out.println("      *****************************************************");
				System.out.println("      *         REPORTE DE VENTAS POR VENDEDORES          *");
				System.out.println("      *****************************************************");
				System.out.println("-------------------------------------------------------------------");
				System.out.println("TipoDocumentoVendedor;NúmeroDocumentoVendedor");
				System.out.println("-------------------------------------------------------------------");

				while (scanner.hasNextLine()) {
					String SalesSeller = scanner.nextLine();
					System.out.println(SalesSeller);
				}
				System.out.println("-------------------------------------------------------------------");

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "El archivo no pudo ser cargado" + e);
			}
			System.out.println();
		}
	}

	// Lista se ventas por producto

	public void SalesProduct() {

		int opcion = 0, subopcion = 0;
		
    	String fileFormatPath = "src/formats/";
    	String filePathInp = "src/inputs/";
    	
    	createProductsFile writerP = new createProductsFile();
        writerP.writeCSV(fileFormatPath);
        System.out.println("-------------------------------------------------------");
        createSellersFile writerS = new createSellersFile();
        writerS.writeCSV(fileFormatPath);
        System.out.println("-------------------------------------------------------");
        createSalesMenFile generator = new createSalesMenFile();
        generator.generateSalesFilesForSellers(fileFormatPath,filePathInp);
        System.out.println("-------------------------------------------------------");
        fileReader reader = new fileReader();
        reader.readCSVFiles(fileFormatPath);
        reader.readCSVFiles(filePathInp);
        System.out.println("GenerateInfoFiles has finished");
        System.out.println("-------------------------------------------------------");
        
    }

    private static class createProductsFile {
        public void writeCSV(String filePath) {
        	filePath = filePath + "Product Information File Format.csv";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
                writer.write("Product Information File Format");
                writer.newLine();
                writer.write("ID Product;Name Product;Price Per Unit Product");
                writer.newLine();
                writer.write("R0001;Radeon RX 7900 XTX;899.99");
                writer.newLine();
                writer.write("R0002;Radeon RX 7900 XT;689.99");
                writer.newLine();
                writer.write("R0003;Radeon RX 7800 XT;479.99");
                writer.newLine();
                writer.write("R0004;Radeon RX 7700 XT;399.99");
                writer.newLine();
                writer.write("R0005;Radeon RX 7600 XT;309.99");
                writer.newLine();
                writer.write("R0006;Radeon RX 7600;249.99");
                System.out.println("The products file was created.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static class createSellersFile {
        public void writeCSV(String filePath) {
        	filePath = filePath + "Seller Information File Format.csv";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
                writer.write("Seller Information File Format:");
                writer.newLine();
                writer.write("Document Type;Document Number;Seller Names;Seller Surnames");
                writer.newLine();
                writer.write("CC;100326038;Santiago;Aranda Hurtado");
                writer.newLine();
                writer.write("CC;100335162;Andres David;Arias Combita");
                writer.newLine();
                writer.write("CC;100307123;Luis Felipe;Ayala Fernández");
                writer.newLine();
                writer.write("CC;100325998;Santiago;Valencia Marin");
                writer.newLine();
                writer.write("CC;100326150;Leidy Johana;Lopez Rincon");
                System.out.println("The sellers file was created.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static class createSalesMenFile {
    	private void generateSalesFilesForSellers(String fileFormatPath, String filePathInp) {
    		String filePathPiff = fileFormatPath + "Product Information File Format.csv";
    		String filePathSiff = fileFormatPath + "Seller Information File Format.csv";
    		try {
    			List<String[]> sellers = readCSV(filePathSiff);
    			List<String[]> products = readCSV(filePathPiff);
    			for (String[] seller : sellers) {
    				String documentType = seller[0];
    				String documentNumber = seller[1];
    				String fileName = filePathInp + documentNumber + " sales.csv";
    				try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
    					writer.write(documentType + ";" + documentNumber + "\n");
    					for (String[] product : products) {
    						String productId = product[0];
    						int randomQuantity = generateRandomQuantity();
    						writer.write(productId + ";" + randomQuantity + "\n");
    					}
    				}
    			}
    			System.out.println("The Sales files was created.");
    		} catch (IOException e) {
    			System.err.println("Error generating sales files: " + e.getMessage());
    		}
    	}
    
    	private List<String[]> readCSV(String filePath) throws IOException {
    		List<String[]> data = new ArrayList<>();
    		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
    			String line;
    			reader.readLine();
    			reader.readLine();
    			while ((line = reader.readLine()) != null) {
    				String[] values = line.split(";");
    				data.add(values);
    			}
    		}
    		return data;
    	}

    	private int generateRandomQuantity() {
    		return new Random().nextInt(10) + 1;
    	}
    }

    private static class fileReader {

    	private void readCSVFiles(String directoryPath) {
            try {
                Files.list(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
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

	// Lista se ventas en general

	public void SalesReport() {

		int opcion = 0, subopcion = 0;

	}

	/**
	 * The main method, which serves as the entry point to the program. It creates
	 * an instance of the GenerateInfoFiles class and calls the Start method.
	 *
	 * @param args command line arguments (not used) @
	 */

	public static void main(String[] args) throws FileNotFoundException {

		GenerateInfoFiles generateInfoFiles = new GenerateInfoFiles();
		generateInfoFiles.Start();
		
	}

}