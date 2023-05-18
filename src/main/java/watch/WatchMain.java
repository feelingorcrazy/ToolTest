package watch;

import watch.properties.PathProperties;

/**
 * @author VV
 * @date 2023/5/15
 */
public class WatchMain {
	public static final String dirPath = PathProperties.get("dirPath");
	public static final String outPath = PathProperties.get("outPath");
	public static void main(String[] args) {
		WatchDir.watch(dirPath,outPath);
		WatchDir.move(outPath);
		System.out.println("文件夹监听启动成功");
	}
}
