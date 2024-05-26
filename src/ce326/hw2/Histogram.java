package ce326.hw2;

import java.io.IOException;
import java.io.PrintStream;
import java.io.File;

public class Histogram{
	private static final short LUMINANCE = 236;
	int[] numOfPixelsWithLuminance;
	double[] probabilityOfPixelWithLuminance;
	//short[][] newImageY;
	short[] equalizedLuminocity;
	YUVImage img;

	public Histogram(YUVImage img){
		this.img = img;
		numOfPixelsWithLuminance = new int[LUMINANCE];
		probabilityOfPixelWithLuminance = new double[LUMINANCE];
		//newImageY = new short[img.getHeight()][img.getWidth()];
		equalizedLuminocity = new short[LUMINANCE];

		int i,j;
		for(i=0 ;i< img.getHeight() ;i++){
			for(j=0 ;j< img.getWidth() ;j++){
				numOfPixelsWithLuminance[img.getPixel(i,j).getY()]++;
			}
		}


		//int size = img.getHeight() *  img.getWidth();
	}



	public void equalize(){
		int size = img.getHeight() *  img.getWidth();
		int i,j;
		for(i=0 ;i< LUMINANCE ;i++){
			probabilityOfPixelWithLuminance[i] =  ((double) numOfPixelsWithLuminance[i] / (double) size);
		}

		for(i=1 ;i<LUMINANCE ;i++){
			probabilityOfPixelWithLuminance[i] += probabilityOfPixelWithLuminance[i-1];
		}

		/*for(i=0 ;i< img.getHeight() ;i++){
			for(j=0 ;j< img.getWidth() ;j++){
				newImageY[i][j] = (short) (probabilityOfPixelWithLuminance[img.getPixel(i,j).getY()] * LUMINANCE);
			}
		}*/

		for(i=0 ;i<LUMINANCE ;i++){
			equalizedLuminocity[i] = (short) (probabilityOfPixelWithLuminance[i] * LUMINANCE);
		}

		for(i=0 ;i<img.getHeight() ;i++){
			for(j=0 ;j<img.getWidth() ;j++){
				img.getPixel(i,j).setY(getEqualizedLuminocity(img.getPixel(i,j).getY()));
			}
		}


	}


	public short getEqualizedLuminocity(int luminocity){
		return  equalizedLuminocity[luminocity];
	}

	public String toString(){
		int i;

		StringBuilder strBuilder = new StringBuilder();
		for(i=0 ;i<LUMINANCE ;i++){
			strBuilder.append(String.format("\n%3d.(%4d)\t",i,numOfPixelsWithLuminance[i]));
			int j=0;
			for(j=0; j < numOfPixelsWithLuminance[i] / 1000 ;j++){
				strBuilder.append("#");
			}
			for(j=0; j<(numOfPixelsWithLuminance[i]/100) % 10;j++){
				strBuilder.append("$");
			}
			for(j=0; j< (numOfPixelsWithLuminance[i]/10) % 10;j++){
				strBuilder.append("@");
			}
			for(j=0; j<numOfPixelsWithLuminance[i] % 10;j++){
				strBuilder.append("*");
			}

		}
		strBuilder.append("\n");
		return strBuilder.toString();
	}


	public void toFile(File file){
		try {
			if (file.exists()) {
				if(file.isDirectory()){
					return;
				}
				if (!file.delete()) {
					return;
				}
			}

			if (!file.createNewFile()) {
				return;
			}

			//Scanner sc = new Scanner(file);
			PrintStream writeStream = new PrintStream(file);
			writeStream.print(toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
