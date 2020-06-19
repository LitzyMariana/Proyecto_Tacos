package sample.Componentes;

import ModelosTaqueria.PedidoDAO;
import javafx.scene.control.*;
import sample.Vistas.FormPedido;


import java.util.Optional;

public class ButtonCellPed extends TableCell<PedidoDAO,String> {

    private Button btnCelda;
    private PedidoDAO objP;

    public ButtonCellPed(int opc)
    {
        if( opc == 1) {
            btnCelda = new Button(("Editar"));
            btnCelda.setOnAction(event ->
            {
                TableView<PedidoDAO> tbvTemp;
                tbvTemp = ButtonCellPed.this.getTableView();
                objP = ButtonCellPed.this.getTableView().getItems().get(ButtonCellPed.this.getIndex());
                if( objP != null )
                    System.out.println("");
                else
                    System.out.println("");
                new FormPedido(tbvTemp, objP,2);

            });
        }
        else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event ->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema");
                alert.setHeaderText("Confirmación de Acción");
                alert.setContentText("¿Desea eliminar el Pedido?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    objP = ButtonCellPed.this.getTableView().getItems().get(ButtonCellPed.this.getIndex());
                    if( objP != null )
                        System.out.println("");
                    else
                        System.out.println("");
                    objP.delPedido();
                    ButtonCellPed.this.getTableView().setItems(objP.selAllPedido());
                    ButtonCellPed.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item, boolean empty)
    {
        super.updateItem(item, empty);
        if( !empty )
            setGraphic(btnCelda);
    }
}
