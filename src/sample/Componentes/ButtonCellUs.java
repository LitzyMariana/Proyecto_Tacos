package sample.Componentes;

import ModelosTaqueria.UsuarioDAO;
import javafx.scene.control.*;
import sample.Vistas.FormUsuario;

import java.util.Optional;

public class ButtonCellUs extends TableCell<UsuarioDAO,String>{

    private Button btnCelda;
    private UsuarioDAO objUs;

    public ButtonCellUs(int opc){
        if( opc == 1) {
            btnCelda = new Button(("Editar"));
            btnCelda.setOnAction(event ->
            {
                TableView<UsuarioDAO> tbvTemp;
                tbvTemp = ButtonCellUs.this.getTableView();
                objUs = ButtonCellUs.this.getTableView().getItems().get(ButtonCellUs.this.getIndex());
                new FormUsuario(tbvTemp, objUs,2);

            });
        }
        else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event ->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema");
                alert.setHeaderText("Confirmación de Acción");
                alert.setContentText("¿Desea eliminar a este Usuario?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    objUs = ButtonCellUs.this.getTableView().getItems().get(ButtonCellUs.this.getIndex());
                    if( objUs != null )
                        System.out.println("");
                    else
                        System.out.println("");
                    objUs.delUusario();
                    ButtonCellUs.this.getTableView().setItems(objUs.selAllUsuarios());
                    ButtonCellUs.this.getTableView().refresh();
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
