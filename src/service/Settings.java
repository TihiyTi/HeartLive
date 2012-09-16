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

    public String getComPortFinder() {
        return comPortFinder;
    }

    public void setComPortFinder(String comPortFinder) {
        this.comPortFinder = comPortFinder;
    }




}
