package ce326.hw2;

public class YUVPixel{
	private short y;
	private short u;
	private short v;


	public YUVPixel(short y,short u,short v){
		this.y =y;
		this.u = u;
		this.v = v;
	}

	public  YUVPixel(YUVPixel pixel){
		this(pixel.y, pixel.u, pixel.v);
	}

	public YUVPixel(RGBPixel pixel){
		y = (short) (((66*pixel.getRed() + 129* pixel.getGreen() + 25* pixel.getBlue() +128) >> 8) + 16);
		u = (short) (((-38*pixel.getRed()  - 74* pixel.getGreen() + 112* pixel.getBlue() +128) >> 8) + 128);
		v = (short) (((112*pixel.getRed() - 94* pixel.getGreen() - 18* pixel.getBlue() +128) >> 8) + 128);

	}

	public short getY() {
		return y;
	}
	public short getU() {
		return u;
	}
	public short getV() {
		return v;
	}

	public void setY(short y){
		this.y = y;
	}
	public void setU(short u) {
		this.u = u;
	}
	public void setV(short v) {
		this.v = v;
	}

	public String toString(){
		return String.format("%d %d %d",getY(),getU(),getV());
	}

}
