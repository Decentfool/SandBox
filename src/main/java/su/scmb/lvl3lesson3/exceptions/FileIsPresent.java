package su.scmb.lvl3lesson3.exceptions;

public class FileIsPresent extends Throwable {
    public FileIsPresent() {
        super("File already exists");
    }
}
