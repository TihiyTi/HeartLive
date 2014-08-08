import com.tihiy.comm.FileSignalReader;
import com.tihiy.comm.parse.Reo32Parser;
import com.tihiy.fxclient.SignalPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FxApplication extends Application{
    File file;
    boolean autoChooseFile = true;

    private void init(Stage primaryStage) {
        Button openFile = new Button("Open file");
        openFile.setOnAction(event -> {
            if(autoChooseFile){
                file = new File("C:\\Users\\Alex\\Documents\\My Box Files\\Asp\\Experiment\\20140724\\2rad.txt");
            }else{
                FileChooser fileChooser = new FileChooser();
                file = fileChooser.showOpenDialog(new Stage());
            }
            System.out.println(file);
            FileSignalReader reader = new FileSignalReader();
            Map<Reo32Parser.Signal, List<Double>> map = reader.readFileToMap(file);
            List<List<Double>> listOfSignals = new ArrayList<>();
            for(Reo32Parser.Signal mapKey:map.keySet()){
                listOfSignals.add(map.get(mapKey));
            }
            SignalPanel panel = new SignalPanel(listOfSignals);
//            SignalPanel panel = new SignalPanel(4, true);

            primaryStage.setScene(new Scene(panel, 500,200));
        });
        Pane pane = new Pane();
        pane.getChildren().addAll(openFile);
        primaryStage.setScene(new Scene(pane,100,100));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
