import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

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
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GUI extends JFrame {
    
    
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
    public static JPanel chartWindowPanel = new JPanel();
    	
    public static JPanel resultPanel;
    public static JPanel configurationPanel;
    public static JPanel generateAvgMeasurementPanel;
    public static JPanel generatePixelMapPanel;
    public static JPanel windowPanel;

    
    public static JPanel pixelPanel = new JPanel();
    public static JPanel squarePanel = new JPanel();
    
    public static JCheckBox avg = new JCheckBox("œrednia");
    public static JCheckBox median = new JCheckBox("mediana");
    public static JCheckBox std = new JCheckBox("ochylenie standardowe");
    public static JCheckBox variance = new JCheckBox("wariancja");
    public static JCheckBox autocorrelationButton = new JCheckBox("autokorelacja");
    public static JCheckBox measurement = new JCheckBox("przebieg");
    public static JCheckBox autocorrelationStd = new JCheckBox("odchylenie autokorelacji");
    
    public static JLabel kLabel;
    public static JLabel kStartLabel;
    public static JLabel differenceLabel;
    public static JLabel startPointLabel;
    public static JLabel pixelLabel;
    public static JLabel differenceForAutocorrelationLabel;
    public static JButton calculateCorrelationButton;
    public static JLabel timeForPixelLabel;
    public static JButton createPixelMapButton;
    public static JLabel testLabel;
    public static JButton testButton;
    public static JLabel windowLabel;
    public static JLabel windowAcceptableValueLabel;
    public static JButton windowButton;
    public static JSpinner windowSpinner;
    public static JSpinner windowAcceptableValueSpinner;
    public static JButton createPixelMapForAllTimeButton;
    public static JButton calculateAvgMeasurementForPixelButton;
    public static JButton clearChartButton;
    public static JButton filesButton = new JButton("Wybierz plik");
    public static JButton filesAButton = new JButton("P³aszczyzna A");
    public static JButton filesBButton = new JButton("P³aszczyzna B");
	
    public static JSpinner kSpinner;
    public static JSpinner kStartSpinner;
    public static JSpinner differenceSpinner;
    public static JSpinner startPointSpinner;
    public static JSpinner pixelSpinner;
    public static JSpinner timeForPixelSpinner;
    public static JSpinner testSpinner;
    public static JSpinner differenceForAutocorrelationSpinner;
    
    public static JTabbedPane tabbedPanel;
   
    public GUI() {

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
                
       configurationPanel = new JPanel();
        
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
    	settingsPanel.setBorder(BorderFactory.createTitledBorder(null, "Parametry", TitledBorder.LEFT, TitledBorder.TOP, new Font("sans Serif", Font.PLAIN, 11), Color.BLACK));
    	
//      -----------------------------------------------------------------------------------
//      Panel na kontrolki do ustawienia testu korelacji
//    -----------------------------------------------------------------------------------
               
        JPanel testCorrelationPanel = new JPanel();
        GridBagLayout gridbag_testCorrelationPanel = new GridBagLayout();
        testCorrelationPanel.setLayout(gridbag_testCorrelationPanel);
        testCorrelationPanel.setBorder(BorderFactory.createTitledBorder(null, "Test korelacji", TitledBorder.LEFT, TitledBorder.TOP, new Font("sans Serif", Font.PLAIN, 11), Color.BLACK));

//      -----------------------------------------------------------------------------------
//      Panel na kontrolki do wyboru okna
//    -----------------------------------------------------------------------------------
               
        JPanel windowPanel = new JPanel();
        GridBagLayout gridbag_windowPanel = new GridBagLayout();
        windowPanel.setLayout(gridbag_windowPanel);
        windowPanel.setBorder(BorderFactory.createTitledBorder(null, "Wybor okna", TitledBorder.LEFT, TitledBorder.TOP, new Font("sans Serif", Font.PLAIN, 11), Color.BLACK));
     
        
//      -----------------------------------------------------------------------------------
//      Panel na kontrolki do generowania mapy pixeli
//    -----------------------------------------------------------------------------------
               
        generatePixelMapPanel = new JPanel();
        GridBagLayout gridbag_generatePixelMapPanel = new GridBagLayout();
        generatePixelMapPanel.setLayout(gridbag_generatePixelMapPanel);
        generatePixelMapPanel.setBorder(BorderFactory.createTitledBorder(null, "Mapa pikseli", TitledBorder.LEFT, TitledBorder.TOP, new Font("sans Serif", Font.PLAIN, 11), Color.BLACK));
        
//      -----------------------------------------------------------------------------------
//      Panel na kontrolki do generowania œredniego przebiegu
//    -----------------------------------------------------------------------------------
               
        generateAvgMeasurementPanel = new JPanel();
        GridBagLayout gridbag_generateAvgMeasurementPanel = new GridBagLayout();
        generateAvgMeasurementPanel.setLayout(gridbag_generateAvgMeasurementPanel);
        generateAvgMeasurementPanel.setBorder(BorderFactory.createTitledBorder(null, "Przebieg œredni", TitledBorder.LEFT, TitledBorder.TOP, new Font("sans Serif", Font.PLAIN, 11), Color.BLACK));
        
//      -----------------------------------------------------------------------------------
//      Kontrolki do generowania przebigu œredniego
//    -----------------------------------------------------------------------------------        
       	calculateAvgMeasurementForPixelButton = new JButton("Wygeneruj œredni przebieg");
    	calculateAvgMeasurementForPixelButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {
				chartDataPanel.removeAll();
				Main.calculateAvgMeasurementForPixel();
				timeForPixelLabel.setEnabled(true);
				timeForPixelSpinner.setEnabled(true);
				createPixelMapButton.setEnabled(true);
			}
		});
        
    	Insets insets = new Insets(5, 5, 5, 5);
    	gridbag_generateAvgMeasurementPanel.setConstraints(calculateAvgMeasurementForPixelButton,  new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	generateAvgMeasurementPanel.add(calculateAvgMeasurementForPixelButton);
    	
//      -----------------------------------------------------------------------------------
//      Kontrolki do ustawiania testu korelacji
//    -----------------------------------------------------------------------------------        
        testLabel = new JLabel("przesuniêcie do testów:");
    	testSpinner = new JSpinner();
    	testSpinner.setPreferredSize(new Dimension(50, 15));
    	testSpinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				Main.przesuniecie_do_testow = (Integer) testSpinner.getValue();
				
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
    	
    	
    	
    	gridbag_testCorrelationPanel.setConstraints(testLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag_testCorrelationPanel.setConstraints(testButton, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));   	
    	gridbag_testCorrelationPanel.setConstraints(testSpinner, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	testCorrelationPanel.add(testLabel);
    	testCorrelationPanel.add(testSpinner);
    	testCorrelationPanel.add(testButton);
    	
    	
//-----------------------------------------------------------------------------------------
//    	Kontrolki do wyboru okna 
//    	------------------------------------------------------------------------------------
        windowLabel = new JLabel("d³ugoœæ fragmentu:");
      	windowSpinner = new JSpinner();
      	windowSpinner.setPreferredSize(new Dimension(50, 15));
      	windowSpinner.addChangeListener(new ChangeListener() {
  			
  			public void stateChanged(ChangeEvent e) {
  				Main.window_test_fragment_length = (Integer) windowSpinner.getValue();
  				
  			}
  		});
      	
      	windowAcceptableValueLabel = new JLabel("wartoœæ akceptowalna:");
      	windowAcceptableValueSpinner = new JSpinner();
        SpinnerNumberModel model = new SpinnerNumberModel(0.3, 0.0, 0.5, 0.05);
        windowAcceptableValueSpinner.setModel(model);
      	windowAcceptableValueSpinner.addChangeListener(new ChangeListener() {
  			
  			public void stateChanged(ChangeEvent e) {
  				chartWindowPanel.removeAll();
  				Main.window_test_acceptable_value = (Double) windowAcceptableValueSpinner.getValue();
  				
  			}
  		});
      	
      	windowButton = new JButton("Wybór okna");
      	windowButton.addActionListener(new ActionListener(
      			) {
  			
  			public void actionPerformed(ActionEvent e) {
  				Main.choiceWindow();
//  				chartCorrelationPanel.removeAll();
//  				Main.test();
  			}
  		});
      	
      	
      	
      	gridbag_windowPanel.setConstraints(windowLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
      	gridbag_windowPanel.setConstraints(windowSpinner, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
      	gridbag_windowPanel.setConstraints(windowAcceptableValueLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
      	gridbag_windowPanel.setConstraints(windowAcceptableValueSpinner, new GridBagConstraints(1, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));   	  	
      	gridbag_windowPanel.setConstraints(windowButton, new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));   
      	
      	windowPanel.add(windowLabel);
      	windowPanel.add(windowSpinner);
      	windowPanel.add(windowButton);
      	windowPanel.add(windowAcceptableValueLabel);
      	windowPanel.add(windowAcceptableValueSpinner);
    	
//      -----------------------------------------------------------------------------------
//      Kontrolki do generowania mapy pikseli
//    -----------------------------------------------------------------------------------       
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
		    	kLabel.setEnabled(true);
		    	kSpinner.setEnabled(true);
		    	kStartLabel.setEnabled(true);
		    	kStartSpinner.setEnabled(true);
		        differenceLabel.setEnabled(true);
		        differenceSpinner.setEnabled(true);
		        startPointLabel.setEnabled(true);
		        startPointSpinner.setEnabled(true);
		        differenceForAutocorrelationLabel.setEnabled(true);
		        differenceForAutocorrelationSpinner.setEnabled(true);
		        pixelLabel.setEnabled(true);
		        pixelSpinner.setEnabled(true);
		        calculateCorrelationButton.setEnabled(true);
		        clearChartButton.setEnabled(true);
		        
		        testButton.setEnabled(true);
		        testLabel.setEnabled(true);
		        testSpinner.setEnabled(true);
			}
		});
    	
    	gridbag_generatePixelMapPanel.setConstraints(timeForPixelLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag_generatePixelMapPanel.setConstraints(createPixelMapButton, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag_generatePixelMapPanel.setConstraints(timeForPixelSpinner, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	generatePixelMapPanel.add(timeForPixelLabel);
    	generatePixelMapPanel.add(timeForPixelSpinner);
    	generatePixelMapPanel.add(createPixelMapButton);
    	
    	
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
    	
    	differenceForAutocorrelationLabel = new JLabel("d³ugoœæ okna dla autokorelacji:");
    	differenceForAutocorrelationSpinner = new JSpinner(new SpinnerNumberModel());
    	differenceForAutocorrelationSpinner.setPreferredSize(new Dimension(50, 15));

    	pixelLabel = new JLabel("numer piksela:");
    	pixelSpinner = new JSpinner();
    	pixelSpinner.setPreferredSize(new Dimension(50, 15));
    	pixelSpinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				Main.pixel = (Integer) pixelSpinner.getValue();
				
			}
		});
    	
    	clearChartButton = new JButton("Wyczyœæ wykresy");
    	clearChartButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {
				Main.clearCharts();
			}
		});
    	createPixelMapForAllTimeButton = new JButton("Wygeneruj mapê pikseli for all time");
    	createPixelMapForAllTimeButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {
				Main.createPixelMapForAllTimeButton();
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
				Main.dlugosc_okna_czasowego_dla_autokorelacji = (Integer) differenceForAutocorrelationSpinner.getValue();
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
					try {
						Main.calculateForPixel();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}			
				else{
					Main.calculateAutocorrelationForPixel();
				}
			}
		});
    	
    	gridbag.setConstraints(kLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(kStartLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(differenceLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(startPointLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(differenceForAutocorrelationLabel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(pixelLabel, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(calculateCorrelationButton, new GridBagConstraints(0, 6, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(clearChartButton,  new GridBagConstraints(0, 7, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(createPixelMapForAllTimeButton, new GridBagConstraints(0, 8, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	gridbag.setConstraints(kSpinner, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(kStartSpinner, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(differenceSpinner, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(startPointSpinner, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(differenceForAutocorrelationSpinner, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	gridbag.setConstraints(pixelSpinner, new GridBagConstraints(1, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));

    	
    	   	  
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
    	
    	differenceForAutocorrelationSpinner.addChangeListener(new ChangeListener() {      
    		  public void stateChanged(ChangeEvent e) {
    		    Main.dlugosc_okna_czasowego_dla_autokorelacji = (Integer) differenceForAutocorrelationSpinner.getValue();
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
    	settingsPanel.add(differenceForAutocorrelationLabel);
    	settingsPanel.add(differenceForAutocorrelationSpinner);
    	settingsPanel.add(pixelLabel);
    	settingsPanel.add(pixelSpinner);
    	settingsPanel.add(calculateCorrelationButton);
    	settingsPanel.add(createPixelMapForAllTimeButton);
    	settingsPanel.add(clearChartButton);
    	
//      -----------------------------------------------------------------------------------
//      wybór typu przetwarzania (vector / pixel)
//    -----------------------------------------------------------------------------------
    	
        JPanel calculateTypePanel = new JPanel();
        GridBagLayout gridbagCalculateType = new GridBagLayout();
        calculateTypePanel.setLayout(gridbagCalculateType);
        calculateTypePanel.setBorder(BorderFactory.createTitledBorder(null, "Typ przetwarzania", TitledBorder.LEFT, TitledBorder.TOP, new Font("sans Serif", Font.PLAIN, 11), Color.BLACK));
        
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
        operationTypePanel.setBorder(BorderFactory.createTitledBorder(null, "Operacja", TitledBorder.LEFT, TitledBorder.TOP, new Font("sans Serif", Font.PLAIN, 11), Color.BLACK));
        
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
        inputFilePanel.setBorder(BorderFactory.createTitledBorder(null, "Plik wejœciowy", TitledBorder.LEFT, TitledBorder.TOP, new Font("sans Serif", Font.PLAIN, 11), Color.BLACK));
        
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
        
//    	filesButton = new JButton("Wybierz plik");
    	filesButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {		
				JFileChooser fileChooser = new JFileChooser();
		    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		    	int result = fileChooser.showOpenDialog(GUI.this);
		    	if (result == JFileChooser.APPROVE_OPTION) {
		    	    Main.file = fileChooser.getSelectedFile();
		    	    System.out.println("plik from GUI: "+Main.file);
		    	    Main.framesA = Main.loadFile();
		    	}
		    	if(Main.isPixel){
		    		if(Main.isAutocorrelation){
		    			calculateAvgMeasurementForPixelButton.setEnabled(true);
		    		}
		    		else{
		    			System.out.println("tutaj");
		    	        timeForPixelLabel.setEnabled(true);
		    	        timeForPixelSpinner.setEnabled(true);
		    	        createPixelMapButton.setEnabled(true); 
		    	        
		    			kLabel.setEnabled(true);
		    	    	kSpinner.setEnabled(true);
		    	        differenceLabel.setEnabled(true);
		    	        differenceSpinner.setEnabled(true);
		    	        startPointLabel.setEnabled(true);
		    	        startPointSpinner.setEnabled(true);
		    	        pixelLabel.setEnabled(true);
		    	        pixelSpinner.setEnabled(true);
		    	        calculateCorrelationButton.setEnabled(true);       
		    	        testLabel.setEnabled(true);      
		    	        testButton.setEnabled(true);
		    	        testSpinner.setEnabled(true);
		    		}
		    		
		    	}
		    	else{
		    		System.out.println("vektor odklik");
		    		kLabel.setEnabled(true);
			    	kSpinner.setEnabled(true);
			    	kStartLabel.setEnabled(true);
			    	kStartSpinner.setEnabled(true);
			        differenceLabel.setEnabled(true);
			        differenceSpinner.setEnabled(true);
			        startPointLabel.setEnabled(true);
			        startPointSpinner.setEnabled(true);
			        differenceForAutocorrelationLabel.setEnabled(true);
			        differenceForAutocorrelationSpinner.setEnabled(true);
			        pixelLabel.setEnabled(true);
			        pixelSpinner.setEnabled(true);
			        calculateCorrelationButton.setEnabled(true);
			        clearChartButton.setEnabled(true);
			        
			        testButton.setEnabled(true);
			        testLabel.setEnabled(true);
			        testSpinner.setEnabled(true);
		    	}
		    	
			}
		});
    	
    	filesAButton = new JButton("P³aszczyzna A");
    	filesAButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {		
				JFileChooser fileChooser = new JFileChooser();
		    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		    	int result = fileChooser.showOpenDialog(GUI.this);
		    	if (result == JFileChooser.APPROVE_OPTION) {
		    	    Main.file = fileChooser.getSelectedFile();
		    	    System.out.println("plik from GUI: "+Main.file);
		    	    Main.framesA = Main.loadFile();
		    	}
		    	if(Main.isPixel){
		    		if(Main.isAutocorrelation){
		    			calculateAvgMeasurementForPixelButton.setEnabled(true);
		    		}
		    		else{
		    			calculateAvgMeasurementForPixelButton.setEnabled(true);
		    			System.out.println("tutaj");
		    	        timeForPixelLabel.setEnabled(true);
		    	        timeForPixelSpinner.setEnabled(true);
		    	        createPixelMapButton.setEnabled(true); 
		    	        
		    			kLabel.setEnabled(true);
		    	    	kSpinner.setEnabled(true);
		    	        differenceLabel.setEnabled(true);
		    	        differenceSpinner.setEnabled(true);
		    	        startPointLabel.setEnabled(true);
		    	        startPointSpinner.setEnabled(true);
		    	        kStartLabel.setEnabled(true);
		    	        kStartSpinner.setEnabled(true);
		    	        pixelLabel.setEnabled(true);
		    	        pixelSpinner.setEnabled(true);
		    	        calculateCorrelationButton.setEnabled(true);       
		    	        testLabel.setEnabled(true);      
		    	        testButton.setEnabled(true);
		    	        testSpinner.setEnabled(true);
		    		}
		    		
		    	}
		    	else{
		    		System.out.println("vektor odklik");
		    		kLabel.setEnabled(true);
			    	kSpinner.setEnabled(true);
			    	kStartLabel.setEnabled(true);
			    	kStartSpinner.setEnabled(true);
			        differenceLabel.setEnabled(true);
			        differenceSpinner.setEnabled(true);
			        startPointLabel.setEnabled(true);
			        startPointSpinner.setEnabled(true);
			        differenceForAutocorrelationLabel.setEnabled(true);
			        differenceForAutocorrelationSpinner.setEnabled(true);
			        pixelLabel.setEnabled(true);
			        pixelSpinner.setEnabled(true);
			        calculateCorrelationButton.setEnabled(true);
			        clearChartButton.setEnabled(true);
			        
			        testButton.setEnabled(true);
			        testLabel.setEnabled(true);
			        testSpinner.setEnabled(true);
		    	}
		    	
			}
		});
    	
    	filesBButton = new JButton("P³aszczyzna B");
    	filesBButton.addActionListener(new ActionListener(
    			) {
			
			public void actionPerformed(ActionEvent e) {		
				JFileChooser fileChooser = new JFileChooser();
		    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		    	int result = fileChooser.showOpenDialog(GUI.this);
		    	if (result == JFileChooser.APPROVE_OPTION) {
		    	    Main.file = fileChooser.getSelectedFile();
		    	    System.out.println("plik from GUI: "+Main.file);
		    	    Main.framesB = Main.loadFile();
		    	}
		    	if(Main.isPixel){
		    		if(Main.isAutocorrelation){
		    			calculateAvgMeasurementForPixelButton.setEnabled(true);
		    		}
		    		else{
		    			System.out.println("tutaj");
		    	        timeForPixelLabel.setEnabled(true);
		    	        timeForPixelSpinner.setEnabled(true);
		    	        createPixelMapButton.setEnabled(true); 
		    	        
		    			kLabel.setEnabled(true);
		    	    	kSpinner.setEnabled(true);
		    	        differenceLabel.setEnabled(true);
		    	        differenceSpinner.setEnabled(true);
		    	        startPointLabel.setEnabled(true);
		    	        startPointSpinner.setEnabled(true);
		    	        pixelLabel.setEnabled(true);
		    	        pixelSpinner.setEnabled(true);
		    	        calculateCorrelationButton.setEnabled(true);       
		    	        testLabel.setEnabled(true);      
		    	        testButton.setEnabled(true);
		    	        testSpinner.setEnabled(true);
		    		}
		    		
		    	}
		    	else{
		    		System.out.println("vektor odklik");
		    		kLabel.setEnabled(true);
			    	kSpinner.setEnabled(true);
			    	kStartLabel.setEnabled(true);
			    	kStartSpinner.setEnabled(true);
			        differenceLabel.setEnabled(true);
			        differenceSpinner.setEnabled(true);
			        startPointLabel.setEnabled(true);
			        startPointSpinner.setEnabled(true);
			        differenceForAutocorrelationLabel.setEnabled(true);
			        differenceForAutocorrelationSpinner.setEnabled(true);
			        pixelLabel.setEnabled(true);
			        pixelSpinner.setEnabled(true);
			        calculateCorrelationButton.setEnabled(true);
			        clearChartButton.setEnabled(true);
			        
			        testButton.setEnabled(true);
			        testLabel.setEnabled(true);
			        testSpinner.setEnabled(true);
		    	}
		    	
			}
		});
    	
    	
       	inputFileGridbag.setConstraints(normalFile, new GridBagConstraints(0, 0, 1, 1, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(manchesterFile, new GridBagConstraints(1, 0, 1, 1, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(generateFile, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(pixelFile, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(filesButton, new GridBagConstraints(0, 1, 4, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(filesAButton, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	inputFileGridbag.setConstraints(filesBButton, new GridBagConstraints(2, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	inputFilePanel.add(normalFile);
    	inputFilePanel.add(manchesterFile);
    	inputFilePanel.add(generateFile);
    	inputFilePanel.add(pixelFile);
    	inputFilePanel.add(filesButton);
        inputFilePanel.add(filesAButton);
        inputFilePanel.add(filesBButton);
//-----------------------------------------------------------------------------------------------------------------
    	       
    	GridBagLayout configurationGridbag = new GridBagLayout();
    	configurationPanel.setLayout(configurationGridbag);
    	configurationGridbag.setConstraints(calculateTypePanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	configurationGridbag.setConstraints(operationTypePanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	configurationGridbag.setConstraints(inputFilePanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));   	
    	configurationGridbag.setConstraints(generateAvgMeasurementPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	configurationGridbag.setConstraints(generatePixelMapPanel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	configurationGridbag.setConstraints(settingsPanel, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	configurationGridbag.setConstraints(testCorrelationPanel, new GridBagConstraints(0, 6, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	configurationGridbag.setConstraints(windowPanel, new GridBagConstraints(0, 7, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0));
    	
    	
        configurationPanel.add(calculateTypePanel);
        configurationPanel.add(operationTypePanel);
        configurationPanel.add(inputFilePanel);
        configurationPanel.add(settingsPanel);
        configurationPanel.add(testCorrelationPanel);
        configurationPanel.add(windowPanel);
        
       
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(tabbedPanel, BorderLayout.CENTER);

        BoxLayout bl = new BoxLayout(pixelPanel, BoxLayout.Y_AXIS);
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
				Main.refreshAllChart();		
			}
		});
        avg.setSelected(true);
        avg.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartAllPanel.removeAll();
				Main.refreshAllChart();				
			}
		});
        median.setSelected(true);
        median.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartAllPanel.removeAll();
				Main.refreshAllChart();	
			}
		});
        autocorrelationStd.setSelected(true);
        autocorrelationStd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartAllPanel.removeAll();
				Main.refreshAllChart();	
			}
		});
        std.setSelected(true);
        std.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartAllPanel.removeAll();
				Main.refreshAllChart();			
			}
		});
        variance.setSelected(true);
        variance.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartAllPanel.removeAll();
				Main.refreshAllChart();				
			}
		});
        autocorrelationButton.setSelected(true);
        autocorrelationButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				chartAllPanel.removeAll();
				Main.refreshAllChart();
				
			}
		});
        chartButtonPanel.add(measurement);
        chartButtonPanel.add(avg);
        chartButtonPanel.add(median);
        chartButtonPanel.add(std);
        chartButtonPanel.add(variance);
        chartButtonPanel.add(autocorrelationButton);
        chartButtonPanel.add(autocorrelationStd);
        
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
        clearChartButton.setVisible(true);
        
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
    	kLabel.setEnabled(false);
    	kSpinner.setVisible(true);
    	kSpinner.setEnabled(false);
    	kStartLabel.setVisible(true);
    	kStartLabel.setEnabled(false);
    	kStartSpinner.setVisible(true);
    	kStartSpinner.setEnabled(false);
        differenceLabel.setVisible(true);
        differenceLabel.setEnabled(false);
        differenceSpinner.setVisible(true);
        differenceSpinner.setEnabled(false);
        startPointLabel.setVisible(true);
        startPointLabel.setEnabled(false);
        startPointSpinner.setVisible(true);
        startPointSpinner.setEnabled(false);
        differenceForAutocorrelationLabel.setVisible(true);
        differenceForAutocorrelationLabel.setEnabled(false);
        differenceForAutocorrelationSpinner.setVisible(true);
        differenceForAutocorrelationSpinner.setEnabled(false);
        pixelLabel.setVisible(true);
        pixelLabel.setEnabled(false);
        pixelSpinner.setVisible(true);
        pixelSpinner.setEnabled(false);
        timeForPixelLabel.setVisible(true);
        timeForPixelLabel.setEnabled(false);
        timeForPixelSpinner.setVisible(true);
        timeForPixelSpinner.setEnabled(false);
        calculateCorrelationButton.setVisible(true);
        calculateCorrelationButton.setEnabled(false);
        createPixelMapButton.setVisible(true);   
        filesButton.setVisible(true);        
        
        filesAButton.setVisible(false);
        filesBButton.setVisible(false);
        createPixelMapButton.setEnabled(false);
//        createPixelMapForAllTimeButton.setVisible(true);
        testLabel.setVisible(true);    
        testLabel.setEnabled(false);
        testButton.setVisible(true);
        testButton.setEnabled(false);
        testSpinner.setVisible(true);
        testSpinner.setEnabled(false);
        calculateAvgMeasurementForPixelButton.setVisible(true);
        calculateAvgMeasurementForPixelButton.setEnabled(false);
        clearChartButton.setVisible(true);
        clearChartButton.setEnabled(false);
        
        
        configurationPanel.add(generatePixelMapPanel);
        configurationPanel.add(generateAvgMeasurementPanel);
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
    	kLabel.setEnabled(false);
    	kSpinner.setVisible(true);
    	kSpinner.setEnabled(false);
    	kStartLabel.setVisible(true);
    	kStartLabel.setEnabled(false);
    	kStartSpinner.setVisible(true);
    	kStartSpinner.setEnabled(false);
        differenceLabel.setVisible(true);
        differenceLabel.setEnabled(false);
        differenceSpinner.setVisible(true);
        differenceSpinner.setEnabled(false);
        startPointLabel.setVisible(true);
        startPointLabel.setEnabled(false);
        startPointSpinner.setVisible(true);
        startPointSpinner.setEnabled(false);
        calculateCorrelationButton.setVisible(true); 
        calculateCorrelationButton.setEnabled(false);
        testLabel.setVisible(true);      
        testButton.setVisible(true);
        testSpinner.setVisible(true);
        clearChartButton.setVisible(true);
        filesButton.setVisible(true);        
        
        filesAButton.setVisible(false);
        filesBButton.setVisible(false);
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
        kStartLabel.setVisible(true);
        kStartSpinner.setVisible(true);
        pixelLabel.setVisible(true);
        pixelSpinner.setVisible(true);
        calculateCorrelationButton.setVisible(true);       
        testLabel.setVisible(true);      
        testButton.setVisible(true);
        testSpinner.setVisible(true);
        filesAButton.setVisible(true);
        filesBButton.setVisible(true);
        
        filesButton.setVisible(false);        
        timeForPixelLabel.setVisible(true);
        timeForPixelSpinner.setVisible(true);
        createPixelMapButton.setVisible(true); 
        calculateAvgMeasurementForPixelButton.setVisible(true);
        
        
        createPixelMapForAllTimeButton.setVisible(false);
    	clearChartButton.setVisible(false);
    	
        tabbedPanel.removeAll();
        tabbedPanel.addTab("Przebieg", chartDataPanel);
        tabbedPanel.addTab("Pixel", pixelPanel);
        tabbedPanel.addTab("Test", chartTestPanel);
        tabbedPanel.addTab("Korelacja", chartCorrelationPanel);
        tabbedPanel.addTab("Przesuniêcie w czasie", chartCorrelationInTimePanel);
        tabbedPanel.addTab("Wybór okna", chartWindowPanel);
        
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
        filesAButton.setVisible(true);
        filesBButton.setVisible(true);
        
        filesButton.setVisible(false);
        pixelLabel.setVisible(false);
        pixelSpinner.setVisible(false);
        timeForPixelLabel.setVisible(false);
        timeForPixelSpinner.setVisible(false);
        createPixelMapButton.setVisible(false); 
        createPixelMapForAllTimeButton.setVisible(false);
    	kStartLabel.setVisible(false);
    	kStartSpinner.setVisible(false);
    	calculateAvgMeasurementForPixelButton.setVisible(false);
    	clearChartButton.setVisible(false);
    	
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
