package sample.Vistas;

import ModelosTaqueria.BebidaDAO;
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
import sample.Componentes.ButtonCellBeb;

public class CRUDBebida extends Stage{

    private Scene escena;
    private VBox vbox;
    private TableView<BebidaDAO> tbvBebida;
    private Button btnAgregar;
    private BebidaDAO objB;

    public CRUDBebida(){
        objB = new BebidaDAO();
        CrearGUI();
        this.setTitle("Administración de las Bebidas");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvBebida = new TableView<>();
        tbvBebida.setPrefSize(300,500);
        CrearTabla();
        btnAgregar = new Button("Agregue una Bebida");
        btnAgregar.setAlignment(Pos.CENTER);
        btnAgregar.setPrefSize(300,20);
        btnAgregar.setOnAction(event -> AgregarBebida());
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.getChildren().addAll(tbvBebida,btnAgregar);
        escena = new Scene(vbox,500,600);
        escena.getStylesheets().add("sample/Estilos/CRUDB.css");
    }

    private void CrearTabla() {

        TableColumn<BebidaDAO,Integer> tbcIdBebida = new TableColumn<>("ID");
        tbcIdBebida.setCellValueFactory(new PropertyValueFactory<>("id_bebida"));

        TableColumn<BebidaDAO,String> tbcTipo = new TableColumn<>("TIPO");
        tbcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo_bebida"));

        TableColumn<BebidaDAO,String> tbcNombre = new TableColumn<>("NOMBRE");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_b"));

        TableColumn<BebidaDAO,Float> tbcCosto = new TableColumn<>("COSTO");
        tbcCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        TableColumn<BebidaDAO,Float> tbcPrecio = new TableColumn<>("PRECIO");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));


        TableColumn<BebidaDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<BebidaDAO, String>, TableCell<BebidaDAO, String>>() {
                    @Override
                    public TableCell<BebidaDAO, String> call(TableColumn<BebidaDAO, String> param) {
                        return new ButtonCellBeb(2);
                    }
                }
        );

        TableColumn<BebidaDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<BebidaDAO, String>, TableCell<BebidaDAO, String>>() {
                    @Override
                    public TableCell<BebidaDAO, String> call(TableColumn<BebidaDAO, String> param) {
                        return new ButtonCellBeb(1);
                    }
                }
        );


        tbvBebida.getColumns().addAll(tbcIdBebida,tbcTipo,tbcNombre,tbcCosto,tbcPrecio,tbcEditar,tbcBorrar);
        tbvBebida.setItems(objB.selAllBebidas());
    }

    private void AgregarBebida()
    {
        new FormBebida(tbvBebida, null,1);
    }

}
