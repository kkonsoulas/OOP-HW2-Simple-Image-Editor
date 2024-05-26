package ce326.hw2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


public class YUVImage{

	YUVPixel[][] image;
	int width;
	int height;

	public YUVImage(int width,int height){
		image = new YUVPixel[height][width];
		this.width = width;
		this.height = height;
	}

	public YUVImage(YUVImage copyImg){
		this(copyImg.width,copyImg.height);
		int i,j;
		for(i=0 ;i<height ;i++){
			for(j=0 ;j<width ;j++){
				image[i][j] = new YUVPixel(copyImg.getPixel(i,j));
			}
		}
	}

	public YUVImage(RGBImage RGBImg){
		this(RGBImg.width, RGBImg.height);
		int i,j;
		for(i=0 ;i<height ;i++){
			for(j=0 ;j<width ;j++){
				image[i][j] = new YUVPixel(RGBImg.getPixel(i,j));
			}
		}

	}

	public YUVImage(File file) throws FileNotFoundException, UnsupportedFileFormatException {
		if(!file.exists() || !file.canRead()){
			throw new FileNotFoundException();
		}

		Scanner fileScan = new Scanner(file);
		if(!fileScan.next().equals("YUV3")){
			throw new UnsupportedFileFormatException();
		}

		setWidth(fileScan.nextInt());
		setHeight(fileScan.nextInt());

		setImage(new YUVPixel[height][width]);
		int i,j;
		for(i=0 ;i<height ;i++){
			for(j=0 ;j<width ;j++){
				setPixel(i,j,new YUVPixel(fileScan.nextShort(),fileScan.nextShort(),fileScan.nextShort()));
			}
		}

	}

	public YUVPixel getPixel(int row, int col){
		return image[row][col];
	}

	public void setPixel(int row ,int col, YUVPixel pixel){
		image[row][col] = pixel;
	}

	public void setImage(YUVPixel[][] image){
		this.image = image;
	}

	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public void equalize(){
		Histogram hist = new Histogram(this);
		hist.equalize();

	}

	public String toString(){
		StringBuilder str = new StringBuilder(String.format("YUV3\n%d %d\n", getWidth(), getHeight()));
		int i,j;
		for(i=0 ;i<getHeight() ;i++){
			for(j=0 ;j<getWidth() ;j++){
				str.append(getPixel(i, j).toString());
				str.append("\n");
			}
		}
		return str.toString();
	}


	public void toFile(File file){

		try {

			if (file.exists()) {
				if(file.isDirectory()){
					return;
				}

				if(!file.delete()) {
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
