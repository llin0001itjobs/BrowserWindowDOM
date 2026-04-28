package org.llin.demo.browserDOM.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "dev")
@Profile("dev")
@Validated
public class DevAppProperties {

	public static class Spring {

		private Security security = new Security();

		public static class Security {

			private Oauth2 oauth2 = new Oauth2();

			public static class Oauth2 {
				private Client client = new Client();

				public static class Client {
					private Registration registration = new Registration();

					public static class Registration {
						
						private Github github = new Github();

						public static class Github {

							private String redirectUri;

							public String getRedirectUri() {
								return redirectUri;
							}

							public void setRedirectUri(String redirectUri) {
								this.redirectUri = redirectUri;
							}

							@Override
							public String toString() {
								return "Github [redirectUri=" + redirectUri + "]";
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

					public Registration getRegistration() {
						return registration;
					}

					public void setRegistration(Registration registration) {
						this.registration = registration;
					}

					@Override
					public String toString() {
						return "Client [registration=" + registration + "]";
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

			public Oauth2 getOauth2() {
				return oauth2;
			}

			public void setOauth2(Oauth2 oauth2) {
				this.oauth2 = oauth2;
			}

			@Override
			public String toString() {
				return "Security [oauth2=" + oauth2 + "]";
			}
		}

		public Security getSecurity() {
			return security;
		}

		public void setSecurity(Security security) {
			this.security = security;
		}

	}

}
