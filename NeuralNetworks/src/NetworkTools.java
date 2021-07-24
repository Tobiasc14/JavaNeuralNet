import java.awt.image.BufferedImage;

public class NetworkTools {
	public static double[] createArray(int size, double intValue){
		if (size < 1){
			return null;
		}
		double [] ar = new double[size];
		for (int i = 0; i < size; i ++){
			ar[i] = intValue;
		}
		return ar;
	}
	
	public static double[] createRandomArray(int size, double lowerBound, double upperBound){
		if (size<1){
			return null;
		}
		double [] ar = new double[size];
		for (int i = 0; i < size; i ++){
			ar[i] = randomValue(lowerBound,upperBound);
		}
		return ar;
		
	}
	public static double[][] createRandomArray(int sizex, int sizey, double lowerBound, double upperBound){
		if (sizex < 1 || sizey < 1){
			return null;
		}
		double [][] ar = new double[sizex][sizey];
		for (int i = 0; i < sizex; i ++){
			ar[i] = createRandomArray(sizey,lowerBound,upperBound);
		}
		return ar;
	}
	public static double randomValue(double lowerBound, double upperBound){
		return Math.random()*(upperBound-lowerBound) + lowerBound;
		
	}
	public static Integer[] randomValues(int lowerBound, int upperBound, int amount){
		lowerBound--;
		
		if (amount > upperBound-lowerBound){
			return null;
		}
		Integer [] values = new Integer[amount];
		for (int i = 0; i < amount; i ++){
			int n = (int)Math.random()*(upperBound-lowerBound + 1) + lowerBound;
			while (containsValue(values,n)){
				n = (int)Math.random()*(upperBound-lowerBound + 1) + lowerBound;
			}
			values[i] = n;
		}
		return values;
		
	}
	public static <T extends Comparable<T>> boolean containsValue(T[] ar, T value){
		for(int i = 0; i < ar.length; i ++){
			if (ar[i] != null){
				if (value.compareTo(ar[i]) == 0){
					return true;
				}
			}
		}
		return false;
	}
	public static int indexHighestValue(double [] values){
		int index = 0;
		for (int i = 0; i < values.length; i ++){
			if (values[i] > values[index]){
				index = i;
			}
		}
		return index;
	}
	public static double [] imageToPercent(BufferedImage image){

		int [] pixels = new int [image.getWidth()*image.getHeight()];
		double [] answer = new double[image.getWidth()*image.getHeight()];
		int i = 0;
		for (int y = 0; i < image.getHeight(); y ++){
			for (int x = 0; x < image.getWidth(); x ++){
				pixels[i] = image.getRGB(x, y);
				i++;
			}
		}
		for (int j = 0; j < pixels.length; j ++){
			answer[j] = pixels[j]*-1.0/16777215.0;
		}
		
		return answer;
	}
	public static double[] createTargetArray(int target){
		double [] targetArr = new double [10];
		for (int i = 0; i < targetArr.length; i ++){
			if (i == target){
				targetArr[i]=1;
			}
			else{
				targetArr[i]=0;
			}
		}
		return targetArr;
		
	}
	
	public static int numFromOutput(double [] output){
		int [] round = new int [output.length];
		for (int i = 0; i < output.length; i ++){
			round[i] = (int)(output[i] + .5);
		}
		if (round[0] == 1){
			return 0;
		}
		if (round[1] == 1){
			return 1;
		}
		if (round[2] == 1){
			return 2;
		}
		if (round[3] == 1){
			return 3;
		}
		if (round[4] == 1){
			return 4;
		}
		if (round[5] == 1){
			return 5;
		}
		if (round[6] == 1){
			return 6;
		}
		if (round[7] == 1){
			return 7;
		}
		if (round[8] == 1){
			return 8;
		}
		if (round[9] == 1){
			return 9;
		}
		return 11;
		
		
	}
	
	
	
}




