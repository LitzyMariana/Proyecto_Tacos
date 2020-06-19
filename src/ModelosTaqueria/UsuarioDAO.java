package ModelosTaqueria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDAO {
    public int id_usuario;
    private String nombre;
    private  String telefono;
    private float sueldo;
    private String tipo_usuario;
    private String contraseña;

    public String nombreUs = "";

    private UsuarioDAO objUs;

    public int bandera=0;

    public int getId_usuario() { return id_usuario; }

    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public float getSueldo() { return sueldo; }

    public void setSueldo(float sueldo) { this.sueldo = sueldo; }

    public String getTipo_usuario() { return tipo_usuario; }

    public void setTipo_usuario(String tipo_usuario) { this.tipo_usuario = tipo_usuario; }

    public String getContraseña() { return contraseña; }

    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    private Connection con;

    public UsuarioDAO() { con = ConexionTaqueria.con; }

    public int sellOneUsuario()
    {

        String query = "select id_usuario, nombre, telefono, sueldo, tipo_usuario, contraseña from usuario where nombre='"+nombre+"' and contraseña='"+contraseña+"';";

        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if(res.next()){
                objUs = new UsuarioDAO();
                objUs.setId_usuario(res.getInt("id_usuario"));
                bandera = 1;
                id_usuario = objUs.getId_usuario();

            }
            else {
                bandera = 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bandera;
    }

    public ObservableList<UsuarioDAO> selAllUsuarios(){
        ObservableList<UsuarioDAO> listaUs = FXCollections.observableArrayList();
        UsuarioDAO objUs = null;
        String query = "select * from usuario order by id_usuario";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objUs = new UsuarioDAO();
                objUs.setId_usuario(res.getInt("id_usuario"));
                objUs.setNombre(res.getString("nombre"));
                objUs.setTelefono(res.getString("telefono"));
                objUs.setSueldo(res.getFloat("sueldo"));
                objUs.setTipo_usuario(res.getString("tipo_usuario"));
                objUs.setContraseña(res.getString("contraseña"));


                listaUs.add(objUs);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaUs;
    }

    public void insUsuario(){
        String query = "insert into usuario " +
                "(nombre,telefono,sueldo,tipo_usuario,contraseña) " +
                "values('"+nombre+"','"+telefono+"',"+sueldo+",'"+tipo_usuario+"','"+contraseña+"');";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delUusario(){
        String query = "delete from usuario where id_usuario="+ id_usuario+";";
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){}
    }

    public void updUsuario(){
        String query = "update usuario set nombre='"+nombre+"'" +
                ",telefono='"+telefono+"',sueldo="+sueldo+",tipo_usuario='"+tipo_usuario+"',contraseña='"+contraseña+"' where " +
                "id_usuario="+ id_usuario;
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

    public int DatosT(){

        String query = "select * from usuario where id_usuario='"+id_usuario+"';";

        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if(res.next()){
                objUs = new UsuarioDAO();
                objUs.setNombre(res.getString("nombre"));
                nombreUs =  objUs.getNombre();
            }
            else {
                bandera = 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bandera;
    }
}