package util.decompression;

import java.io.InputStream;

/**
 * @author VV
 * @date 2023/5/17
 */
@FunctionalInterface
public interface Decompression<R,T>{
	void apply(R r,T t) throws Exception;
}
