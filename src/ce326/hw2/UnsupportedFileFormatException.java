package ce326.hw2;

public class UnsupportedFileFormatException extends java.lang.Exception{
	String msg;

	public UnsupportedFileFormatException(){
		super();
	}

	public UnsupportedFileFormatException(String msg){
		super(msg);
	}

	@Override
	public String toString() {
		return "UnsupportedFileFormatException{" +
				"msg='" + msg + '\'' +
				'}';
	}
}
