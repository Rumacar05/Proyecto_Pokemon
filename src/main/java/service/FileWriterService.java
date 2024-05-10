package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterService {
    public static boolean saveFile(String fullPathFile, String content) throws Exception {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fullPathFile))) {
            bw.write(content);
            bw.newLine();
        } catch (IOException ex) {
            throw new Exception("Se ha producido un error guardando el fichero \n"+ ex.getMessage());
        }

        return true;
    }
}
