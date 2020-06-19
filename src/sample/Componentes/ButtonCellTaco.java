package sample.Componentes;

import ModelosTaqueria.TacoDAO;
import javafx.scene.control.*;
import sample.Vistas.FormTaco;
import java.util.Optional;

public class ButtonCellTaco extends TableCell<TacoDAO,String>
{
    private Button btnCelda;
    private TacoDAO objT;

    public ButtonCellTaco(int opc)
    {
        if( opc == 1) {
            btnCelda = new Button(("Editar"));
            btnCelda.setOnAction(event ->
            {
                TableView<TacoDAO> tbvTemp;
                tbvTemp = ButtonCellTaco.this.getTableView();
                objT = ButtonCellTaco.this.getTableView().getItems().get(ButtonCellTaco.this.getIndex());
                if( objT != null )
                    System.out.println("");
                else
                    System.out.println("");
                new FormTaco(tbvTemp, objT,2);
                //ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
            });
        }
        else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event ->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema");
                alert.setHeaderText("Confirmación de Acción");
                alert.setContentText("¿Deseas eliminar este taco?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    objT = ButtonCellTaco.this.getTableView().getItems().get(ButtonCellTaco.this.getIndex());
                    if( objT != null )
                        System.out.println("");
                    else
                        System.out.println("");
                    objT.delTaco();

                    //Refrescar la tabla
                    ButtonCellTaco.this.getTableView().setItems(objT.selAllTacos());
                    ButtonCellTaco.this.getTableView().refresh();
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
