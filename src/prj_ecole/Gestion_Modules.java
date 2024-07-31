/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prj_ecole;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author hp
 */
public class Gestion_Modules extends javax.swing.JFrame {
    private int idresp;
    /**
     * Creates new form Gestion_Modules
     */
    public Gestion_Modules(int idresp) {
        initComponents();
        this.idresp = idresp;
         displayModules();
         fetchRespo();
         TableModules.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    //Récupère l'indice de la ligne sélectionnée dans la table.
                    int selectedRow = TableModules.getSelectedRow();
                    if (selectedRow != -1) {
                        String selectedClassName = TableModules.getValueAt(selectedRow, 2).toString();
                        String selectedProfessorName = TableModules.getValueAt(selectedRow, 3).toString();

                        cmbClasse.setSelectedItem(selectedClassName);
                        cmbProfessor.setSelectedItem(selectedProfessorName);

                        txtNom.setText(TableModules.getValueAt(selectedRow, 1).toString());
                    }
                }
            }
        });
    }
    private void fetchRespo() {
        try {
            Connection cnx = Config.getConnection();
            cnx.setAutoCommit(true);

            String queryProf = "SELECT Nom, Prenom FROM Responsable WHERE Id_respo = ?";
            try (PreparedStatement cmdProf = cnx.prepareStatement(queryProf)) {
                cmdProf.setInt(1, this.idresp);
                try (ResultSet reader = cmdProf.executeQuery()) {
                    if (reader.next()) {
                        String nom = reader.getString("Nom");
                        String prenom = reader.getString("Prenom");

                        label2.setText(nom + " " + prenom);
                        label2.setFont(new Font("Arial", Font.BOLD, 20));
                        label2.setForeground(Color.BLACK);
                    } else {
                        label2.setText("Informations du professeur non trouvées");
                        
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage());
        }
    }

    private void displayModules() {
         DefaultTableModel model = new DefaultTableModel();
         model.setColumnIdentifiers(new Object[]{"ID", "Nom du Module", "Nom du classe", "Nom du Professeur"});

         try (Connection connection = Config.getConnection()) {
              String query = "SELECT Id_module, Nom_module, Class.Nom_class as nomclass, Professeur.Nom AS NomProf FROM Professeur INNER JOIN Module ON Professeur.Id_prof = Module.Id_prof INNER JOIN Class ON Class.Id_class = Module.Id_class";

             try (PreparedStatement statement = connection.prepareStatement(query);
                  ResultSet resultSet = statement.executeQuery()) {

                 while (resultSet.next()) {
                       int moduleId = resultSet.getInt("Id_module");
                       String moduleName = resultSet.getString("Nom_module");
                       String classname = resultSet.getString("nomclass");
                       String professorName = resultSet.getString("NomProf");

                model.addRow(new Object[]{moduleId, moduleName, classname, professorName});
                 TableModules.setModel(model); 
                hideColumns(TableModules, 0);
                 TableModules.setSelectionBackground(SystemColor.activeCaption);
                 TableModules.setSelectionForeground(Color.BLUE);
            }
          }
        } catch (SQLException ex) {
        ex.printStackTrace();
       }

       TableModules.setModel(model);
       populateClassComboBox();
       populateProfessorComboBox();
    }
     private void hideColumns(JTable table, int columnIndex) {
    TableColumn column = table.getColumnModel().getColumn(columnIndex);
    column.setMinWidth(0);
    column.setMaxWidth(0);
    column.setWidth(0);
    column.setPreferredWidth(0);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableModules = new javax.swing.JTable();
        btnAjouter = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        txtNom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbProfessor = new javax.swing.JComboBox<>();
        cmbClasse = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        label2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Nom du prof");

        TableModules.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom du module", "Nom de classe", "Nom du professeur"
            }
        ));
        TableModules.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(TableModules);
        TableModules.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnAjouter.setBackground(new java.awt.Color(77, 125, 202));
        btnAjouter.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        btnAjouter.setText("Ajouter");
        btnAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterActionPerformed(evt);
            }
        });

        btnEnregistrer.setBackground(new java.awt.Color(77, 125, 202));
        btnEnregistrer.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        btnEnregistrer.setText("Enregistrer");
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerActionPerformed(evt);
            }
        });

        btnModifier.setBackground(new java.awt.Color(77, 125, 202));
        btnModifier.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        btnModifier.setText("Modifier");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nom de module");

        label.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label.setText("Nom du classe");

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel10.setText("Espace de gestion des modules");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        cmbProfessor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbClasse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(label))
                            .addGap(44, 44, 44)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(115, 115, 115)
                                    .addComponent(btnAjouter)
                                    .addGap(85, 85, 85)
                                    .addComponent(btnEnregistrer)
                                    .addGap(59, 59, 59)
                                    .addComponent(btnModifier))
                                .addComponent(cmbClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(166, 166, 166)
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmbProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(80, Short.MAX_VALUE)))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(359, 359, 359))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addComponent(jLabel10)
                .addGap(89, 89, 89)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmbProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label)
                    .addComponent(cmbClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouter)
                    .addComponent(btnModifier)
                    .addComponent(btnEnregistrer))
                .addGap(79, 79, 79)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1080, 680));

        jPanel2.setBackground(new java.awt.Color(77, 125, 202));

        jPanel3.setBackground(new java.awt.Color(98, 140, 209));

        jButton1.setText("Se Déconnecter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        label2.setText("jLabel3");
        label2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label2MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Bienvenue,");

        jPanel4.setBackground(new java.awt.Color(77, 125, 202));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Gestion Des Classes");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Gestion Des Etudiants");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Gestion Des Professeurs");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(77, 125, 202));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Gestion Des Modules");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(label2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(label2)
                .addGap(128, 128, 128)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(157, 157, 157)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 680));

        setSize(new java.awt.Dimension(1282, 681));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void populateClassComboBox() {
    try (Connection connection = Config.getConnection()) {
        String query = "SELECT Nom_class FROM Class";
        try (PreparedStatement cmd = connection.prepareStatement(query)) {
   
            try (ResultSet rs = cmd.executeQuery()) {
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                model.addElement("--choisir la classe--");
                while (rs.next()) {
                    model.addElement(rs.getString("Nom_class"));
                }
                cmbClasse.setModel(model);
                if (cmbClasse.getItemCount() > 1) {
                    cmbClasse.setSelectedIndex(-1);
                }
            }
        }
      
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage());
    }
}

    private void populateProfessorComboBox() {
       try (Connection connection = Config.getConnection()) {
            String query = "SELECT Nom FROM Professeur";
            try (PreparedStatement cmd = connection.prepareStatement(query)) {
          
                try (ResultSet rs = cmd.executeQuery()) {
                     DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                     model.addElement("--choisir le professeur--");
                     while (rs.next()) {
                     model.addElement(rs.getString("Nom"));
                }
                   cmbProfessor.setModel(model);
                   if (cmbProfessor.getItemCount() > 1) {
                       cmbProfessor.setSelectedIndex(-1);
                }
            }
        }
      
        
       } catch (SQLException ex) {
             JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage());
       }
    }
    private void btnAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) TableModules.getModel();

        if (btnAjouter.getText().equals("Ajouter")) {
            model.addRow(new Object[model.getColumnCount()]);
            btnAjouter.setText("Annuler");
            clearTextFields();
        } else {
            int rowCount = model.getRowCount();
            model.removeRow(rowCount - 1); 
            btnAjouter.setText("Ajouter");
        }
    }//GEN-LAST:event_btnAjouterActionPerformed
    private void clearTextFields() {
       txtNom.setText("");
       
    }
    public int getProfessorIdByName(String nomProfesseur) {
        int idProfesseur = -1;
        try (Connection cnx = Config.getConnection()) {
            cnx.setAutoCommit(true);

            String query = "SELECT Id_prof FROM Professeur WHERE Nom = ?";
            try (PreparedStatement command = cnx.prepareStatement(query)) {
                command.setString(1, nomProfesseur);

                try (ResultSet resultSet = command.executeQuery()) {
                    if (resultSet.next()) {
                        idProfesseur = resultSet.getInt("Id_prof");
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        return idProfesseur;
    }
    public int getClassByName(String nomclasse) {
        int idProfesseur = -1; 

        try (Connection cnx = Config.getConnection()) {
            cnx.setAutoCommit(true);

            String query = "SELECT Id_class FROM Class WHERE Nom_class = ?";
            try (PreparedStatement command = cnx.prepareStatement(query)) {
                command.setString(1, nomclasse);

                try (ResultSet resultSet = command.executeQuery()) {
                    if (resultSet.next()) {
                        idProfesseur = resultSet.getInt("Id_class");
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE); 
        }

        return idProfesseur;
    }
    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
    String nomProfesseurSelectionne = cmbProfessor.getSelectedItem().toString();
    int idProfesseurSelectionne = getProfessorIdByName(nomProfesseurSelectionne);
    String nomClasseSelectionnee = cmbClasse.getSelectedItem().toString();
    int idClasseSelectionnee = getClassByName(nomClasseSelectionnee);

    try {
        Connection connection = Config.getConnection();
        PreparedStatement insertCommand = connection.prepareStatement("INSERT INTO Module(Nom_module, Id_prof, Id_class) VALUES (?, ?, ?)");

        insertCommand.setString(1, txtNom.getText());
        insertCommand.setInt(2, idProfesseurSelectionne);
        insertCommand.setInt(3, idClasseSelectionnee);

        insertCommand.executeUpdate();

        JOptionPane.showMessageDialog(null, "L'enregistrement a été ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

        displayModules();
        clearTextFields();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de l'enregistrement : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        try {
        Connection connection = Config.getConnection();

        if (TableModules.getSelectedRowCount() > 0) {
            int selectedModuleId = (int) TableModules.getValueAt(TableModules.getSelectedRow(), TableModules.getColumn("ID").getModelIndex());

            String updateQuery = "UPDATE Module SET Nom_module = ?, Id_prof = ?, Id_class = ? WHERE Id_module = ?";

            PreparedStatement cmd = connection.prepareStatement(updateQuery);

            cmd.setInt(4, selectedModuleId);
            cmd.setString(1, txtNom.getText());
            cmd.setInt(2, getProfessorIdByName(cmbProfessor.getSelectedItem().toString()));
            cmd.setInt(3, getClassByName(cmbClasse.getSelectedItem().toString()));

            cmd.executeUpdate();

            JOptionPane.showMessageDialog(null, "La modification a été enregistrée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

            displayModules();

            clearTextFields();
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à modifier.", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la modification : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_btnModifierActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        this.dispose();
        connexion loginFrame = new connexion();
        loginFrame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void label2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label2MouseClicked
        // TODO add your handling code here:
        Gestion_Modules rsp = new Gestion_Modules(idresp);
        rsp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_label2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        respo rsp = new respo(idresp);
        rsp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        Gest_class cl = new Gest_class(idresp);
        cl.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        Gest_etud et = new Gest_etud(idresp);
        et.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        Gestion_Modules gm = new Gestion_Modules(idresp);
        gm.setVisible(true);
    }//GEN-LAST:event_jLabel12MouseClicked

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
            java.util.logging.Logger.getLogger(Gestion_Modules.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestion_Modules.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestion_Modules.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestion_Modules.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestion_Modules(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableModules;
    private javax.swing.JButton btnAjouter;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnModifier;
    private javax.swing.JComboBox<String> cmbClasse;
    private javax.swing.JComboBox<String> cmbProfessor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label2;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
