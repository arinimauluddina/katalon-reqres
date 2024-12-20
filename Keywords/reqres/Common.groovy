package reqres

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper
import internal.GlobalVariable

public class Common {

	private static JsonSlurper jsonSlurper = new JsonSlurper()

	@Keyword
	def int registerNewUser(String email, String password) {
		def response = WS.sendRequestAndVerify(findTestObject("Object Repository/Register User",
				["email": email, "password": password]))
		def jsonResponse = jsonSlurper.parseText(response.getResponseText())
		WS.verifyResponseStatusCode(response, 200)
		return jsonResponse.id
	}

	@Keyword
	def  registerUnsuccesful(String email) {
		def response = WS.sendRequestAndVerify(findTestObject("Object Repository/Register User",
				["email": email]))
		def jsonResponse = jsonSlurper.parseText(response.getResponseText())
		WS.verifyResponseStatusCode(response, 400)
	}

	@Keyword
	def String doLogin(String email, String password) {
		def response = WS.sendRequestAndVerify(findTestObject("Object Repository/Login",
				["email": email, "password": password]))
		def jsonResponse = jsonSlurper.parseText(response.getResponseText())
		WS.verifyResponseStatusCode(response, 200)
		return jsonResponse.token
	}

	@Keyword
	def  loginUnsuccesful(String email) {
		def response = WS.sendRequestAndVerify(findTestObject("Object Repository/Login",
				["email": email]))
		def jsonResponse = jsonSlurper.parseText(response.getResponseText())
		WS.verifyResponseStatusCode(response, 400)
		KeywordUtil.logInfo("Status Code: ${response.getStatusCode()}")
	}
}
