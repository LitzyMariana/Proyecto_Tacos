package sample.Vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class P_Admin extends Stage {

    private Scene scene;
    private HBox hBox;
    //private VBox vBox;
    private Button btnAlimentos, btnAdministrativo;
    //private Label lblAlimentos, lblAdministrativos;

    public P_Admin(){
        CrearGUI();
        this.setTitle("Menu Administrativo");
        this.setScene(scene);
        this.show();

    }

    private void CrearGUI() {
        hBox = new HBox();
      //vBox = new VBox();
        btnAlimentos = new Button("Alimentos");
        btnAlimentos.setOnAction(handler);
        btnAdministrativo = new Button("Administrativo");
        btnAdministrativo.setOnAction(handler);

        hBox.getChildren().addAll(btnAlimentos,btnAdministrativo);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);

        scene = new Scene(hBox, 800, 400);
        scene.getStylesheets().add("sample/Estilos/PAdmin.css");
    }

    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if( actionEvent.getSource() == btnAlimentos )
            {
                new PanelAlimentos();
            }
            else
            {
                if( actionEvent.getSource() == btnAdministrativo )
                {
                    new PanelAdministracion();
                }

            }
        }
    };

}
