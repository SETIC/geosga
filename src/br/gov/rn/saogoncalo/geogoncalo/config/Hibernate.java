package br.gov.rn.saogoncalo.geogoncalo.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.gov.rn.saoconcalo.geogoncalo.models.Bairro;
import br.gov.rn.saoconcalo.geogoncalo.models.Categoria;
import br.gov.rn.saoconcalo.geogoncalo.models.Credencial;
import br.gov.rn.saoconcalo.geogoncalo.models.Funcionario;
import br.gov.rn.saoconcalo.geogoncalo.models.Imagem;
import br.gov.rn.saoconcalo.geogoncalo.models.Marcador;
import br.gov.rn.saoconcalo.geogoncalo.models.Obra;
import br.gov.rn.saoconcalo.geogoncalo.models.Status;
import br.gov.rn.saoconcalo.geogoncalo.models.Tipo;

public class Hibernate{
	private static Hibernate hibernate;
	private SessionFactory sessionFactory;
	private Configuration configuration;

	public Hibernate() {
		setSession();
	}
	
	@SuppressWarnings("deprecation")
	private void setSession() {
		if (sessionFactory == null) {
			try {
				configuration = new Configuration()
						.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
						.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
						.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/db_sao_goncalo")
						.setProperty("hibernate.connection.username", "postgres")
						.setProperty("hibernate.connection.password", "123456")				 
						.setProperty("hibernate.show_sql", "false")
						.setProperty("hibernate.format_sql", "true")
						.setProperty("hibernate.c3p0.acquire_increment", "1")
						.setProperty("hibernate.c3p0.idle_test_period", "100")
						.setProperty("hibernate.c3p0.maxIdleTime", "300")
						.setProperty("hibernate.c3p0.max_size", "75")
						.setProperty("hibernate.c3p0.max_statements", "0")
						.setProperty("hibernate.c3p0.min_size", "20")
						.setProperty("hibernate.c3p0.timeout", "180")
						.setProperty("hibernate.cache.user_query_cache", "false")
						.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false")
						.addAnnotatedClass(Bairro.class)
						.addAnnotatedClass(Categoria.class)
						.addAnnotatedClass(Imagem.class)
						.addAnnotatedClass(Marcador.class)
						.addAnnotatedClass(Obra.class)
						.addAnnotatedClass(Tipo.class)
						.addAnnotatedClass(Status.class)
						.addAnnotatedClass(Credencial.class)
						.addAnnotatedClass(Funcionario.class);

				sessionFactory = configuration.buildSessionFactory();
			} catch (Throwable ex) {
				System.err.println("Houve um erro na criação da sessão hibernate. Erro: " + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
	}
	
	public Session getSession() {
		setSession();
		Session toReturn = sessionFactory.openSession();
		return toReturn;
	}

	public static Hibernate getInstance() {
		if (hibernate == null) {
			hibernate = new Hibernate();
		}
		return hibernate;
	}
}
