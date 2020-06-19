package sample.Vistas;

import ModelosTaqueria.TacoDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormTaco extends Stage{

    Scene escena;

    TextField txtNombre, txtCosto, txtPrecio;
    Label lblNombre, lblCosto, lblPrecio;
    VBox vBox;
    Button btnAgregar, btnActualizar;

    TableView<TacoDAO> tbvComida;

    TacoDAO objT;

    public FormTaco(TableView<TacoDAO> tbvComida, TacoDAO obj, int bandera){

        if( obj != null) {
            System.out.println("");
            objT = obj;
        }
        else {
            objT = new TacoDAO();
            System.out.println("");
        }
        this.tbvComida = tbvComida;
        CrearGUI(bandera);
        this.setTitle("Gestor de Tacos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI(int bandera){

        vBox = new VBox();

        txtNombre = new TextField();
        txtNombre.setPromptText("Ingrese el tipo de taco");
        txtNombre.setText(objT.getTipo_taco());
        txtNombre.setMaxSize(150,20);

        txtCosto = new TextField();
        txtCosto.setPromptText("Ingrese el costo");
        txtCosto.setText(objT.getCosto()+"");
        txtCosto.setMaxSize(150,20);

        txtPrecio = new TextField();
        txtPrecio.setPromptText("Ingrese el precio");
        txtPrecio.setText(objT.getPrecio()+"");
        txtPrecio.setMaxSize(150,20);

        lblNombre = new Label("Nombre");
        lblCosto = new Label("Costo");
        lblPrecio = new Label("Precio");


        btnAgregar = new Button("Agregar el Taco");
        btnAgregar.setOnAction(actionEvent -> enviarDatos(1));

        btnActualizar = new Button("Actualizar el Taco");
        btnActualizar.setOnAction(actionEvent -> enviarDatos(2));

        if(bandera == 1){
            btnActualizar.setDisable(true);
        }
        else{
            btnAgregar.setDisable(true);
        }

        vBox.getChildren().addAll(lblNombre,txtNombre,lblCosto,txtCosto,lblPrecio, txtPrecio, btnAgregar, btnActualizar);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        escena = new Scene(vBox,300, 300);
        escena.getStylesheets().add("sample/Estilos/FormstyleT.css");
    }

    private void enviarDatos(int proceso){

        objT.setTipo_taco(txtNombre.getText());
        objT.setCosto(Float.parseFloat(txtCosto.getText()));
        objT.setPrecio(Float.parseFloat(txtPrecio.getText()));


        if( proceso  == 1 ){

            objT.insTaco();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡El Taco se ha registrado exitosamente!");
            alert.setHeaderText(null);
            alert.setContentText("Registro Correcto");
            alert.showAndWait();
        }
        else{
            objT.updTaco();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Actualizado");
            alert.setHeaderText(null);
            alert.setContentText("¡Se ha Actualizado Correctamente!");
            alert.showAndWait();
        }

        tbvComida.setItems(objT.selAllTacos());
        tbvComida.refresh();

        txtNombre.setText("");
        txtCosto.setText("");
        txtPrecio.setText("");

    }

}
