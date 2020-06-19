package ModelosTaqueria;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.Vistas.CRUDPM;
import sample.Vistas.P_Mesero;

import java.io.IOException;

public class GenerarPdf
{
    PedidoDAO dao = new PedidoDAO();
    UsuarioDAO objU;
    P_Mesero orden;


    public void GenerarTicket(int id_usuario, int id_pedido, int num_mesa){


        dao.setId_usuario(id_usuario);
        dao.setId_pedido(id_pedido);
        dao.setNum_mesa(num_mesa);


        objU= new UsuarioDAO();
        objU.setId_usuario(id_usuario);
        objU.DatosT();

        ObservableList<PedidoDAO> Ticket = dao.GenerarTicket();


        try {
            PdfWriter writer = new PdfWriter(CRUDPM.DEST);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate());
            document.setTextAlignment(TextAlignment.CENTER);
            document.setMargins(20, 20, 20, 20);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);
            PdfFont bold = PdfFontFactory.createFont(StandardFonts.COURIER_BOLD);
            PdfFont font1 = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
            PdfFont font2 = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);


            DashedLine dashedLine = new DashedLine(1);

            Text text1 = new Text("Ticket del Pago ").setFont(font1).setFontSize(18).setBold();
            Paragraph p3 = new Paragraph().add(text1).add("\n\n");
            p3.setTextAlignment(TextAlignment.CENTER);

            Text text3 = new Text("Mesa : " + num_mesa).setFont(font2).setFontSize(14);
            Text text4 = new Text("Usuario : " + objU.getTipo_usuario()).setFont(font2).setFontSize(14);
            Text text5 = new Text("Pedido : " + dao.getId_pedido() ).setFont(font2).setFontSize(14);
            Text text6 = new Text("Mesero : " + objU.nombreUs).setFont(font2).setFontSize(14);
            Paragraph p2 = new Paragraph().add(text3).add("\n").add(text4).add("\n").add(text5).add("\n").add(text6);

            float propina = (float) (dao.total * 0.15);
            Text text7 = new Text("Propina: $ " + propina + "pesos").setFont(font1).setFontSize(18);
            Text text8 = new Text("Total : $ " + dao.total + "pesos\n").setFont(font1).setFontSize(18);
            Paragraph p4 = new Paragraph().add(text8).add(text7);

            document.add(new Paragraph("\n\n")).add(p2).add(new LineSeparator(dashedLine)).add(p3);

            Table table = new Table(UnitValue.createPercentArray(new float[]{3, 3, 8}))
                    .useAllAvailableWidth();

            process(table, null, bold, true); //Imprime los encabezados
            for (PedidoDAO cu : Ticket) {
                process(table, cu, font, false);
            }

            document.add(table);
            document.add(new Paragraph("\n\n")).add(p4);
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Imprimiendo Ticket");
        alert.setHeaderText(null);
        alert.setContentText("Se ha creado el Ticket");
        alert.showAndWait();
    }

    public void process(Table table, PedidoDAO cu, PdfFont font, boolean isHeader) {

        if (isHeader)
        {
            table.addHeaderCell(new Cell().add(new Paragraph("Mesa").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombre").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Total").setFont(font)));

        }
        else
        {
            table.addCell(new Cell().add(new Paragraph("" + cu.getNum_mesa()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph("" + cu.getNombre_cliente()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph("" + cu.getTotal()).setFont(font)));
        }
    }
}