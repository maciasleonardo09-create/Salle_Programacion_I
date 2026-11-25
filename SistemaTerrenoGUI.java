import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class SistemaTerrenoGUI extends JFrame {
    private static final Color COLOR_FONDO = new Color(80, 200, 120);
    private static final String CARD_MENU = "MENU";
    private static final String CARD_COORDENADAS = "COORDENADAS";
    private static final String CARD_COSTOS = "COSTOS";
    private static final String CARD_RESULTADOS = "RESULTADOS";

    private final CalculadoraTerreno calculadora = new CalculadoraTerreno();
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

    // Componentes de Coordenadas
    private final JTextField txtX = new JTextField(10);
    private final JTextField txtY = new JTextField(10);
    private final JTextArea areaCoordenadas = new JTextArea(8, 25);

    // Componentes de Costos
    private final JTextField txtPrecioM2 = new JTextField(12);
    private final JTextField txtPrecioReja = new JTextField(12);

    // Componentes de Resultados
    private final JTextField txtNombreProyecto = new JTextField("Terreno sin nombre", 20);
    private final JLabel lblArea = new JLabel("0.00 m²");
    private final JLabel lblPerimetro = new JLabel("0.00 m");
    private final JLabel lblCostoPropiedad = new JLabel("$ 0.00");
    private final JLabel lblCostoEnrejado = new JLabel("$ 0.00");
    private final JLabel lblCostoGlobal = new JLabel("$ 0.00");
    private final PanelPoligono panelPoligono = new PanelPoligono();

    public SistemaTerrenoGUI() {
        setTitle("Sistema de Análisis Geométrico y Evaluación de Costos de Terrenos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 540);
        setLocationRelativeTo(null);
        mainPanel.setBackground(COLOR_FONDO);

        mainPanel.add(crearPanelMenu(), CARD_MENU);
        mainPanel.add(crearPanelCoordenadas(), CARD_COORDENADAS);
        mainPanel.add(crearPanelCostos(), CARD_COSTOS);
        mainPanel.add(crearPanelResultados(), CARD_RESULTADOS);

        add(mainPanel);
        cardLayout.show(mainPanel, CARD_MENU);
    }

    private JPanel crearPanelMenu() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(new EmptyBorder(30, 50, 30, 50));

        JLabel titulo = new JLabel(
                "<html><center>SISTEMA DE ANÁLISIS GEOMÉTRICO Y<br>EVALUACIÓN DE COSTOS DE TERRENOS</center></html>",
                SwingConstants.CENTER
        );
        titulo.setForeground(Color.white);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel opcionesPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        opcionesPanel.setBackground(COLOR_FONDO);
        opcionesPanel.setBorder(new EmptyBorder(20, 80, 20, 80));

        JButton btn1 = crearBotonMenu("1. Ingresar coordenadas");
        JButton btn2 = crearBotonMenu("2. Ingresar costos");
        JButton btn3 = crearBotonMenu("3. Calcular");
        JButton btn4 = crearBotonMenu("4. Mostrar resultados");
        JButton btn5 = crearBotonMenu("5. Salir");

        btn1.addActionListener(e -> {
            actualizarListaCoordenadas();
            cardLayout.show(mainPanel, CARD_COORDENADAS);
        });

        btn2.addActionListener(e -> {
            if (calculadora.getPrecioMetroCuadrado() > 0) {
                txtPrecioM2.setText(String.valueOf(calculadora.getPrecioMetroCuadrado()));
            }
            if (calculadora.getPrecioMetroLinealReja() > 0) {
                txtPrecioReja.setText(String.valueOf(calculadora.getPrecioMetroLinealReja()));
            }
            cardLayout.show(mainPanel, CARD_COSTOS);
        });

        btn3.addActionListener(e -> ejecutarCalculo(true));

        btn4.addActionListener(e -> {
            if (!calculadora.isCalculado()) {
                if (!ejecutarCalculo(false)) {
                    return;
                }
            }
            actualizarVistaResultados();
            cardLayout.show(mainPanel, CARD_RESULTADOS);
        });

        btn5.addActionListener(e -> {
            int resp = JOptionPane.showConfirmDialog(
                    this,
                    "¿Deseas salir del sistema?",
                    "Salir",
                    JOptionPane.YES_NO_OPTION
            );
            if (resp == JOptionPane.YES_OPTION) {
                dispose();
                System.exit(0);
            }
        });

        opcionesPanel.add(btn1);
        opcionesPanel.add(btn2);
        opcionesPanel.add(btn3);
        opcionesPanel.add(btn4);
        opcionesPanel.add(btn5);

        panel.add(opcionesPanel, BorderLayout.CENTER);

        JLabel pie = new JLabel("Seleccione una opción", SwingConstants.CENTER);
        pie.setForeground(Color.LIGHT_GRAY);
        pie.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(pie, BorderLayout.SOUTH);

        return panel;
    }

    private JButton crearBotonMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 15));
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        return btn;
    }

    private JPanel crearPanelCoordenadas() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBorder(new EmptyBorder(20, 25, 20, 25));
        panel.setBackground(COLOR_FONDO);
        

        JLabel titulo = new JLabel("Ingreso de Coordenadas de Vértices (X, Y)", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(COLOR_FONDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Coordenada X:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtX, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Coordenada Y:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtY, gbc);

        JButton btnAgregar = new JButton("Agregar punto");
        JButton btnLimpiar = new JButton("Limpiar lista");

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(btnAgregar, gbc);
        gbc.gridx = 1;
        formPanel.add(btnLimpiar, gbc);

        areaCoordenadas.setEditable(false);
        areaCoordenadas.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(areaCoordenadas);
        scroll.setBackground(COLOR_FONDO);
        scroll.getViewport().setBackground(COLOR_FONDO);
        scroll.setBorder(new TitledBorder("Lista de coordenadas"));

        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.setBackground(COLOR_FONDO);
        centro.add(formPanel, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);
        panel.add(centro, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, CARD_MENU));
        panel.add(btnVolver, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> {
            try {
                double x = Double.parseDouble(txtX.getText().trim());
                double y = Double.parseDouble(txtY.getText().trim());
                calculadora.agregarVertice(x, y);
                txtX.setText("");
                txtY.setText("");
                txtX.requestFocus();
                actualizarListaCoordenadas();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Ingresa valores numéricos válidos para X y Y.",
                        "Error de entrada",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        btnLimpiar.addActionListener(e -> {
            calculadora.limpiarVertices();
            actualizarListaCoordenadas();
        });

        return panel;
    }

    private void actualizarListaCoordenadas() {
        StringBuilder sb = new StringBuilder();
        List<Vertice> lista = calculadora.getVertices();
        for (int i = 0; i < lista.size(); i++) {
            sb.append(String.format("V%d: %s%n", i + 1, lista.get(i).toString()));
        }
        areaCoordenadas.setText(sb.toString());
    }

    private JPanel crearPanelCostos() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(new EmptyBorder(25, 40, 25, 40));

        JLabel titulo = new JLabel("Costos Unitarios del Terreno y Cercado", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 2, 12, 12));
        form.setBackground(COLOR_FONDO);
        form.setBorder(new EmptyBorder(30, 30, 30, 30));

        form.add(new JLabel("Precio por metro cuadrado ($ / m²):"));
        form.add(txtPrecioM2);

        form.add(new JLabel("Precio por metro lineal de reja ($ / m):"));
        form.add(txtPrecioReja);

        JButton btnGuardar = new JButton("Guardar costos");
        JButton btnVolver = new JButton("Volver al menú");

        form.add(btnGuardar);
        form.add(btnVolver);

        panel.add(form, BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> {
            try {
                double precioM2 = Double.parseDouble(txtPrecioM2.getText().trim());
                double precioReja = Double.parseDouble(txtPrecioReja.getText().trim());
                calculadora.setCostos(precioM2, precioReja);
                JOptionPane.showMessageDialog(this, "Costos unitarios guardados correctamente.");
                cardLayout.show(mainPanel, CARD_MENU);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Ingresa números válidos en ambos campos de costo.",
                        "Error de entrada",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Validación",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, CARD_MENU));

        return panel;
    }

    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBackground(COLOR_FONDO);
        top.add(new JLabel("Proyecto / Terreno:"));
        top.add(txtNombreProyecto);
        panel.add(top, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(1, 2, 15, 0));
        grid.setBackground(COLOR_FONDO);

        JPanel izq = new JPanel(new BorderLayout(8, 8));
        izq.setBackground(COLOR_FONDO);
        izq.setBorder(new TitledBorder("Resultados Geométricos"));

        JPanel datosGeom = new JPanel(new GridLayout(2, 2, 6, 6));
        datosGeom.setBackground(COLOR_FONDO);
        datosGeom.add(new JLabel("Área del terreno:"));
        datosGeom.add(lblArea);
        datosGeom.add(new JLabel("Perímetro del terreno:"));
        datosGeom.add(lblPerimetro);

        izq.add(datosGeom, BorderLayout.NORTH);
        izq.add(panelPoligono, BorderLayout.CENTER);

        JPanel der = new JPanel(new GridLayout(6, 1, 8, 8));
        der.setBackground(COLOR_FONDO);
        der.setBorder(new TitledBorder("Evaluación de Costos y Presupuesto"));

        der.add(new JLabel(String.format("Precio unitario m²: $ %.2f", calculadora.getPrecioMetroCuadrado())));
        der.add(new JLabel(String.format("Precio unitario reja/m: $ %.2f", calculadora.getPrecioMetroLinealReja())));
        der.add(crearFilaCosto("Costo total de la propiedad:", lblCostoPropiedad));
        der.add(crearFilaCosto("Costo total del enrejado:", lblCostoEnrejado));
        der.add(crearFilaCosto("Costo global del proyecto:", lblCostoGlobal));

        grid.add(izq);
        grid.add(der);
        panel.add(grid, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(COLOR_FONDO);
        JButton btnVolver = new JButton("Volver al menú");

        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, CARD_MENU));

        bottom.add(btnVolver, BorderLayout.WEST);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearFilaCosto(String etiqueta, JLabel valorLabel) {
        JPanel fila = new JPanel(new BorderLayout());
        fila.setBackground(COLOR_FONDO);
        fila.add(new JLabel(etiqueta), BorderLayout.WEST);
        valorLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        fila.add(valorLabel, BorderLayout.EAST);
        return fila;
    }

    private boolean ejecutarCalculo(boolean mostrarMensajeExito) {
        try {
            calculadora.calcular();
            actualizarVistaResultados();
            if (mostrarMensajeExito) {
                JOptionPane.showMessageDialog(
                        this,
                        "Cálculos completados. Usa la opción 4 para ver el detalle.",
                        "Cálculo exitoso",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
            return true;
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
    }

    private void actualizarVistaResultados() {
        lblArea.setText(String.format("%,.2f m²", calculadora.getArea()));
        lblPerimetro.setText(String.format("%,.2f m", calculadora.getPerimetro()));
        lblCostoPropiedad.setText(String.format("$ %,.2f", calculadora.getCostoTotalPropiedad()));
        lblCostoEnrejado.setText(String.format("$ %,.2f", calculadora.getCostoTotalEnrejado()));
        lblCostoGlobal.setText(String.format("$ %,.2f", calculadora.getCostoGlobalProyecto()));
        panelPoligono.setVertices(calculadora.getVertices());
        panelPoligono.repaint();
    }

    private static class PanelPoligono extends JPanel {
        private List<Vertice> vertices;

        public PanelPoligono() {
            setBackground(COLOR_FONDO);
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }

        public void setVertices(List<Vertice> vertices) {
            this.vertices = vertices;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (vertices == null || vertices.size() < 3) {
                return;
            }

            double minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
            double minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;

            for (Vertice v : vertices) {
                minX = Math.min(minX, v.getX());
                maxX = Math.max(maxX, v.getX());
                minY = Math.min(minY, v.getY());
                maxY = Math.max(maxY, v.getY());
            }

            double rangoX = Math.max(maxX - minX, 1.0);
            double rangoY = Math.max(maxY - minY, 1.0);
            int margen = 30;
            int anchoDisp = Math.max(getWidth() - 2 * margen, 10);
            int altoDisp = Math.max(getHeight() - 2 * margen, 10);
            double escala = Math.min(anchoDisp / rangoX, altoDisp / rangoY);

            int n = vertices.size();
            int[] px = new int[n];
            int[] py = new int[n];

            for (int i = 0; i < n; i++) {
                Vertice v = vertices.get(i);
                px[i] = margen + (int) ((v.getX() - minX) * escala);
                // Invertir Y para que coordenadas mayores queden arriba
                py[i] = getHeight() - margen - (int) ((v.getY() - minY) * escala);
            }

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.DARK_GRAY);
            g2.setStroke(new BasicStroke(2f));
            g2.drawPolygon(px, py, n);

            g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
            for (int i = 0; i < n; i++) {
                g2.fillOval(px[i] - 3, py[i] - 3, 6, 6);
                g2.drawString(vertices.get(i).toString(), px[i] + 5, py[i] - 5);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaTerrenoGUI().setVisible(true));
    }
}