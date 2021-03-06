package org.revenj.postgres.jinq;

import ch.epfl.labos.iu.orm.queryll2.symbolic.MethodSignature;
import org.revenj.extensibility.Container;
import org.revenj.postgres.QueryProvider;
import org.revenj.postgres.jinq.transform.MetamodelUtil;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;

public class JinqMetaModel extends MetamodelUtil {

	private static final HashMap<Class<?>, String> classSources = new HashMap<>();
	private static final HashMap<String, String> stringSources = new HashMap<>();

	public JinqMetaModel() {
		safeMethods.add(new MethodSignature("java/lang/ThreadLocal", "get", "()Ljava/lang/Object;"));
	}

	public static JinqMetaModel configure(Container container) {
		org.revenj.postgres.jinq.JinqMetaModel metamodel = new org.revenj.postgres.jinq.JinqMetaModel();
		container.registerInstance(MetamodelUtil.class, metamodel, false);
		DataSource dataSource = container.resolve(DataSource.class);
		container.registerInstance(QueryProvider.class, new RevenjQueryProvider(metamodel, dataSource), false);
		return metamodel;
	}

	public void registerProperty(Class<?> clazz, String methodName, String property) throws IOException {
		try {
			addProperty(clazz.getMethod(methodName), property);
		} catch (NoSuchMethodException e) {
			throw new IOException(e);
		}
	}

	public void registerDataSource(Class<?> clazz, String dataSource) {
		classSources.put(clazz, dataSource);
		stringSources.put(clazz.getCanonicalName(), dataSource);
	}

	@Override
	public <U> String dataSourceNameFromClass(Class<U> dataSource) {
		return classSources.get(dataSource);
	}

	@Override
	public String dataSourceNameFromClassName(String className) {
		return stringSources.get(className);
	}
}
