package cafe.C_nuevaVenta.controlador;

import cafe.C_nuevaVenta.modelo.Venta;
import cafe.C_nuevaVenta.vista.FrmNuevaVenta;

public class RecoletarDatosMuni {

    public Venta recolectar() {
        Venta ventas = new Venta();
        FrmNuevaVenta nuevaVenta = FrmNuevaVenta.getInstancia();
        if (nuevaVenta.getTxtID().getText().trim().isEmpty() | nuevaVenta.getTxtNombreProducto().getText().trim().isEmpty() |
                nuevaVenta.getCbProducto().getSelectedItem().toString().trim().isEmpty() | nuevaVenta.getTextFecha().getText().trim().isEmpty() |
                nuevaVenta.getTextFecha().getText().trim().isEmpty() | nuevaVenta.getTxtStock().getText().trim().isEmpty()) {
            return null;
        } else {

            ventas.setsID(nuevaVenta.getTxtID().getText());
            ventas.setsNombreProducto(nuevaVenta.getTxtNombreProducto().getText());
            ventas.setsFecha(nuevaVenta.getTextFecha().getText());
            ventas.setsStock(nuevaVenta.getTxtStock().getText());
            return ventas;
        }
    }
}
