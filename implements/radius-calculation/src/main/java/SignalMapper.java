import java.util.Set;

public abstract class SignalMapper {
//    private String[][] map;
    public abstract String getElement(int column, int row);
    public abstract String[] getColumn(int column);
    public abstract void settingMap();
    public abstract Set<String> getMapSet();
    public abstract int getIndex(String name);
}
