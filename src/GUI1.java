import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GUI1 extends JFrame {
    
    
    public static Dimension frameDimension = new Dimension(1250, 700);
    public static JPanel chartDataPanel = new JPanel();
    public static JPanel chartCorrelationPanel = new JPanel();
    public static JPanel chartTestPanel = new JPanel();
   
    public GUI1() {

        initUI();
        addComponents();
    }

    private void initUI() {

        setTitle("GUI");
        setSize(frameDimension);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void addComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setSize(getMaximumSize());
        
        JTabbedPane tabbedPanel = new JTabbedPane();
        
        
        JPanel configurationPanel = new JPanel();
        configurationPanel.setLayout(new FlowLayout() {
            public Dimension preferredLayoutSize(Container target) {
                Dimension sd = super.preferredLayoutSize(target);
                sd.height = (int) (frameDimension.getHeight()/3 * 1);
                return sd;
            }
        });
        configurationPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        configurationPanel.setSize(this.getSize().width/3, this.getSize().height);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout(){
            public Dimension preferredLayoutSize(Container target) {
                Dimension sd = super.preferredLayoutSize(target);

                sd.width = (int) (frameDimension.getWidth()/3 * 2);

                return sd;
            }
        });
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        resultPanel.setSize(this.getSize().width, this.getSize().height /3);
       
        
        JPanel settingsPanel = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
    	settingsPanel.setLayout(gridbag);
    	settingsPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
    	
    	JLabel kLabel = new JLabel("K:");
    	final JSpinner kSpinner = new JSpinner(new SpinnerNumberModel());
    	JSlider kSlider = new JSlider(JSlider.HORIZONTAL, 0, 30, 15);
    	kSlider.setMajorTickSpacing(10);
    	kSlider.setMinorTickSpacing(1);
    	kSlider.setPaintLabels(true);
    	kSlider.setPaintLabels(true);
    	Font font = new Font("Serif", Font.ITALIC, 10);
    	kSlider.setFont(font);
    	kSlider.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				JSlider s = (JSlider) arg0.getSource();
				kSpinner.setValue(s.getValue());
				
			}
		});
    	
    	JLabel differenceLabel = new JLabel("difference:");
    	final JSpinner differenceSpinner = new JSpinner(new SpinnerNumberModel());
    
       	JSlider differenceSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 5);
    	differenceSlider.setMajorTickSpacing(10);
    	differenceSlider.setMinorTickSpacing(1);
    	differenceSlider.setPaintLabels(true);
    	differenceSlider.setPaintLabels(true);
    	differenceSlider.setFont(font);
    	differenceSlider.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				JSlider s = (JSlider) arg0.getSource();
				differenceSpinner.setValue(s.getValue());
				
			}
		});
    	
    	JLabel startPointLabel = new JLabel("startPoint:");
    	final JSpinner startPointSpinner = new JSpinner(new SpinnerNumberModel());
    	startPointSpinner.setPreferredSize(new Dimension(10, 10));

    	JLabel pixelLabel = new JLabel("Pixel:");
    	final JSpinner pixelSpinner = new JSpinner();
    	pixelSpinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				Main.pixel = (Integer) pixelSpinner.getValue();
				
			}
		});
    	
    	JRadioButton vector = new JRadioButton("Vector");
    	vector.setMnemonic(KeyEvent.VK_B);
    	vector.setActionCommand("Vector");
    	vector.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				pixelSpinner.setEnabled(false);
				if(e.getStateChange()==1){
					Main.isVector = true;
				}

				
			}
		});

    	vector.setSelected(true);

        JRadioButton pixel = new JRadioButton("Pixel");
        pixel.setMnemonic(KeyEvent.VK_C);
        pixel.setActionCommand("Pixel");
		pixel.addItemListener(new ItemListener() {
					
					public void itemStateChanged(ItemEvent e) {
						pixelSpinner.setEnabled(true);
						if(e.getStateChange()==1){
							Main.isVector = false;
						}
		
						
					}
				});

        ButtonGroup group = new ButtonGroup();
        group.add(vector);
        group.add(pixel);

    	Insets insets = new Insets(0, 0, 0, 0);

    	double x = 0.7;
    	gridbag.setConstraints(kLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(differenceLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(startPointLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(pixelLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(vector, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	
    	gridbag.setConstraints(kSpinner, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(differenceSpinner, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(startPointSpinner, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(pixelSpinner, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(pixel, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	kLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
    	differenceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
    	startPointLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
    	
    	JButton calculateCorrelationButton = new JButton("Calculate");
    	calculateCorrelationButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartCorrelationPanel.removeAll();
				chartDataPanel.removeAll();
				chartTestPanel.removeAll();
				if(Main.isVector){
					Main.calculateForVector();
				}
				else{
					Main.calculateForPixel();
				}			
			}
		});
    	
    
    	kSpinner.addChangeListener(new ChangeListener() {      
    		  public void stateChanged(ChangeEvent e) {
    		    System.out.println("k spinner");
    		    Main.k = (Integer) kSpinner.getValue();
    		    
    		  }
    		});
    	
    	differenceSpinner.addChangeListener(new ChangeListener() {      
  		  public void stateChanged(ChangeEvent e) {
  		    System.out.println("difference spinner");
  		    Main.difference = (Integer) differenceSpinner.getValue();
  		  }
  		});
    	
    	startPointSpinner.addChangeListener(new ChangeListener() {      
  		  public void stateChanged(ChangeEvent e) {
  		    System.out.println("start point spinner");
  		    Main.startPoint = (Integer) startPointSpinner.getValue();
  		  }
  		});
    	
    	settingsPanel.add(kLabel);
    	settingsPanel.add(kSpinner);
    	settingsPanel.add(differenceLabel);
    	settingsPanel.add(differenceSpinner);
    	settingsPanel.add(startPointLabel);
    	settingsPanel.add(startPointSpinner);
    	settingsPanel.add(pixelLabel);
    	settingsPanel.add(pixelSpinner);
    	settingsPanel.add(vector);
    	settingsPanel.add(pixel);
    	
    	    	
    	
    	JButton filesButton = new JButton("Wybierz plik");
    	//filesButton.
    	JPanel filesPanel = new JPanel();
    	filesPanel.setLayout(new GridLayout(2,2));
    	filesPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	JPanel dataPanel = new JPanel();
    	dataPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	JPanel timePanel = new JPanel();
    	timePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
    	configurationPanel.setLayout(new BoxLayout(configurationPanel, BoxLayout.Y_AXIS));
    	configurationPanel.add(Box.createVerticalGlue());
        configurationPanel.add(settingsPanel);
        configurationPanel.add(calculateCorrelationButton);
     
        resultPanel.add(tabbedPanel);
        tabbedPanel.add(chartDataPanel);
        tabbedPanel.add(chartCorrelationPanel);
        tabbedPanel.add(chartTestPanel);
   
        mainPanel.add(configurationPanel, BorderLayout.LINE_START);
        mainPanel.add(resultPanel, BorderLayout.CENTER);
        
        this.add(mainPanel);
    }
    
    public void fileChooser(){
    	 JFrame.setDefaultLookAndFeelDecorated(true);
    	    JDialog.setDefaultLookAndFeelDecorated(true);
    	    JFrame frame = new JFrame("JComboBox Test");
    	    frame.setLayout(new FlowLayout());
    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    JButton button = new JButton("Select File");
    	    button.addActionListener(new ActionListener() {
    	      public void actionPerformed(ActionEvent ae) {
    	        JFileChooser fileChooser = new JFileChooser();
    	        int returnValue = fileChooser.showOpenDialog(null);
    	        if (returnValue == JFileChooser.APPROVE_OPTION) {
    	          File selectedFile = fileChooser.getSelectedFile();
    	          System.out.println(selectedFile.getName());
    	        }
    	      }
    	    });
    	    frame.add(button);
    	    frame.pack();
    	    frame.setVisible(true);
    }
}
