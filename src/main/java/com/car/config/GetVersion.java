package com.car.config;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;


/**
 * @功能描述: 静态资源版本号
 * @项目版本: V1.0.0
 * @项目名称: 借款报销系统
 * @相对路径：.GetVersion.java
 * @创建作者：<a href="ying@leyoujia.com">xieyinyan</a>
 * @创建日期：2019-09-29 15:28:11
 */
public class GetVersion implements TemplateMethodModelEx {

	@Value("${static.dir:}")
	private String staticDir;
	/**初始化系统启动时间，添加版本号参数*/
	private static long  visonIndex;
	static {
		RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
		visonIndex  = bean.getStartTime();
	}
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		SimpleScalar fileName = (SimpleScalar) arguments.get(0);
		long v = getVersion(fileName.getAsString());
		if(fileName.getAsString().contains("?")) {
			return fileName + "&v=" + visonIndex;
		}
		return fileName + "?v=" + visonIndex;
	}

	private long getVersion(String fileName) {
		try {
			return new File(staticDir + "/" + fileName).lastModified();
		} catch (Exception e) {
			return 1;
		}
	}
}
