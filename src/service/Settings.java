package service;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 16.09.12
 * Time: 16:21
 */

public class Settings {
    public Settings(String comPortFinder) {
        this.comPortFinder = comPortFinder;
    }

    private String comPortFinder;
    private String fileReaderConfig = "single";

    public String getComPortFinder() {
        return comPortFinder;
    }

    public void setComPortFinder(String comPortFinder) {
        this.comPortFinder = comPortFinder;
    }

    public String getFileReaderConfig() {
        return fileReaderConfig;
    }

    public void setFileReaderConfig(String fileReaderConfig) {
        this.fileReaderConfig = fileReaderConfig;
    }




}
