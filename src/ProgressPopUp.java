import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class ProgressPopUp {
	
	private JProgressBar progressBar;
	private JFrame f;
	
	public void setProgress(int progress){
		progressBar.setValue(progress);
	}
	
	public int getProgress(){
		return progressBar.getValue();
	}
	
	public ProgressPopUp() {
		    f = new JFrame("JProgressBar Sample");
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    Container content = f.getContentPane();
		    progressBar = new JProgressBar();
		    progressBar.setIndeterminate(true);
//		    progressBar.setStringPainted(true);
		    Border border = BorderFactory.createTitledBorder("Reading...");
		    progressBar.setBorder(border);
		    content.add(progressBar, BorderLayout.NORTH);
		    f.setAlwaysOnTop(true);
		    f.setLocationRelativeTo(null);
		    f.setSize(300, 100);
		    f.setVisible(true);

	}
	
	public void close(){
		f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
	}

}