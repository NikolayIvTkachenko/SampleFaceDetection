/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facedetection;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/**
 *
 * @author tkach
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private ImagePanel imagePanel;
    private JFileChooser fileChooser;
    private FaceDetection faceDetection;
    private File file;
	
    public MainFrame(){
        super(Constants.APPLICATION_NAME);
        
        setJMenuBar(createMenuBar());
	
        this.imagePanel=new ImagePanel();
        this.fileChooser = new JFileChooser();
        this.faceDetection = new FaceDetection();
		
        add(imagePanel,BorderLayout.CENTER);
		
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(this);	
    }
    
    public JMenuBar createMenuBar(){	
        JMenuBar menuBar = new JMenuBar();
		
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load image");
        JMenuItem detectMenuItem = new JMenuItem("Detect faces");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(loadMenuItem);
        fileMenu.add(detectMenuItem);
        fileMenu.add(exitMenuItem);
		
        loadMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    MainFrame.this.file = fileChooser.getSelectedFile();
                    MainFrame.this.imagePanel.loadImage(MainFrame.this.file);
                }		
            }
        });
		
        detectMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.faceDetection.detectFaces(MainFrame.this.file, MainFrame.this.imagePanel);	
            }
        });
		
        JMenu aboutMenu = new JMenu("About");
        JMenu helpMenu = new JMenu("Help");
		
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        menuBar.add(helpMenu);
		
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this, Constants.EXIT_WARNING,"Warning",JOptionPane.YES_NO_OPTION);
                if( action == JOptionPane.OK_OPTION ){
                    System.gc();
                    System.exit(0);
                }
            }
        });	
        return menuBar;
    }
}
