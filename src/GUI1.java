import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GUI1 extends JFrame {
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public static JPanel chartDataPanel = new JPanel();
    public static JPanel chartCorrelationPanel = new JPanel();
    public static JPanel chartTestPanel = new JPanel();
    public static JPanel chartCorrelationInTimePanel = new JPanel();
    public static JPanel chartStatisticPanel = new JPanel();
    public static JPanel chartAllPanel = new JPanel();
    public static JPanel allPanel = new JPanel();
    public static JPanel resultPanel;
    
    public static JPanel pixelPanel = new JPanel();
    public static JPanel squarePanel = new JPanel();
    
    public static JCheckBox avg = new JCheckBox("œrednia");
    public static JCheckBox median = new JCheckBox("mediana");
    public static JCheckBox std = new JCheckBox("ochylenie standardowe");
    public static JCheckBox variance = new JCheckBox("wariancja");
    public static JCheckBox autocorrelationButton = new JCheckBox("autokorelacja");
    public static JCheckBox measurement = new JCheckBox("przebieg");
    
    public static JLabel kLabel;
    public static JLabel kStartLabel;
    public static JLabel differenceLabel;
    public static JLabel startPointLabel;
    public static JLabel pixelLabel;
    public static JButton calculateCorrelationButton;
    public static JLabel timeForPixelLabel;
    public static JButton createPixelMapButton;
    public static JLabel testLabel;
    public static JButton testButton;
    public static JButton createPixelMapForAllTimeButton;
    public static JButton calculateAvgMeasurementForPixelButton;
	
    public static JSpinner kSpinner;
    public static JSpinner kStartSpinner;
    public static JSpinner differenceSpinner;
    public static JSpinner startPointSpinner;
    public static JSpinner pixelSpinner;
    public static JSpinner timeForPixelSpinner;
    public static JSpinner testSpinner;
    
    public static JTabbedPane tabbedPanel;
   
    public GUI1() {

        initUI();
        addComponents();
    }

    private void initUI() {

        setTitle("GUI");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void addComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setSize(getMaximumSize());
        
//      -----------------------------------------------------------------------------------
//        miejsce na wykresy
//        ---------------------------------------------------------------------------------
        tabbedPanel = new JTabbedPane();
        
//      -----------------------------------------------------------------------------------
//        panel konfiguracyjny (wszystkie przyciski)
//      -----------------------------------------------------------------------------------
                
        JPanel configurationPanel = new JPanel();
        
//    -----------------------------------------------------------------------------------
//      ca³a prawa czêœæ (tam gdzie s¹ zak³adki)
//    -----------------------------------------------------------------------------------

        resultPanel = new JPanel();
        
//    -----------------------------------------------------------------------------------
//      Panel na kontrolki do ustawiania parametrów
//    -----------------------------------------------------------------------------------
               
        JPanel settingsPanel = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
    	settingsPanel.setLayout(gridbag);
    	settingsPanel.setBorder(BorderFactory.createTitledBorder("Parametry"));
    	
//      -----------------------------------------------------------------------------------
//      Kontrolki do ustawiania parametrów
//    -----------------------------------------------------------------------------------        

    	
    	kLabel = new JLabel("d³ugoœæ okna czasowego (n):");
    	kSpinner = new JSpinner(new SpinnerNumberModel());
    	kSpinner.setPreferredSize(new Dimension(50, 15));
    	
    	differenceLabel = new JLabel("d³ugoœæ przesuniêcia (k):");
    	differenceSpinner = new JSpinner(new SpinnerNumberModel());
    	differenceSpinner.setPreferredSize(new Dimension(50, 15));
    	
    	startPointLabel = new JLabel("czas pocz¹tkowy (t):");
    	startPointSpinner = new JSpinner(new SpinnerNumberModel());
    	startPointSpinner.setPreferredSize(new Dimension(50, 15));

    	pixelLabel = new JLabel("numer piksela:");
    	pixelSpinner = new JSpinner();
    	pixelSpinner.setPreferredSize(new Dimension(50, 15));
    	pixelSpinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				Main.pixel = (Integer) pixelSpinner.getValue();
				
			}
		});
    	
    	
    	timeForPixelLabel = new JLabel("numer ramki dla mapy pixeli:");
    	timeForPixelSpinner = new JSpinner();
    	timeForPixelSpinner.setPreferredSize(new Dimension(50, 15));
    	timeForPixelSpinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				Main.timeForPixelMap = (Integer) timeForPixelSpinner.getValue();
				
			}
		});
    	
    	createPixelMapButton = new JButton("Wygeneruj mapê pikseli");
    	createPixelMapButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {
				Main.createPixelMap();
			}
		});
    	
    	createPixelMapForAllTimeButton = new JButton("Wygeneruj mapê pikseli for all time");
    	createPixelMapForAllTimeButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {
				Main.createPixelMapForAllTimeButton();
			}
		});
    	
    	
    	testLabel = new JLabel("przesuniêcie do testów:");
    	testSpinner = new JSpinner();
    	testSpinner.setPreferredSize(new Dimension(50, 15));
    	testSpinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				Main.przesuniecie_do_testow = (Integer) testSpinner.getValue();
				
			}
		});
    	
    	kStartLabel = new JLabel("przesuniêcie pocz¹tkowe (k startowe):");
    	kStartSpinner = new JSpinner(new SpinnerNumberModel());
    	kStartSpinner.setPreferredSize(new Dimension(50, 15));
    
    	calculateCorrelationButton = new JButton("Wygeneruj wykresy");
    	calculateCorrelationButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Main.dlugosc_przesuniecia_k = (Integer) differenceSpinner.getValue();
				Main.dlugosc_okna_czasowego_n = (Integer) kSpinner.getValue();
				Main.czas_poczatkowy_t = (Integer) startPointSpinner.getValue();
				chartCorrelationPanel.removeAll();
				chartDataPanel.removeAll();
				chartTestPanel.removeAll();
				chartCorrelationInTimePanel.removeAll();
				chartStatisticPanel.removeAll();
				chartAllPanel.removeAll();
				if(Main.isVector && !Main.isAutocorrelation){
					System.out.println("Vector + predkosc");
					Main.calculateForVector();
				}
				else if(Main.isVector && Main.isAutocorrelation){
					System.out.println("vektor + autokorelacja");
					Main.calculateForAutocorrelation();
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
    	
    	testButton = new JButton("Test");
    	testButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {
				chartCorrelationPanel.removeAll();
				Main.test();
			}
		});
    	
    	calculateAvgMeasurementForPixelButton = new JButton("Wygeneruj œredni przebieg");
    	calculateAvgMeasurementForPixelButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {
				chartDataPanel.removeAll();
				Main.calculateAvgMeasurementForPixel();
			}
		});
    	
    	Insets insets = new Insets(5, 5, 5, 5);    	

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
    	gridbag.setConstraints(createPixelMapForAllTimeButton, new GridBagConstraints(0, 10, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(calculateAvgMeasurementForPixelButton,  new GridBagConstraints(0, 11, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	gridbag.setConstraints(kSpinner, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(kStartSpinner, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(differenceSpinner, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(startPointSpinner, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(pixelSpinner, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(timeForPixelSpinner, new GridBagConstraints(1, 6, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(testSpinner, new GridBagConstraints(1, 8, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	   	  
    	kSpinner.addChangeListener(new ChangeListener() {      
    		  public void stateChanged(ChangeEvent e) {
    		    Main.dlugosc_okna_czasowego_n = (Integer) kSpinner.getValue();		    
    		  }
    		});
    	
    	differenceSpinner.addChangeListener(new ChangeListener() {      
  		  public void stateChanged(ChangeEvent e) {
  		    Main.dlugosc_przesuniecia_k = (Integer) differenceSpinner.getValue();
  		  }
  		});
    	
    	kStartSpinner.addChangeListener(new ChangeListener() {      
    		  public void stateChanged(ChangeEvent e) {
    		    Main.k_poczatkowe = (Integer) kStartSpinner.getValue();
    		  }
    		});
    	
    	startPointSpinner.addChangeListener(new ChangeListener() {      
  		  public void stateChanged(ChangeEvent e) {
  		    Main.czas_poczatkowy_t = (Integer) startPointSpinner.getValue();
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
    	settingsPanel.add(calculateCorrelationButton);
    	settingsPanel.add(testButton);
    	settingsPanel.add(createPixelMapForAllTimeButton);
    	settingsPanel.add(calculateAvgMeasurementForPixelButton);
    	
//      -----------------------------------------------------------------------------------
//      wybór typu przetwarzania (vector / pixel)
//    -----------------------------------------------------------------------------------
    	
        JPanel calculateTypePanel = new JPanel();
        GridBagLayout gridbagCalculateType = new GridBagLayout();
        calculateTypePanel.setLayout(gridbagCalculateType);
        calculateTypePanel.setBorder(BorderFactory.createTitledBorder("Typ przetwarzania"));
        
        JRadioButton vector = new JRadioButton("Vektor");
    	vector.setMnemonic(KeyEvent.VK_B);
    	vector.setActionCommand("Vector");
    	vector.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				pixelSpinner.setEnabled(false);
				if(e.getStateChange()==1){
					Main.isVector = true;
					if(Main.isAutocorrelation){
						addComponentsForAutocorelationForVector();
					}
					else{
						addComponentForSpeedForVector();
					}
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
							if(Main.isAutocorrelation){
								addComponentsForAutocorelationForPixel();
							}
							else{
								addComponentForSpeedForPixel();
							}
						}						
					}
				});
		
	   ButtonGroup groupType = new ButtonGroup();
	   groupType.add(vector);
	   groupType.add(pixel);
	   
   	   calculateTypePanel.add(vector);
   	   calculateTypePanel.add(pixel);
        
//      -----------------------------------------------------------------------------------
//      panel na wybór operacji (autokorelacja / prêdkoœæ )
//    -----------------------------------------------------------------------------------
        
        JPanel operationTypePanel = new JPanel();
        operationTypePanel.setLayout(gridbagCalculateType);
        operationTypePanel.setBorder(BorderFactory.createTitledBorder("Operacja"));
        
        JRadioButton autocorrelation = new JRadioButton("Autokorelacja");
    	autocorrelation.setMnemonic(KeyEvent.VK_A);
    	autocorrelation.setActionCommand("Autocorrelation");
    	autocorrelation.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					Main.isAutocorrelation = true;
					if(Main.isVector){
						addComponentsForAutocorelationForVector();
					}
					else{
						addComponentsForAutocorelationForPixel();
					}
				}				
			}
		});
    	autocorrelation.setSelected(true);
    	
    	JRadioButton speed = new JRadioButton("Prêdkoœæ");
    	speed.setMnemonic(KeyEvent.VK_A);
    	speed.setActionCommand("Prêdkoœæ");
    	speed.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					Main.isAutocorrelation = false;
					if(Main.isVector){
						addComponentForSpeedForVector();
					}
					else{
						addComponentForSpeedForPixel();
					}
				}				
			}
		});
    	
        ButtonGroup groupOperationType = new ButtonGroup();
        groupOperationType.add(autocorrelation);
        groupOperationType.add(speed);
        
    	operationTypePanel.add(autocorrelation);
    	operationTypePanel.add(speed);
        
//      -----------------------------------------------------------------------------------
//      panel na wybór pliku
//    -----------------------------------------------------------------------------------
        
        JPanel inputFilePanel = new JPanel();
        GridBagLayout inputFileGridbag = new GridBagLayout();
        inputFilePanel.setLayout(inputFileGridbag);
        inputFilePanel.setBorder(BorderFactory.createTitledBorder("Plik wejœciowy"));
        
        JRadioButton pixelFile = new JRadioButton("pixel");
    	pixelFile.setMnemonic(KeyEvent.KEY_FIRST);
    	pixelFile.setActionCommand("fileType");
    	pixelFile.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				Main.isManchester = false;
				Main.isGenerate = false;
				Main.isPixel = true;
			}
		});   	
    	
        JRadioButton normalFile = new JRadioButton("normal");
    	normalFile.setMnemonic(KeyEvent.KEY_FIRST);
    	normalFile.setActionCommand("fileType");
    	normalFile.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				Main.isManchester = false;
				Main.isGenerate = false;
				Main.isPixel = false;
			}
		});   	
    	
    	JRadioButton manchesterFile = new JRadioButton("Manchester");
    	manchesterFile.setMnemonic(KeyEvent.KEY_LAST);
    	manchesterFile.setActionCommand("fileType");
    	manchesterFile.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				Main.isManchester = true;	
				Main.isPixel = false;
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
				Main.isPixel = false;
			}
		});    	
    	
    	ButtonGroup fileTypeGroup = new ButtonGroup();
        fileTypeGroup.add(normalFile);
        fileTypeGroup.add(manchesterFile);
        fileTypeGroup.add(generateFile);
        fileTypeGroup.add(pixelFile);
        
    	JButton filesButton = new JButton("Wybierz plik");
    	filesButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {		
				JFileChooser fileChooser = new JFileChooser();
		    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		    	int result = fileChooser.showOpenDialog(GUI1.this);
		    	if (result == JFileChooser.APPROVE_OPTION) {
		    	    Main.file = fileChooser.getSelectedFile();
		    	    System.out.println("plik from GUI: "+Main.file);
		    	}
			}
		});
    	
       	inputFileGridbag.setConstraints(normalFile, new GridBagConstraints(0, 0, 1, 1, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(manchesterFile, new GridBagConstraints(1, 0, 1, 1, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(generateFile, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(pixelFile, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(filesButton, new GridBagConstraints(0, 1, 4, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	inputFilePanel.add(normalFile);
    	inputFilePanel.add(manchesterFile);
    	inputFilePanel.add(generateFile);
    	inputFilePanel.add(pixelFile);
    	inputFilePanel.add(filesButton);
        
//-----------------------------------------------------------------------------------------------------------------
    	       
    	GridBagLayout configurationGridbag = new GridBagLayout();
    	configurationPanel.setLayout(configurationGridbag);
    	configurationGridbag.setConstraints(calculateTypePanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	configurationGridbag.setConstraints(operationTypePanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	configurationGridbag.setConstraints(inputFilePanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	configurationGridbag.setConstraints(settingsPanel, new GridBagConstraints(0, 3, 1, 1, 1, 3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
        configurationPanel.add(calculateTypePanel);
        configurationPanel.add(inputFilePanel);
        configurationPanel.add(settingsPanel);
        configurationPanel.add(operationTypePanel);
       
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(tabbedPanel, BorderLayout.CENTER);

        FlowLayout bl = new FlowLayout();
        pixelPanel.setLayout(bl);
     
        squarePanel.setMaximumSize(new Dimension(670,670));
        squarePanel.setMinimumSize(new Dimension(670,670));
        squarePanel.setPreferredSize(new Dimension(670,670));

        pixelPanel.add(squarePanel);      
        
        allPanel.setLayout(new BorderLayout());
        JPanel chartButtonPanel = new JPanel();
        measurement.setSelected(true);
        measurement.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartAllPanel.removeAll();
				Main.generateAllChart();		
			}
		});
        avg.setSelected(true);
        avg.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartAllPanel.removeAll();
				Main.generateAllChart();				
			}
		});
        median.setSelected(true);
        median.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartAllPanel.removeAll();
				Main.generateAllChart();	
			}
		});
        std.setSelected(true);
        std.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartAllPanel.removeAll();
				Main.generateAllChart();			
			}
		});
        variance.setSelected(true);
        variance.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartAllPanel.removeAll();
				Main.generateAllChart();				
			}
		});
        autocorrelationButton.setSelected(true);
        autocorrelationButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartAllPanel.removeAll();
				Main.generateAllChart();
				
			}
		});
        chartButtonPanel.add(measurement);
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
        
        
        kLabel.setVisible(true);
    	kSpinner.setVisible(true);
    	kStartLabel.setVisible(true);
    	kStartSpinner.setVisible(true);
        differenceLabel.setVisible(true);
        differenceSpinner.setVisible(true);
        startPointLabel.setVisible(true);
        startPointSpinner.setVisible(true);
        calculateCorrelationButton.setVisible(true);       
        testLabel.setVisible(true);      
        testButton.setVisible(true);
        testSpinner.setVisible(true);
        
        pixelLabel.setVisible(false);
        pixelSpinner.setVisible(false);
        timeForPixelLabel.setVisible(false);
        timeForPixelSpinner.setVisible(false);
        createPixelMapButton.setVisible(false);
        createPixelMapForAllTimeButton.setVisible(false);
        calculateAvgMeasurementForPixelButton.setVisible(false);
    }
    
    
    public void addComponentsForAutocorelationForPixel(){
    	kLabel.setVisible(true);
    	kSpinner.setVisible(true);
    	kStartLabel.setVisible(true);
    	kStartSpinner.setVisible(true);
        differenceLabel.setVisible(true);
        differenceSpinner.setVisible(true);
        startPointLabel.setVisible(true);
        startPointSpinner.setVisible(true);
        pixelLabel.setVisible(true);
        pixelSpinner.setVisible(true);
        timeForPixelLabel.setVisible(true);
        timeForPixelSpinner.setVisible(true);
        calculateCorrelationButton.setVisible(true);
        createPixelMapButton.setVisible(true);   
        createPixelMapForAllTimeButton.setVisible(true);
        testLabel.setVisible(true);      
        testButton.setVisible(true);
        testSpinner.setVisible(true);
        calculateAvgMeasurementForPixelButton.setVisible(true);
        
        tabbedPanel.removeAll();
        tabbedPanel.addTab("Przebieg", chartDataPanel);
        tabbedPanel.addTab("Pixel", pixelPanel);
        tabbedPanel.addTab("Korelacja", chartCorrelationPanel);
        tabbedPanel.addTab("Autokorelacja", chartCorrelationInTimePanel);
        tabbedPanel.addTab("Dane statystyczne", chartStatisticPanel);
        tabbedPanel.addTab("Wszystko", allPanel);
    }
    
    public void addComponentsForAutocorelationForVector(){
    	kLabel.setVisible(true);
    	kSpinner.setVisible(true);
    	kStartLabel.setVisible(true);
    	kStartSpinner.setVisible(true);
        differenceLabel.setVisible(true);
        differenceSpinner.setVisible(true);
        startPointLabel.setVisible(true);
        startPointSpinner.setVisible(true);
        calculateCorrelationButton.setVisible(true);       
        testLabel.setVisible(true);      
        testButton.setVisible(true);
        testSpinner.setVisible(true);
        
        pixelLabel.setVisible(false);
        pixelSpinner.setVisible(false);
        timeForPixelLabel.setVisible(false);
        timeForPixelSpinner.setVisible(false);
        createPixelMapButton.setVisible(false);
        createPixelMapForAllTimeButton.setVisible(false);
        calculateAvgMeasurementForPixelButton.setVisible(false);
        
        tabbedPanel.removeAll();
        tabbedPanel.addTab("Przebieg", chartDataPanel);
        tabbedPanel.addTab("Korelacja", chartCorrelationPanel);
        tabbedPanel.addTab("Autokorelacja", chartCorrelationInTimePanel);
        tabbedPanel.addTab("Dane statystyczne", chartStatisticPanel);
        tabbedPanel.addTab("Wszystko", allPanel);
        
        
    }
    
    public void addComponentForSpeedForPixel(){
    	kLabel.setVisible(true);
    	kSpinner.setVisible(true);
        differenceLabel.setVisible(true);
        differenceSpinner.setVisible(true);
        startPointLabel.setVisible(true);
        startPointSpinner.setVisible(true);
        pixelLabel.setVisible(true);
        pixelSpinner.setVisible(true);
        calculateCorrelationButton.setVisible(true);       
        testLabel.setVisible(true);      
        testButton.setVisible(true);
        testSpinner.setVisible(true);
        
        timeForPixelLabel.setVisible(false);
        timeForPixelSpinner.setVisible(false);
        createPixelMapButton.setVisible(false); 
        createPixelMapForAllTimeButton.setVisible(false);
    	kStartLabel.setVisible(false);
    	kStartSpinner.setVisible(false);
    	calculateAvgMeasurementForPixelButton.setVisible(false);
    	
        tabbedPanel.removeAll();
        tabbedPanel.addTab("Przebieg", chartDataPanel);
        tabbedPanel.addTab("Test", chartTestPanel);
        tabbedPanel.addTab("Korelacja", chartCorrelationPanel);
    }
    
    public void addComponentForSpeedForVector(){
    	kLabel.setVisible(true);
    	kSpinner.setVisible(true);
        differenceLabel.setVisible(true);
        differenceSpinner.setVisible(true);
        startPointLabel.setVisible(true);
        startPointSpinner.setVisible(true);
        calculateCorrelationButton.setVisible(true);       
        testLabel.setVisible(true);      
        testButton.setVisible(true);
        testSpinner.setVisible(true);
        
        pixelLabel.setVisible(false);
        pixelSpinner.setVisible(false);
        timeForPixelLabel.setVisible(false);
        timeForPixelSpinner.setVisible(false);
        createPixelMapButton.setVisible(false); 
        createPixelMapForAllTimeButton.setVisible(false);
    	kStartLabel.setVisible(false);
    	kStartSpinner.setVisible(false);
    	calculateAvgMeasurementForPixelButton.setVisible(false);
    	
        tabbedPanel.removeAll();
        tabbedPanel.addTab("Przebieg", chartDataPanel);
        tabbedPanel.addTab("Test", chartTestPanel);
        tabbedPanel.addTab("Korelacja", chartCorrelationPanel);
    }
    public void fileChooser(){
    	 	JFrame.setDefaultLookAndFeelDecorated(true);
    	    JDialog.setDefaultLookAndFeelDecorated(true);
    	    
    	    JFrame frame = new JFrame("JComboBox Test");
    	    
    	    frame.setLayout(new FlowLayout());
    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	    frame.pack();
    	    frame.setVisible(true);
    }
}
