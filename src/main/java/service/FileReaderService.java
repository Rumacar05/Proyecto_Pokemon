package service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderService {
    public static String readFile(String fullPathFile) throws Exception {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(fullPathFile))) {
            String line = br.readLine();

            while (line != null) {
                content.append(line).append(System.lineSeparator());
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            throw new Exception(String.format("El fichero %s no existe", fullPathFile));
        } catch (IOException ex) {
            throw new Exception("Se ha producido un error leyendo el fichero \n" + ex.getMessage());
        }

        return content.toString();
    }
}
