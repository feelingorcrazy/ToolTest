package util.decompression;

import util.FileUtil;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author VV
 * @date 2023/5/17
 */
public class ZipDecompression {
	public static void out(File file, String outPath) throws IOException {
		InputStream input = new FileInputStream(file);
		ZipInputStream zipInputStream = new ZipInputStream(input);
		ZipEntry entry;
		while ((entry = zipInputStream.getNextEntry()) != null) {
			if (!entry.isDirectory()) {
				String filePath = entry.getName();
				if (filePath.contains("/")) {
					filePath = filePath.substring(entry.getName().lastIndexOf("/") + 1);
				}
				FileUtil.writeFile(zipInputStream, outPath + "/" + filePath);
			} else {
				System.out.println("-----文件夹");
			}
		}
		input.close();
		zipInputStream.closeEntry();
		System.out.println("zip解压成功");
	}
}
