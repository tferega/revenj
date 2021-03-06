package gen.model.test;



public final class CompositeList   implements java.io.Serializable, org.revenj.patterns.Identifiable {
	
	
	@com.fasterxml.jackson.annotation.JsonCreator 
	public CompositeList(
			@com.fasterxml.jackson.annotation.JsonProperty("URI")  final String URI,
			@com.fasterxml.jackson.annotation.JsonProperty("id")  final java.util.UUID id,
			@com.fasterxml.jackson.annotation.JsonProperty("enn")  final gen.model.test.En[] enn,
			@com.fasterxml.jackson.annotation.JsonProperty("en")  final gen.model.test.En en,
			@com.fasterxml.jackson.annotation.JsonProperty("tsl")  final java.util.List<java.time.OffsetDateTime> tsl,
			@com.fasterxml.jackson.annotation.JsonProperty("change")  final java.time.LocalDate change,
			@com.fasterxml.jackson.annotation.JsonProperty("entities")  final java.util.List<gen.model.test.Entity> entities,
			@com.fasterxml.jackson.annotation.JsonProperty("simple")  final gen.model.test.Simple simple) {
			
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.id = id != null ? id : new java.util.UUID(0L, 0L);
		this.enn = enn != null ? enn : new gen.model.test.En[] { };
		org.revenj.Guards.checkNulls(enn);
		this.en = en != null ? en : gen.model.test.En.A;
		this.tsl = tsl != null ? tsl : new java.util.ArrayList<java.time.OffsetDateTime>(4);
		org.revenj.Guards.checkNulls(tsl);
		this.change = change != null ? change : java.time.LocalDate.of(1, 1, 1);
		this.entities = entities != null ? entities : new java.util.ArrayList<gen.model.test.Entity>(4);
		org.revenj.Guards.checkNulls(entities);
		this.simple = simple != null ? simple : new gen.model.test.Simple();
	}

	
	
	public CompositeList() {
			
		this.URI = java.util.UUID.randomUUID().toString();
		this.id = java.util.UUID.randomUUID();
		this.enn = new gen.model.test.En[] { };
		this.en = gen.model.test.En.A;
		this.tsl = new java.util.ArrayList<java.time.OffsetDateTime>(4);
		this.change = java.time.LocalDate.now();
		this.entities = new java.util.ArrayList<gen.model.test.Entity>(4);
		this.simple = new gen.model.test.Simple();
	}

	
	private final String URI;

	
	@com.fasterxml.jackson.annotation.JsonProperty("URI")
	public String getURI()  {
		
		return this.URI;
	}

	
	@Override
	public int hashCode() {
		return URI.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;
		final CompositeList other = (CompositeList) obj;

		return URI.equals(other.URI);
	}

	@Override
	public String toString() {
		return "CompositeList(" + URI + ')';
	}
	private static final long serialVersionUID = 4725101763819715429L;
	
	private final java.util.UUID id;

	
	@com.fasterxml.jackson.annotation.JsonProperty("id")
	public java.util.UUID getId()  {
		
		return this.id;
	}

	
	private final gen.model.test.En[] enn;

	
	@com.fasterxml.jackson.annotation.JsonProperty("enn")
	public gen.model.test.En[] getEnn()  {
		
		return this.enn;
	}

	
	private final gen.model.test.En en;

	
	@com.fasterxml.jackson.annotation.JsonProperty("en")
	public gen.model.test.En getEn()  {
		
		return this.en;
	}

	
	private final java.util.List<java.time.OffsetDateTime> tsl;

	
	@com.fasterxml.jackson.annotation.JsonProperty("tsl")
	public java.util.List<java.time.OffsetDateTime> getTsl()  {
		
		return this.tsl;
	}

	
	private final java.time.LocalDate change;

	
	@com.fasterxml.jackson.annotation.JsonProperty("change")
	public java.time.LocalDate getChange()  {
		
		return this.change;
	}

	
	private final java.util.List<gen.model.test.Entity> entities;

	
	@com.fasterxml.jackson.annotation.JsonProperty("entities")
	public java.util.List<gen.model.test.Entity> getEntities()  {
		
		return this.entities;
	}

	
	private final gen.model.test.Simple simple;

	
	@com.fasterxml.jackson.annotation.JsonProperty("simple")
	public gen.model.test.Simple getSimple()  {
		
		return this.simple;
	}

	

public static class ForSimple   implements java.io.Serializable, org.revenj.patterns.Specification<CompositeList> {
	
	
	
	public ForSimple(
			 final java.util.List<gen.model.test.Simple> simples) {
			
		setSimples(simples);
	}

	
	
	public ForSimple() {
			
		this.simples = new java.util.ArrayList<gen.model.test.Simple>(4);
	}

	private static final long serialVersionUID = -6890494611897344354L;
	
	private java.util.List<gen.model.test.Simple> simples;

	
	@com.fasterxml.jackson.annotation.JsonProperty("simples")
	public java.util.List<gen.model.test.Simple> getSimples()  {
		
		return simples;
	}

	
	public ForSimple setSimples(final java.util.List<gen.model.test.Simple> value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"simples\" cannot be null!");
		org.revenj.Guards.checkNulls(value);
		this.simples = value;
		
		return this;
	}

	
		public boolean test(gen.model.test.CompositeList it) {
			return (this.getSimples().contains(it.getSimple()));
		}
}

}
