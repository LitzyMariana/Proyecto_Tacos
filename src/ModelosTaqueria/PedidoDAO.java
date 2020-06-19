package ModelosTaqueria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



public class PedidoDAO {



    private int id_pedido;
    private String fecha;
    private int cantidad_t;
    private int cantidad_b;
    private int num_mesa;
    public float total;
    private String nombre_cliente;
    private int id_usuario;
    private int id_registro;
    private int id_taco;
    private int id_bebida;

    public String usuario;
    public String tipo_taco;
    public String tipo_bebida;

    private PedidoDAO objPe;

    public float getTotal() { return total; }
    public void setTotal(float total) { this.total = total; }
    public int getId_pedido() { return id_pedido; }
    public void setId_pedido(int id_pedido) { this.id_pedido = id_pedido; }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public int getCantidad_t() { return cantidad_t; }
    public void setCantidad_t(int cantidad_t) { this.cantidad_t = cantidad_t; }
    public int getCantidad_b() { return cantidad_b; }
    public void setCantidad_b(int cantidad_b) { this.cantidad_b = cantidad_b; }
    public int getNum_mesa() { return num_mesa; }
    public void setNum_mesa(int num_mesa) { this.num_mesa = num_mesa; }
    public String getNombre_cliente() { return nombre_cliente; }
    public void setNombre_cliente(String nombre_cliente) { this.nombre_cliente = nombre_cliente; }
    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }
    public int getId_registro() { return id_registro; }
    public void setId_registro(int id_registro) { this.id_registro = id_registro; }
    public int getId_taco() { return id_taco; }
    public void setId_taco(int id_taco) { this.id_taco = id_taco; }
    public int getId_bebida() { return id_bebida; }
    public void setId_bebida(int id_bebida) { this.id_bebida = id_bebida; }

    private Connection con;

    public PedidoDAO (){ con = ConexionTaqueria.con; }

    public ObservableList<PedidoDAO> selAllPedido(){

        ObservableList<PedidoDAO> listaP = FXCollections.observableArrayList();
        PedidoDAO objP = null;
        String query = "select * from pedido;";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new PedidoDAO();
                objP.setId_pedido(res.getInt("id_pedido"));
                objP.setFecha(res.getString("fecha"));
                objP.setCantidad_t(res.getInt("cantidad_t"));
                objP.setCantidad_b(res.getInt("cantidad_b"));
                objP.setNum_mesa(res.getInt("num_mesa"));
                objP.setTotal(res.getFloat("Total"));
                objP.setNombre_cliente(res.getString("nombre_cliente"));
                objP.setId_usuario(res.getInt("id_usuario"));
                objP.setId_registro(res.getInt("id_registro"));
                objP.setId_taco(res.getInt("id_taco"));
                objP.setId_bebida(res.getInt("id_bebida"));

                listaP.add(objP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaP;
    }


    public void insPedido(){
        String query = "insert into pedido " +
                "(fecha, cantidad_t, cantidad_b, num_mesa , total, nombre_cliente , id_taco, id_bebida) " +
                "values('"+fecha+"',"+cantidad_t+","+cantidad_b+","+num_mesa+","+total+",'"+nombre_cliente+"',"+id_taco+","+id_bebida+");";
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void delPedido(){
        String query = "delete from pedido where id_pedido="+id_pedido;
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){}
    }

    public void updPedido(){
        String query = "update pedido set fecha='"+fecha+"', cantidad_t="+cantidad_t
                +", cantidad_b="+cantidad_b+", num_mesa="+num_mesa+", total="+total+" ,nombre_cliente='"+
                nombre_cliente+"', id_taco="+id_taco
                +",id_bebida="+id_bebida+" where id_pedido="+id_pedido;
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ObservableList<PedidoDAO> GenerarTicket(){
        ObservableList<PedidoDAO> listaP = FXCollections.observableArrayList();
        PedidoDAO objP = null;
        String query = "select total " +
                "from pedido " +
                "where id_pedido = " + id_pedido + " and num_mesa = " + num_mesa +";";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new PedidoDAO();
                objP.setNum_mesa(res.getInt("Mesa"));
                objP.setNombre_cliente(res.getString("Cliente"));
                objP.setId_pedido(res.getInt("Pedido"));
                objP.setTotal(res.getFloat("Total"));

                listaP.add(objP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaP;
    }
}
