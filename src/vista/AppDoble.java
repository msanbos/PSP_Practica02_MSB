package vista;

import modelo.Cliente;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class AppDoble extends JFrame{
    //Nuestra lista de clientes
    private List<Cliente> clientes = new ArrayList<>();
    
    //Text Fields para incluir los datos (Ahora Swing: JTextField)
    private JTextField numField;
    private JTextField nomField;
    private JTextField edadField;
    private JTextField punField;
 
    //Etiquetas para para los campos de texto
    private JLabel numLabel;
    private JLabel nomLabel;
    private JLabel edadLabel;
    private JLabel punLabel;
    
    //Los textos para las etiquetas
    private static String nomText = "Nombre: ";
    private static String numText= "Número: ";
    private static String edadText = "Edad: ";
    private static String punText = "PUNTOS: ";    
    
    //Creamos los botones
    private JButton anterior;
    private JButton siguiente;
    
    //ïndice para saber en que cliente estoy
    private int indice = 0;
    
    private boolean focusIsSet = false;
    
    public AppDoble(){
        //Le ponemos el título
        super("Gestor de Clientes");
        
        //Listener de cerrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Inicializamos nuestros clientes
        clientes.add(new Cliente(1,"José Antonio",36,200));
        clientes.add(new Cliente(2,"Manuel Jesús",19,100));
        clientes.add(new Cliente(3,"Federico",48));
               
        //Crea las etiquetas (Esto a lo mejor se puede hacer antes)
        numLabel = new JLabel(numText);
        nomLabel = new JLabel(nomText);
        edadLabel = new JLabel(edadText);
        punLabel = new JLabel(punText);
        
        //Inicializamos los TextField
        numField = new JTextField(10);
        numField.setEditable(false);    //False.Solo muestran información
        nomField = new JTextField(10);
        nomField.setEditable(false);
        edadField = new JTextField(10);
        edadField.setEditable(false);
        punField = new JTextField(10);
        punField.setEditable(false);
        //Pintamos el atributo de los Puntos de Azul
        punField.setForeground(Color.blue);
        
        //Inicializamos los botones
        siguiente = new JButton ("Siguiente ->");
        anterior = new JButton ("<- Anterior");
        
        //Añadimos un Listener por Defecto a los botones
        siguiente.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Siempre que respete el tamaño máximo de la tabla
                if(indice< clientes.size()-1){
                    indice++;//Al producirse el evento suma un indice
                    actualizarVista();//Y actualiza la vista 
                }
            }
        
        });
        
        anterior.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //No existen índices negativos
                if(indice>0){
                    indice--;//Al producirse el evento suma un indice
                    actualizarVista();//Y actualiza la vista 
                }
            }
        
        });
        //Disponemos los Label en un panel, el texto.
        //GridLayout(Fila, Columna, Espacio HOR, Espacio VER)
        JPanel labelPane = new JPanel();
        labelPane.setLayout(new GridLayout (0,1));
        labelPane.add(numLabel);
        labelPane.add(nomLabel);
        labelPane.add(edadLabel);
        labelPane.add(punLabel);
        
        //Los TextField de los datos en otro panel
        JPanel fieldPane = new JPanel();
        fieldPane.setLayout(new GridLayout(0,1));
        fieldPane.add(numField);
        fieldPane.add(nomField);
        fieldPane.add(edadField);
        fieldPane.add(punField);
        
        //Otro panel para los botones
        JPanel botonPane = new JPanel();
        botonPane.setLayout(new GridLayout(1,0));
        botonPane.add(anterior);
        botonPane.add(siguiente);
        
        //Contenemos los paneles en otro panel principal
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(labelPane, BorderLayout.WEST);
        contentPane.add(fieldPane, BorderLayout.CENTER);
        contentPane.add(botonPane, BorderLayout.SOUTH);
        
        //"Machacamos" el contanodor objeto "Doble", hijo de JFrame por el extends
        setContentPane(contentPane);
        //Mostramos el primer cliente        
        //Vamos a crear un método para actualizar los parámetros
        actualizarVista();
        
        
    }
            
   
    public static void main (String[] args){
        //Creamos toda la interfaz Swing en un "hilo de eventos"
        //Cada run () es un hilo
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                final AppDoble dobleApp = new AppDoble();
    /*      ***EJEMPLO SI QUISIERAMOS PONER UN FOCUS***            
                //WindowListener ya solo sirve para poner focus
                dobleApp.addWindowListener(new WindowAdapter(){
                    //Pone el focus
                    public void WindowActivated(WindowEvent e){
                        dobleApp.setFocus();
                    }
                });
    */
                //Empaqueta y visibiliza 
                dobleApp.pack();
                dobleApp.setVisible(true);
            }
        
        });
    }
    
    private void actualizarVista(){
        //El cliente actual
        Cliente cliente = clientes.get(indice);
        
        //Actualizamos los atributos con los datos del cliente
        numField.setText(String.valueOf(cliente.getNumero()));
        nomField.setText(cliente.getNombre());
        edadField.setText(String.valueOf(cliente.getEdad()));
        punField.setText(String.valueOf(cliente.getPuntos()));
        
        /*
        Activamos o desactivamos los botones en los limites del
        ArrayList para que no nos dé error
        */
        siguiente.setEnabled(indice< clientes.size()-1);//Si no se cumple = false
        anterior.setEnabled(indice>0);
    }
/*EJEMPLO DE USAR UN FOCUS PARA UN COMPONENTE CONCRETO
    private void setFocus() {
        if (!focusIsSet) {
            numeroField.requestFocusInWindow();
            focusIsSet = true;
        }
    }
*/
    
}
