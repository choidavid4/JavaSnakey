import javax.swing.*;

public class LobbyPanelTestDrive{
    public static void main(String[] args){
        JFrame frame = new JFrame("LobbyPanelTestDrive");
        
       
        //Primero agregamos los componentes
        frame.add(new LobbyPanel());
         
        //Luego seteamos las propiedades
    
        //Metodo pack() para que el frame se adapte al tamaño de sus componentes. Tiene que estar luego de haber agregado los componentes.
        //frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        frame.pack();
        

    }
}