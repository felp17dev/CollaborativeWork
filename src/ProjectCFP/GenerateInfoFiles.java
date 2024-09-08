/** This program consists of a menu in which you can choose 
 * the .txt document to display.
 *Authors:
 *Santiago Aranda Hurtado
 *Andres David Arias Combita
 *Luis Felipe Ayala Fernández
 *Santiago Valencia Marin
 */

package ProjectCFP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * This class handles the generation and display of information files.
 * It provides options to see a list of vendors, a product listing, 
 * and a sales report.
 */

public class GenerateInfoFiles {
	
	/**
     * A static instance of Scanner to receive user input from the keyboard.
     */

	static Scanner keyBoard = new Scanner(System.in);
	
	/**
     * Starts the main menu where the user can select different options
     * to view vendor information, product information, or sales reports.
     * The menu runs in a loop until the user chooses to exit.
     */

	public void Start() {
		int opcion = 0;

		do {
			System.out.println("----------Hello---------- ");
			System.out.println("1. See list of vendors");
			System.out.println("2. See product listing");
			System.out.println("3. Sales report");
			System.out.println("4. Exit");
			System.out.println("Pick your choise");
			System.out.println();
			opcion = keyBoard.nextInt();

			switch (opcion) {
			case 1:
				Archive1();
				break;
			case 2:
				Archive2();
				break;
			case 3:
				Archive3();
				break;
			case 4:
				break;
			}

		} while (opcion != 4);

		System.out.println("----------Sesion finished----------");
	}
	
	/**
     * Reads and displays the contents of the SellerInfo.txt file.
     * If the file is not found or cannot be loaded, an error message 
     * is displayed.
     */

	public void Archive1() {
		int opcion = 0;
		Path path = Path.of("SellerInfo.txt");
		try (Scanner scanner = new Scanner(path)) {

			while (scanner.hasNextLine()) {
				String archive1 = scanner.nextLine();
				System.out.println(archive1);
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "el archivo no pudo ser cargado" + e);
		}
		System.out.println();
	}
	
	/**
     * Reads and displays the contents of the ProductInfo.txt file.
     * If the file is not found or cannot be loaded, an error message 
     * is displayed.
     */

	public void Archive2() {
		int opcion = 0;
		Path path = Path.of("ProductInfo.txt");
		try (Scanner scanner = new Scanner(path)) {

			while (scanner.hasNextLine()) {
				String archive2 = scanner.nextLine();
				System.out.println(archive2);

			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "el archivo no pudo ser cargado" + e);
		}
		System.out.println();
	}
	
	/**
     * Reads and displays the contents of the seller1Report.txt file.
     * If the file is not found or cannot be loaded, an error message 
     * is displayed.
     */

	public void Archive3() {
		int opcion = 0;
		Path path = Path.of("seller1Report.txt");
		try (Scanner scanner = new Scanner(path)) {

			while (scanner.hasNextLine()) {
				String archive3 = scanner.nextLine();
				System.out.println(archive3);

			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "el archivo no pudo ser cargado" + e);
		}
		System.out.println();
	}
	
	 /**
     * The main method, which serves as the entry point to the program.
     * It creates an instance of the GenerateInfoFiles class and calls 
     * the Start method.
     *
     * @param args command line arguments (not used)
     * @
     */

	public static void main(String[] args) throws FileNotFoundException {

		GenerateInfoFiles generateInfoFiles = new GenerateInfoFiles();
		generateInfoFiles.Start();

	}

}