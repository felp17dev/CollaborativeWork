package ProjectCFP;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class prueba {
	public static void main(String[] args) {
		
		Path path = Path.of("Sellernfo.txt");
		
		try (Scanner scanner = new Scanner(path)){
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println(line);
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "el archivo no pudo ser cargado" + e);
		}
		
	}

}
