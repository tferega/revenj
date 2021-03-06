package org.revenj.postgres.jinq;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import org.revenj.patterns.Query;
import org.revenj.postgres.QueryProvider;
import org.revenj.postgres.jinq.jpqlquery.JinqPostgresQuery;

import org.revenj.patterns.DataSource;
import org.revenj.patterns.ServiceLocator;
import org.revenj.postgres.jinq.transform.MetamodelUtil;

final class RevenjQueryProvider implements QueryProvider {
	private final MetamodelUtil metamodel;
	private final javax.sql.DataSource dataSource;
	private final RevenjQueryComposerCache cachedQueries = new RevenjQueryComposerCache();

	public RevenjQueryProvider(MetamodelUtil metamodel, javax.sql.DataSource dataSource) {
		this.metamodel = metamodel;
		this.dataSource = dataSource;
	}

	public <T extends DataSource> Query<T> query(Connection connection, ServiceLocator locator, Class<T> manifest) {
		return RevenjQueryComposer.findAll(
				metamodel,
				manifest,
				cachedQueries,
				connection,
				locator,
				this::getFromDataSource,
				Connection::close);
	}

	private Connection getFromDataSource() throws SQLException {
		return dataSource.getConnection();
	}
}
