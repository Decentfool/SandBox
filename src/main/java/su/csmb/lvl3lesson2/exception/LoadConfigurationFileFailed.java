package su.csmb.lvl3lesson2.exception;

import java.io.FileNotFoundException;

public class LoadConfigurationFileFailed extends FileNotFoundException {
    public LoadConfigurationFileFailed(String message) { super("File not found");
    }
}
