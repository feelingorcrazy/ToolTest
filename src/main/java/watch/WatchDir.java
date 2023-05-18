package watch;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.watchers.DelayWatcher;
import util.decompression.*;
import watch.properties.PathProperties;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author VV
 * @date 2023/5/17
 */
public class WatchDir {

	public static Map<String, Decompression<File, String>> map = new HashMap();
	static {
		map.put("zip", ZipDecompression::out);
		map.put("vue", VueDecompression::out);
		map.put("js", JsDecompression::out);
		map.put("sql", SqlDecompression::out);
		map.put("java", JavaDecompression::out);
		map.put("xml", XmlDecompression::out);
	}
	public static Map<String, String> mapPath = new HashMap();
	static {
		mapPath.put("vue", PathProperties.get("vue"));
		mapPath.put("js",PathProperties.get("js"));
		mapPath.put("sql",PathProperties.get("sql"));
		mapPath.put("xml",PathProperties.get("xml"));
		mapPath.put("java","java");
	}

	public static void watch(String dirPath, String outPath) {
		WatchMonitor.createAll(new File(dirPath), new DelayWatcher(new SimpleWatcher() {
			@Override
			public void onCreate(WatchEvent<?> event, Path currentPath) {
				try {
					WatchMonitor.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				File file = new File(dirPath + "/" + event.context());
				String type = FileTypeUtil.getType(file);
				if (map.containsKey(type)) {
					try {
						map.get(type).apply(file, outPath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				//删除该文件
				file.delete();
			}
		}, 1000)).start();
	}


	public static void move(String dirPath) {
		WatchMonitor.createAll(new File(dirPath), new DelayWatcher(new SimpleWatcher() {
			@Override
			public void onCreate(WatchEvent<?> event, Path currentPath) {
				try {
					WatchMonitor.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();

				}
				File file = new File(dirPath + "/" + event.context());
				String type = FileTypeUtil.getType(file);
				if (map.containsKey(type)) {
					try {
						map.get(type).apply(file, mapPath.get(type));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				//删除该文件
				file.delete();
			}
		}, 1000)).start();
	}


}
