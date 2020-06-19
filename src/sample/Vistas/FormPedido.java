package sample.Vistas;

import ModelosTaqueria.BebidaDAO;
import ModelosTaqueria.PedidoDAO;
import ModelosTaqueria.TacoDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class FormPedido extends Stage{

    Scene escena;

    TextField txtFecha, txtcantidad_t, txtcantidad_b, txtnum_mesa, txttotal, txtnombre_cliente;
    Label  lblFecha, lblcboxTaco, lblcantidad_t, lblcboxBebida, lblcantidad_b, lblnum_mesa,lbltotal, lblnombre_cliente;
    VBox vBox;
    ComboBox<TacoDAO> cboxTaco;
    ComboBox<BebidaDAO> cboxBebida;
    Button btnAgregar, btnActualizar;

    TableView<PedidoDAO> tbvPedido;

    PedidoDAO objP;

    public FormPedido(TableView<PedidoDAO> tbvPedido, PedidoDAO obj, int bandera)
    {
        if( obj != null) {
            objP = obj;
        }
        else {
            objP = new PedidoDAO();
            }

        this.tbvPedido = tbvPedido;
        CrearGUI(bandera);
        this.setTitle("Gestión de Pedidos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI(int bandera)
    {
        vBox = new VBox();

        txtFecha = new TextField();
        txtFecha.setPromptText("Ingresa la fecha");
        txtFecha.setText(objP.getFecha()+"");
        txtFecha.setMaxSize(150,20);

        txtcantidad_t = new TextField();
        txtcantidad_t.setPromptText("Ingrese el número de tacos");
        txtcantidad_t.setText(objP.getCantidad_t()+"");
        txtcantidad_t.setMaxSize(150,20);

        txtcantidad_b = new TextField();
        txtcantidad_b.setPromptText("Ingrese el número de Bebidas");
        txtcantidad_b.setText(objP.getCantidad_b()+"");
        txtcantidad_b.setMaxSize(150,20);

        txtnum_mesa = new TextField();
        txtnum_mesa.setPromptText("Ingrese la mesa");
        txtnum_mesa.setText(objP.getNum_mesa()+"");
        txtnum_mesa.setMaxSize(150,20);

        txttotal = new TextField();
        txttotal.setPromptText("Ingrese el total");
        txttotal.setText(objP.getNum_mesa()+"");
        txttotal.setMaxSize(150,20);

        txtnombre_cliente = new TextField();
        txtnombre_cliente.setPromptText("Ingrese el Nombre del cliente");
        txtnombre_cliente.setText(objP.getNombre_cliente()+"");
        txtnombre_cliente.setMaxSize(150,20);

        lblFecha = new Label ("Fecha");
        lblcantidad_t = new Label("No. de Tacos");
        lblcantidad_b = new Label("No. de Bebidas");
        lblnum_mesa = new Label("Mesa");
        lbltotal = new Label("Total");
        lblnombre_cliente = new Label("Cliente");
        lblcboxTaco = new Label("Taco");
        lblcboxBebida = new Label("Bebida");

        cboxTaco = new ComboBox();
        cboxTaco.setItems(new TacoDAO().selAllTacos());
        TacoDAO objT = new TacoDAO();
        objT.setId_taco(objP.getId_taco());
        objT.getTacoByID();
        cboxTaco.setValue(objT);

        cboxBebida = new ComboBox();
        cboxBebida.setItems(new BebidaDAO().selAllBebidas());
        BebidaDAO objB = new BebidaDAO();
        objB.setId_bebida(objP.getId_bebida());
        objB.getBebidaByID();
        cboxBebida.setValue(objB);


        btnAgregar = new Button("Agregar Pedido");
        btnAgregar.setOnAction(actionEvent -> enviarDatos(1));

        btnActualizar = new Button("Actualizar Pedido");
        btnActualizar.setOnAction(actionEvent -> enviarDatos(2));

        if(bandera == 1)
        {
            btnActualizar.setDisable(true);
        }
        else
        {
            btnAgregar.setDisable(true);
        }

        vBox.getChildren().addAll(lblFecha,txtFecha,lblcboxTaco, cboxTaco,lblcantidad_t,txtcantidad_t,lblcboxBebida,cboxBebida,lblcantidad_b,txtcantidad_b,
                lblnum_mesa, txtnum_mesa, lbltotal, txttotal,lblnombre_cliente,txtnombre_cliente, btnAgregar, btnActualizar);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        escena = new Scene(vBox,400, 680);
        escena.getStylesheets().add("sample/Estilos/FormstyleP.css");
    }



    private void enviarDatos(int proceso)
    {
        objP.setFecha(txtFecha.getText());
        objP.setCantidad_t(Integer.parseInt(txtcantidad_t.getText()));
        objP.setCantidad_b(Integer.parseInt(txtcantidad_b.getText()));
        objP.setNum_mesa(Integer.parseInt(txtnum_mesa.getText()));
        objP.setTotal(Float.parseFloat(txttotal.getText()));
        objP.setNombre_cliente(txtnombre_cliente.getText());


        TacoDAO objTemp = cboxTaco.getValue();
        objP.setId_taco(objTemp.getId_taco());
        if ( objP.getId_taco() >= 1)
            objP.updPedido();
        else
            objP.insPedido();

        BebidaDAO objTempo = cboxBebida.getValue();
        objP.setId_bebida(objTempo.getId_bebida());
        if ( objP.getId_bebida() >= 1)
            objP.updPedido();
        else
            objP.insPedido();


        if( proceso  == 1 )
        {
            objP.insPedido();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡El Pedido se ha registrado exitosamente!");
            alert.setHeaderText(null);
            alert.setContentText("Registro Correcto");
            alert.showAndWait();
        }
        else
        {
            objP.updPedido();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Actualizado");
            alert.setHeaderText(null);
            alert.setContentText("¡Se ha Actualizado Correctamente!");
            alert.showAndWait();
        }

        tbvPedido.setItems(objP.selAllPedido());
        tbvPedido.refresh();

        txtFecha.setText("");
        txtcantidad_t.setText("");
        txtcantidad_b.setText("");
        txtnum_mesa.setText("");
        txtnombre_cliente.setText("");

    }

}