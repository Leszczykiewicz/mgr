import java.util.ArrayList;


public class FindPeak {
	
	public static ArrayList<Integer> peaks = new ArrayList<Integer>();

    public static void peak(float[] arr)
    {
        peak(arr, 0, arr.length - 1);
    }    
    public static void peak (float arr[], int low, int high)
    {
    	float avg = 0;
    	for(float v: arr){
    		avg += v;
    	}
    	avg /= arr.length;
//    	ArrayList<Integer> result = new ArrayList<Integer>();
        int N = arr.length;
        if (high - low < 2){
        	System.out.println("wyj¹tek");
//        	result.add(0);
        	return;
        }           
        int mid = (low + high) / 2;
        System.out.println(arr[mid-1]+" "+arr[mid]+ " "+arr[mid+1]);
        System.out.println(low+" "+high);
        System.out.println(avg);
        if (arr[mid] - arr[mid - 1] >= 0 && arr[mid] - arr[mid + 1] >= 0 && arr[mid]>0.5){
        	System.out.println("peak");
        	System.out.println(arr[mid] +" ");
        	peaks.add(mid);
        }
            
        /* Recursively find other peak elements */        
        peak (arr, low, mid);
        peak (arr, mid, high);   
        System.out.println("normal " + peaks.size());
      
    }   

}
