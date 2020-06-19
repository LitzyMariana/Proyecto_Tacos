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

public class PanelAdministracion extends Stage {

    private Scene escena;
    private GridPane gridPane;
    private Button btnUsuario, btnVentas;
    private Label lblUsuario, lblVentas;



    public PanelAdministracion(){
        CrearGUI();
        this.setTitle("Panel de Administración");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI()
    {
        gridPane = new GridPane();

        lblUsuario = new Label("Administre a los Usuarios");
        Image icono3 = new Image("sample/Imagenes/user1.png");
        ImageView imagen3 = new ImageView(icono3);
        imagen3.setFitHeight(100);
        imagen3.setFitWidth(100);
        btnUsuario = new Button("", imagen3);
        btnUsuario.setOnAction(handler);

        lblVentas = new Label("Revise las Ventas");
        Image icono4 = new Image("sample/Imagenes/ventas.png");
        ImageView imagen4 = new ImageView(icono4);
        imagen4.setFitHeight(100);
        imagen4.setFitWidth(100);
        btnVentas = new Button("", imagen4);
        btnVentas.setOnAction(handler);


        gridPane.add(lblUsuario,0, 0);
        gridPane.add(btnUsuario,0,1);
        gridPane.add(lblVentas,0, 2);
        gridPane.add(btnVentas,0, 3);

        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);


        escena = new Scene(gridPane, 600, 700);
        escena.getStylesheets().add("sample/Estilos/P_Alimentos.css");
    }

    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if( actionEvent.getSource()== btnUsuario )
            {
                new CRUDUsuario();
            }
            else
            {
                if(actionEvent.getSource()== btnVentas)
                        {
                            new CRUDPedido();
                        }
                    }
                }
           };
}
