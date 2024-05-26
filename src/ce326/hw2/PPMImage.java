package ce326.hw2;
import java.io.*;
import java.util.Scanner;
import java.io.File;

public class PPMImage extends RGBImage{

	public PPMImage(File file) throws FileNotFoundException , UnsupportedFileFormatException{
		if(!file.exists() || !file.canRead()){
			throw new FileNotFoundException();
		}

		Scanner fileScan = new Scanner(file);
		if(!fileScan.next().equals("P3")){
			throw new UnsupportedFileFormatException();

		}

		setWidth(fileScan.nextInt());
		setHeight(fileScan.nextInt());
		setColordepth(fileScan.nextInt());

		setImage(new RGBPixel[height][width]);
		int i,j;
		for(i=0 ;i<height ;i++){
			for(j=0 ;j<width ;j++){
				setPixel(i,j,new RGBPixel(fileScan.nextShort(),fileScan.nextShort(),fileScan.nextShort()));
			}
		}

	}


	public PPMImage(RGBImage copyImage){
		super(copyImage);
	}

	public PPMImage(YUVImage copyImage){
		super(copyImage);
	}

	public String toString(){
		StringBuilder str = new StringBuilder(String.format("P3\n%d %d\n%d\n", getWidth(), getHeight(), getColordepth()));
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
