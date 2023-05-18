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
	public static void out(File file, String outPath) throws IOException {
		InputStream is = new FileInputStream(file);
		File file1 = new File(outPath);
		file1.mkdirs();
		File[] files = file1.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				f.mkdir();
				outPath = outPath + "/" + f.getName();
			}
		}

		FileUtil.writeFile(is, outPath + "/" +file.getName());
		is.close();
		System.out.println("vue移动成功");
	}
}
