package org.llin.demo.browserDOM.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyDefaultConfig {

	private final static String HTTP_PROTOCOL = "http://";
	
	@Value("${server.address}")
	private String serverAddress;
		
	@Value("${server.port}")
	private String serverPort;
	
	@Value("${server.servlet.context-path}")
	private String contextPath;
	
	@Value("${spring.data.rest.basePath}")
	private String restBasePath;
	
	@Value("${spring.jackson.date-format}")
	private String dateFormat;
	
	@Value("${spring.jackson.time-zone}")
	private String timeZone;

	@Value("${spring.jpa.open-in-view}")
	private boolean jpaOpenInView;
	
	@Value("${management.info.env.enabled}")
	private boolean infoEnvEnabled;

	@Value("${spring.data.jpa.repository.packages}")
	private String packageRepo;
	
	@Value("${spring.data.jpa.entity.packages-to-scan}")
	private String packageEntityToScan;
	
	@Value("${spring.data.rest.default-page-size}")
	private int restDefaultPageSize;
	
	@Value("${spring.data.rest.max-page-size}")
	private int restMaxPageSize;

	@Value("${management.endpoints.web.exposure.include}")
	private String endpointListInclude;
	
	@Value("${management.endpoints.web.exposure.exclude}")
	private String endpointListExclude;

	@Value("${info.app.name}")
	private String appName;
	
	@Value("${info.app.description}")
	private String appDescription;
	
	@Value("${info.app.version}")
	private String appVersion;

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	
	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public boolean isJpaOpenInView() {
		return jpaOpenInView;
	}

	public void setJpaOpenInView(boolean jpaOpenInView) {
		this.jpaOpenInView = jpaOpenInView;
	}

	public boolean isInfoEnvEnabled() {
		return infoEnvEnabled;
	}

	public void setInfoEnvEnabled(boolean infoEnvEnabled) {
		this.infoEnvEnabled = infoEnvEnabled;
	}

	public String getPackageRepo() {
		return packageRepo;
	}

	public void setPackageRepo(String packageRepo) {
		this.packageRepo = packageRepo;
	}

	public String getPackageEntityToScan() {
		return packageEntityToScan;
	}

	public void setPackageEntityToScan(String packageEntityToScan) {
		this.packageEntityToScan = packageEntityToScan;
	}

	public String getRestBasePath() {
		return restBasePath;
	}

	public void setRestBasePath(String restBasePath) {
		this.restBasePath = restBasePath;
	}

	public int getRestDefaultPageSize() {
		return restDefaultPageSize;
	}

	public void setRestDefaultPageSize(int restDefaultPageSize) {
		this.restDefaultPageSize = restDefaultPageSize;
	}

	public int getRestMaxPageSize() {
		return restMaxPageSize;
	}

	public void setRestMaxPageSize(int restMaxPageSize) {
		this.restMaxPageSize = restMaxPageSize;
	}

	public String getEndpointListInclude() {
		return endpointListInclude;
	}

	public void setEndpointListInclude(String endpointListInclude) {
		this.endpointListInclude = endpointListInclude;
	}

	public String getEndpointListExclude() {
		return endpointListExclude;
	}

	public void setEndpointListExclude(String endpointListExclude) {
		this.endpointListExclude = endpointListExclude;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDescription() {
		return appDescription;
	}

	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getApiUrl() {
		return HTTP_PROTOCOL + serverAddress + ":" + serverPort + contextPath + restBasePath; 
	}
	
	@Override
	public String toString() {
		return "PropertyConfig [contextPath=" + contextPath + ", dateFormat=" + dateFormat + ", timeZone=" + timeZone
				+ ", serverPort=" + serverPort + ", jpaOpenInView=" + jpaOpenInView + ", infoEnvEnabled=" + infoEnvEnabled
				+ ", packageRepo=" + packageRepo + ", packageEntityToScan=" + packageEntityToScan + ", restBasePath="
				+ restBasePath + ", restDefaultPageSize=" + restDefaultPageSize + ", restMaxPageSize=" + restMaxPageSize
				+ ", endpointListInclude=" + endpointListInclude + ", endpointListExclude=" + endpointListExclude
				+ ", appName=" + appName + ", appDescription=" + appDescription + ", appVersion=" + appVersion + "]";
	}
	
}
