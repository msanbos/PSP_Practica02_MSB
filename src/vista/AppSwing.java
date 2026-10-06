package vista;

import modelo.Cliente; //Importamos nuestro modelo
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

/*
*   Vamos a crear las dos versiones como en los ejemplos, sobre todo para 
*   enterarnos un poco de que va el tema. 
*   En este ejemplo usamos un método que devuelve un componente(JPanel) y luego 
*   se añade al JFrame
*/

public class AppSwing {
    //Creamos  nuestra lista
    private List<Cliente> clientes = new ArrayList<>();
    private int indice = 0;
    
    //COMPONENTES GRÁFICOS:
    //Los TextFields para los datos. Como atributos para crear las funciones de actualizarVista
    private JTextField numField;
    private JTextField nomField;
    private JTextField edadField;
    private JTextField punField;
    //Los botones. Los usamos también en la función de actualizarVista
    private JButton siguiente;
    private JButton anterior;
    

    //Usamos un constructor para rellenar la lista de clientes precargada
    public AppSwing(){
        clientes.add(new Cliente(1,"José Antonio",36,200));
        clientes.add(new Cliente(2,"Manuel Jesús",19,100));
        clientes.add(new Cliente(3,"Federico",48));
    }
    
    //Creamos los componentes con una función
    public Component createComponents(){
        //Creamos los Label para las etiquetas de texto. final
        final JLabel numLabel = new JLabel("Número: ");
        final JLabel nomLabel = new JLabel("Nombre: ");
        final JLabel edadLabel = new JLabel("Edad: ");
        final JLabel punLabel = new JLabel("PUNTOS: ");
        //Creamos los botones
        siguiente = new JButton("Siguiente ->");
        anterior = new JButton("<- Anterior");
        //siguiente.setMnemonic(KeyEvent.VK_I);//Para reasignar teclas a los botones
        
        //Creamos las Acciones por defecto con el Listener de los botones
        siguiente.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(indice<clientes.size()-1){
                    indice++;
                    actualizarVista();
                }
            }
        });
        anterior.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(indice>0){
                    indice--;
                    actualizarVista();
                }
            }
        });
        //label.setLabelFor(button); // Para minuválidos
        
        //Panel para las etiquetas        
        JPanel labelPane = new JPanel(new GridLayout(0,1));//(0,1) solo una columna
        labelPane.add(numLabel); //labelPane.add(new JLabel("Número: ");
        labelPane.add(nomLabel);
        labelPane.add(edadLabel);
        labelPane.add(punLabel);
        
        //Creamos los TextField
        numField = new JTextField(10);
        numField.setEditable(false);
        nomField = new JTextField(10);
        nomField.setEditable(false);
        edadField = new JTextField(10);
        edadField.setEditable(false);
        punField = new JTextField(10);
        punField.setEditable(false);
        
        //Panel para los textField
        JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(numField);
        fieldPane.add(nomField);
        fieldPane.add(edadField);
        fieldPane.add(punField);
        
        //Panel para los botones 
        JPanel botonPane = new JPanel(new GridLayout(1,0));
        botonPane.add(anterior);
        botonPane.add(siguiente);
        
        //Panel vacío para incluir los demás
        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        pane.setLayout(new BorderLayout());
        pane.add(labelPane, BorderLayout.WEST);
        pane.add(fieldPane, BorderLayout.CENTER);
        pane.add(botonPane, BorderLayout.SOUTH);
        
        //Para mostrar el primero 
        actualizarVista();
        
        return pane;
    }
    
    public static void main(String[] args){
        // UIManager: de Java Swing es la encargada de gestionar y almacenar ajustes globales de interfaz 
        //getCross...estilo metal
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) { } 
        
        //Arrancamos en un hilo la interfaz Swing
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                //El contenedor de más alto nivel y le añade el contenido. Título
                JFrame frame = new JFrame("Gestor de Clientes");
                //Lo que pasa si cerramos la ventana
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //LLamada al constructor por defecto
                AppSwing swingApp = new AppSwing();
                //Crear los componentes. LLamada a la función
                Component contents = swingApp.createComponents();
                //añadir el Jframe
                frame.getContentPane().add(contents,BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    
    private void actualizarVista(){
        Cliente cliente = clientes.get(indice);
        numField.setText(String.valueOf(cliente.getNumero()));
        nomField.setText(cliente.getNombre());
        edadField.setText(String.valueOf(cliente.getEdad()));
        punField.setText(String.valueOf(cliente.getPuntos()));
        //Activar o Desactivar los botones según el límite del ArrayList
        siguiente.setEnabled(indice<clientes.size()-1);
        anterior.setEnabled(indice>0);
    }
}
