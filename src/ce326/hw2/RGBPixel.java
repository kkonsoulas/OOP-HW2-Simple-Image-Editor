package ce326.hw2;

public class RGBPixel{
	private static final short MAX_COLOR = 255;
	private byte red;
	private byte green;
	private byte blue;

	private static short clip(int num){
		if(num<0){
			return 0;
		}
		else if(num > 255){
			return 255;
		}
		else{
			return (short) num;
		}
	}

	public RGBPixel(short red, short green, short blue){
		setRGB(red,green,blue);
	}

	public RGBPixel(RGBPixel pixel){
		setRGB(pixel.getRed(),pixel.getGreen(),pixel.getBlue());

	}

	public  RGBPixel(YUVPixel pixel){
		short c = (short) (pixel.getY() - 16);
		short d = (short) (pixel.getU() -128);
		short e =  (short) (pixel.getV() -128);

		setRed(clip(((298*c +409*e + 128)>>8)));
		setGreen(clip(((298*c -100*d -208*e + 128)>>8)));
		setBlue(clip(((298*c + 516*d + 128)>>8)));
	}

	public void setRed(byte red){
		this.red = red;
	}

	public void setRed(short red){
		if(red <= MAX_COLOR && red >= 0)
			this.red = (byte)  (red -128);
		else
			System.out.println("could not set red, wrong value: "+ red);
	}


	public void setGreen(byte green){
		this.green = green;
	}

	public void setGreen(short green){
		if(green <= MAX_COLOR && green >= 0)
			this.green = (byte)  (green -128);
		else
			System.out.println("could not set red, wrong value: "+ green);
	}



	public void setBlue(byte blue){
		this.blue = blue;
	}

	public void setBlue(short blue){
		if(blue <= MAX_COLOR && blue >= 0)
			this.blue = (byte)  (blue -128);
		else
			System.out.println("could not set red, wrong value: "+ blue);
	}


	public short getRed(){
		return (short) (red + 128);
	}
	public short getGreen(){
		return (short) (green + 128);
	}
	public short getBlue(){
		return (short) (blue + 128);
	}

	public int getRGB(){
		int rgb = 0;
		int color;

		color = getBlue();
		rgb = rgb | color;

		color = getGreen();
		rgb = rgb | (color << 8);

		color = getRed();
		rgb = rgb | (color << 16);

		// rgb: 0x 12  34   56   78
		//	      (0) red green blue
		return  rgb;
	}

	public final void setRGB(short red, short green, short blue){
		setRed(red);
		setGreen(green);
		setBlue(blue);
	}



	public String toString(){
		return String.format("%d %d %d",getRed(),getGreen(),getBlue());
	}

}
