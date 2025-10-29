package Asgn2;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

// loads environment variables (GH token)

public class EnvLoader {

    public static void loadEnv(String path) {

        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEach(line -> {
                if (!line.startsWith("#") && line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    System.setProperty(parts[0].trim(), parts[1].trim());
                }
            });
        } catch (IOException e) {
            System.out.println("No .env file found or could not load it.");
        }

    }

}
