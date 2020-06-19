package sample.Vistas;

import ModelosTaqueria.BebidaDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormBebida extends Stage{

    Scene escena;

    TextField txtTipo, txtNombre, txtCosto, txtPrecio;
    Label lblTipo, lblNombre, lblCosto, lblPrecio;
    VBox vBox;
    Button btnAgregar, btnActualizar;

    TableView<BebidaDAO> tbvBebida;

    BebidaDAO objB;

    public FormBebida(TableView<BebidaDAO> tbvBebida, BebidaDAO obj, int bandera)
    {
        if( obj != null) {
            System.out.println("");
            objB = obj;
        }
        else {
            objB = new BebidaDAO();
            System.out.println("");
        }
        this.tbvBebida = tbvBebida;
        CrearGUI(bandera);
        this.setTitle("Gestor Bebidas");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI(int bandera)
    {
        vBox = new VBox();

        txtTipo = new TextField();
        txtTipo.setPromptText("Ingresa el tipo de la bebida");
        txtTipo.setText(objB.getTipo_bebida());
        txtTipo.setMaxSize(200,20);

        txtNombre = new TextField();
        txtNombre.setPromptText("Ingrese el nombre de la bebida");
        txtNombre.setText(objB.getNombre_b());
        txtNombre.setMaxSize(200,20);

        txtCosto = new TextField();
        txtCosto.setPromptText("Ingrese el costo de la bebida");
        txtCosto.setText(objB.getCosto()+"");
        txtCosto.setMaxSize(200,20);

        txtPrecio = new TextField();
        txtPrecio.setPromptText("Ingrese el precio de la bebida");
        txtPrecio.setText(objB.getPrecio()+"");
        txtPrecio.setMaxSize(200,20);

        lblTipo = new Label("Tipo");
        lblNombre = new Label("Nombre");
        lblCosto = new Label("Costo");
        lblPrecio = new Label("Precio");

        btnAgregar = new Button("Agregar Bebida");
        btnAgregar.setOnAction(actionEvent -> enviarDatos(1));

        btnActualizar = new Button("Actualizar Bebida");
        btnActualizar.setOnAction(actionEvent -> enviarDatos(2));

        if(bandera == 1)
        {
            btnActualizar.setDisable(true);
        }
        else
        {
            btnAgregar.setDisable(true);
        }

        vBox.getChildren().addAll(lblTipo, txtTipo,lblNombre,txtNombre,lblCosto,txtCosto,lblPrecio,txtPrecio,btnAgregar, btnActualizar);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        escena = new Scene(vBox,300, 500);
        escena.getStylesheets().add("sample/Estilos/FormstyleB.css");
    }

    private void enviarDatos(int proceso)
    {
        objB.setTipo_bebida(txtTipo.getText());
        objB.setNombre_b(txtNombre.getText());
        objB.setCosto(Float.parseFloat(txtCosto.getText()));
        objB.setPrecio(Float.parseFloat(txtPrecio.getText()));


        if( proceso  == 1 )
        {
            objB.insBebida();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡La Bebida se ha registrado exitosamente!");
            alert.setHeaderText(null);
            alert.setContentText("La Bebida se Registro Correctamente");
            alert.showAndWait();
        }
        else
        {
            objB.updBebida();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bebida Registrada");
            alert.setHeaderText(null);
            alert.setContentText("¡Se ha actualizado correctamente!");
            alert.showAndWait();
        }

        tbvBebida.setItems(objB.selAllBebidas());
        tbvBebida.refresh();

        txtTipo.setText("");
        txtNombre.setText("");
        txtCosto.setText("");
        txtPrecio.setText("");

    }

}
