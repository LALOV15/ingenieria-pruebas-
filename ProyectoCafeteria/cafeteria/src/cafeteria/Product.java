package cafeteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Articulo;

public class Product extends javax.swing.JFrame {

    // SE CREA UN MODEL PARA PODER AGREGAR DATOS AL JLIST
    DefaultListModel<Articulo> model = new DefaultListModel();

    public Product() {
        initComponents();
        
        // SE ESTABLECE EL MODEL AL JLIST
        listaCompra.setModel(model);
    }

    // METODO PARA RESTAR DE LA BASE DE DATOS LOS PRODUCTOS COMPRADOS
    public void resta(Articulo item) {

        String sql = "SELECT ingrediente, disponible FROM preparacion p left join ingrediente i on p.ingrediente = i.id_ingrediente where articulo = " + item.getIdArticulo();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = DBConexion.conectar();
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int disponible = rs.getInt("disponible");
                String idIngredientte = rs.getString("ingrediente");
                disponible = disponible - 1;
                String sql1 = "UPDATE ingrediente SET disponible = " + disponible + " WHERE id_ingrediente = " + idIngredientte + ";";
                Statement pst1 = null;
                int rs1 = 0;
                pst1 = cn.createStatement();
                rs1 = pst1.executeUpdate(sql1);

                if (rs1 == 0) {
                    JOptionPane.showMessageDialog(null, "Error al Restar");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error ");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    
    // METODO PARA REGISTRAR LOS PRODUCTOS COMPRADOS EN LA BASE DE DATOS
    public void buy() {

        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        boolean sold = true;
        
        for (int i = 0; i < model.getSize(); i++) {
            Articulo item = model.getElementAt(i);

            resta(item);

            String sql = "INSERT INTO venta(fecha, pago, costo, producto) VALUES ('" + fecha + "', 1, " + item.getPrecio() + ", " + item.getIdArticulo() + ")";
            Connection cn = null;
            Statement pst = null;
            int rs = 0;

            try {
                cn = DBConexion.conectar();
                pst = cn.createStatement();
                rs = pst.executeUpdate(sql);

                if (rs == 0) {
                    //error
                    JOptionPane.showMessageDialog(null, "Error al vender " + item.getNombre());
                    sold = false;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
            } finally {
                try {
                    if (pst != null) {
                        pst.close();
                    }
                    if (cn != null) {
                        cn.close();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        }

        if (sold) {
        JOptionPane.showMessageDialog(null, " ¡¡ COMPRA EXITOSA !! ");    
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        out = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cafeAmericano = new javax.swing.JButton();
        cafeCapuchino = new javax.swing.JButton();
        cafeExpresso = new javax.swing.JButton();
        cafeLatte = new javax.swing.JButton();
        cafeMoka = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        pastel = new javax.swing.JButton();
        muffin = new javax.swing.JButton();
        pay = new javax.swing.JButton();
        helado = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        galletas = new javax.swing.JButton();
        back = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        buy = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaCompra = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Productos");

        jPanel1.setBackground(new java.awt.Color(88, 36, 23));
        jPanel1.setForeground(new java.awt.Color(88, 36, 23));

        out.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logout.png"))); // NOI18N
        out.setContentAreaFilled(false);
        out.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 0, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cafeteria");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(out, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Café");

        cafeAmericano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Americano_P.png"))); // NOI18N
        cafeAmericano.setText("Americano");
        cafeAmericano.setContentAreaFilled(false);
        cafeAmericano.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cafeAmericano.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cafeAmericano.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Americano_G.png"))); // NOI18N
        cafeAmericano.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cafeAmericano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cafeAmericanoActionPerformed(evt);
            }
        });

        cafeCapuchino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Cappuccino_P.png"))); // NOI18N
        cafeCapuchino.setText("Capuchino");
        cafeCapuchino.setContentAreaFilled(false);
        cafeCapuchino.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cafeCapuchino.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cafeCapuchino.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Cappuccino_G.png"))); // NOI18N
        cafeCapuchino.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cafeCapuchino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cafeCapuchinoActionPerformed(evt);
            }
        });

        cafeExpresso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Expresso_P.png"))); // NOI18N
        cafeExpresso.setText("Expresso");
        cafeExpresso.setContentAreaFilled(false);
        cafeExpresso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cafeExpresso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cafeExpresso.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Expresso_G.png"))); // NOI18N
        cafeExpresso.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cafeExpresso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cafeExpressoActionPerformed(evt);
            }
        });

        cafeLatte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Latte_P.png"))); // NOI18N
        cafeLatte.setText("Latte");
        cafeLatte.setContentAreaFilled(false);
        cafeLatte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cafeLatte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cafeLatte.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Latte_G.png"))); // NOI18N
        cafeLatte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cafeLatte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cafeLatteActionPerformed(evt);
            }
        });

        cafeMoka.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Moka_P.png"))); // NOI18N
        cafeMoka.setText("Moka");
        cafeMoka.setContentAreaFilled(false);
        cafeMoka.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cafeMoka.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cafeMoka.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Moka_G.png"))); // NOI18N
        cafeMoka.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cafeMoka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cafeMokaActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        pastel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Pastel_P.png"))); // NOI18N
        pastel.setText("Pastel");
        pastel.setContentAreaFilled(false);
        pastel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pastel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pastel.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Pastel_G.png"))); // NOI18N
        pastel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pastel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pastelActionPerformed(evt);
            }
        });

        muffin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Muffin_P.png"))); // NOI18N
        muffin.setText("Muffin");
        muffin.setContentAreaFilled(false);
        muffin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        muffin.setFocusable(false);
        muffin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        muffin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Muffin_G.png"))); // NOI18N
        muffin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        muffin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muffinActionPerformed(evt);
            }
        });

        pay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Pay_P.png"))); // NOI18N
        pay.setText("Pay");
        pay.setContentAreaFilled(false);
        pay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pay.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Pay_G.png"))); // NOI18N
        pay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payActionPerformed(evt);
            }
        });

        helado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Helado_P.png"))); // NOI18N
        helado.setText("Helado");
        helado.setContentAreaFilled(false);
        helado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        helado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        helado.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Helado_G.png"))); // NOI18N
        helado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        helado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heladoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Postre");

        galletas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Galleta_P.png"))); // NOI18N
        galletas.setText("Galletas");
        galletas.setContentAreaFilled(false);
        galletas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        galletas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        galletas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Galleta_G.png"))); // NOI18N
        galletas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        galletas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                galletasActionPerformed(evt);
            }
        });

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Return.png"))); // NOI18N
        back.setContentAreaFilled(false);
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ReturnGrande.png"))); // NOI18N
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Resumen de compra");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        buy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Buy_P.png"))); // NOI18N
        buy.setContentAreaFilled(false);
        buy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buy.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Buy_G.png"))); // NOI18N
        buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listaCompra);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addComponent(buy, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(buy, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cafeAmericano, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cafeLatte, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(65, 65, 65)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cafeCapuchino, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(cafeMoka, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(65, 65, 65)
                                        .addComponent(cafeExpresso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(galletas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(helado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pastel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addComponent(pay, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(muffin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cafeExpresso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cafeAmericano, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cafeCapuchino, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cafeLatte, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cafeMoka, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(galletas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pastel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(pay, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(muffin, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(helado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void heladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heladoActionPerformed
        // SE CREA UN OBJETO DE TIPO ARTICULO Y SE AGREGA AL MODEL PARA MOSTRARLO EN LA LISTA
        Articulo item = new Articulo(9, "Helado", 16);
        model.addElement(item);
    }//GEN-LAST:event_heladoActionPerformed

    private void buyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyActionPerformed
        // SE LLAMA AL METODO BUY Y SE LIMPIAN LOS REGISTROS DE LA LISTA
        buy();
        model.clear();
    }//GEN-LAST:event_buyActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // REDIRECCIONA A LA PANTALLA HOME Y CIERRA LA ACTUAL
        Home home = new Home();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_backActionPerformed

    private void outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outActionPerformed
        // REDIRECCIONA A LA PANTALLA LOGIN Y CIERRA LA ACTUAL
        Login log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_outActionPerformed

    private void cafeLatteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cafeLatteActionPerformed
        // SE CREA UN OBJETO DE TIPO ARTICULO Y SE AGREGA AL MODEL PARA MOSTRARLO EN LA LISTA
        Articulo item = new Articulo(4, "Latte", 19);
        model.addElement(item);
    }//GEN-LAST:event_cafeLatteActionPerformed

    private void cafeAmericanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cafeAmericanoActionPerformed
        // SE CREA UN OBJETO DE TIPO ARTICULO Y SE AGREGA AL MODEL PARA MOSTRARLO EN LA LISTA
        Articulo item = new Articulo(1, "Americano", 11);
        model.addElement(item);
    }//GEN-LAST:event_cafeAmericanoActionPerformed

    private void cafeCapuchinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cafeCapuchinoActionPerformed
        // SE CREA UN OBJETO DE TIPO ARTICULO Y SE AGREGA AL MODEL PARA MOSTRARLO EN LA LISTA
        Articulo item = new Articulo(2, "Capuchino", 13);
        model.addElement(item);
    }//GEN-LAST:event_cafeCapuchinoActionPerformed

    private void cafeExpressoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cafeExpressoActionPerformed
        // SE CREA UN OBJETO DE TIPO ARTICULO Y SE AGREGA AL MODEL PARA MOSTRARLO EN LA LISTA
        Articulo item = new Articulo(3, "Expresso", 14);
        model.addElement(item);
    }//GEN-LAST:event_cafeExpressoActionPerformed

    private void cafeMokaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cafeMokaActionPerformed
        // SE CREA UN OBJETO DE TIPO ARTICULO Y SE AGREGA AL MODEL PARA MOSTRARLO EN LA LISTA
        Articulo item = new Articulo(5, "Moka", 15);
        model.addElement(item);
    }//GEN-LAST:event_cafeMokaActionPerformed

    private void galletasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_galletasActionPerformed
        // SE CREA UN OBJETO DE TIPO ARTICULO Y SE AGREGA AL MODEL PARA MOSTRARLO EN LA LISTA
        Articulo item = new Articulo(6, "Galletas", 12);
        model.addElement(item);
    }//GEN-LAST:event_galletasActionPerformed

    private void pastelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pastelActionPerformed
        // SE CREA UN OBJETO DE TIPO ARTICULO Y SE AGREGA AL MODEL PARA MOSTRARLO EN LA LISTA
        Articulo item = new Articulo(7, "Pastel", 50);
        model.addElement(item);
    }//GEN-LAST:event_pastelActionPerformed

    private void muffinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muffinActionPerformed
        // SE CREA UN OBJETO DE TIPO ARTICULO Y SE AGREGA AL MODEL PARA MOSTRARLO EN LA LISTA
        Articulo item = new Articulo(10, "Muffin", 10);
        model.addElement(item);
    }//GEN-LAST:event_muffinActionPerformed

    private void payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payActionPerformed
        // SE CREA UN OBJETO DE TIPO ARTICULO Y SE AGREGA AL MODEL PARA MOSTRARLO EN LA LISTA
        Articulo item = new Articulo(8, "Pay", 20);
        model.addElement(item);
    }//GEN-LAST:event_payActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Product().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton buy;
    private javax.swing.JButton cafeAmericano;
    private javax.swing.JButton cafeCapuchino;
    private javax.swing.JButton cafeExpresso;
    private javax.swing.JButton cafeLatte;
    private javax.swing.JButton cafeMoka;
    private javax.swing.JButton galletas;
    private javax.swing.JButton helado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JList<Articulo> listaCompra;
    private javax.swing.JButton muffin;
    private javax.swing.JButton out;
    private javax.swing.JButton pastel;
    private javax.swing.JButton pay;
    // End of variables declaration//GEN-END:variables
}
