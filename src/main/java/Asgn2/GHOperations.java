package Asgn2;
import java.io.IOException;
import java.util.List;
import javiergs.tulip.GitHubHandler;

public class GHOperations {

    private final GitHubHandler handler;

    public GHOperations(String token) {
        this.handler = new GitHubHandler(token);
    }

    // display files from GitHub folder URL
    public List<String> showFiles(String url) throws IOException {
        return handler.listFiles(url);
    }

    // list all files in subfolders
    public List<String> listFilesRecursive(String url) throws IOException {
        return handler.listFilesRecursive(url);
    }

}
