package org.zynick.ourtime.ourtime;

import java.io.InputStream;
import java.util.List;

/**
 * Created by zynick on 2014/12/30.
 */
public interface EventParser {
    /**
     * 解析输入流 得到event对象集合
     */
    public List<event> parse(InputStream is) throws Exception;

    /**
     * 序列化event对象集合得到xml字符串
     */
    public String serialize(List<event> events) throws Exception;
}
