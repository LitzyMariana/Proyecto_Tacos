package ModelosTaqueria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BebidaDAO {

    private int id_bebida;
    private String tipo_bebida;
    private String nombre_b;
    private float costo;
    private float precio;

    public int getId_bebida() { return id_bebida; }
    public void setId_bebida(int id_bebida) { this.id_bebida = id_bebida; }
    public String getTipo_bebida() { return tipo_bebida; }
    public void setTipo_bebida(String tipo_bebida) { this.tipo_bebida = tipo_bebida; }
    public String getNombre_b() { return nombre_b; }
    public void setNombre_b(String nombre_b) { this.nombre_b = nombre_b; }
    public float getCosto() { return costo; }
    public void setCosto(float costo) { this.costo = costo; }
    public float getPrecio() { return precio; }
    public void setPrecio(float precio) { this.precio = precio; }

    private Connection con;

    public BebidaDAO()
    {
        con = ConexionTaqueria.con;
    }

    public ObservableList<BebidaDAO> selAllBebidas(){

        ObservableList<BebidaDAO> listaB = FXCollections.observableArrayList();
        BebidaDAO objB = null;
        String query = "select * from bebida order by id_bebida";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objB = new BebidaDAO();
                objB.setId_bebida(res.getInt("id_bebida"));
                objB.setTipo_bebida(res.getString("tipo_bebida"));
                objB.setNombre_b(res.getString("nombre_b"));
                objB.setCosto(res.getFloat("costo"));
                objB.setPrecio(res.getFloat("precio"));

                listaB.add(objB);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaB;
    }

    public void insBebida(){
        String query = "insert into bebida(tipo_bebida,nombre_b,costo,precio) values('"+tipo_bebida+"','"+nombre_b+"',"+ costo +","+precio+");";
        try {
            Statement stm = con.createStatement();
            stm.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delBebida(){
        String query = "delete from bebida where id_bebida="+id_bebida;
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){}
    }

    public void updBebida(){
        String query = "update bebida set tipo_bebida='"+tipo_bebida+"'" +
                ",nombre_b='"+nombre_b+"',costo="+costo+",precio="+precio+" where " +
                "id_bebida="+id_bebida;
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

    public void getBebidaByID(){
        String query ="select * from bebida where id_bebida ="+id_bebida;
        try {
            Statement stmt = ConexionTaqueria.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                tipo_bebida = res.getString("tipo_bebida");
                nombre_b = res.getString("nombre_b");
                costo = res.getFloat("costo");
                precio = res.getFloat("precio");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public String toString(){
        return nombre_b;
    }


}
