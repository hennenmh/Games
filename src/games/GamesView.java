/*
 * GamesView.java
 */

package games;

import business.*;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class GamesView extends FrameView {
    Game g;
    public GamesView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = GamesApp.getApplication().getMainFrame();
            aboutBox = new GamesAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        GamesApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jtabGames = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jbtnTTT1 = new javax.swing.JButton();
        jbtnTTT2 = new javax.swing.JButton();
        jbtnTTT3 = new javax.swing.JButton();
        jbtnTTT4 = new javax.swing.JButton();
        jbtnTTT5 = new javax.swing.JButton();
        jbtnTTT6 = new javax.swing.JButton();
        jbtnTTT7 = new javax.swing.JButton();
        jbtnTTT8 = new javax.swing.JButton();
        jbtnTTT9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtxtGuess = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtGuessHistory = new javax.swing.JTextArea();
        jbtnSubmit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtxtGameName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtxtMoveResult = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtInstructions = new javax.swing.JTextArea();
        jbtnClear = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jmnuGuessing = new javax.swing.JMenu();
        jmnuHiLo = new javax.swing.JMenuItem();
        jmnuHotCold = new javax.swing.JMenuItem();
        jmnuTTT = new javax.swing.JMenu();
        jmnuTTTNew = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        jtabGames.setEnabled(false);
        jtabGames.setName("jtabGames"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(games.GamesApp.class).getContext().getResourceMap(GamesView.class);
        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jbtnTTT1.setFont(resourceMap.getFont("jbtnTTT1.font")); // NOI18N
        jbtnTTT1.setName("jbtnTTT1"); // NOI18N
        jbtnTTT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTTT1ActionPerformed(evt);
            }
        });

        jbtnTTT2.setFont(resourceMap.getFont("jbtnTTT1.font")); // NOI18N
        jbtnTTT2.setAlignmentY(0.0F);
        jbtnTTT2.setBorderPainted(false);
        jbtnTTT2.setMargin(new java.awt.Insets(0, 14, 0, 14));
        jbtnTTT2.setName("jbtnTTT2"); // NOI18N
        jbtnTTT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTTT2ActionPerformed(evt);
            }
        });

        jbtnTTT3.setFont(resourceMap.getFont("jbtnTTT1.font")); // NOI18N
        jbtnTTT3.setAlignmentY(0.0F);
        jbtnTTT3.setBorderPainted(false);
        jbtnTTT3.setMargin(new java.awt.Insets(0, 14, 0, 14));
        jbtnTTT3.setName("jbtnTTT3"); // NOI18N
        jbtnTTT3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTTT3ActionPerformed(evt);
            }
        });

        jbtnTTT4.setFont(resourceMap.getFont("jbtnTTT1.font")); // NOI18N
        jbtnTTT4.setName("jbtnTTT4"); // NOI18N
        jbtnTTT4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTTT4ActionPerformed(evt);
            }
        });

        jbtnTTT5.setFont(resourceMap.getFont("jbtnTTT1.font")); // NOI18N
        jbtnTTT5.setAlignmentY(0.0F);
        jbtnTTT5.setBorderPainted(false);
        jbtnTTT5.setMargin(new java.awt.Insets(0, 14, 0, 14));
        jbtnTTT5.setName("jbtnTTT5"); // NOI18N
        jbtnTTT5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTTT5ActionPerformed(evt);
            }
        });

        jbtnTTT6.setFont(resourceMap.getFont("jbtnTTT1.font")); // NOI18N
        jbtnTTT6.setAlignmentY(0.0F);
        jbtnTTT6.setBorderPainted(false);
        jbtnTTT6.setMargin(new java.awt.Insets(0, 14, 0, 14));
        jbtnTTT6.setName("jbtnTTT6"); // NOI18N
        jbtnTTT6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTTT6ActionPerformed(evt);
            }
        });

        jbtnTTT7.setFont(resourceMap.getFont("jbtnTTT1.font")); // NOI18N
        jbtnTTT7.setName("jbtnTTT7"); // NOI18N
        jbtnTTT7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTTT7ActionPerformed(evt);
            }
        });

        jbtnTTT8.setFont(resourceMap.getFont("jbtnTTT1.font")); // NOI18N
        jbtnTTT8.setAlignmentY(0.0F);
        jbtnTTT8.setBorderPainted(false);
        jbtnTTT8.setMargin(new java.awt.Insets(0, 14, 0, 14));
        jbtnTTT8.setName("jbtnTTT8"); // NOI18N
        jbtnTTT8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTTT8ActionPerformed(evt);
            }
        });

        jbtnTTT9.setFont(resourceMap.getFont("jbtnTTT1.font")); // NOI18N
        jbtnTTT9.setAlignmentY(0.0F);
        jbtnTTT9.setBorderPainted(false);
        jbtnTTT9.setMargin(new java.awt.Insets(0, 14, 0, 14));
        jbtnTTT9.setName("jbtnTTT9"); // NOI18N
        jbtnTTT9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTTT9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jbtnTTT1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnTTT2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnTTT3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jbtnTTT4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnTTT5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnTTT6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jbtnTTT7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnTTT8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnTTT9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnTTT3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnTTT2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnTTT1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnTTT6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnTTT5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnTTT4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnTTT9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnTTT8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnTTT7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jtabGames.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jtxtGuess.setFont(resourceMap.getFont("jtxtGuess.font")); // NOI18N
        jtxtGuess.setName("jtxtGuess"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jtxtGuessHistory.setEditable(false);
        jtxtGuessHistory.setColumns(20);
        jtxtGuessHistory.setLineWrap(true);
        jtxtGuessHistory.setRows(5);
        jtxtGuessHistory.setWrapStyleWord(true);
        jtxtGuessHistory.setName("jtxtGuessHistory"); // NOI18N
        jScrollPane1.setViewportView(jtxtGuessHistory);

        jbtnSubmit.setText(resourceMap.getString("jbtnSubmit.text")); // NOI18N
        jbtnSubmit.setEnabled(false);
        jbtnSubmit.setName("jbtnSubmit"); // NOI18N
        jbtnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtGuess, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnSubmit)
                .addGap(63, 63, 63))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtGuess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jbtnSubmit))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtabGames.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jtxtGameName.setFont(resourceMap.getFont("jtxtGameName.font")); // NOI18N
        jtxtGameName.setEnabled(false);
        jtxtGameName.setName("jtxtGameName"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jtxtMoveResult.setFont(resourceMap.getFont("jtxtMoveResult.font")); // NOI18N
        jtxtMoveResult.setEnabled(false);
        jtxtMoveResult.setName("jtxtMoveResult"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jtxtInstructions.setEditable(false);
        jtxtInstructions.setColumns(20);
        jtxtInstructions.setLineWrap(true);
        jtxtInstructions.setRows(5);
        jtxtInstructions.setWrapStyleWord(true);
        jtxtInstructions.setName("jtxtInstructions"); // NOI18N
        jScrollPane2.setViewportView(jtxtInstructions);

        jbtnClear.setText(resourceMap.getString("jbtnClear.text")); // NOI18N
        jbtnClear.setName("jbtnClear"); // NOI18N
        jbtnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtabGames, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtxtGameName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)))
                        .addGap(24, 24, 24)
                        .addComponent(jbtnClear)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtMoveResult, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtGameName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jbtnClear))
                .addGap(18, 18, 18)
                .addComponent(jtabGames, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtMoveResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jmnuGuessing.setText(resourceMap.getString("jmnuGuessing.text")); // NOI18N
        jmnuGuessing.setName("jmnuGuessing"); // NOI18N

        jmnuHiLo.setText(resourceMap.getString("jmnuHiLo.text")); // NOI18N
        jmnuHiLo.setName("jmnuHiLo"); // NOI18N
        jmnuHiLo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnuHiLoActionPerformed(evt);
            }
        });
        jmnuGuessing.add(jmnuHiLo);

        jmnuHotCold.setText(resourceMap.getString("jmnuHotCold.text")); // NOI18N
        jmnuHotCold.setName("jmnuHotCold"); // NOI18N
        jmnuHotCold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnuHotColdActionPerformed(evt);
            }
        });
        jmnuGuessing.add(jmnuHotCold);

        fileMenu.add(jmnuGuessing);

        jmnuTTT.setText(resourceMap.getString("jmnuTTT.text")); // NOI18N
        jmnuTTT.setName("jmnuTTT"); // NOI18N

        jmnuTTTNew.setText(resourceMap.getString("jmnuTTTNew.text")); // NOI18N
        jmnuTTTNew.setName("jmnuTTTNew"); // NOI18N
        jmnuTTTNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnuTTTNewActionPerformed(evt);
            }
        });
        jmnuTTT.add(jmnuTTTNew);

        fileMenu.add(jmnuTTT);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(games.GamesApp.class).getContext().getActionMap(GamesView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 425, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnTTT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTTT1ActionPerformed
        square(jbtnTTT1, 1);
        jbtnTTT1.setEnabled(false);
    }//GEN-LAST:event_jbtnTTT1ActionPerformed

    private void jbtnTTT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTTT2ActionPerformed
        square(jbtnTTT2, 2);
        jbtnTTT2.setEnabled(false);
    }//GEN-LAST:event_jbtnTTT2ActionPerformed

    private void jbtnTTT3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTTT3ActionPerformed
        square(jbtnTTT3, 3);
        jbtnTTT3.setEnabled(false);
    }//GEN-LAST:event_jbtnTTT3ActionPerformed

    private void jbtnTTT4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTTT4ActionPerformed
        square(jbtnTTT4, 4);
        jbtnTTT4.setEnabled(false);
    }//GEN-LAST:event_jbtnTTT4ActionPerformed

    private void jbtnTTT5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTTT5ActionPerformed
        square(jbtnTTT5, 5);
        jbtnTTT5.setEnabled(false);
    }//GEN-LAST:event_jbtnTTT5ActionPerformed

    private void jbtnTTT6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTTT6ActionPerformed
       square(jbtnTTT6, 6);
       jbtnTTT6.setEnabled(false);
    }//GEN-LAST:event_jbtnTTT6ActionPerformed

    private void jbtnTTT7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTTT7ActionPerformed
       square(jbtnTTT7, 7);
       jbtnTTT7.setEnabled(false);
    }//GEN-LAST:event_jbtnTTT7ActionPerformed

    private void jbtnTTT8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTTT8ActionPerformed
       square(jbtnTTT8, 8);
       jbtnTTT8.setEnabled(false);
    }//GEN-LAST:event_jbtnTTT8ActionPerformed

    private void jbtnTTT9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTTT9ActionPerformed
        square(jbtnTTT9, 9);
        jbtnTTT9.setEnabled(false);
    }//GEN-LAST:event_jbtnTTT9ActionPerformed

    private void jbtnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSubmitActionPerformed
        g.setMove(jtxtGuess.getText());
        if (!g.getErrorMsg().isEmpty()) {
            jtxtGuessHistory.append(g.getErrorMsg());
        } else {
            jtxtGuessHistory.append(g.getMoveResults());
        }
        jtxtGuess.setText("");
        jtxtGuess.requestFocusInWindow();
        if (g.isGameOver()) {
            jtxtGuess.setEnabled(false);
            jtxtGuessHistory.setEnabled(false);
            jbtnSubmit.setEnabled(false);
        }
    }//GEN-LAST:event_jbtnSubmitActionPerformed

    private void jmnuHiLoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnuHiLoActionPerformed
        g = new GuessHiLo();
        setup();
    }//GEN-LAST:event_jmnuHiLoActionPerformed

    private void jmnuHotColdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnuHotColdActionPerformed
        g = new GuessHotCold();
        setup();
    }//GEN-LAST:event_jmnuHotColdActionPerformed

    private void jmnuTTTNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnuTTTNewActionPerformed
        g = new TicTacToe();
        setup();
    }//GEN-LAST:event_jmnuTTTNewActionPerformed

    private void jbtnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClearActionPerformed
        jtxtGameName.setText("");
        jtxtInstructions.setText("");
        jtxtGuessHistory.setText("");
        jbtnSubmit.setEnabled(false);
        jtabGames.setEnabled(false);
        jtabGames.setTitleAt(1, "Guessing");
        jtxtMoveResult.setText("");
        clearTTT();
    }//GEN-LAST:event_jbtnClearActionPerformed
private void square(javax.swing.JButton b, int m) {
    //This is a method to put the X and O values on the selected squares;
    //the finished version will need to communicate with the class involved
    //in playing the game.
    
    //sample: alternating X and O
//    if (b.getText().equalsIgnoreCase("O")) {
//        b.setText("X");
//    } else {
//        b.setText("O");
//    }  
    
    //Trying to get the buttons to not overwrite themselves if clicked 
    //more than once.
//    TicTacToe ttt = (TicTacToe) g;
//    if (!ttt.getPlayer().equalsIgnoreCase(g.getMoveResults())) {
//        statusMessageLabel.setText("Choose another square.");
//        } else {
        if (!g.isGameOver()) {
            g.setMove(String.valueOf(m));
            jtxtMoveResult.setText(g.getEndMsg());
            
            if (g.getErrorMsg().isEmpty()) {

                
                    b.setText(g.getMoveResults());
                
            } else {
                statusMessageLabel.setText(g.getErrorMsg());
            }
        }
    //}
}

private void setup() {
    jtxtGameName.setText(g.getGameName());
    jtxtInstructions.setText(g.getInstructions());
    if (g instanceof GuessingGame) {
        jtabGames.setEnabled(true);
        jtabGames.setEnabledAt(1, true);
        jtabGames.setEnabledAt(0, false);
        jtabGames.setSelectedIndex(1);
        jtabGames.setTitleAt(1, g.getGameName());
        jtxtGuess.setEnabled(true);
        jbtnSubmit.setEnabled(true);
        jtxtGuessHistory.setEnabled(true);
        jtxtGuessHistory.setText("");
        jtxtGuessHistory.append("I'm thinking of a number between 1 and 100.\n");
    }
    else if (g instanceof TicTacToe) {
        jtabGames.setEnabled(true);
        jtabGames.setEnabledAt(0, true);
        jtabGames.setEnabledAt(1, false);
        jtabGames.setSelectedIndex(0);
        jtxtMoveResult.setText("");
        clearTTT();
        jtxtMoveResult.setText(g.getEndMsg());
        
    }
}

private void clearTTT() {
    jbtnTTT1.setText("");
    jbtnTTT2.setText("");
    jbtnTTT3.setText("");
    jbtnTTT4.setText("");
    jbtnTTT5.setText("");
    jbtnTTT6.setText("");
    jbtnTTT7.setText("");
    jbtnTTT8.setText("");
    jbtnTTT9.setText("");
    
    jbtnTTT1.setEnabled(true);
    jbtnTTT2.setEnabled(true);
    jbtnTTT3.setEnabled(true);
    jbtnTTT4.setEnabled(true);
    jbtnTTT5.setEnabled(true);
    jbtnTTT6.setEnabled(true);
    jbtnTTT7.setEnabled(true);
    jbtnTTT8.setEnabled(true);
    jbtnTTT9.setEnabled(true);
    g = new TicTacToe();
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnSubmit;
    private javax.swing.JButton jbtnTTT1;
    private javax.swing.JButton jbtnTTT2;
    private javax.swing.JButton jbtnTTT3;
    private javax.swing.JButton jbtnTTT4;
    private javax.swing.JButton jbtnTTT5;
    private javax.swing.JButton jbtnTTT6;
    private javax.swing.JButton jbtnTTT7;
    private javax.swing.JButton jbtnTTT8;
    private javax.swing.JButton jbtnTTT9;
    private javax.swing.JMenu jmnuGuessing;
    private javax.swing.JMenuItem jmnuHiLo;
    private javax.swing.JMenuItem jmnuHotCold;
    private javax.swing.JMenu jmnuTTT;
    private javax.swing.JMenuItem jmnuTTTNew;
    private javax.swing.JTabbedPane jtabGames;
    private javax.swing.JTextField jtxtGameName;
    private javax.swing.JTextField jtxtGuess;
    private javax.swing.JTextArea jtxtGuessHistory;
    private javax.swing.JTextArea jtxtInstructions;
    private javax.swing.JTextField jtxtMoveResult;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}