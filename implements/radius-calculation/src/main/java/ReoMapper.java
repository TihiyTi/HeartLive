import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReoMapper extends   SignalMapper{
    private static final String[][] MAP = {
            {"P1","F","R1"},
            {"P2","F","R2"},
            {"P3","F","R3"},
            {"P4","F","R4"},
            {"P5","F","R5"}};

    private Map<String, Integer> mapmap = new HashMap<String, Integer>(){{
        put("P1", 0);
        put("P2", 2);
        put("P3", 4);
        put("P4", 6);
        put("P5", 8);
        put("F", 10);
    }};



    @Override
    public String getElement(int column, int row) {
        return MAP[column][row];
    }
    @Override
    public String[]  getColumn(int column){
        return MAP[column];
    }

    @Override
    public void settingMap() {
    }

    @Override
    public Set<String> getMapSet() {
        return mapmap.keySet();
    }

    @Override
    public int getIndex(String name) {
        return mapmap.get(name);
    }

}
