/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebCamDetector;

import javax.swing.JFrame;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**@author tkach*/
public class MainFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private Detector processor;
    private CameraPanel cameraPanel;
    
    public MainFrame(){
        super("Face Detection");
		
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        processor = new Detector();
        cameraPanel = new CameraPanel();
        setContentPane(cameraPanel);
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setVisible(true);	
    }
	
    public void displayScreen(){
        Mat webcamImage = new Mat();
        VideoCapture videoCapture = new VideoCapture(0);
		
        if (videoCapture.isOpened()) {
            while (true) {
                videoCapture.read(webcamImage);
				
                if (!webcamImage.empty()) {
                    setSize(webcamImage.width() + 50, webcamImage.height() + 70);
                    webcamImage = processor.detect(webcamImage);
                    cameraPanel.convertMatToImage(webcamImage); 											
                    cameraPanel.repaint();
                } else {
                    System.out.println("Problem");
                    break;
                }
            }
        }
    }    
    
}
