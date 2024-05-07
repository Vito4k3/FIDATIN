package Homepage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestioneFile {
    private String folderName = "Fidatin";
    Path folderPath  = Paths.get(System.getProperty("user.home"), folderName);

    public GestioneFile(){creaCartella();}

    public void creaCartella() {
        if(!Files.exists(folderPath)){
            try {
                Files.createDirectory(folderPath);
                System.out.println("Cartella creata");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Path getPath(){return this.folderPath;}

    }
