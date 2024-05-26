package ce326.hw2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;


public class PPMImageStacker{
	List<PPMImage> images;
	PPMImage finalImage;

	public PPMImageStacker(File dir) throws FileNotFoundException, UnsupportedFileFormatException {
		if(!dir.exists()){
			throw new FileNotFoundException(String.format("[ERROR] Directory %s does not exist!",dir.getName()));
		}

		if(!dir.isDirectory()){
			throw new FileNotFoundException(String.format("[ERROR] %s is not a directory!",dir.getName()) );
		}


		images = new ArrayList<PPMImage>();

		for(File f : Objects.requireNonNull(dir.listFiles())){
			images.add(new PPMImage(f));
		}
		finalImage =  new PPMImage(images.get(0));
	}

	public void stack(){

		int i,j;
		long[][] redArray = new long[images.get(0).getHeight()][images.get(0).getWidth()];
		long[][] greenArray = new long[images.get(0).getHeight()][images.get(0).getWidth()];
		long[][] blueArray = new long[images.get(0).getHeight()][images.get(0).getWidth()];

		ListIterator<PPMImage> it =  images.listIterator();
		while(it.hasNext()){
			PPMImage currImage = it.next();
			RGBPixel pixel;
			for(i=0 ;i<currImage.getHeight() ;i++){
				for(j=0 ;j< currImage.getWidth() ;j++){
					pixel = currImage.getPixel(i,j);
					redArray[i][j] +=  pixel.getRed();
					greenArray[i][j] += pixel.getGreen();
					blueArray[i][j] += pixel.getBlue();

				}
			}

		}

		int numOfImages = images.size();
		for(i=0 ;i<finalImage.getHeight() ;i++){
			for(j=0 ;j< finalImage.getWidth() ;j++){
				finalImage.setPixel(i,j,new RGBPixel((short) (redArray[i][j]/numOfImages),(short) (greenArray[i][j]/numOfImages),(short) (blueArray[i][j]/numOfImages) ));
			}
		}


	}

	public PPMImage getStackedImage(){
		return finalImage;
	}

}
