package sample.Vistas;

import ModelosTaqueria.TacoDAO;
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
import sample.Componentes.ButtonCellTaco;

public class CRUDTaco extends Stage{

    private Scene escena;
    private VBox vbox;
    private TableView<TacoDAO> tbvComida;
    private Button btnAgregar;
    private TacoDAO objT;

    public CRUDTaco(){
        objT = new TacoDAO();
        CrearGUI();
        this.setTitle("Administración de los Tacos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvComida = new TableView<>();
        tbvComida.setPrefSize(300,500);
        CrearTabla();
        btnAgregar = new Button("Agregue un Taco");
        btnAgregar.setAlignment(Pos.CENTER);
        btnAgregar.setPrefSize(300,20);
        btnAgregar.setOnAction(event -> AgregarComida());
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.getChildren().addAll(tbvComida,btnAgregar);
        escena = new Scene(vbox, 420,600);
        escena.getStylesheets().add("sample/Estilos/CRUDT.css");

    }

    private void CrearTabla() {

        TableColumn<TacoDAO,Integer> tbcIdTco = new TableColumn<>("ID");
        tbcIdTco.setCellValueFactory(new PropertyValueFactory<>("id_taco"));

        TableColumn<TacoDAO,String> tbcNombre = new TableColumn<>("NOMBRE");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("tipo_taco"));

        TableColumn<TacoDAO,Float> tbcCosto = new TableColumn<>("COSTO");
        tbcCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        TableColumn<TacoDAO,Float> tbcPrecio = new TableColumn<>("PRECIO");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));


        TableColumn<TacoDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<TacoDAO, String>, TableCell<TacoDAO, String>>() {
                    @Override
                    public TableCell<TacoDAO, String> call(TableColumn<TacoDAO, String> param) {
                        return new ButtonCellTaco(2);
                    }
                }
        );

        TableColumn<TacoDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<TacoDAO, String>, TableCell<TacoDAO, String>>() {
                    @Override
                    public TableCell<TacoDAO, String> call(TableColumn<TacoDAO, String> param) {
                        return new ButtonCellTaco(1);
                    }
                }
        );


        tbvComida.getColumns().addAll(tbcIdTco,tbcNombre,tbcCosto, tbcPrecio,tbcEditar,tbcBorrar);
        tbvComida.setItems(objT.selAllTacos());
    }

    private void AgregarComida()
    {
        new FormTaco(tbvComida, null,1);
    }

}
