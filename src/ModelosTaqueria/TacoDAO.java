package ModelosTaqueria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TacoDAO {

    private int id_taco;
    private String tipo_taco;
    private float costo;
    private float precio;

    public int getId_taco() { return id_taco; }
    public void setId_taco(int id_taco) { this.id_taco = id_taco; }
    public String getTipo_taco() { return tipo_taco; }
    public void setTipo_taco(String tipo_taco) { this.tipo_taco = tipo_taco; }
    public float getCosto() { return costo; }
    public void setCosto(float costo) { this.costo = costo; }
    public float getPrecio() { return precio; }
    public void setPrecio(float precio) { this.precio = precio; }

    private Connection con;
    public TacoDAO()
    {
        con = ConexionTaqueria.con;
    }

    public ObservableList<TacoDAO> selAllTacos(){
        ObservableList<TacoDAO> listaT = FXCollections.observableArrayList();
        TacoDAO objT = null;
        String query = "select * from taco order by id_taco";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objT = new TacoDAO();
                objT.setId_taco(res.getInt("id_taco"));
                objT.setTipo_taco(res.getString("tipo_taco"));
                objT.setCosto(res.getFloat("costo"));
                objT.setPrecio(res.getFloat("precio"));


                listaT.add(objT);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaT;
    }

    public void insTaco(){
        String query = "insert into taco " +
                "(tipo_taco,costo,precio) " +
                "values('"+tipo_taco+"',"+costo+","+precio+");";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delTaco(){
        String query = "delete from taco where id_taco="+ id_taco;
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){}
    }

    public void updTaco(){
        String query = "update taco set tipo_taco='"+tipo_taco+"'" +
                ",costo="+costo+",precio="+precio+" where " +
                "id_taco="+ id_taco;
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

    public void getTacoByID(){
        String query ="select * from taco where id_taco ="+id_taco;
        try {
            Statement stmt = ConexionTaqueria.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()){
                tipo_taco = res.getString("tipo_taco");
                costo = res.getFloat("costo");
                precio = res.getFloat("precio");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return tipo_taco;
    }
}
