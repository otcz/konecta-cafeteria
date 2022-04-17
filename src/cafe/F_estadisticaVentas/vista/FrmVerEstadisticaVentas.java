/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe.F_estadisticaVentas.vista;


import cafe.A_BD.ConnectionBD;
import cafe.C_nuevaVenta.controlador.AlmacenarVenta;
import cafe.C_nuevaVenta.modelo.Ventas;
import cafe.E_verProductos.vista.FrmVerProductos;
import cafe.F_estadisticaVentas.modelo.Jtable;
import cafe.F_estadisticaVentas.modelo.TituloTabla;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static cafe.A_inicio.vista.FrmInicio.colorPrimary;

public class FrmVerEstadisticaVentas extends JFrame {

    private JScrollPane jScrollPane1;
    private JTable jtRegistros;
    private JPanel pnNorte;
    private JPanel pnPrincipal;
    private JPanel pnSur;
    private JPanel pnCentro;
    private JLabel lbBuscar;
    public static FrmVerProductos verRegistro;
    private DefaultPieDataset defaultpiedataset;
    private JFreeChart chart;
    private PiePlot3D pieplot3d;
    private ChartPanel chartPanel;
    public static FrmVerEstadisticaVentas estadisticaVentas;

    public FrmVerEstadisticaVentas() {
        ImageIcon icono = new ImageIcon(getClass().getResource("/cafe/imagen/ICONO.png"));
        this.setIconImage(icono.getImage());
        GridBagConstraints gridBagConstraints;
        pnPrincipal = new JPanel();
        pnNorte = new JPanel();
        lbBuscar = new JLabel();
        pnSur = new JPanel();
        pnCentro = new JPanel();
        jScrollPane1 = new JScrollPane();
        jtRegistros = new Jtable(TituloTabla.tabla_estadistica, new Object[0][0]);


        defaultpiedataset = new DefaultPieDataset();
        chart = ChartFactory.createPieChart3D("ESTADISTICA VENTAS", defaultpiedataset, true, true, false);
        pieplot3d = (PiePlot3D) chart.getPlot();
        pieplot3d.setDepthFactor(0.5);
        pieplot3d.setStartAngle(290D);
        pieplot3d.setDirection(Rotation.CLOCKWISE);
        pieplot3d.setForegroundAlpha(0.5F);
        chartPanel = new ChartPanel(chart);


        for (int i = 0; i < AlmacenarVenta.getVentas().size(); i++) {
            defaultpiedataset.setValue(AlmacenarVenta.getVentas().get(i).getsNombreProducto() + " - " + AlmacenarVenta.getVentas().get(i).getsCantidad(), new Double(AlmacenarVenta.getVentas().get(i).getsCantidad()));
        }


        setBackground(colorPrimary);
        setForeground(colorPrimary);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
        pnPrincipal.setBackground(colorPrimary);
        pnPrincipal.setLayout(new BorderLayout());
        pnNorte.setBackground(colorPrimary);
        pnNorte.setLayout(new GridBagLayout());
        lbBuscar.setBackground(colorPrimary);
        lbBuscar.setFont(new Font("Roboto", 1, 14)); // NOI18N
        lbBuscar.setForeground(new Color(255, 255, 255));
        lbBuscar.setAlignmentX(1.0F);
        lbBuscar.setAlignmentY(1.0F);
        lbBuscar.setMaximumSize(new Dimension(300, 30));
        lbBuscar.setMinimumSize(new Dimension(300, 30));
        lbBuscar.setPreferredSize(new Dimension(300, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(23, 26, 17, 0);
        pnNorte.add(lbBuscar, gridBagConstraints);

        pnPrincipal.add(pnNorte, BorderLayout.NORTH);
        pnSur.setBackground(new Color(255, 255, 255));
        pnSur.setLayout(new BorderLayout());

        pnCentro.setBackground(new Color(255, 255, 255));
        pnCentro.setLayout(new BorderLayout());

        jScrollPane1.setBackground(colorPrimary);
        jScrollPane1.setViewportView(jtRegistros);
        pnSur.add(chartPanel, BorderLayout.SOUTH);
        pnCentro.add(jScrollPane1, BorderLayout.CENTER);
        pnPrincipal.add(pnCentro, BorderLayout.CENTER);
        pnPrincipal.add(pnSur, BorderLayout.SOUTH);
        getContentPane().add(pnPrincipal);
        pack();
        DefaultTableModel model = (DefaultTableModel) getJtRegistros().getModel();


        for (int i = 0; i < AlmacenarVenta.getVentas().size(); i++) {
            Ventas ventas = AlmacenarVenta.getVentas().get(i);
            model.addRow(ventas.getPropiedades());
        }
        MaxStock();
    }

    public void MaxStock() {
        try {
            Connection con = new ConnectionBD().connection();
            String SQL = "SELECT SUM( DISTINCT cantidad) FROM ventas;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                getLbBuscar().setText("MAYOR VENTA " + rs.getObject(1).toString());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public JLabel getLbBuscar() {
        return lbBuscar;
    }

    public void setLbBuscar(JLabel lbBuscar) {
        this.lbBuscar = lbBuscar;
    }

    public JFreeChart getChart() {
        return chart;
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }

    public PiePlot3D getPieplot3d() {
        return pieplot3d;
    }

    public void setPieplot3d(PiePlot3D pieplot3d) {
        this.pieplot3d = pieplot3d;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public void setChartPanel(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JTable getJtRegistros() {
        return jtRegistros;
    }

    public void setJtRegistros(JTable jtRegistros) {
        this.jtRegistros = jtRegistros;
    }


    public JPanel getPnPrincipal() {
        return pnPrincipal;
    }

    public void setPnPrincipal(JPanel pnPrincipal) {
        this.pnPrincipal = pnPrincipal;
    }

    public DefaultPieDataset getDefaultpiedataset() {
        return defaultpiedataset;
    }

    public void setDefaultpiedataset(DefaultPieDataset defaultpiedataset) {
        this.defaultpiedataset = defaultpiedataset;
    }

    public static FrmVerEstadisticaVentas getInstancia() {
        if (estadisticaVentas == null) {
            estadisticaVentas = new FrmVerEstadisticaVentas();
        } else {
            return estadisticaVentas;
        }
        return estadisticaVentas;
    }
}
