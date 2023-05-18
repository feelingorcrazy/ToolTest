package watch.properties;

import cn.hutool.core.io.resource.ResourceUtil;

import java.io.IOException;
import java.util.Properties;

/**
 * @author VV
 * @date 2023/5/18
 */
public class PathProperties {
	private static final Properties propertie = new Properties();

	static {
		try {
			propertie.load(ResourceUtil.getStream("path.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static String get(String str) {
		return propertie.get(str).toString();
	}
}
