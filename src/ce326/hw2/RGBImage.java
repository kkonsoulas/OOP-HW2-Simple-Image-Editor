package ce326.hw2;

public class RGBImage{
	public static final int MAX_COLORDEPTH =  255;

	RGBPixel[][] image;
	int width;
	int height;
	int colordepth;

	public RGBImage(){}

	public RGBImage(int width ,int height ,int colordepth){
		image = new RGBPixel[height][width];
		this.width = width;
		this.height = height;
		this.colordepth = colordepth;

	}

	public RGBImage(RGBImage copyImage){
		//image = new copyImage.image;
		image = new RGBPixel[copyImage.height][copyImage.width];
		width = copyImage.width;
		height = copyImage.height;
		colordepth = copyImage.colordepth;
		int i,j;
		for(i=0 ;i<height ;i++){
			for(j=0 ;j<width ;j++){
				image[i][j] = new RGBPixel(copyImage.image[i][j]);
			}
		}

	}

	public RGBImage(YUVImage copyImage){
		this(copyImage.getWidth(), copyImage.getHeight(), 255);
		int i,j;
		for(i=0 ;i<height ;i++){
			for(j=0 ;j<width ;j++){
				image[i][j] = new RGBPixel(copyImage.getPixel(i,j));
			}
		}
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public int getColordepth(){
		return colordepth;
	}

	protected void setWidth(int width){
		this.width = width;
	}

	protected void setHeight(int height){
		this.height = height;
	}

	protected void setColordepth(int colordepth){
		this.colordepth = colordepth;
	}

	public RGBPixel[][] getImage(){
		return image;
	}

	public void setImage(RGBPixel[][] newImage){
		image = newImage;
	}

	RGBPixel getPixel(int row ,int col){
		return image[row][col];
	}

	public void setPixel(int row, int col ,RGBPixel pixel){
		image[row][col] = pixel;
	}

	public void grayscale(){
		int i,j;
		RGBPixel pixel;
		short gray;
		for(i=0 ;i<getHeight() ;i++){
			for(j=0 ;j<getWidth() ;j++){
				pixel = getPixel(i,j);
				gray = (short) (0.3*pixel.getRed() + 0.59*pixel.getGreen() + 0.11*pixel.getBlue());
				pixel.setRGB(gray,gray,gray);
			}
		}
	}


	public void doublesize(){
		int i,j;
		RGBPixel pixel;
		RGBPixel[][] newImage  = new RGBPixel[2*height][2*width];
		for(i=0 ;i<getHeight() ;i++){
			for(j=0 ;j<getWidth() ;j++){
				pixel = getPixel(i,j);
				//maybe it will be needed new pixels to be created
				//for now there should be no problem
				newImage[2*i][2*j] = pixel;
				newImage[2*i + 1][2*j] = pixel;
				newImage[2*i][2*j +1] = pixel;
				newImage[2*i +1][2*j +1] = pixel;

			}
		}
		setHeight(2*getHeight());
		setWidth(2*getWidth());
		setImage(newImage);
	}



	public void halfsize(){
		int i,j;
		RGBPixel pixel;
		int red,green,blue;
		setHeight(height/2);
		setWidth(width/2);
		RGBPixel[][] newImage  = new RGBPixel[height][width];
		for(i=0 ;i<getHeight() ;i++){
			for(j=0 ;j<getWidth() ;j++){
				pixel = getPixel(i,j);

				red = getPixel(2*i,2*j).getRed();
				green = getPixel(2*i,2*j).getGreen();
				blue = getPixel(2*i,2*j).getBlue();

				red += getPixel(2*i +1,2*j).getRed();
				green += getPixel(2*i +1,2*j).getGreen();
				blue += getPixel(2*i +1,2*j).getBlue();

				red += getPixel(2*i,2*j +1).getRed();
				green += getPixel(2*i,2*j +1).getGreen();
				blue += getPixel(2*i,2*j +1).getBlue();

				red += getPixel(2*i +1,2*j +1).getRed();
				green += getPixel(2*i +1,2*j +1).getGreen();
				blue += getPixel(2*i +1,2*j +1).getBlue();

				red = red/4;
				green = green/4;
				blue = blue/4;
				newImage[i][j] = new RGBPixel((short)red,(short)green,(short)blue);
			}
		}
		setImage(newImage);
	}


	public void rotateCounterClockwise(){
		RGBPixel[][] newImage = new RGBPixel[width][height];
		int i,j;

		for(i=0 ;i<height ;i++){
			for(j=0 ;j<width ;j++){
				newImage[(width-1) - j][i] = getPixel(i,j);
			}
		}
		i = height;
		j = width;
		setWidth(i);
		setHeight(j);
		setImage(newImage);
	}


	public void rotateClockwise(){
		RGBPixel[][] newImage = new RGBPixel[width][height];
		int i,j;

		for(i=0 ;i<height ;i++){
			for(j=0 ;j<width ;j++){
				newImage[j][(height-1) - i] = getPixel(i,j);
			}
		}
		i = height;
		j = width;
		setWidth(i);
		setHeight(j);
		setImage(newImage);
	}








}
