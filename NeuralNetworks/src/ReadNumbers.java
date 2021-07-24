import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class ReadNumbers extends Network {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double trainingRate = .002;
		
		double [] target0 = NetworkTools.createTargetArray(0);
		double [] target1 = NetworkTools.createTargetArray(1);
		double [] target2 = NetworkTools.createTargetArray(2);
		double [] target3 = NetworkTools.createTargetArray(3);
		double [] target4 = NetworkTools.createTargetArray(4);
		double [] target5 = NetworkTools.createTargetArray(5);
		double [] target6 = NetworkTools.createTargetArray(6);
		double [] target7 = NetworkTools.createTargetArray(7);
		double [] target8 = NetworkTools.createTargetArray(8);
		double [] target9 = NetworkTools.createTargetArray(9);
		
		String [] inputs0 = new String[]{"zero.png","testZero1.png","testZero2.png","testZero3.png","testZero4.png","testZero5.png","testZero6.png","testZero7.png","testZero8.png","testZero9.png"};
		double [][] inputZeros = new double[inputs0.length][];
		String [] inputs1 = new String[]{"one.png","testOne1.png","testOne2.png","testOne3.png","testOne4.png","testOne5.png","testOne6.png","testOne7.png","testOne8.png","testOne9.png"};
		double [][] inputOnes = new double[inputs1.length][];
		String [] inputs2 = new String[]{"two.png","testTwo1.png","testTwo2.png","testTwo3.png","testTwo4.png","testTwo5.png","testTwo6.png","testTwo7.png","testTwo8.png","testTwo9.png"};
		double [][] inputTwos = new double[inputs2.length][];
		String [] inputs8 = new String[]{"eight.png","testEight1.png","testEight2.png","testEight3.png","testEight4.png","testEight5.png","testEight6.png","testEight7.png","testEight8.png","testEight9.png"};
		double [][] inputEights = new double[inputs8.length][];
		
		Network skyNet = new Network(400,6,10);
		try {
			
			for (int i = 0; i < inputs0.length; i ++){
				inputZeros[i] = imageToBinary(ImageIO.read(skyNet.getClass().getResource(inputs0[i])));
				inputOnes[i] = imageToBinary(ImageIO.read(skyNet.getClass().getResource(inputs1[i])));
				inputTwos[i] = imageToBinary(ImageIO.read(skyNet.getClass().getResource(inputs2[i])));
				inputEights[i] = imageToBinary(ImageIO.read(skyNet.getClass().getResource(inputs8[i])));
			}
			
			
			System.out.println("zero: " + Arrays.toString(inputZeros[0]));
			System.out.println("one: " +Arrays.toString(inputOnes[0]));
			System.out.println("two: " +Arrays.toString(inputTwos[0]));
			System.out.println("eight: " +Arrays.toString(inputEights[0]));	
			
			
		
			for (int i = 0; i <10000; i ++){
						skyNet.train(inputZeros[0], target0, trainingRate);
						skyNet.train(inputZeros[1], target0, trainingRate);
						skyNet.train(inputZeros[2], target0, trainingRate);
						skyNet.train(inputZeros[3], target0, trainingRate);
						skyNet.train(inputZeros[4], target0, trainingRate);
						skyNet.train(inputZeros[5], target0, trainingRate);					
						skyNet.train(inputOnes[0], target1, trainingRate);
						skyNet.train(inputOnes[1], target1, trainingRate);
						skyNet.train(inputOnes[2], target1, trainingRate);
						skyNet.train(inputOnes[3], target1, trainingRate);
						skyNet.train(inputOnes[4], target1, trainingRate);
						skyNet.train(inputOnes[5], target1, trainingRate);
						skyNet.train(inputTwos[1], target2, trainingRate);
						skyNet.train(inputTwos[1], target2, trainingRate);
						skyNet.train(inputTwos[2], target2, trainingRate);
						skyNet.train(inputTwos[3], target2, trainingRate);
						skyNet.train(inputTwos[4], target2, trainingRate);
						skyNet.train(inputTwos[5], target2, trainingRate);
						skyNet.train(inputEights[0], target8, trainingRate);
						skyNet.train(inputEights[1], target8, trainingRate);
						skyNet.train(inputEights[2], target8, trainingRate);
						skyNet.train(inputEights[3], target8, trainingRate);
						skyNet.train(inputEights[4], target8, trainingRate);
						skyNet.train(inputEights[5], target8, trainingRate);
						
						
						
						skyNet.train(inputZeros[6], target0, trainingRate);
						skyNet.train(inputZeros[7], target0, trainingRate);
						skyNet.train(inputZeros[8], target0, trainingRate);
						skyNet.train(inputZeros[9], target0, trainingRate);
						skyNet.train(inputOnes[6], target1, trainingRate);
						skyNet.train(inputOnes[7], target1, trainingRate);
						skyNet.train(inputOnes[8], target1, trainingRate);
						skyNet.train(inputOnes[9], target1, trainingRate);
						skyNet.train(inputTwos[6], target2, trainingRate);
						skyNet.train(inputTwos[7], target2, trainingRate);
						skyNet.train(inputTwos[8], target2, trainingRate);
						skyNet.train(inputTwos[9], target2, trainingRate);
						skyNet.train(inputEights[6], target8, trainingRate);
						skyNet.train(inputEights[7], target8, trainingRate);
						skyNet.train(inputEights[8], target8, trainingRate);
						skyNet.train(inputEights[9], target8, trainingRate);
						
						
						
			}
						
						
								
			
			
			double [] output0 = skyNet.calculate(imageToBinary(ImageIO.read(skyNet.getClass().getResource("testZero.png"))));
			System.out.println(Arrays.toString(output0));
			System.out.println(numFromOutput(output0));			
			
			double [] output1 = skyNet.calculate(imageToBinary(ImageIO.read(skyNet.getClass().getResource("testOne.png"))));
			System.out.println(Arrays.toString(output1));
			System.out.println(numFromOutput(output1));
			
			double [] output2 = skyNet.calculate(imageToBinary(ImageIO.read(skyNet.getClass().getResource("testTwo.png"))));
			System.out.println(Arrays.toString(output2));
			System.out.println(numFromOutput(output2));
			
			double [] output8 = skyNet.calculate(imageToBinary(ImageIO.read(skyNet.getClass().getResource("testEight.png"))));
			System.out.println(Arrays.toString(output8));
			System.out.println(numFromOutput(output8));
			
			//For checking original pixel values to make sure they actually differ in some way
			//System.out.println("Zero " +Arrays.toString(imageToPercent(ImageIO.read(skyNet.getClass().getResource("eight.png")))));
			//System.out.println("Zero " + Arrays.toString(imageToPercent(ImageIO.read(skyNet.getClass().getResource("zero.png")))));

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		

	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------
	//I need to improve my scaling method
	//-------------------------------------------------------------------------------------------------------------------------------------------------------
	public static double [] imageToPercent(BufferedImage image){

		int [] pixels = new int [image.getWidth()*image.getHeight()];
		double [] answer = new double[image.getWidth()*image.getHeight()];
		int i = 0;
		for (int y = 0; y < image.getHeight(); y ++){
			for (int x = 0; x < image.getWidth(); x ++){
				pixels[i] = (image.getRGB(x, y));
				i++;
				
			}
		}
		//System.out.println("Original Pixel Values: " + Arrays.toString(pixels));
		for (int j = 0; j < pixels.length; j ++){
			answer[j] = ((Math.abs(pixels[j])* 10* 10*10*10 )/16777215.0) ;
			//System.out.println("Modified Pixel Values " + answer[j]);
		}
		
		return answer;
	}
	public static String numFromOutput(double [] output){
		
		String possible = "Possible Numbers are";
		int   maxLoc = 0;
		double max = 0;
		for (int i = 0; i < output.length; i++){
			if (output[i] > max){
				max = output[i];
				maxLoc = i;
				
			}
			System.out.println(output[i]);
			System.out.println("");
		}
		/*
		for (int i = 0; i < maxLoc.length; i ++){
			if (maxLoc[i] != 0 ){
				//possible = possible + ", " + (maxLoc[i]);
			}
		}
	*/
		return possible +" " + maxLoc;
		
	}
	
	public static double [] imageToBinary(BufferedImage image){

		int [] pixels = new int [image.getWidth()*image.getHeight()];
		double [] answer = new double[image.getWidth()*image.getHeight()];
		int i = 0;
		for (int y = 0; y < image.getHeight(); y ++){
			for (int x = 0; x < image.getWidth(); x ++){
				pixels[i] = (image.getRGB(x, y));
				i++;
				
			}
		}
		//System.out.println("Original Pixel Values: " + Arrays.toString(pixels));
		for (int j = 0; j < pixels.length; j ++){
			if (Math.abs(pixels[j]) == 1){
				answer[j] = 0;
			}
			else{
				answer[j] = 1;
			}
			 ;
			//System.out.println("Modified Pixel Values " + answer[j]);
		}
		
		return answer;
	}

}
