package sample.Componentes;

import ModelosTaqueria.BebidaDAO;
import javafx.scene.control.*;
import sample.Vistas.FormBebida;
import java.util.Optional;

public class ButtonCellBeb extends TableCell<BebidaDAO,String> {

    private Button btnCelda;
    private BebidaDAO objB;

    public ButtonCellBeb(int opc) {
        if( opc == 1) {
            btnCelda = new Button(("Editar"));
            btnCelda.setOnAction(event ->
            {
                TableView<BebidaDAO> tbvTemp;
                tbvTemp = ButtonCellBeb.this.getTableView();
                objB = ButtonCellBeb.this.getTableView().getItems().get(ButtonCellBeb.this.getIndex());
                if( objB != null )
                    System.out.println("");
                else
                    System.out.println("");
                new FormBebida(tbvTemp,objB,2);
            });
        }
        else{
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event ->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema");
                alert.setHeaderText("Confirmación de Acción");
                alert.setContentText("¿Deseas eliminar la Bebida?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    objB = ButtonCellBeb.this.getTableView().getItems().get(ButtonCellBeb.this.getIndex());
                    if( objB != null )
                        System.out.println("");
                    else
                        System.out.println("");
                    objB.delBebida();
                    ButtonCellBeb.this.getTableView().setItems(objB.selAllBebidas());
                    ButtonCellBeb.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item, boolean empty){
        super.updateItem(item, empty);
        if( !empty )
            setGraphic(btnCelda);
    }
}
