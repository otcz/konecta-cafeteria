package cafe.B_menuPrincipal.control;

import cafe.D_nuevoEditarProducto.vista.FrmCrearNuevoProducto;
import cafe.D_nuevoEditarProducto.vista.FrmEditarProducto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoEditarProducto implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        FrmEditarProducto editarProducto = FrmEditarProducto.getInstancia();
        editarProducto.setVisible(true);
    }
}
