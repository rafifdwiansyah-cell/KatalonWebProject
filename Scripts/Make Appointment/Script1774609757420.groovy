import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.text.SimpleDateFormat
import java.util.Date

//WebUI.callTestCase(findTestCase('Login'), [:], FailureHandling.STOP_ON_FAILURE)


switch (facility.toLowerCase()) {
	case 'tokyo':
		WebUI.click(findTestObject('Dashboard Page/select_FacilityTokyo'))
		break
	case 'hongkong':
		WebUI.click(findTestObject('Dashboard Page/select_FacilityHongkong'))
		break
	case 'seoul':
		WebUI.click(findTestObject('Dashboard Page/select_FacilitySeoul'))
		break
	default:
		WebUI.comment("Facility tidak dikenali: " + facility)
		break
}


if (Apply.equalsIgnoreCase('Yes')) {
	WebUI.click(findTestObject('Object Repository/Dashboard Page/check_Apply for hospital readmission'))
}

if (Healthcare_Program.equalsIgnoreCase('Medicaid')) {
	WebUI.click(findTestObject('Dashboard Page/btn_RadioMedicaid'))
} else if (Healthcare_Program.equalsIgnoreCase('Medicare')) {
	WebUI.click(findTestObject('Dashboard Page/btn_RadioMedicare'))
} else if (Healthcare_Program.equalsIgnoreCase('None')) {
	WebUI.click(findTestObject('Dashboard Page/btn_RadioNone'))
} else {
	WebUI.comment("Healthcare tidak dikenali : " + Healthcare_Program)
}

WebUI.setText(findTestObject('Dashboard Page/txt_Date'), Visit_Date)

WebUI.sendKeys(findTestObject('Dashboard Page/txt_Date'), Keys.chord(Keys.TAB))

WebUI.setText(findTestObject('Dashboard Page/txt_Comment'), Comment)

WebUI.click(findTestObject('Dashboard Page/btn_Book appointment'))

WebUI.verifyTextPresent('Appointment Confirmation', false)

String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
String path = "Screenshots/screenshot_" + timestamp + ".png"
WebUI.takeScreenshot(path)

WebUI.click(findTestObject('Dashboard Page/btn_Go to Homepage'))

WebUI.verifyTextPresent('CURA Healthcare Service', false)

