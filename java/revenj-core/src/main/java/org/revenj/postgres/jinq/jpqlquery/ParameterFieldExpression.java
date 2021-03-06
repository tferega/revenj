package org.revenj.postgres.jinq.jpqlquery;

public class ParameterFieldExpression extends Expression {
	private int lambdaIndex;
	private String fieldName;

	public ParameterFieldExpression(int lambdaIndex, String fieldName) {
		this.lambdaIndex = lambdaIndex;
		this.fieldName = fieldName;
	}

	@Override
	public void generateQuery(QueryGenerationState queryState, OperatorPrecedenceLevel operatorPrecedenceScope) {
		//TODO switch to Postgres $ so params can be reused
		queryState.registerParameter(this, lambdaIndex, fieldName);
		queryState.appendQuery("?");
	}

	@Override
	public void prepareQueryGeneration(
			QueryGenerationPreparationPhase preparePhase,
			QueryGenerationState queryState) {
		// Nothing to do.
	}

	@Override
	public boolean equals(Object obj) {
		if (!getClass().equals(obj.getClass())) return false;
		ParameterFieldExpression o = (ParameterFieldExpression) obj;
		return lambdaIndex == o.lambdaIndex && fieldName.equals(o.fieldName);
	}

	@Override
	public void visit(ExpressionVisitor visitor) {
		visitor.visitParameterField(this);
	}
}
