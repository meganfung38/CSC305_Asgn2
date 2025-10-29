package Asgn2;
import java.io.IOException;
import java.util.List;
import javiergs.tulip.GitHubHandler;
import java.net.URI;
import java.net.URISyntaxException;

// helper for accessing GH URLs

public class GHOperations {

    // components
    private final GitHubHandler handler;

    public GHOperations(String token) {

        // initialize components
        this.handler = new GitHubHandler(token);

    }

    public List<String> listFilesRecursive(String url) throws IOException {

        // list all files in subfolders
        return handler.listFilesRecursive(url);

    }

    public static GHInfo parseGHURL(String url) {

        try {

            URI uri = new URI(url); // get uri
            String[] repo_routes = uri.getPath().split("/");

            // validate GH URL
            if (repo_routes.length < 5 || !"tree".equals(repo_routes[3])) {
                throw new IllegalArgumentException("Bad GH URL: " + url);
            }
            // example:
            // /<owner>/<repo>/tree/<ref>/....
            return new GHInfo(repo_routes[1], repo_routes[2], repo_routes[4]);


        } catch (URISyntaxException e) {

            throw new IllegalArgumentException("Bad GH URL: " + url, e);

        }

    }

    public String getFileContent(String owner, String repo, String path, String ref) throws IOException {

        // return file content
        return handler.getFileContent(owner, repo, path, ref);

    }

}
