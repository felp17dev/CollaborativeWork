package ProjectCFP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GenerateInfoFiles {

	static Scanner keyBoard = new Scanner(System.in);

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

	public static void main(String[] args) throws FileNotFoundException {

		GenerateInfoFiles generateInfoFiles = new GenerateInfoFiles();
		generateInfoFiles.Start();

	}

}