package sample.Vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PanelAlimentos extends Stage {

    private Scene escena;
    private GridPane gridPane;
    private Button btnTacos, btnBebida;
    private Label lblTacos, lblBebida;



    public PanelAlimentos(){
        CrearGUI();
        this.setTitle("Panel de Alimentos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {

        lblTacos = new Label("Administre los Tacos");
        gridPane = new GridPane();
        Image icono1 = new Image("sample/Imagenes/tacoComida.png");
        ImageView imagen1 = new ImageView(icono1);
        imagen1.setFitHeight(100);
        imagen1.setFitWidth(100);
        btnTacos = new Button("",imagen1);
        btnTacos.setOnAction(handler);

        lblBebida = new Label("Administre las Bebidas");
        Image icono2 = new Image("sample/Imagenes/bebidas.png");
        ImageView imagen2 = new ImageView(icono2);
        imagen2.setFitHeight(100);
        imagen2.setFitWidth(100);
        btnBebida = new Button("",imagen2);
        btnBebida.setOnAction(handler);

        gridPane.add(lblTacos,0, 0);
        gridPane.add(btnTacos,0,1);
        gridPane.add(lblBebida,0, 2);
        gridPane.add(btnBebida,0, 3);


        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);


        escena = new Scene(gridPane, 600, 700);
        escena.getStylesheets().add("sample/Estilos/P_Alimentos.css");
    }

    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if( actionEvent.getSource()== btnTacos )
            {
                new CRUDTaco();
            }
            else
            {
                if( actionEvent.getSource() == btnBebida )
                {
                    new CRUDBebida();
                }

            }
        }
    };

}


