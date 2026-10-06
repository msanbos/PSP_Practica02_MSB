package modelo;

public class Cliente {
    private int numero;
    private String nombre;
    private int edad;
    private double puntos;

    public Cliente(int numero, String nombre, int edad){
        this.numero = numero;
        this.nombre = nombre;
        this.edad = edad;
        this.puntos = 0;
    }
    
    public Cliente(int numero, String nombre, int edad,double puntos){
        this(numero,nombre,edad);
        this.puntos = puntos;
    }

    public int getNumero() {return numero;}
    public void setNumero(int numero) {this.numero = numero;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}
    public double getPuntos() {return puntos;}
    public void setPuntos(double puntos) {this.puntos = puntos;}
    
    
    @Override
    public String toString(){        
        return "["+this.numero+"] "+this.nombre+" - "+this.edad+" - PUNTOS: "+this.puntos;
    }
}
