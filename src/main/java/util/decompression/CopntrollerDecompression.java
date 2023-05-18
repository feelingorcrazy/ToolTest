package util.decompression;

import util.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author VV
 * @date 2023/5/17
 */
public class CopntrollerDecompression {
	public static void out(InputStream inputStream, String path) throws IOException {
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		ZipEntry entry;
		while ((entry = zipInputStream.getNextEntry()) != null) {
			if (!entry.isDirectory()) {
				String filePath = entry.getName();
				if (filePath.contains("/")) {
					filePath = filePath.substring(entry.getName().lastIndexOf("/") + 1);
				}
				FileUtil.writeFile(zipInputStream, path + "/" + filePath);
			} else {
				System.out.println("-----文件夹");
			}
		}
		zipInputStream.closeEntry();
		System.out.println("controller移动成功");
	}
}
