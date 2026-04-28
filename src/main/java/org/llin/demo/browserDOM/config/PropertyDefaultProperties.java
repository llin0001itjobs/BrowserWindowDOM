package org.llin.demo.browserDOM.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "")
@Validated
public class PropertyDefaultProperties {

	@NotNull
	private App app = new App();
	
	@NotNull
	private Server server = new Server();

	@NotNull
	private Spring spring = new Spring();
	
	private Logging logging = new Logging();

	public static class App {
		private Mail mail = new Mail();
					
		public static class Mail {
			
			private Subject subject = new Subject();
			private Text text = new Text();
			
			public static class Subject {
				
				private String registered;
				
				private String verified;

				public String getRegistered() {
					return registered;
				}

				public void setRegistered(String registered) {
					this.registered = registered;
				}

				public String getVerified() {
					return verified;
				}

				public void setVerified(String verified) {
					this.verified = verified;
				}
				
			}
			
			public static class Text {
				
				private String registered;
				
				private String verified;

				public String getRegistered() {
					return registered;
				}

				public void setRegistered(String registered) {
					this.registered = registered;
				}

				public String getVerified() {
					return verified;
				}

				public void setVerified(String verified) {
					this.verified = verified;
				}
				
			}

			public Subject getSubject() {
				return subject;
			}

			public void setSubject(Subject subject) {
				this.subject = subject;
			}

			public Text getText() {
				return text;
			}

			public void setText(Text text) {
				this.text = text;
			}
			
		}

		public Mail getMail() {
			return mail;
		}

		public void setMail(Mail mail) {
			this.mail = mail;
		}
	}
	
	public static class Server {
		private String port;
		private Servlet servlet = new Servlet();

		public static class Servlet {
			private String contextPath;

			public String getContextPath() {
				return contextPath;
			}

			public void setContextPath(String contextPath) {
				this.contextPath = contextPath;
			}

			@Override
			public String toString() {
				return "Servlet [contextPath=" + contextPath + "]";
			}
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public Servlet getServlet() {
			return servlet;
		}

		public void setServlet(Servlet servlet) {
			this.servlet = servlet;
		}

		@Override
		public String toString() {
			return "Server [port=" + port + ", servlet=" + servlet + "]";
		}
	}

	public static class Spring {
		private Profiles profiles = new Profiles();
		private Datasource datasource = new Datasource();
		private Jpa jpa = new Jpa();
		private Mail mail = new Mail();
		private Security security = new Security();
		private Thymeleaf thymeleaf = new Thymeleaf();

		public static class Profiles {
			private String active;

			public String getActive() {
				return active;
			}

			public void setActive(String active) {
				this.active = active;
			}

			@Override
			public String toString() {
				return "Profiles [active=" + active + "]";
			}
		}

		public static class Datasource {
			@NotNull
			private String driverClassName;
			@NotNull
			private String url;
			@NotNull
			private String username;
			@NotNull
			private String password;

			public String getDriverClassName() {
				return driverClassName;
			}

			public void setDriverClassName(String driverClassName) {
				this.driverClassName = driverClassName;
			}

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}

			@Override
			public String toString() {
				return "Datasource [driverClassName=" + driverClassName + ", url=" + url + ", username=" + username
						+ ", password=" + password + "]";
			}
		}

		public static class Jpa {
			private boolean showSql;
			private Hibernate hibernate = new Hibernate();
			private Properties properties = new Properties();

			public static class Hibernate {
				private String ddlAuto;

				public String getDdlAuto() {
					return ddlAuto;
				}

				public void setDdlAuto(String ddlAuto) {
					this.ddlAuto = ddlAuto;
				}

				@Override
				public String toString() {
					return "Hibernate [ddlAuto=" + ddlAuto + "]";
				}
			}

			public static class Properties {
				private Hibernate hibernate = new Hibernate();

				public static class Hibernate {
					private String dialect;

					public String getDialect() {
						return dialect;
					}

					public void setDialect(String dialect) {
						this.dialect = dialect;
					}

					@Override
					public String toString() {
						return "Hibernate [dialect=" + dialect + "]";
					}
				}

				public Hibernate getHibernate() {
					return hibernate;
				}

				public void setHibernate(Hibernate hibernate) {
					this.hibernate = hibernate;
				}

				@Override
				public String toString() {
					return "Properties [hibernate=" + hibernate + "]";
				}
			}

			public boolean isShowSql() {
				return showSql;
			}

			public void setShowSql(boolean showSql) {
				this.showSql = showSql;
			}

			public Hibernate getHibernate() {
				return hibernate;
			}

			public void setHibernate(Hibernate hibernate) {
				this.hibernate = hibernate;
			}

			public Properties getProperties() {
				return properties;
			}

			public void setProperties(Properties properties) {
				this.properties = properties;
			}

			@Override
			public String toString() {
				return "Jpa [showSql=" + showSql + ", hibernate=" + hibernate + ", properties=" + properties + "]";
			}
		}

		public static class Mail {
			private String host;
			private String port;
			private String username;
			private String password;
			private Properties properties = new Properties();

			public static class Properties {
				private MailSmtp mailSmtp = new MailSmtp();

				public static class MailSmtp {

					private boolean auth;
					private Starttls starttls;

					public static class Starttls {
						private boolean enable;

						public boolean isEnable() {
							return enable;
						}

						public void setEnable(boolean enable) {
							this.enable = enable;
						}

						@Override
						public String toString() {
							return "Starttls [enable=" + enable + "]";
						}

					}

					public boolean isAuth() {
						return auth;
					}

					public void setAuth(boolean auth) {
						this.auth = auth;
					}

					public Starttls getStarttls() {
						return starttls;
					}

					public void setStarttls(Starttls starttls) {
						this.starttls = starttls;
					}

					@Override
					public String toString() {
						return "MailSmtp [auth=" + auth + ", starttls=" + starttls + "]";
					}

				}

				public MailSmtp getMail() {
					return mailSmtp;
				}

				public void setMail(MailSmtp mailSmtp) {
					this.mailSmtp = mailSmtp;
				}

				@Override
				public String toString() {
					return "Properties [mailSmtp=" + mailSmtp + "]";
				}

			}

			public String getHost() {
				return host;
			}

			public void setHost(String host) {
				this.host = host;
			}

			public String getPort() {
				return port;
			}

			public void setPort(String port) {
				this.port = port;
			}

			public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}

			public Properties getProperties() {
				return properties;
			}

			public void setProperties(Properties properties) {
				this.properties = properties;
			}

			@Override
			public String toString() {
				return "Mail [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password
						+ ", properties=" + properties + "]";
			}
		}

		public static class Security {
			private boolean debug;
			private User user = new User();
			private Oauth2 oauth2 = new Oauth2();

			public static class User {
				private String name;
				private String password;

				public String getName() {
					return name;
				}

				public void setName(String name) {
					this.name = name;
				}

				public String getPassword() {
					return password;
				}

				public void setPassword(String password) {
					this.password = password;
				}

				@Override
				public String toString() {
					return "User [name=" + name + ", password=" + password + "]";
				}
			}

			public static class Oauth2 {
				private Client client = new Client();

				public static class Client {
					private Registration registration = new Registration();
					private Provider provider = new Provider();

					public static class Registration {
						private Github github = new Github();

						public static class Github {
							private String clientId;
							private String clientSecret;
							private String scope;
							private String authorizationGrantType;
							private String redirectUri;

							public String getClientId() {
								return clientId;
							}

							public void setClientId(String clientId) {
								this.clientId = clientId;
							}

							public String getClientSecret() {
								return clientSecret;
							}

							public void setClientSecret(String clientSecret) {
								this.clientSecret = clientSecret;
							}

							public String getScope() {
								return scope;
							}

							public void setScope(String scope) {
								this.scope = scope;
							}

							public String getAuthorizationGrantType() {
								return authorizationGrantType;
							}

							public void setAuthorizationGrantType(String authorizationGrantType) {
								this.authorizationGrantType = authorizationGrantType;
							}

							public String getRedirectUri() {
								return redirectUri;
							}

							public void setRedirectUri(String redirectUri) {
								this.redirectUri = redirectUri;
							}

							@Override
							public String toString() {
								return "Github [clientId=" + clientId + ", clientSecret=" + clientSecret + ", scope="
										+ scope + ", authorizationGrantType=" + authorizationGrantType
										+ ", redirectUri=" + redirectUri + "]";
							}
						}

						public Github getGithub() {
							return github;
						}

						public void setGithub(Github github) {
							this.github = github;
						}

						@Override
						public String toString() {
							return "Registration [github=" + github + "]";
						}
					}

					public static class Provider {
						private Github github = new Github();

						public static class Github {
							private String authorizationUri;
							private String tokenUri;
							private String userInfoUri;
							private String userNameAttribute;

							public String getAuthorizationUri() {
								return authorizationUri;
							}

							public void setAuthorizationUri(String authorizationUri) {
								this.authorizationUri = authorizationUri;
							}

							public String getTokenUri() {
								return tokenUri;
							}

							public void setTokenUri(String tokenUri) {
								this.tokenUri = tokenUri;
							}

							public String getUserInfoUri() {
								return userInfoUri;
							}

							public void setUserInfoUri(String userInfoUri) {
								this.userInfoUri = userInfoUri;
							}

							public String getUserNameAttribute() {
								return userNameAttribute;
							}

							public void setUserNameAttribute(String userNameAttribute) {
								this.userNameAttribute = userNameAttribute;
							}

							@Override
							public String toString() {
								return "Github [authorizationUri=" + authorizationUri + ", tokenUri=" + tokenUri
										+ ", userInfoUri=" + userInfoUri + ", userNameAttribute=" + userNameAttribute
										+ "]";
							}
						}

						public Github getGithub() {
							return github;
						}

						public void setGithub(Github github) {
							this.github = github;
						}

						@Override
						public String toString() {
							return "Provider [github=" + github + "]";
						}
					}

					public Registration getRegistration() {
						return registration;
					}

					public void setRegistration(Registration registration) {
						this.registration = registration;
					}

					public Provider getProvider() {
						return provider;
					}

					public void setProvider(Provider provider) {
						this.provider = provider;
					}

					@Override
					public String toString() {
						return "Client [registration=" + registration + ", provider=" + provider + "]";
					}
				}

				public Client getClient() {
					return client;
				}

				public void setClient(Client client) {
					this.client = client;
				}

				@Override
				public String toString() {
					return "Oauth2 [client=" + client + "]";
				}
			}

			public boolean isDebug() {
				return debug;
			}

			public void setDebug(boolean debug) {
				this.debug = debug;
			}

			public User getUser() {
				return user;
			}

			public void setUser(User user) {
				this.user = user;
			}

			public Oauth2 getOauth2() {
				return oauth2;
			}

			public void setOauth2(Oauth2 oauth2) {
				this.oauth2 = oauth2;
			}

			@Override
			public String toString() {
				return "Security [debug=" + debug + ", user=" + user + ", oauth2=" + oauth2 + "]";
			}
		}

		public static class Thymeleaf {
			private String prefix;
			private String suffix;
			private String mode;
			private String encoding;

			public String getPrefix() {
				return prefix;
			}

			public void setPrefix(String prefix) {
				this.prefix = prefix;
			}

			public String getSuffix() {
				return suffix;
			}

			public void setSuffix(String suffix) {
				this.suffix = suffix;
			}

			public String getMode() {
				return mode;
			}

			public void setMode(String mode) {
				this.mode = mode;
			}

			public String getEncoding() {
				return encoding;
			}

			public void setEncoding(String encoding) {
				this.encoding = encoding;
			}

			@Override
			public String toString() {
				return "Thymeleaf [prefix=" + prefix + ", suffix=" + suffix + ", mode=" + mode + ", encoding="
						+ encoding + "]";
			}
		}

		public Profiles getProfiles() {
			return profiles;
		}

		public void setProfiles(Profiles profiles) {
			this.profiles = profiles;
		}

		public Datasource getDatasource() {
			return datasource;
		}

		public void setDatasource(Datasource datasource) {
			this.datasource = datasource;
		}

		public Jpa getJpa() {
			return jpa;
		}

		public void setJpa(Jpa jpa) {
			this.jpa = jpa;
		}

		public Mail getMail() {
			return mail;
		}

		public void setMail(Mail mail) {
			this.mail = mail;
		}

		public Security getSecurity() {
			return security;
		}

		public void setSecurity(Security security) {
			this.security = security;
		}

		public Thymeleaf getThymeleaf() {
			return thymeleaf;
		}

		public void setThymeleaf(Thymeleaf thymeleaf) {
			this.thymeleaf = thymeleaf;
		}

		@Override
		public String toString() {
			return "Spring [profiles=" + profiles + ", datasource=" + datasource + ", jpa=" + jpa + ", mail=" + mail
					+ ", security=" + security + ", thymeleaf=" + thymeleaf + "]";
		}

	}

	public static class Logging {
		private Level level = new Level();

		public static class Level {

			private Org org = new Org();

			public static class Org {
				private Springframework springframework = new Springframework();
				private String thymeleaf;

				public static class Springframework {
					private String security;
					private String securityOauth2;
					private String web;

					public String getSecurity() {
						return security;
					}

					public void setSecurity(String security) {
						this.security = security;
					}

					public String getSecurityOauth2() {
						return securityOauth2;
					}

					public void setSecurityOauth2(String securityOauth2) {
						this.securityOauth2 = securityOauth2;
					}

					public String getWeb() {
						return web;
					}

					public void setWeb(String web) {
						this.web = web;
					}

					@Override
					public String toString() {
						return "Springframework [security=" + security + ", securityOauth2=" + securityOauth2 + ", web="
								+ web + "]";
					}

				}

				public String getThymeleaf() {
					return thymeleaf;
				}

				public void setThymeleaf(String thymeleaf) {
					this.thymeleaf = thymeleaf;
				}

				public Springframework getSpringframework() {
					return springframework;
				}

				public void setSpringframework(Springframework springframework) {
					this.springframework = springframework;
				}

				@Override
				public String toString() {
					return "Org [springframework=" + springframework + ", thymeleaf=" + thymeleaf + "]";
				}

			}

			public Org getOrg() {
				return org;
			}

			public void setOrg(Org org) {
				this.org = org;
			}

			@Override
			public String toString() {
				return "Level [org=" + org + "]";
			}

		}

		public Level getLevel() {
			return level;
		}

		public void setLevel(Level level) {
			this.level = level;
		}

		@Override
		public String toString() {
			return "Logging [level=" + level + "]";
		}

	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}
	
	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public Spring getSpring() {
		return spring;
	}

	public void setSpring(Spring spring) {
		this.spring = spring;
	}

	public Logging getLogging() {
		return logging;
	}

	public void setLogging(Logging logging) {
		this.logging = logging;
	}

	@Override
	public String toString() {
		return "PropertyDefaultProperties [server=" + server + ", spring=" + spring + ", logging=" + logging + "]";
	}

}
