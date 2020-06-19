package sample;

import ModelosTaqueria.ConexionTaqueria;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Vistas.*;

public class Main extends Application {

    //Creación de Elementos
    MenuBar mnbpry;//Creación del Primer menu
    Menu   mencompP,mensalir;
    MenuItem  mitProy, mitbye;
    Scene escena;
    BorderPane bdrPrincipal;


    @Override
    public void start(Stage primaryStage) throws Exception{

        //Se instancian los objetos
        bdrPrincipal = new BorderPane();
        mnbpry = new MenuBar();//Se instancia el primer menu
        bdrPrincipal.setTop(mnbpry);

        mencompP = new Menu("Proyecto Taqueria");
        mensalir = new Menu("Salir");

        mitProy = new MenuItem("Proyecto: Taqueria");


        mitProy.setOnAction(event -> OpcionMenu(1));

        mitbye = new MenuItem("Bye):");
        mitbye.setOnAction(event -> OpcionMenu(20));//Se hace funcional el menu dandole una opcion

        //Union de menus

        mencompP.getItems().addAll(mitProy);//El menu Proyecto se añade al menu compP
        mensalir.getItems().add(mitbye);
        mnbpry.getMenus().addAll(mencompP,mensalir);

        escena = new Scene(bdrPrincipal);
        escena.getStylesheets().add("sample/Estilos/estilos_principal.css");

        //Se crea la conexión a la Base de Datos
        ConexionTaqueria.crearConexion();

        primaryStage.setTitle("Hola de nuevo): ");
        primaryStage.setScene(escena);
        primaryStage.setMaximized(true);
        primaryStage.show();
}

    private void OpcionMenu(int i) {
        switch (i){
            case 1:
                new LoginTaqueria();
                break;

            case 20:
                System.exit(0);//Se usa para salir del programa
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
