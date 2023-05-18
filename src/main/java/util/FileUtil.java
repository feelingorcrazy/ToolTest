package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author VV
 * @date 2023/5/17
 */
public class FileUtil {
	public static void writeFile(InputStream inputStream, String path){
		try (OutputStream outputStream = new FileOutputStream(path)) {
			byte[] bytes = new byte[2048];
			int len;
			while ((len = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, len);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
