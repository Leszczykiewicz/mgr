import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileUtils {
		
	public ArrayList<Frame> getFramesForPixel(String file, int size){
		System.out.println("get frames for pixel");
		ArrayList<Frame> result = new ArrayList<Frame>();
		Frame frame = null;
		float[] C = new float[size];
		int i = 0;
		int lineCounter = 0;
		int frameID = 0;
		frame = new Frame();
		frame.setTime(frameID);
		for(String line : file.split("\n")){
			lineCounter++;
				if(!line.trim().matches("##EOF")){
	
			        	String[] n = line.split(" ");
			        	for(String k:n){
			        		if(k.trim().length()>0){
			        			float tmp =  Float.parseFloat(k);
			        			if(tmp>1){
			        				tmp = 1;
			        			}
			        			else if(tmp < -1){
			        				tmp = -1;
			        			}
			        			C[i] = tmp;
			        			i++;
			        		}		        			
			        	}
			        if(i == size){
			        	for(int k=0; k<C.length; k++){
			        	}
			        	frame.setC(C);
			        	result.add(frame);
			        	i = 0;
			        	C = new float[size];
			        	frameID++;
			        	frame = new Frame();
			        	frame.setTime(frameID);
			        	lineCounter = 0;
			        }				
				}
				}
		return result;

		
	}
	
	public ArrayList<Frame> getFrames(String file, int size){
		ArrayList<Frame> result = new ArrayList<Frame>();
		Frame frame = null;
		float[] C = new float[size];
		int i = 0;
		int lineCounter = 0;
		for(String line : file.split("\n")){
			lineCounter++;
				if(!line.trim().matches("##EOF")){
		        	
			        	frame = new Frame();
			        	frame.setTime(lineCounter);

			        	String[] n = line.split(",");
			        	for(String k:n){
			        		if(k.trim().length()>0){
			        			float tmp =  Float.parseFloat(k);
			        			System.out.println(tmp);
			        			if(tmp>1){
			        				tmp = 1;
			        			}
			        			else if(tmp < -1){
			        				tmp = -1;
			        			}		        				
			        			C[i] = tmp;
			        			i++;
			        		}		        			
			        	}
			        }
			        if(i == size){
			        	frame.setC(C);
			        	result.add(frame);
			        	i = 0;
			        	C = new float[size];
			        }
				}
//		}
		return result;
	}
	
	
	
	public String openFile(String path) {
        FileInputStream fis = null;
        try {
            File file = new File(path);
            fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            return str;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }
	
	 public boolean saveToFile(String path, String content){
	        try {
	            PrintWriter out = new PrintWriter(path);
	            out.print(content);
	            out.close();
	            return true;
	        } catch (FileNotFoundException ex) {
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	        }

	        return false;
	    }

}
