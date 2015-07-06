package org.revenj.extensibility;

import java.lang.reflect.GenericArrayType;
import java.util.List;

public interface PluginLoader {
	<T> List<Class<T>> find(Class<T> manifest) throws Exception;

	default <T> T[] resolve(Container container, Class<T> manifest) throws Exception {
		try (Container scope = container.createScope()) {
			List<Class<T>> manifests = find(manifest);
			for (Class<T> sc : manifests) {
				scope.registerClass(manifest, sc, false);
			}
			return (T[]) scope.resolve((GenericArrayType) () -> manifest);
		}
	}
}
