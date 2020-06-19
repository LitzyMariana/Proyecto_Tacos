package sample.Vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class P_Mesero extends Stage {

    //public static String DEST = "Tickets/Ticket.pdf";

    private Scene escena;
    private VBox vBox;
    private Button btnPedido;
    private Label lblPedido;
//    int usuario, pedido, mesa; pdf

    //pdf GenerarPdf pdf = new GenerarPdf();

    public P_Mesero() {
        CrearGUI();
        this.setTitle("Panel de Mesero");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vBox = new VBox();

        lblPedido = new Label("Realizar Pedido");

        Image icono10 = new Image("sample/Imagenes/tacop.png");
        ImageView imagen10 = new ImageView(icono10);
        imagen10.setFitHeight(100);
        imagen10.setFitWidth(100);
        btnPedido = new Button("",imagen10);
        btnPedido.setOnAction(handler);

        vBox.getChildren().addAll(lblPedido,btnPedido);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);

        escena = new Scene(vBox, 500, 300);
        escena.getStylesheets().add("sample/Estilos/PMesero.css");
    }

    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == btnPedido) {
                new CRUDPM();
            }

        }
    };

   /* EventHandler<ActionEvent> handler2 = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == btnTicket) {
                File file = new File(DEST);
                file.getParentFile().mkdir();
                pdf.GenerarTicket(usuario, pedido, mesa);
            }
        }

    };*/
}



