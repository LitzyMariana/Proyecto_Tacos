package sample.Vistas;

import ModelosTaqueria.UsuarioDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginTaqueria extends Stage {

    private Scene escena;
    private VBox vbox;
    private HBox hBoxU, hBoxC;
    private Label lblIcono, lblSign, lblUser, lblPwd;
    private TextField txtUser;
    private PasswordField txtPassword;
    private Button btnIngresar;
    private UsuarioDAO objUs;

    public LoginTaqueria()
    {
        objUs = new UsuarioDAO();
        CrearGUI();
        this.setTitle("Inicio de Sesión");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI()
    {
        vbox = new VBox();
        hBoxU = new HBox();
        hBoxC = new HBox();

        lblIcono = new Label();
        Image icono = new Image("sample/Imagenes/taco.png");
        ImageView imagen = new ImageView(icono);
        imagen.setFitHeight(150);
        imagen.setFitWidth(150);
        lblIcono.setGraphic(imagen);

        lblSign = new Label("Inicie sesión");
        lblUser = new Label("Usuario");
        lblPwd = new Label("Contraseña");

        txtUser = new TextField();
        txtUser.setPromptText("Ingrese su usuario");
        txtUser.setMaxSize(150,20);

        txtPassword = new PasswordField();
        txtPassword.setPromptText("Ingrese su Contraseña");
        txtPassword.setMaxSize(150,20);

        btnIngresar = new Button("Login");
        btnIngresar.setOnAction(event -> validaDatos());


        hBoxU.getChildren().addAll(lblUser,txtUser);
        hBoxU.setAlignment(Pos.CENTER);
        hBoxU.setSpacing(10);

        hBoxC.getChildren().addAll(lblPwd,txtPassword);
        hBoxC.setAlignment(Pos.CENTER);
        hBoxC.setSpacing(10);


        vbox.getChildren().addAll(lblSign, lblIcono,hBoxU,hBoxC,btnIngresar);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        escena = new Scene(vbox, 500, 500);
        escena.getStylesheets().add("sample/Estilos/login.css");
    }

    private void validaDatos()
    {
        objUs.setNombre(txtUser.getText());
        objUs.setContraseña(txtPassword.getText());

        objUs.sellOneUsuario();

        if(objUs.bandera == 1 && txtUser.getText().equals("Litzy"))
        {
            Alerta("Iniciaste Sesión como Administrador");
            new P_Admin();
            this.close();
        }
        else
        {
            if(objUs.bandera == 1)
            {
                Alerta("Iniciaste Sesión como mesero");
                new P_Mesero();
                this.close();
            }
            else
            {
                if( objUs.bandera == 0 )
                {
                    Alerta("Usuario o Contraseña incorrecta. Por favor, Vuelve a Intentar");
                    txtUser.setText("");
                    txtPassword.setText("");
                }
                else
                {
                    Alerta("Se ha producido un error");
                }
            }
        }


    }

    private void Alerta(String contenido)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inicio de Sesión");
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}
