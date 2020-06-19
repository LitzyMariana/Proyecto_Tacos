package sample.Vistas;

import ModelosTaqueria.GenerarPdf;
import ModelosTaqueria.PedidoDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import sample.Componentes.ButtonCellPed;

import java.io.File;

public class CRUDPM extends Stage{

    public static String DEST = "Tickets/Ticket.pdf";

    private Scene escena;
    private VBox vbox;
    private TableView<PedidoDAO> tbvPedido;
    private Button btnAgregar, btnTicket;
    private PedidoDAO objP;

    int usuario, pedido, mesa;
    GenerarPdf pdf = new GenerarPdf();

    public CRUDPM(){
        objP = new PedidoDAO();
        CrearGUI();
        this.setTitle("Administración de los Pedidos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvPedido = new TableView<>();
        tbvPedido.setPrefSize(300,500);
        CrearTabla();

        btnAgregar = new Button("Agregar Pedido");
        btnAgregar.setAlignment(Pos.CENTER);
        btnAgregar.setPrefSize(300,20);
        btnAgregar.setOnAction(event -> AgregarPedido());

        btnTicket = new Button("Imprimir Ticket");
        btnTicket.setAlignment(Pos.CENTER);
        btnTicket.setPrefSize(300,20);
        btnTicket.setOnAction(handler);

        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.getChildren().addAll(tbvPedido,btnAgregar,btnTicket);
        escena = new Scene(vbox,680,600);
        escena.getStylesheets().add("sample/Estilos/CRUDPM.css");
    }



    private void CrearTabla() {

        TableColumn<PedidoDAO,Integer> tbcId_pedido = new TableColumn<>("ID");
        tbcId_pedido.setCellValueFactory(new PropertyValueFactory<>("id_pedido"));

        TableColumn<PedidoDAO, String>tbcFecha=new TableColumn<>("FECHA");
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<PedidoDAO,Integer> tbcCantidad_t = new TableColumn<>("No. Tacos");
        tbcCantidad_t.setCellValueFactory(new PropertyValueFactory<>("cantidad_t"));

        TableColumn<PedidoDAO,Integer> tbcCantidad_b = new TableColumn<>("No. Bebidas");
        tbcCantidad_b.setCellValueFactory(new PropertyValueFactory<>("cantidad_b"));

        TableColumn<PedidoDAO,Integer> tbcNum_mesa = new TableColumn<>("No. de Mesa");
        tbcNum_mesa.setCellValueFactory(new PropertyValueFactory<>("num_mesa"));

        TableColumn<PedidoDAO,Float> tbcTotal = new TableColumn<>("Total");
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<PedidoDAO,String> tbcNombre_cliente = new TableColumn<>("Cliente");
        tbcNombre_cliente.setCellValueFactory(new PropertyValueFactory<>("nombre_cliente"));

        TableColumn<PedidoDAO,Integer> tbcId_usuario = new TableColumn<>("ID USUARIO");
        tbcId_usuario.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));

        TableColumn<PedidoDAO,Integer> tbcId_registro = new TableColumn<>("ID REGISTRO");
        tbcId_registro.setCellValueFactory(new PropertyValueFactory<>("id_registro"));

        TableColumn<PedidoDAO,Integer> tbcId_taco = new TableColumn<>("ID TACO");
        tbcId_taco.setCellValueFactory(new PropertyValueFactory<>("id_taco"));

        TableColumn<PedidoDAO,Integer> tbcId_bebida = new TableColumn<>("ID BEBIDA");
        tbcId_bebida.setCellValueFactory(new PropertyValueFactory<>("id_bebida"));

        TableColumn<PedidoDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<PedidoDAO, String>, TableCell<PedidoDAO, String>>() {
                    @Override
                    public TableCell<PedidoDAO, String> call(TableColumn<PedidoDAO, String> param) {
                        return new ButtonCellPed(2);
                    }
                }
        );

        TableColumn<PedidoDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<PedidoDAO, String>, TableCell<PedidoDAO, String>>() {
                    @Override
                    public TableCell<PedidoDAO, String> call(TableColumn<PedidoDAO, String> param) {
                        return new ButtonCellPed(1);
                    }
                }
        );


        tbvPedido.getColumns().addAll(tbcId_pedido,tbcFecha,tbcCantidad_t,tbcCantidad_b,tbcNum_mesa,tbcTotal,tbcNombre_cliente,tbcEditar,tbcBorrar);
        tbvPedido.setItems(objP.selAllPedido());
    }

    private void AgregarPedido()
    {
        new FormPedidoM(tbvPedido, null,1);
    }

      EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (actionEvent.getSource() == btnTicket) {
                    File file = new File(DEST);
                    file.getParentFile().mkdir();
                    pdf.GenerarTicket(usuario, pedido, mesa);
                }
            }

        };

}
