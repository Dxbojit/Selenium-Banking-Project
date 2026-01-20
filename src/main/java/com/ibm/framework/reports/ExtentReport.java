package com.ibm.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	private static ExtentReports extent;
	
	private ExtentReport() {
	}
	
	public static void initReports() {
		if(extent != null)
		{
			return;
		}
		String reportPath = System.getProperty("user.dir")
		        + "/target/extent-report/ExtentReport.html";
		
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("TDDBank Automation Report");
        reporter.config().setDocumentTitle("Automation Test Results");
        
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        extent.setSystemInfo("Project", "TDDBank");
        extent.setSystemInfo("Framework", "Selenium + Cucumber + TestNG");
        extent.setSystemInfo("Browser", "Chrome / Firefox");
        
	}
	
	 public static void flushReports() {
	        if (extent != null) {
	            extent.flush();
	        }
	    }
	 
	    public static void createTest(String scenarioName) {
	        ExtentManager.setTest(extent.createTest(scenarioName));
	    }
}
