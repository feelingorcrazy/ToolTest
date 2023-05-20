package util.decompression;

import util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author VV
 * @date 2023/5/17
 */
public class VueDecompression {
	public static String path = "";
	public static void out(File file, String outPath) throws IOException {
		InputStream is = new FileInputStream(file);
		File file1 = new File(outPath);
		file1.mkdirs();
		FileUtil.writeFile(is, path + "/" +file.getName());
		is.close();
		System.out.println("vue移动成功");
	}
}
