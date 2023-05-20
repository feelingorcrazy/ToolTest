package util.decompression;

import cn.hutool.core.util.StrUtil;
import util.FileUtil;
import watch.properties.PathProperties;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author VV
 * @date 2023/5/17
 */
public class JavaDecompression {
	public static Map<String, String> filePathMap = new HashMap<>();

	static {
		filePathMap.put("ServiceImpl.java", PathProperties.get("ServiceImpl.java"));
		filePathMap.put("Service.java", PathProperties.get("Service.java"));
		filePathMap.put("Mapper.java", PathProperties.get("Mapper.java"));
		filePathMap.put("null", PathProperties.get("null"));
		filePathMap.put("Controller.java", PathProperties.get("Controller.java"));
	}

	public static void out(File file, String outPath) throws IOException {
		String fileName = file.getName();

		InputStream is = new FileInputStream(file);
		FileReader fileReader = new FileReader(file);

		String entityName = "";
		Optional<String> optional = filePathMap.keySet().parallelStream().filter(str -> fileName.endsWith(str)).findAny();
		if (optional.isPresent()) {
			outPath = filePathMap.get(optional.get());
		} else {
			outPath = filePathMap.get("null");
			entityName = file.getName().replace(".java", "");
		}
		File file1 = new File(outPath);
		file1.mkdirs();

		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		String txt;
		while ((txt = raf.readLine()) != null) {
			Matcher matcher = Pattern.compile("List<\\s*(\\w+)\\s*>").matcher(txt);
			if (matcher.find()) {
				entityName = matcher.group(0).replace("List<", "").replace(">", "");
			}
		}

		file1 = new File(PathProperties.get("vue") +"/"+ StrUtil.toUnderlineCase(entityName.replace("Rc","" )));
		file1.mkdirs();
		VueDecompression.path = PathProperties.get("vue") +"/"+ StrUtil.toUnderlineCase(entityName.replace("Rc","" ));
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		File outFile = new File(outPath + "/" + file.getName());
		FileWriter fileWriter = new FileWriter(outFile);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);            // 逐行读取文件并替换文本
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains(".front.domain")) {
				line = line.replaceAll(".domain", ".domain.entity");
			}
			bufferedWriter.write(line);
			bufferedWriter.newLine();
		}            // 关闭文件

		bufferedReader.close();
		bufferedWriter.close();


		is.close();
		raf.close();
		System.out.println("java移动成功");
	}
}
