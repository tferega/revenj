package org.revenj.postgres.jinq.transform;

import ch.epfl.labos.iu.orm.queryll2.symbolic.TypedValueVisitorException;
import org.revenj.postgres.jinq.jpqlquery.ColumnExpressions;
import org.revenj.postgres.jinq.jpqlquery.JinqPostgresQuery;
import org.revenj.postgres.jinq.jpqlquery.SelectFromWhere;

public class SortingTransform extends RevenjOneLambdaQueryTransform {
	public SortingTransform(RevenjQueryTransformConfiguration config, boolean isAscending) {
		super(config);
		this.isAscending = isAscending;
	}

	private boolean isAscending;

	@Override
	public <U, V> JinqPostgresQuery<U> apply(JinqPostgresQuery<V> query, LambdaAnalysis lambda, SymbExArgumentHandler parentArgumentScope) throws QueryTransformException {
		try {
			if (query instanceof SelectFromWhere && query.canSort()) {
				SelectFromWhere<V> sfw = (SelectFromWhere<V>) query;
				SymbExToColumns translator = config.newSymbExToColumns(SelectFromWhereLambdaArgumentHandler.fromSelectFromWhere(sfw, lambda, config.metamodel, parentArgumentScope, false));

				ColumnExpressions<U> returnExpr = makeSelectExpression(translator, lambda);

				// Create the new query, merging in the analysis of the method
				SelectFromWhere<U> toReturn = (SelectFromWhere<U>) sfw.shallowCopy();
				SelectFromWhere.SortingParameters sort = new SelectFromWhere.SortingParameters();
				sort.expr = returnExpr.getOnlyColumn();
				sort.isAscending = isAscending;
				toReturn.sort.add(0, sort);
				return toReturn;
			}
			throw new QueryTransformException("Existing query cannot be transformed further");
		} catch (TypedValueVisitorException e) {
			throw new QueryTransformException(e);
		}
	}

	@Override
	public String getTransformationTypeCachingTag() {
		return SortingTransform.class.getName();
	}
}
