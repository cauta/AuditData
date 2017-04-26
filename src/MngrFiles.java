import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class MngrFiles {
	public static final String folderData = "src\\DataSet\\";
	public static final String folderEncrypted = "src\\Encrypted\\";
	public static final String folderDecrypted = "src\\Decrypted\\";
	File _fileResult = null;
	FileOutputStream _fos = null;
	
	public MngrFiles(){
		
	}
	
	public int getFileList(String folderData, Vector<String> fileList){
		//fileList = new Vector<String>();
		
		//get file from folder
		File f = new File(folderData);
		File[] listOfFiles = f.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				fileList.add(listOfFiles[i].getName());
				//System.out.println("File " + fileList.lastElement());
			}
			else if (listOfFiles[i].isDirectory()) {
				//System.out.println("Directory " + listOfFiles[i].getName());
			}
			
		}
		return fileList.size();
	}
	
	public void writeResultCsv() throws IOException {
		_fileResult = new File("Result.csv");
		_fos		= new FileOutputStream(_fileResult);
		String buffer = "File name, MAC, Probvector\n";
		byte[] bytes = buffer.getBytes();
		int numfile = Source.fileList.size();
		
		if (!_fileResult.exists()) {
			_fileResult.createNewFile();
		}
		
		_fos.write(bytes);
		_fos.flush();
		
		for(int i = 0; i < numfile; i++) {
			buffer = Source.fileList.get(i) + ", " + Source.MAC.get(i) + ", " + Source.ProbVector.get(i) +"\n";
			bytes = buffer.getBytes();
			_fos.write(bytes);
			_fos.flush();
		}
		
		_fos.close();
	}
}
