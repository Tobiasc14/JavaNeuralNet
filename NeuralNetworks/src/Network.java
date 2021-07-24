import java.util.Arrays;

public class Network {
	
	private double [][] output;
	//where output[Layer in network][Neuron in a given layer]
	
	private double [][][] weight;
	//where weights[Layer in network][Neuron in a given layer][previous neuron]
	
	private double [][] bias;
	//where bias[Layer in network][Neuron in a given layer]
	
	private double [][] errorSignal;
	private double [][] errorDerivative;
	
	
	final int [] LAYER_SIZES;
	//literally an array of the sizes of the different layers in the network. If input is 3, hidden is 2, and final is 3 neurons, array is [3,2,3]
	public final int INPUT_SIZE;
	//Number of Input neurons
	public final int OUTPUT_SIZE;
	//number of output neurons
	
	//NETWORK_SIZE is number of layers we have
	public final int NETWORK_SIZE;
	
	
	//int... in this case is an abreviation for int []
	public Network(int...LAYER_SIZES){
		this.LAYER_SIZES = LAYER_SIZES; //Access to array of layer sizes
		this.INPUT_SIZE = LAYER_SIZES[0];//size of input to neural network
		this.OUTPUT_SIZE = LAYER_SIZES[LAYER_SIZES.length-1];//size of layer at last index, or the output of the network
		this.NETWORK_SIZE = LAYER_SIZES.length;//How many layers there are 
		this.output = new double[NETWORK_SIZE][];//network size equivelant to how many neurons in last layer. ouput takes [layer][neuron]. This makes output [networksize] the length of the last layer 
		this.errorSignal = new double[NETWORK_SIZE][];//Similar to above, sets error signal size of layers to number of layers in network, as each layer and each neuron within that layer has an error.
		this.errorDerivative = new double[NETWORK_SIZE][];//See above
		this.weight = new double[NETWORK_SIZE][][];//See above
		this.bias = new double[NETWORK_SIZE][];//see above
		
		
		
		//when you iterate through NETWORK_SIZE you are iterating through each layer in the network
		for (int i = 0; i < NETWORK_SIZE; i ++){
			this.output[i] = new double[LAYER_SIZES[i]];
			this.errorSignal[i] = new double[LAYER_SIZES[i]];
			this.errorDerivative[i] = new double[LAYER_SIZES[i]];
			
			
			//randomly assigning biases 
			this.bias[i] = NetworkTools.createRandomArray(LAYER_SIZES[i], .3, .6);
			if (i>0){
			
				this.weight[i] = NetworkTools.createRandomArray(LAYER_SIZES[i], LAYER_SIZES[i-1], -.5, .5);
				//where weights[Layer in network][Neuron in a given layer][previous neuron]
			}
		}
		
		
	}
	
	public void train(double [] input, double [] target, double trainingRate){
		if (input.length != INPUT_SIZE || target.length != OUTPUT_SIZE){
			return;		
		}
		calculate(input);
		backPropError(target);
		updateWeight(trainingRate);
		
	}
	
	public void backPropError(double [] target){
		for (int neuron = 0; neuron < LAYER_SIZES[NETWORK_SIZE-1]; neuron ++){
			errorSignal[NETWORK_SIZE-1][neuron] = (output[NETWORK_SIZE-1][neuron] - target[neuron])*errorDerivative[NETWORK_SIZE-1][neuron];
		}
		for (int layer = NETWORK_SIZE -2; layer > 0; layer --){
			for (int neuron = 0; neuron < LAYER_SIZES[layer]; neuron ++){
				double sum = 0;
				for (int nextNeuron = 0; nextNeuron < LAYER_SIZES[layer+1]; nextNeuron++){
					sum = sum + weight[layer+1][nextNeuron][neuron]*errorSignal[layer+1][nextNeuron];
				}
				this.errorSignal[layer][neuron] = sum*errorDerivative[layer][neuron];
			}
			
		}
	}
	
	public void updateWeight(double trainingRate){
		for (int layer = 1; layer < NETWORK_SIZE; layer ++){
			for (int neuron = 0; neuron < LAYER_SIZES[layer]; neuron ++){
				for (int prevNeuron = 0; prevNeuron < LAYER_SIZES[layer - 1]; prevNeuron ++){
					double delta = -trainingRate*output[layer-1][prevNeuron]*errorSignal[layer][neuron];
					weight[layer][neuron][prevNeuron] += delta;
				}
				double delta = -trainingRate*errorSignal[layer][neuron];
				bias[layer][neuron] += delta;			}
		}
	}
	
	
	//This method is how the neural network actually works, the math behind each individual neuron
	public double[] calculate(double... input){
		//checks if input size is more then the number of neurons
		if (input.length != this.INPUT_SIZE){
			return null;
		}
		this.output[0] = input;  //where network size is how many layers you have
		for (int layer = 1; layer < NETWORK_SIZE; layer ++ ){//this iterates through the layers in the network
			for (int neuron = 0; neuron< LAYER_SIZES[layer]; neuron ++){//within a given layer, this goes through each neuron
				double sum = bias[layer][neuron];//value of that neuron starts with its randomly assigned bias value 
				for (int prevNeuron = 0; prevNeuron < LAYER_SIZES[layer-1]; prevNeuron++){
					//Apparently the way the network actually works is by summing the values of the previous neurons times their weights.
					sum = sum + (output[layer-1][prevNeuron]*weight[layer][neuron][prevNeuron]);
				}
				output[layer][neuron] = sigma(sum);
				errorDerivative[layer][neuron] = output[layer][neuron]*(1-output[layer][neuron]);
				
			}
		}
		//The above code is the calculating for the ENTIRE network.
		return output[NETWORK_SIZE-1];
		
		
	}
	//This is basically just some special math formula function that weights everything between 0 and 1
	public double sigma(double x){
		return 1d/(1+Math.exp(-x));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Network skynet = new Network(4,8,3,2);
		
		
		double [] input = new double[]{1,.3,.5,.7};
		double [] input2 = new double[]{0,.6,.2,.9};
		//input values
		
		//output values we want if someone give the neural network the data of the input values
		double [] target = new double []{.1,.9};
		double [] target2 = new double []{.9,.1};
		
		
		//These two create random input arrays and target arrays of length 4 containing numbers from 1 to 100
		
		/*
		double [] input = NetworkTools.createRandomArray(4,0,1);
		System.out.println("input" +Arrays.toString(input));
		
		double [] input2 = NetworkTools.createRandomArray(4,0,1);
		System.out.println("input 2 " + Arrays.toString(input2));

		
		
		double [] target = NetworkTools.createRandomArray(2,0,1);
		System.out.println("Target " + Arrays.toString(target));
		
		double [] target2 = NetworkTools.createRandomArray(2,0,1);
		System.out.println("Target2 " + Arrays.toString(target2));
		*/
		
		//This traverses the target array and converts any numbers larger then 1 to decimals by dividing by 10
		//The number of times each number had to be divided by 10 is stored in the count array
	/*	double [] count = new double[input.length];
		for (int j = 0; j < target.length; j ++){
			count[j] = 0;
			while (target[j] > 1){
				target[j] = target[j]/10.0;
				count[j]++;
			}
		}
		*/
		//The algorithm "Learns" iterating through i times and incrementing by, in this case, .3
		//It is putting in the input, seeing how close its output it, and adjusting accordingly. it's being trained.
		// it is changing its own weights accordingly, which is how its storing the training
		
		
		
		for (int i = 0; i < 10000; i ++){
			skynet.train(input, target, .2);
			skynet.train(input2, target2, .2);
		}
		
		//this first line tells it to actually calculate an output based on the input now that it has been trained
		//next for statement converts the decimal outputs back into a similar form to the inputs.
		//it does this by iterating through the output array and multiplying the values by the 
		
	/*	
		
		for (int n = 0; n < output.length; n ++){
			output[n] = output[n]*Math.pow(10, count[n]);
			
		}
		*/
		System.out.println("input" +Arrays.toString(input));
		System.out.println("input2" +Arrays.toString(input2));
		System.out.println("Target " + Arrays.toString(target));
		System.out.println("Target2 " + Arrays.toString(target2));
		double [] output = skynet.calculate(input);
		System.out.println("Output "+ Arrays.toString(skynet.calculate(input)));
		double [] output2 = skynet.calculate(input2);
		System.out.println("Output 2 " +Arrays.toString(skynet.calculate(input2)));
		System.out.println();
		System.out.println();
		System.out.println(Arrays.toString(NetworkTools.createTargetArray(7)));
		
		
	}

}
