package Asgn2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

// helper class
// computes size and complexity analysis for .java files

public class FileAnalyzer {

    // components
    private final GHOperations ghOperations;

    public FileAnalyzer(GHOperations ghOperations) {

        // initialize components
        this.ghOperations = ghOperations;

    }

    public List<AnalyzedFile> analyzeFiles(String GHUrl) throws IOException {

        // create output list
        List<AnalyzedFile> out = new ArrayList<>();

        // get GH URL info
        GHInfo ghInfo = GHOperations.parseGHURL(GHUrl);

        // get .java files from GH URL
        List<String> filePaths = ghOperations.listFilesRecursive(GHUrl);

        // iterate through file paths in GH URL
        for (String filePath : filePaths) {

            // confirm java file
            if (!filePath.toLowerCase().endsWith(".java")) {
                continue;
            }

            // read and analyze
            String content = ghOperations.getFileContent(ghInfo.owner(), ghInfo.repo(), filePath, ghInfo.ref());
            int size = (int) content.lines().filter(l -> !l.trim().isEmpty()).count();  // only count lines with content
            int complexity = countComplexity(content);

            // get file name
            String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);

            // create AnalyzedFile object and store metadata for current .java file
            out.add(new AnalyzedFile(fileName, size, complexity));

        }

        return out;

    }

    private int countComplexity(String content) {

        int count = 0;  // establish a counter
        String[] controlStatements = {"if", "switch", "for", "while"};  // control statements

        // clean content: remove string literals and comments
        String cleaned = content.replaceAll("\"(\\\\.|[^\"\\\\])*\"", "");
        cleaned = cleaned.replaceAll("(?s)/\\*.*?\\*/", "");
        cleaned = cleaned.replaceAll("//.*", "");

        // iterate through control statements
        for (String controlStatement : controlStatements) {

            // find instances of control statements in content
            var find_instances = Pattern.compile("\\b" + controlStatement + "\\b").matcher(cleaned);
            while (find_instances.find()) {
                count++; // increment counter
            }

        }

        return count;

    }

}
