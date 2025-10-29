package Asgn2;

// record object that represents a single .java file
// contains metadata about file size and complexity

/**
 * @param name       fields
 * @param size       number of lines
 * @param complexity number of control statements (if/ switch/ for/ while)
 */

public record AnalyzedFile(String name, int size, int complexity) {
}
