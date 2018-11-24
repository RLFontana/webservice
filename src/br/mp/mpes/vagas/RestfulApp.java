package br.mp.mpes.vagas;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class RestfulApp extends Application{
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	static String path;

	public RestfulApp() {
		
		this.singletons.add(new MPVagasWS());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return this.empty;
	}

	@Override
	public Set<Object> getSingletons() {
		return this.singletons;
	}
}
