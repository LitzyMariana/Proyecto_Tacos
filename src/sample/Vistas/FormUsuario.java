package sample.Vistas;

import ModelosTaqueria.UsuarioDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormUsuario extends Stage{

    Scene escena;
    TextField txtNombre, txtTelefono, txtSueldo, txtTipo, txtContraseña;
    VBox vBox;
    Button btnAgregar, btnActualizar;
    UsuarioDAO objUs;
    TableView<UsuarioDAO> tbvEmpleado;


    public FormUsuario(TableView<UsuarioDAO> tbvEmpleado, UsuarioDAO obj, int bandera){
        if( obj != null) {
            objUs = obj;
        }
        else {
            objUs = new UsuarioDAO();
        }
        this.tbvEmpleado = tbvEmpleado;
        crearGUI(bandera);
        this.setTitle("Gestor de Usuarios");
        this.setScene(escena);
        this.show();
    }

    private void crearGUI(int bandera)
    {
        vBox = new VBox();

        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del Usuario");
        txtNombre.setText(objUs.getNombre());
        txtNombre.setMaxSize(150,20);

        txtTelefono = new TextField();
        txtTelefono.setPromptText("Telefono");
        txtTelefono.setText(objUs.getTelefono());
        txtTelefono.setMaxSize(150,20);

        txtSueldo = new TextField();
        txtSueldo.setPromptText("Sueldo");
        txtSueldo.setText(objUs.getSueldo()+"");
        txtSueldo.setMaxSize(150,20);

        txtTipo = new TextField();
        txtTipo.setPromptText("Rol");
        txtTipo.setText(objUs.getTipo_usuario());
        txtTipo.setMaxSize(150,20);

        txtContraseña = new TextField();
        txtContraseña.setPromptText("Contraseña");
        txtContraseña.setText(objUs.getContraseña());
        txtContraseña.setMaxSize(150,20);


        btnAgregar = new Button("Agregar Usuario");
        btnAgregar.setOnAction(actionEvent -> onPress(1));

        btnActualizar = new Button("Actualizar Usuario");
        btnActualizar.setOnAction(actionEvent -> onPress(2));

        vBox.getChildren().addAll(txtNombre, txtTelefono, txtSueldo,txtTipo,txtContraseña, btnAgregar,btnActualizar);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        if(bandera == 1)
        {
            btnActualizar.setDisable(true);
        }
        else
        {
            btnAgregar.setDisable(true);
        }

        escena = new Scene(vBox,300, 300);
        escena.getStylesheets().add("sample/Estilos/FormstyleU.css");
    }

    private void onPress(int proceso){

        objUs.setNombre(txtNombre.getText());
        objUs.setTelefono(txtTelefono.getText());
        objUs.setSueldo(Float.parseFloat(txtSueldo.getText()));
        objUs.setTipo_usuario(txtTipo.getText());
        objUs.setContraseña(txtContraseña.getText());




        if( proceso  == 1 )
        {
            objUs.insUsuario();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡El Usuario se ha registrado exitosamente!");
            alert.setHeaderText(null);
            alert.setContentText("¡Registro Correcto!");
            alert.showAndWait();
        }
        else
        {
            objUs.updUsuario();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Actualizado");
            alert.setHeaderText(null);
            alert.setContentText("¡Se ha Actualizado Correctamente!");
            alert.showAndWait();
        }

        tbvEmpleado.setItems(objUs.selAllUsuarios());
        tbvEmpleado.refresh();

        txtNombre.setText("");
        txtTelefono.setText("");
        txtSueldo.setText("");
        txtTipo.setText("");
        txtContraseña.setText("");
    }

}
