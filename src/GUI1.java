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
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GUI1 extends JFrame {
    
    
    public static Dimension frameDimension = new Dimension(1250, 700);
    public static JPanel chartDataPanel = new JPanel();
    public static JPanel chartCorrelationPanel = new JPanel();
    public static JPanel chartTestPanel = new JPanel();
    public static JPanel chartCorrelationInTimePanel = new JPanel();
    public static JPanel chartStatisticPanel = new JPanel();
    public static JPanel chartAllPanel = new JPanel();
    public static JPanel pixelPanel = new JPanel();
    public static JPanel squarePanel = new JPanel();
    public static JCheckBox avg = new JCheckBox("œrednia");
    public static JCheckBox median = new JCheckBox("mediana");
    public static  JCheckBox std = new JCheckBox("ochylenie standardowe");
    public static JCheckBox variance = new JCheckBox("wariancja");
    public static JCheckBox autocorrelationButton = new JCheckBox("autokorelacja");
    public static JCheckBox measurement = new JCheckBox("przebieg");
   
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
                sd.height = (int) (frameDimension.getHeight()/5 * 2);
                return sd;
            }
        });

        configurationPanel.setSize(this.getSize().width/5*2, this.getSize().height);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout(){
            public Dimension preferredLayoutSize(Container target) {
                Dimension sd = super.preferredLayoutSize(target);

                sd.width = (int) (frameDimension.getWidth()/5 * 3);

                return sd;
            }
        });

        resultPanel.setSize(this.getSize().width/5*3, this.getSize().height);
       
        
        JPanel settingsPanel = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
    	settingsPanel.setLayout(gridbag);
    	settingsPanel.setBorder(BorderFactory.createTitledBorder("Parametry"));
    	
        JPanel calculateTypePanel = new JPanel();
//        calculateTypePanel.setLayout(new BoxLayout(calculateTypePanel, BoxLayout.Y_AXIS));
        GridBagLayout gridbagCalculateType = new GridBagLayout();
        calculateTypePanel.setLayout(gridbagCalculateType);
        calculateTypePanel.setBorder(BorderFactory.createTitledBorder("Typ przetwarzania"));
        
        JPanel operationTypePanel = new JPanel();
//      calculateTypePanel.setLayout(new BoxLayout(calculateTypePanel, BoxLayout.Y_AXIS));
      GridBagLayout operationType = new GridBagLayout();
      operationTypePanel.setLayout(gridbagCalculateType);
      operationTypePanel.setBorder(BorderFactory.createTitledBorder("Operacja"));
        
        JPanel inputFilePanel = new JPanel();
        GridBagLayout inputFileGridbag = new GridBagLayout();
        inputFilePanel.setLayout(inputFileGridbag);
        inputFilePanel.setBorder(BorderFactory.createTitledBorder("Plik wejœciowy"));
        
        

    	
    	JLabel kLabel = new JLabel("d³ugoœæ okna czasowego (n):");
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
    	
    	JLabel differenceLabel = new JLabel("d³ugoœæ przesuniêcia (k):");
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
    	
    	JLabel startPointLabel = new JLabel("czas pocz¹tkowy (t):");
    	final JSpinner startPointSpinner = new JSpinner(new SpinnerNumberModel());
    	startPointSpinner.setPreferredSize(new Dimension(10, 10));

    	JLabel pixelLabel = new JLabel("Numer piksela:");
    	final JSpinner pixelSpinner = new JSpinner();
    	pixelSpinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				Main.pixel = (Integer) pixelSpinner.getValue();
				
			}
		});
    	
    	
    	JLabel timeForPixelLabel = new JLabel("Numer ramki dla mapy pixeli:");
    	final JSpinner timeForPixelSpinner = new JSpinner();
    	timeForPixelSpinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				Main.timeForPixelMap = (Integer) timeForPixelSpinner.getValue();
				
			}
		});
    	
    	JButton createPixelMapButton = new JButton("Piksele");
    	createPixelMapButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {
				Main.createPixelMap();
			}
		});
    	
    	
    	JLabel testLabel = new JLabel("Przesuniêcie do testów:");
    	final JSpinner testSpinner = new JSpinner();
    	testSpinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				Main.przesuniecie_do_testow = (Integer) testSpinner.getValue();
				
			}
		});
    	
    	JLabel kStartLabel = new JLabel("przesuniêcie pocz¹tkowe (k startowe):");
    	final JSpinner kStartSpinner = new JSpinner(new SpinnerNumberModel());
    	JSlider kStartSlider = new JSlider(JSlider.HORIZONTAL, 0, 30, 15);
    	kStartSlider.setMajorTickSpacing(10);
    	kStartSlider.setMinorTickSpacing(1);
    	kStartSlider.setPaintLabels(true);
    	kStartSlider.setPaintLabels(true);
    	kSlider.setFont(font);
    	kSlider.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				JSlider s = (JSlider) arg0.getSource();
				kSpinner.setValue(s.getValue());
				
			}
		});
    	
    	JRadioButton vector = new JRadioButton("Vektor");
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

        JRadioButton pixel = new JRadioButton("Piksel");
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
		JRadioButton autocorrelation = new JRadioButton("Autokorelacja");
    	autocorrelation.setMnemonic(KeyEvent.VK_A);
    	autocorrelation.setActionCommand("Autocorrelation");
    	autocorrelation.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
//					Main.isVector = false;
					Main.isAutocorrelation = true;
				}				
			}
		});
    	
    	JRadioButton speed = new JRadioButton("Prêdkoœæ");
    	speed.setMnemonic(KeyEvent.VK_A);
    	speed.setActionCommand("Prêdkoœæ");
    	speed.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
//					Main.isVector = false;
					Main.isAutocorrelation = false;
				}				
			}
		});


    	
        ButtonGroup groupType = new ButtonGroup();
        groupType.add(vector);
        groupType.add(pixel);
        ButtonGroup groupOperationType = new ButtonGroup();
        groupOperationType.add(autocorrelation);
        groupOperationType.add(speed);
        
    	JRadioButton normalFile = new JRadioButton("normal");
    	normalFile.setMnemonic(KeyEvent.KEY_FIRST);
    	normalFile.setActionCommand("fileType");
    	normalFile.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				Main.isManchester = false;
				Main.isGenerate = false;

				
			}
		});
    	
    	
    	JRadioButton manchesterFile = new JRadioButton("Manchester");
    	manchesterFile.setMnemonic(KeyEvent.KEY_LAST);
    	manchesterFile.setActionCommand("fileType");
    	manchesterFile.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				Main.isManchester = true;

				
			}
		});
    	manchesterFile.setSelected(true);
    	
    	JRadioButton generateFile = new JRadioButton("generate");
    	generateFile.setMnemonic(KeyEvent.KEY_FIRST);
    	generateFile.setActionCommand("fileType");
    	generateFile.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				Main.isManchester = false;
				Main.isGenerate = true;

				
			}
		});
    	
    	
    	ButtonGroup fileTypeGroup = new ButtonGroup();
        fileTypeGroup.add(normalFile);
        fileTypeGroup.add(manchesterFile);
        fileTypeGroup.add(generateFile);
        
    	JButton calculateCorrelationButton = new JButton("Calculate");
    	calculateCorrelationButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
//				System.out.println("click click");
				Main.dlugosc_przesuniecia_k = (Integer) differenceSpinner.getValue();
				Main.dlugosc_okna_czasowego_n = (Integer) kSpinner.getValue();
				Main.czas_poczatkowy_t = (Integer) startPointSpinner.getValue();
				chartCorrelationPanel.removeAll();
				chartDataPanel.removeAll();
				chartTestPanel.removeAll();
				chartCorrelationInTimePanel.removeAll();
				chartStatisticPanel.removeAll();
				chartAllPanel.removeAll();
				pixelPanel.removeAll();
				if(Main.isVector && !Main.isAutocorrelation){
					System.out.println("Vector + predkosc");
					Main.calculateForVector();
				}
				else if(Main.isVector && Main.isAutocorrelation){
					System.out.println("vektor + autokorelacja");
					Main.calculateForAutocorrelation();
//					System.out.println("dla autokorelacji");
				}
				else if(!Main.isVector && !Main.isAutocorrelation){
					System.out.println("pixel i predkosc");
					Main.calculateForPixel();
				}			
				else{
					Main.calculateAutocorrelationForPixel();
				}
			}
		});
    	
    	JButton testButton = new JButton("Test");
    	testButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {
				chartCorrelationPanel.removeAll();
				Main.test();
			}
		});
    	

    	Insets insets = new Insets(5, 5, 5, 5);
    	testSpinner.setValue(3000);
    	

    	gridbag.setConstraints(kLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(kStartLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(differenceLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(startPointLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(pixelLabel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(calculateCorrelationButton, new GridBagConstraints(0, 5, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(timeForPixelLabel, new GridBagConstraints(0, 6, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(createPixelMapButton, new GridBagConstraints(0, 7, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(testLabel, new GridBagConstraints(0, 8, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(testButton, new GridBagConstraints(0, 9, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	gridbag.setConstraints(kSpinner, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(kStartSpinner, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(differenceSpinner, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(startPointSpinner, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(pixelSpinner, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(timeForPixelSpinner, new GridBagConstraints(1, 6, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(testSpinner, new GridBagConstraints(1, 8, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	
    	
    	kLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
    	differenceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
    	startPointLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
    	

    	
    
    	kSpinner.addChangeListener(new ChangeListener() {      
    		  public void stateChanged(ChangeEvent e) {
//    		    System.out.println("k spinner");
    		    Main.dlugosc_okna_czasowego_n = (Integer) kSpinner.getValue();
    		    
    		  }
    		});
    	
    	differenceSpinner.addChangeListener(new ChangeListener() {      
  		  public void stateChanged(ChangeEvent e) {
//  		    System.out.println("difference spinner");
  		    Main.dlugosc_przesuniecia_k = (Integer) differenceSpinner.getValue();
  		  }
  		});
    	
    	kStartSpinner.addChangeListener(new ChangeListener() {      
    		  public void stateChanged(ChangeEvent e) {
//    		    System.out.println("difference spinner");
    		    Main.k_poczatkowe = (Integer) kStartSpinner.getValue();
    		  }
    		});
    	
    	startPointSpinner.addChangeListener(new ChangeListener() {      
  		  public void stateChanged(ChangeEvent e) {
//  		    System.out.println("start point spinner");
  		    Main.czas_poczatkowy_t = (Integer) startPointSpinner.getValue();
  		  }
  		});
    	
    	JButton filesButton = new JButton("Wybierz plik");
    	filesButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {		
				JFileChooser fileChooser = new JFileChooser();
		    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		    	int result = fileChooser.showOpenDialog(GUI1.this);
		    	if (result == JFileChooser.APPROVE_OPTION) {
		    	    Main.file = fileChooser.getSelectedFile();
		    	}
			}
		});
    	
    	

        

    	
    	settingsPanel.add(kLabel);
    	settingsPanel.add(kSpinner);
    	settingsPanel.add(differenceLabel);
    	settingsPanel.add(kStartLabel);
    	settingsPanel.add(kStartSpinner);
    	settingsPanel.add(differenceSpinner);
    	settingsPanel.add(startPointLabel);
    	settingsPanel.add(startPointSpinner);
    	settingsPanel.add(pixelLabel);
    	settingsPanel.add(pixelSpinner);
    	settingsPanel.add(testLabel);
    	settingsPanel.add(testSpinner);
    	settingsPanel.add(timeForPixelLabel);
    	settingsPanel.add(timeForPixelSpinner);
    	settingsPanel.add(createPixelMapButton);
//    	settingsPanel.add(vector);
//    	settingsPanel.add(pixel);
//    	settingsPanel.add(autocorrelation);
    	settingsPanel.add(calculateCorrelationButton);
    	settingsPanel.add(testButton);
    	
    	calculateTypePanel.add(vector);
    	calculateTypePanel.add(pixel);
    	operationTypePanel.add(autocorrelation);
    	operationTypePanel.add(speed);
    	
    	inputFileGridbag.setConstraints(normalFile, new GridBagConstraints(0, 0, 1, 1, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(manchesterFile, new GridBagConstraints(1, 0, 1, 1, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(generateFile, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(filesButton, new GridBagConstraints(0, 1, 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	inputFilePanel.add(normalFile);
    	inputFilePanel.add(manchesterFile);
    	inputFilePanel.add(generateFile);
    	inputFilePanel.add(filesButton);
    	
    	
        
    	JPanel filesPanel = new JPanel();
    	filesPanel.setLayout(new GridLayout(2,2));
//    	filesPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	JPanel dataPanel = new JPanel();
//    	dataPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	JPanel timePanel = new JPanel();
//    	timePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
    	GridBagLayout configurationGridbag = new GridBagLayout();
    	configurationPanel.setLayout(configurationGridbag);
    	configurationGridbag.setConstraints(calculateTypePanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	configurationGridbag.setConstraints(operationTypePanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	configurationGridbag.setConstraints(inputFilePanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	configurationGridbag.setConstraints(settingsPanel, new GridBagConstraints(0, 3, 1, 1, 1, 3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
//    	configurationPanel.setLayout(new BoxLayout(configurationPanel, BoxLayout.Y_AXIS));
//    	configurationPanel.add(Box.createVerticalGlue());
//    	configurationPanel.add(vector);
//        configurationPanel.add(pixel);
//        configurationPanel.add(autocorrelation);
        configurationPanel.add(calculateTypePanel);
        configurationPanel.add(inputFilePanel);
        configurationPanel.add(settingsPanel);
        configurationPanel.add(operationTypePanel);
//        configurationPanel.add(calculateCorrelationButton);
//        configurationPanel.add(filesButton);
//        configurationPanel.add(testButton);

//        configurationPanel.add(normalFile);
//        configurationPanel.add(manchesterFile);
     
//        resultPanel.add(tabbedPanel);
//        resultPanel.setSize(new Dimension(1000,700));
        JPanel allPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(tabbedPanel, BorderLayout.CENTER);
        tabbedPanel.addTab("Przebieg", chartDataPanel);
        tabbedPanel.addTab("Korelacja", chartCorrelationPanel);
        tabbedPanel.addTab("Test", chartTestPanel);
        tabbedPanel.addTab("Autokorelacja", chartCorrelationInTimePanel);
        tabbedPanel.addTab("Dane statystyczne", chartStatisticPanel);
        tabbedPanel.addTab("Wszystko", allPanel);
        tabbedPanel.addTab("Pixel", pixelPanel);

        FlowLayout bl = new FlowLayout();
        pixelPanel.setLayout(bl);
        
//        squarePanel.setBackground(Color.RED);
//        squarePanel.setMaximumSize(new Dimension((int)pixelPanel.getMaximumSize().getHeight()/2, (int)pixelPanel.getMaximumSize().getHeight()/2));
//        squarePanel.setMinimumSize(new Dimension((int)pixelPanel.getMaximumSize().getHeight()/2, (int)pixelPanel.getMaximumSize().getHeight()/2));
//       squarePanel.setPreferredSize(new Dimension((int)pixelPanel.getMaximumSize().getHeight()/2, (int)pixelPanel.getMaximumSize().getHeight()/2));
        squarePanel.setMaximumSize(new Dimension(670,670));
        squarePanel.setMinimumSize(new Dimension(670,670));
        squarePanel.setPreferredSize(new Dimension(670,670));

        pixelPanel.add(squarePanel);

        
        
        
        allPanel.setLayout(new BorderLayout());
        JPanel chartButtonPanel = new JPanel();
        measurement.setSelected(true);
//      measurement.setBackground(Color.RED);
        measurement.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Main.generateAllChart();
				
			}
		});
        avg.setSelected(true);
//        avg.setBackground(Color.RED);
        avg.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Main.generateAllChart();
				
			}
		});
        median.setSelected(true);
//        median.setBackground(Color.BLUE);
        median.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Main.generateAllChart();
				
			}
		});
//        std.setBackground(Color.GREEN);
        std.setSelected(true);
        std.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Main.generateAllChart();
				
			}
		});
//        variance.setBackground(Color.YELLOW);
        variance.setSelected(true);
        variance.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Main.generateAllChart();
				
			}
		});
//        autocorrelationButton.setBackground(Color.MAGENTA);
        autocorrelationButton.setSelected(true);
        autocorrelationButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Main.generateAllChart();
				
			}
		});
        chartButtonPanel.add(avg);
        chartButtonPanel.add(median);
        chartButtonPanel.add(std);
        chartButtonPanel.add(variance);
        chartButtonPanel.add(autocorrelationButton);
        allPanel.add(chartButtonPanel, BorderLayout.PAGE_END);
        allPanel.add(chartAllPanel, BorderLayout.CENTER);
       
        mainPanel.add(configurationPanel, BorderLayout.LINE_START);
        mainPanel.add(resultPanel, BorderLayout.CENTER);
        
        this.add(mainPanel);
    }
    
    public void addElementsForAutocorelationForPixel(){
    	
    }
    public void fileChooser(){
    	 	JFrame.setDefaultLookAndFeelDecorated(true);
    	    JDialog.setDefaultLookAndFeelDecorated(true);
    	    
    	    JFrame frame = new JFrame("JComboBox Test");
//    	    frame.setSize(screenSize);
    	    
    	    frame.setLayout(new FlowLayout());
    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    	    JButton button = new JButton("Select File");
//    	    button.addActionListener(new ActionListener() {
//    	      public void actionPerformed(ActionEvent ae) {
//    	        JFileChooser fileChooser = new JFileChooser();
//    	        int returnValue = fileChooser.showOpenDialog(null);
//    	        if (returnValue == JFileChooser.APPROVE_OPTION) {
//    	          File selectedFile = fileChooser.getSelectedFile();
////    	          System.out.println(selectedFile.getName());
//    	        }
//    	      }
//    	    });
//    	    frame.add(button);
    	    frame.pack();
    	    frame.setVisible(true);
    }
}
