package sample.Vistas;

import ModelosTaqueria.UsuarioDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.ButtonCellUs;

public class CRUDUsuario extends Stage{

    private Scene escena;
    private VBox vbox;
    private TableView<UsuarioDAO> tbvEmpleado;
    private Button btnAgregar;
    private UsuarioDAO objUs;

    public CRUDUsuario(){
        objUs = new UsuarioDAO();
        CrearGUI();
        this.setTitle("Administración de Usuarios");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvEmpleado = new TableView<>();
        tbvEmpleado.setPrefSize(300,500);
        CrearTabla();
        btnAgregar = new Button("Agregue un usuario");
        btnAgregar.setAlignment(Pos.CENTER);
        btnAgregar.setPrefSize(300,20);
        btnAgregar.setOnAction(event -> AgregarEmpleado());
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.getChildren().addAll(tbvEmpleado,btnAgregar);

        escena = new Scene(vbox, 650,600);
        escena.getStylesheets().add("sample/Estilos/CRUDU.css");

    }

    private void CrearTabla() {

        TableColumn<UsuarioDAO,Integer> tbcIdUsuario = new TableColumn<>("ID");
        tbcIdUsuario.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));

        TableColumn<UsuarioDAO,String> tbcNombre = new TableColumn<>("NOMBRE");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<UsuarioDAO,String> tbcTelefono = new TableColumn<>("TELEFONO");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        TableColumn<UsuarioDAO,Float> tbcSueldo = new TableColumn<>("SUELDO");
        tbcSueldo.setCellValueFactory(new PropertyValueFactory<>("sueldo"));

        TableColumn<UsuarioDAO,String> tbcTipo = new TableColumn<>("ROL");
        tbcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo_usuario"));

        TableColumn<UsuarioDAO,String> tbcContra = new TableColumn<>("CONTRASEÑA");
        tbcContra.setCellValueFactory(new PropertyValueFactory<>("contraseña"));


        TableColumn<UsuarioDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<UsuarioDAO, String>, TableCell<UsuarioDAO, String>>() {
                    @Override
                    public TableCell<UsuarioDAO, String> call(TableColumn<UsuarioDAO, String> param) {
                        return new ButtonCellUs(2);
                    }
                }
        );

        TableColumn<UsuarioDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<UsuarioDAO, String>, TableCell<UsuarioDAO, String>>() {
                    @Override
                    public TableCell<UsuarioDAO, String> call(TableColumn<UsuarioDAO, String> param) {
                        return new ButtonCellUs(1);
                    }
                }
        );


        tbvEmpleado.getColumns().addAll(tbcIdUsuario,tbcNombre, tbcTelefono,tbcSueldo, tbcTipo,tbcContra,tbcEditar,tbcBorrar);
        tbvEmpleado.setItems(objUs.selAllUsuarios());
    }

    private void AgregarEmpleado()
    {
        new FormUsuario(tbvEmpleado, null,1);
    }

}
