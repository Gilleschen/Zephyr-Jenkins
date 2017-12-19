package Auto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class method {
	int systemPort = 8200;// System port
	int port = 4723;// Appium port
	int device_timeout = 60;// 60sec
	int command_timeout = 30;// 30sec
	LoadExpectResult ExpectResult = new LoadExpectResult();
	static LoadTestCase TestCase = new LoadTestCase();
	static AndroidDriver driver;
	// static String CaseErrorList[][] = new
	// String[TestCase.CaseList.size()][TestCase.DeviceInformation.deviceName
	// .size()];// �����U�רҩ�U�˸m�����O���G (2���}�C)CaseErrorList[CaseList][Devices]

	String ErrorList;
	// String[TestCase.DeviceInformation.deviceName.size()];// �����U�˸m�����O���G
	// WebDriverWait[] wait = new
	// WebDriverWait[TestCase.DeviceInformation.deviceName.size()];
	// WebDriverWait wait = new WebDriverWait(driver, device_timeout);
	// WebDriverWait[] wait = new WebDriverWait[1];
	static String appElemnt;// APP����W��
	static String appInput;// ��J��
	static String appInputXpath;// ��J�Ȫ�Xpath�榡
	static String toElemnt;// APP����W��
	static int startx, starty, endx, endy;// Swipe���ʮy��
	static int iterative;// �e���ưʦ���
	static String scroll;// �e�����ʤ�V
	static String appElemntarray;// �j�M���h����������
	static String checkVerifyText;// �T�{����QuitAPP�e�O�_����LVerifyText
	static String element, appPackage, appActivity, deviceName, platformVersion;
	static int CurrentCaseNumber = -1;// �ثe�����ĴX�Ӵ��ծצC
	static Boolean CommandError;// �P�w���檺���O�O�_�X�{���~�Fture�����T�Ffalse�����~
	static int CurrentErrorDevice = 0;// �έp�ثe�X�����]�Ƽƶq

	public static void main(String[] args) throws IOException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		invokeFunction();
		System.out.println("���յ���!!!!!!!!");

	}

	public static void invokeFunction() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException {
		Object obj = new method();
		Class c = obj.getClass();
		String methodName = null;

		for (int CurrentCase = 0; CurrentCase < TestCase.StepList.size(); CurrentCase++) {
			System.out.println("[info] CaseName:|" + TestCase.CaseList.get(CurrentCase).toString() + "|");

			CommandError = true;// �w�]CommandError��True
			for (int CurrentCaseStep = 0; CurrentCaseStep < TestCase.StepList.get(CurrentCase)
					.size(); CurrentCaseStep++) {
				if (!CommandError) {
					System.out.println("[Result]" + TestCase.CaseList.get(CurrentCase).toString() + ":ERROR!");
					System.out.println("");
					break;// �Y�ثe���ծרҥX�{CommandError=false�A�h���X�ثe�רҨð���U�@�Ӯר�
				}
				switch (TestCase.StepList.get(CurrentCase).get(CurrentCaseStep).toString()) {

				case "LaunchAPP":
					methodName = "LaunchAPP";
					appPackage = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					appActivity = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 2);
					deviceName = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 3);
					platformVersion = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 4);
					CurrentCaseStep = CurrentCaseStep + 4;
					break;

				case "Byid_SendKey":
					methodName = "Byid_SendKey";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					appInput = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 2);
					CurrentCaseStep = CurrentCaseStep + 2;
					break;

				case "Byid_Click":
					methodName = "Byid_Click";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "Byid_Swipe":
					methodName = "Byid_Swipe";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					toElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 2);
					CurrentCaseStep = CurrentCaseStep + 2;
					break;

				case "ByXpath_SendKey":
					methodName = "ByXpath_SendKey";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					appInput = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 2);
					CurrentCaseStep = CurrentCaseStep + 2;
					break;

				case "Byid_Clear":
					methodName = "Byid_Clear";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "ByXpath_Clear":
					methodName = "ByXpath_Clear";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "ByXpath_Click":
					methodName = "ByXpath_Click";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "ByXpath_Swipe":
					methodName = "ByXpath_Swipe";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					toElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 2);
					CurrentCaseStep = CurrentCaseStep + 2;
					break;

				case "Byid_Wait":
					methodName = "Byid_Wait";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "ByXpath_Wait":
					methodName = "ByXpath_Wait";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "HideKeyboard":
					methodName = "HideKeyboard";
					break;

				case "Byid_VerifyText":
					methodName = "Byid_VerifyText";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "ByXpath_VerifyText":
					methodName = "ByXpath_VerifyText";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "Sleep":
					methodName = "Sleep";
					appInput = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "ScreenShot":
					methodName = "ScreenShot";
					break;

				case "Orientation":
					methodName = "Orientation";
					appInput = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "Swipe":
					methodName = "Swipe";
					startx = Integer.valueOf(TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1));
					starty = Integer.valueOf(TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 2));
					endx = Integer.valueOf(TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 3));
					endy = Integer.valueOf(TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 4));
					iterative = Integer.valueOf(TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 5));
					CurrentCaseStep = CurrentCaseStep + 5;
					break;

				case "ByXpath_Swipe_Vertical":
					methodName = "ByXpath_Swipe_Vertical";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					scroll = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 2);
					iterative = Integer.valueOf(TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 3));
					CurrentCaseStep = CurrentCaseStep + 3;
					break;

				case "ByXpath_Swipe_Horizontal":
					methodName = "ByXpath_Swipe_Horizontal";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					scroll = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 2);
					iterative = Integer.valueOf(TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 3));
					CurrentCaseStep = CurrentCaseStep + 3;
					break;

				case "ByXpath_Swipe_FindText_Click_Android":
					methodName = "ByXpath_Swipe_FindText_Click_Android";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					scroll = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 2);
					appElemntarray = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 3);
					appInput = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 4);
					appInputXpath = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 5);
					CurrentCaseStep = CurrentCaseStep + 5;
					break;

				case "Byid_LongPress":
					methodName = "Byid_LongPress";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "ByXpath_LongPress":
					methodName = "ByXpath_LongPress";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "Byid_invisibility":
					methodName = "Byid_invisibility";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "ByXpath_invisibility":
					methodName = "ByXpath_invisibility";
					appElemnt = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep + 1);
					CurrentCaseStep = CurrentCaseStep + 1;
					break;

				case "Back":
					methodName = "Back";
					break;

				case "Home":
					methodName = "Home";
					break;

				case "Power":
					methodName = "Power";
					break;

				// case "Menu":
				// methodName = "Menu";
				// break;

				case "ResetAPP":
					methodName = "ResetAPP";
					break;

				case "QuitAPP":
					methodName = "QuitAPP";
					checkVerifyText = TestCase.StepList.get(CurrentCase).get(CurrentCaseStep - 2).toString();
					break;
				}

				Method method;
				method = c.getMethod(methodName, new Class[] {});
				method.invoke(c.newInstance());

			}

		}
	}

	public void Byid_VerifyText() {
		boolean result = false;
		try {
			System.out.println("[info] Executing:|Byid_VerifyText|" + appElemnt + "|");
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(appElemnt))).getText();
			// �^�Ǵ��ծרҲM�檺�W�ٵ�ExpectResult.LoadExpectResult�A�æs����浲�G��ResultList�M��
			ExpectResult.LoadExpectResult(TestCase.CaseList.get(CurrentCaseNumber).toString());

			for (int j = 0; j < ExpectResult.ResultList.size(); j++) {
				if (element.equals(ExpectResult.ResultList.get(j))) {
					result = true;
					break;
				} else {
					result = false;

				}
			}
			if (result) {
				System.out.println("[info] Result_Byid_VerifyText:|PASS|");
			} else {
				System.out.println("[info] Result_Byid_VerifyText:|FAIL|");
			}

		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
		}

	}

	public void ByXpath_VerifyText() {
		boolean result = false;
		try {
			System.out.println("[info] Executing:|ByXpath_VerifyText|" + appElemnt + "|");
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appElemnt))).getText();

			// �^�Ǵ��ծרҲM�檺�W�ٵ�ExpectResult.LoadExpectResult�A�æs����浲�G��ResultList�M��
			ExpectResult.LoadExpectResult(TestCase.CaseList.get(CurrentCaseNumber).toString());

			for (int j = 0; j < ExpectResult.ResultList.size(); j++) {
				if (element.equals(ExpectResult.ResultList.get(j))) {
					result = true;
					break;
				} else {
					result = false;
				}
			}

			if (result) {
				System.out.println("[info] Result_ByXpath_VerifyText:|PASS|");
			} else {
				System.out.println("[info] Result_ByXpath_VerifyText:|FAIL|");
			}

		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
		}

	}

	public void Byid_Wait() {
		try {
			System.out.println("[info] Executing:|Byid_Wait|" + appElemnt + "|");
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(appElemnt)));
		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
		}

	}

	public void ByXpath_Wait() {
		try {
			System.out.println("[info] Executing:|ByXpath_Wait|" + appElemnt + "|");
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(appElemnt)));
		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
		}

	}

	public void Byid_SendKey() {
		try {
			System.out.println("[info] Executing:|Byid_SendKey|" + appElemnt + "|" + appInput + "|");
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(appElemnt))).sendKeys(appInput);
		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
		}

	}

	public void ByXpath_SendKey() {
		try {
			System.out.println("[info] Executing:|ByXpath_SendKey|" + appElemnt + "|" + appInput + "|");
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appElemnt))).sendKeys(appInput);
		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
		}

	}

	public void Byid_Click() {
		try {
			System.out.println("[info] Executing:|Byid_Click|" + appElemnt + "|");
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(appElemnt))).click();
		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
		}
	}

	public void ByXpath_Click() {
		try {
			System.out.println("[info] Executing:|ByXpath_Click|" + appElemnt + "|");
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appElemnt))).click();
		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
		}
	}

	public void Byid_Clear() {

		try {
			System.out.println("[info] Executing:|Byid_Clear|" + appElemnt + "|Clear|");
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(appElemnt))).clear();
		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;
		}
	}

	public void ByXpath_Clear() {

		try {
			System.out.println("[info] Executing:|ByXpath_Clear|" + appElemnt + "|Clear|");
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appElemnt))).clear();

		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false

		}

	}

	public void HideKeyboard() {
		try {
			if (driver.isKeyboardShown()) {
				System.out.println("[info] Executing:|HideKeyboard|");
				driver.hideKeyboard();
			}
		} catch (Exception e) {
			System.out.println("[Error] Can't hide keyboard");
			CommandError = false;
		}
	}

	public void Sleep() {
		String NewString = "";// �s�r��
		char[] r = { '.' };// �p���I�r��
		char[] c = appInput.toCharArray();// �N�r���ন�r���}�C
		for (int i = 0; i < c.length; i++) {
			if (c[i] != r[0]) {// �P�_�r���O�_���p���I
				NewString = NewString + c[i];// �_�A�N�r���զX���s�r��
			} else {
				break;// �O�A���X�j��
			}
		}

		try {

			System.out.println("[info] Executing:|Sleep|" + NewString + " second..." + "|");
			Thread.sleep(Integer.valueOf(NewString) * 1000);// �N�r���ন���

		} catch (Exception e) {
			;
		}

	}

	public void ScreenShot() {

		Calendar date = Calendar.getInstance();
		String month = Integer.toString(date.get(Calendar.MONTH) + 1);
		String day = Integer.toString(date.get(Calendar.DAY_OF_MONTH));
		String hour = Integer.toString(date.get(Calendar.HOUR_OF_DAY));
		String min = Integer.toString(date.get(Calendar.MINUTE));
		String sec = Integer.toString(date.get(Calendar.SECOND));

		File screenShotFile = (File) driver.getScreenshotAs(OutputType.FILE);
		System.out.println("[info] Executing:|ScreenShot|");
		try {
			FileUtils.copyFile(screenShotFile, new File("C:\\TUTK_QA_TestTool\\TestReport\\"
					+ TestCase.CaseList.get(CurrentCaseNumber) + "_" + month + day + hour + min + sec + ".jpg"));
		} catch (IOException e) {
			System.out.println("[Error]Fail to ScreenShot");
			CommandError = false;
		}
	}

	public void Orientation() {

		try {
			if (appInput.equals("Landscape")) {
				System.out.println("[info] Executing:|Orientation|Landscape|");
				driver.rotate(ScreenOrientation.LANDSCAPE);
			} else if (appInput.equals("Portrait")) {
				System.out.println("[info] Executing:|Orientation|Portrait|");
				driver.rotate(ScreenOrientation.PORTRAIT);
			}
		} catch (Exception e) {
			if (appInput.equals("Landscape")) {
				System.out.println("[Error] Can't rotate to landscape");
			} else {
				System.out.println("[Error] Can't rotate to portrait");
			}
			CommandError = false;
		}
	}

	public void QuitAPP() {
		try {
			System.out.println("[info] Executing:|QuitAPP|");
			driver.quit();

			if (CommandError) {
				System.out.println("[Result]" + TestCase.CaseList.get(CurrentCaseNumber).toString() + ":PASS");
			}

			System.out.println("");
		} catch (Exception e) {
			System.out.println("[Error] Can't quit APP");
			CommandError = false;
		}

	}

	public void ResetAPP() {

		try {
			System.out.println("[info] Executing:|ResetAPP|");
			driver.resetApp();
		} catch (Exception e) {
			System.out.println("[Error] Can't reset APP");
			CommandError = false;
		}
	}

	public void LaunchAPP() {
		CurrentCaseNumber = CurrentCaseNumber + 1;
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, device_timeout);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
		cap.setCapability(MobileCapabilityType.NO_RESET, true);

		try {
			System.out.println("[info] Executing:|LaunchAPP|" + appPackage + "|");
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:" + port + "/wd/hub"), cap);
		} catch (Exception e) {
			System.out.print("[Error] Can't find Device UDID:" + deviceName);
			System.out.println(" or can not find appPackage: " + appPackage);
			CommandError = false;
		}
	}

	public void Back() {
		try {
			System.out.println("[info] Executing:|Back|");
			driver.pressKeyCode(AndroidKeyCode.BACK);
		} catch (Exception e) {
			System.out.println("[Error] Can't execute Back button");
			CommandError = false;
		}

	}

	public void Home() {
		try {
			System.out.println("[info] Executing:|Home|");
			driver.pressKeyCode(AndroidKeyCode.HOME);
		} catch (Exception e) {
			System.out.println("[Error] Can't execute Home button");
			CommandError = false;
		}

	}

	public void Power() {
		try {
			System.out.println("[info] Executing:|Power|");
			driver.pressKeyCode(AndroidKeyCode.KEYCODE_POWER);
		} catch (Exception e) {
			System.out.println("[Error] Can't execute Power button");
			CommandError = false;
		}

	}

	// // public void Menu() {
	// // for (int i = 0; i < driver.size(); i++) {
	// // driver.get(i).pressKeyCode(AndroidKeyCode.MENU);
	// // driver.get(i).pressKeyCode(82);
	// // driver.get(i).pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
	// //
	// // }
	// // }
	//
	public void Byid_invisibility() {
		try {
			System.out.println("[info] Executing:|Byid_invisibility|" + appElemnt + "|");
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(appElemnt)));
		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;
		}
	}

	public void ByXpath_invisibility() {
		try {
			System.out.println("[info] Executing:|ByXpath_invisibility|" + appElemnt + "|");
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(appElemnt)));
		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;
		}
	}

	public void Byid_LongPress() {
		try {
			System.out.println("[info] Executing:|Byid_LongPress|" + appElemnt + "|");
			TouchAction t = new TouchAction(driver);
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			t.longPress(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(appElemnt)))).perform();

		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;
		}
	}

	public void ByXpath_LongPress() {
		try {
			System.out.println("[info] Executing:|ByXpath_LongPress|" + appElemnt + "|");
			TouchAction t = new TouchAction(driver);
			WebDriverWait wait = new WebDriverWait(driver, command_timeout);
			t.longPress(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appElemnt)))).perform();

		} catch (Exception e) {
			System.out.println("[Error] Can't find " + appElemnt);
			CommandError = false;
		}

	}

	// public void ByXpath_Swipe() {
	// Point p1, p2;// p1 ���_�I;p2�����I
	//
	// for (int i = 0; i < driver.size(); i++) {
	// if (driver.get(i) != null) {
	// try {
	// System.out.println("[info] Executing:|ByXpath_Swipe|" + appElemnt + "|" +
	// toElemnt + "|");
	// wait[i] = new WebDriverWait(driver.get(i), command_timeout);
	// p2 =
	// wait[i].until(ExpectedConditions.visibilityOfElementLocated(By.xpath(toElemnt))).getLocation();
	// p1 =
	// wait[i].until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appElemnt)))
	// .getLocation();
	// driver.get(i).swipe(p1.x, p1.y, p1.x, p1.y - (p1.y - p2.y), 1000);
	// ErrorList[i] = "Pass";
	// CaseErrorList[CurrentCaseNumber] = ErrorList;
	// } catch (Exception e) {
	// System.err.print("[Error] Can't find " + appElemnt);
	// System.err.println(" or Can't find " + toElemnt);
	// ErrorList[i] = "Error";// �x�s��i�x�]��command���ѵ��G
	// CaseErrorList[CurrentCaseNumber] = ErrorList;//
	// �x�s��i�x�]�ư����CurrentCaseNumber�ӮרҤ�command���ѵ��G
	// CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
	// driver.set(i, null);
	// CurrentErrorDevice++;// �έp�]�X�����]�Ƽ�
	// }
	// }
	// }
	// }
	//
	// public void Byid_Swipe() {
	// Point p1, p2;// p1 ���_�I;p2�����I
	//
	// for (int i = 0; i < driver.size(); i++) {
	// if (driver.get(i) != null) {
	// try {
	// System.out.println("[info] Executing:|Byid_Swipe|" + appElemnt + "|" +
	// toElemnt + "|");
	// wait[i] = new WebDriverWait(driver.get(i), command_timeout);
	// p2 = wait[i].until(ExpectedConditions.visibilityOfElementLocated(
	// By.id(TestCase.DeviceInformation.appPackage + ":id/" +
	// toElemnt))).getLocation();
	// p1 = wait[i].until(ExpectedConditions.visibilityOfElementLocated(
	// By.id(TestCase.DeviceInformation.appPackage + ":id/" +
	// appElemnt))).getLocation();
	//
	// driver.get(i).swipe(p1.x, p1.y, p1.x, p1.y - (p1.y - p2.y), 1000);
	// ErrorList[i] = "Pass";
	// CaseErrorList[CurrentCaseNumber] = ErrorList;
	// } catch (Exception e) {
	// System.err.print("[Error] Can't find " + appElemnt);
	// System.err.println(" or Can't find " + toElemnt);
	// ErrorList[i] = "Error";// �x�s��i�x�]��command���ѵ��G
	// CaseErrorList[CurrentCaseNumber] = ErrorList;//
	// �x�s��i�x�]�ư����CurrentCaseNumber�ӮרҤ�command���ѵ��G
	// CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
	// driver.set(i, null);
	// CurrentErrorDevice++;// �έp�]�X�����]�Ƽ�
	// }
	// }
	// }
	// }
	//
	// public void Swipe() {
	// for (int i = 0; i < driver.size(); i++) {
	// if (driver.get(i) != null) {
	// for (int j = 0; j < iterative; j++) {
	// try {
	// System.out.println(
	// "[info] Executing:|Swipe|(" + startx + "," + starty + ")|(" + endx + ","
	// + endy + ")|");
	// driver.get(i).swipe(startx, starty, endx, endy, 500);
	// } catch (Exception e) {
	// System.err.println("[Error] Can't swipe " + "(" + startx + "," + starty +
	// ")" + " to (" + endx
	// + "," + endy + ")");
	// ErrorList[i] = "Error";// �x�s��i�x�]��command���ѵ��G
	// CaseErrorList[CurrentCaseNumber] = ErrorList;//
	// �x�s��i�x�]�ư����CurrentCaseNumber�ӮרҤ�command���ѵ��G
	// CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
	// driver.set(i, null);
	// CurrentErrorDevice++;// �έp�]�X�����]�Ƽ�
	// break;// �X����A���}iterative�^��
	// }
	// }
	// }
	// }
	// }
	//
	// public void ByXpath_Swipe_Vertical() {
	// Point p;// ����y��
	// Dimension s;// ����j�p
	// WebElement e;
	//
	// for (int i = 0; i < driver.size(); i++) {
	// if (driver.get(i) != null) {
	// try {
	// System.out.println("[info] Executing:|ByXpath_Swipe_Vertical|" +
	// appElemnt + "|" + scroll + "|"
	// + iterative + "|");
	// wait[i] = new WebDriverWait(driver.get(i), command_timeout);
	// e =
	// wait[i].until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appElemnt)));
	// s = e.getSize();
	// p = e.getLocation();
	// int errorX = (int) Math.round(s.width * 0.01);
	// int errorY = (int) Math.round(s.height * 0.01);
	// for (int j = 0; j < iterative; j++) {
	// if (scroll.equals("DOWN")) {// �e���V�U����
	// driver.get(i).swipe(p.x + errorX, p.y + s.height - errorY, p.x + errorX,
	// p.y + errorY,
	// 1000);
	// } else if (scroll.equals("UP")) {// �e���V�W����
	// driver.get(i).swipe(p.x + errorX, p.y + errorY, p.x + errorX, p.y +
	// s.height - errorY,
	// 1000);
	// }
	// }
	// ErrorList[i] = "Pass";
	// CaseErrorList[CurrentCaseNumber] = ErrorList;
	// } catch (Exception w) {
	// System.err.println("[Error] Can't find " + appElemnt);
	// ErrorList[i] = "Error";// �x�s��i�x�]��command���ѵ��G
	// CaseErrorList[CurrentCaseNumber] = ErrorList;//
	// �x�s��i�x�]�ư����CurrentCaseNumber�ӮרҤ�command���ѵ��G
	// CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
	// driver.set(i, null);
	// CurrentErrorDevice++;// �έp�]�X�����]�Ƽ�
	// }
	// }
	// }
	// }
	//
	// public void ByXpath_Swipe_Horizontal() {
	// Point p;// ����y��
	// Dimension s;// ����j�p
	// WebElement e;
	// for (int i = 0; i < driver.size(); i++) {
	// if (driver.get(i) != null) {
	// try {
	// System.out.println("[info] Executing:|ByXpath_Swipe_Horizontal|" +
	// appElemnt + "|" + scroll + "|"
	// + iterative + "|");
	// wait[i] = new WebDriverWait(driver.get(i), command_timeout);
	// e =
	// wait[i].until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appElemnt)));
	// // e = driver.get(i).findElement(By.xpath(appElemnt));
	// s = e.getSize();
	// p = e.getLocation();
	// int errorX = (int) Math.round(s.getWidth() * 0.01);
	// int errorY = (int) Math.round(s.getHeight() * 0.01);
	// for (int j = 0; j < iterative; j++) {
	// if (scroll.equals("RIGHT")) {// �e���V�k���� (�[�ݵe�����褺�e)
	// driver.get(i).swipe(p.x + errorX, p.y + errorY, p.x + s.width - errorX,
	// p.y + errorY, 1000);
	// } else if (scroll.equals("LEFT")) {// �e���V������ (�[�ݵe���k�褺�e)
	// driver.get(i).swipe(p.x + s.width - errorX, p.y + errorY, p.x + errorX,
	// p.y + errorY, 1000);
	// }
	// }
	// ErrorList[i] = "Pass";
	// CaseErrorList[CurrentCaseNumber] = ErrorList;
	// } catch (Exception w) {
	// System.err.println("[Error] Can't find " + appElemnt);
	// ErrorList[i] = "Error";// �x�s��i�x�]��command���ѵ��G
	// CaseErrorList[CurrentCaseNumber] = ErrorList;//
	// �x�s��i�x�]�ư����CurrentCaseNumber�ӮרҤ�command���ѵ��G
	// CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
	// driver.set(i, null);
	// CurrentErrorDevice++;// �έp�]�X�����]�Ƽ�
	// }
	// }
	//
	// }
	// }
	//
	// // ByXpath_Swipe_FindText_Click_Android���I�G1.�j�M���r�ꤣ�i���� 2.�j�M5�����S��줸��A�h����j�M
	// public void ByXpath_Swipe_FindText_Click_Android() {
	//
	// for (int j = 0; j < driver.size(); j++) {
	// if (driver.get(j) != null) {
	// int SearchNumber = 0;// �j�M����
	// Point ScrollBarP;// ���b����y��
	// Dimension ScrollBarS;// ���b���󤧪��μe
	// WebElement ScrollBar;// ���b����
	//
	// try {
	// System.out.println("[info]
	// Executing:|ByXpath_Swipe_FindText_Click_Android|" + appElemnt + "|"
	// + scroll + "|" + appElemntarray + "|" + appInput + "|" + appInputXpath +
	// "|");
	// wait[j] = new WebDriverWait(driver.get(j), command_timeout);
	// ScrollBar =
	// wait[j].until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appElemnt)));//
	// ���b����
	// ScrollBarS = ScrollBar.getSize();// ���b���󪺪��μe
	// ScrollBarP = ScrollBar.getLocation();// ���b���y��
	// int errorX = (int) Math.round(ScrollBarS.width * 0.1);
	// int errorY = (int) Math.round(ScrollBarS.height * 0.1);
	// List<WebElement> targetlist = wait[j]
	// .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(appElemntarray)));//
	// �n�j�M���h����������M��
	//
	// for (int i = 0; i < targetlist.size(); i++) {
	//
	// if ((targetlist.get(i).getText().toString()).equals(appInput)) {//
	// �Ytargetelement�btargetlist�M�椤�A�h�I��targetelement
	// WebElement targetElement;// �ǳƷj�M������
	// Point targetElementP;// �ǳƷj�M�����󤧮y��
	// Dimension targetElementS;// �ǳƷj�M�����󤧪��μe
	//
	// targetElement = wait[j]
	// .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appInputXpath)));
	//
	// targetElementP = targetElement.getLocation();// �ǳƷj�M���󪺮y��
	// targetElementS = targetElement.getSize();// �ǳƷj�M���󪺪��μe
	//
	// switch (scroll.toString()) {
	//
	// case "DOWN":
	// if (targetElementP.y > ScrollBarP.y + ScrollBarS.height) {//
	// �Y�j�M����y�y�Фj����b�d��A���ܷj�M�������UI�Q���b�B��
	// driver.get(j).swipe(targetElementP.x, ScrollBarS.height + ScrollBarP.y -
	// errorY,
	// targetElementP.x, ScrollBarP.y + errorY, 1000);
	// } else if (targetElementP.y + targetElementS.height == ScrollBarP.y
	// + ScrollBarS.height) {// �Y�j�M����y�y�лP�e���`�M������b���סA���ܷj�M���󪺳���UI�Q���b�B��
	// driver.get(j).swipe(targetElementP.x - errorY, targetElementP.y,
	// targetElementP.x,
	// ScrollBarP.y + errorY, 1000);
	// }
	// break;
	//
	// case "UP":
	// if (targetElementP.y + targetElementS.height < ScrollBarP.y) {//
	// �Y�j�M���󪺳̤jy�y�Фp����by�y�СA���ܷj�M�������UI�Q���b�B��
	// driver.get(j).swipe(targetElementP.x, ScrollBarP.y + errorY,
	// targetElementP.x,
	// ScrollBarS.height + ScrollBarP.y - errorY, 1000);
	// } else {// �Ϥ��A�Y�j�M���󪺳̤jy�y�Фj����by�y�СA���ܷj�M�������UI�Q���b�B��
	// driver.get(j).swipe(targetElementP.x, ScrollBarP.y + errorY,
	// targetElementP.x,
	// ScrollBarP.y + ScrollBarS.height - errorY, 1000);
	// }
	// break;
	//
	// case "LEFT":// �e���V������(�[�ݵe���k�褺�e)
	// if (targetElementP.x > ScrollBarP.x + ScrollBarS.width) {//
	// �Y�j�M����x�y�Фj����b�d��A���ܷj�M�������UI�Q���b�B��
	// driver.get(j).swipe(ScrollBarP.x + ScrollBarS.width - errorX,
	// targetElementP.y,
	// ScrollBarP.x + errorX, targetElementP.y, 1000);
	// } else if (targetElementP.x + targetElementS.width == ScrollBarP.x +
	// ScrollBarS.width) {// �Y�j�M����x�y�лP�e���`�M������b�e�סA���ܷj�M���󪺳���UI�Q���b�B��
	// driver.get(j).swipe(targetElementP.x - errorX, targetElementP.y,
	// ScrollBarP.x + errorX, targetElementP.y, 1000);
	// }
	// break;
	//
	// case "RIGHT":// �e���V�k����(�[�ݵe�����褺�e)
	// if (targetElementP.x + targetElementS.width < ScrollBarP.x) {//
	// �Y�j�M���󪺳̤jx�y�Фp����bx�y�СA���ܷj�M�������UI�Q���b�B��
	// driver.get(j).swipe(ScrollBarP.x + errorX, targetElementP.y,
	// ScrollBarP.x + ScrollBarS.width - errorX, targetElementP.y, 1000);
	// } else if (targetElementP.x == ScrollBarP.x) {//
	// �Y�j�M����x�y�е�����bx�y�СA�i����ܷj�M���󪺳���UI�Q���b�B��
	// driver.get(j).swipe(targetElementP.x + targetElementS.width + errorX,
	// targetElementP.y, ScrollBarP.x + ScrollBarS.width - errorX,
	// targetElementP.y, 1000);
	// }
	// break;
	// }
	//
	// wait[j].until(ExpectedConditions.visibilityOfElementLocated(By.xpath(appInputXpath)))
	// .click();
	// // driver.get(j).findElement(By.xpath(appInputXpath)).click();
	// break;
	// }
	//
	// if (i == targetlist.size() - 1) {// �Ytargetlist���̫�@����Ƥ�粒��A�h�i��Srcoll�즲
	//
	// switch (scroll.toString()) {
	//
	// case "DOWN":
	// driver.get(j).swipe(ScrollBarP.x + errorX, ScrollBarP.y +
	// ScrollBarS.height - errorY,
	// ScrollBarP.x + errorX, ScrollBarP.y + errorY, 1000);// �V�U����
	// break;
	//
	// case "UP":
	// driver.get(j).swipe(ScrollBarP.x + errorX, ScrollBarP.y + errorY,
	// ScrollBarP.x + errorX,
	// ScrollBarP.y + ScrollBarS.height - errorY, 1000);// �V�W����
	// break;
	//
	// case "LEFT":
	// driver.get(j).swipe(ScrollBarP.x + ScrollBarS.width - errorX,
	// ScrollBarP.y + errorY,
	// ScrollBarP.x + errorX, ScrollBarP.y + errorY, 1000);// �e���V������(�[�ݵe���k�褺�e)
	// break;
	//
	// case "RIGHT":
	// driver.get(j).swipe(ScrollBarP.x + errorX, ScrollBarP.y + errorY,
	// ScrollBarP.x + ScrollBarS.width - errorX, ScrollBarP.y + errorY, 1000);//
	// �e���V�k����(�[�ݵe�����褺�e)
	// break;
	//
	// }
	// SearchNumber++;// �֭p�j�M����
	// targetlist.clear();// �M��targetlist
	// targetlist = wait[j].until(
	// ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(appElemntarray)));//
	// �A�����o�stargetlist
	//
	// if (SearchNumber == 10) {// �j�M10�����S��줸��A�h���Xfor
	// System.err.println("Can't find " + appInput);// �L�X�䤣��
	// break;// ���Xfor
	// } else {
	// i = -1;// �YSearchNumber!=10�A�h�Oi=-1(�ت��G�A������for)
	// }
	// }
	// }
	// ErrorList[j] = "Pass";
	// CaseErrorList[CurrentCaseNumber] = ErrorList;
	// } catch (Exception w) {
	// System.err.print("[Error] Can't find " + appElemnt);
	// System.err.print(" or [Error] can not find " + appElemntarray);
	// System.err.println(" or [Error] can not find " + appInputXpath);
	// ErrorList[j] = "Error";// �x�s��i�x�]��command���ѵ��G
	// CaseErrorList[CurrentCaseNumber] = ErrorList;//
	// �x�s��i�x�]�ư����CurrentCaseNumber�ӮרҤ�command���ѵ��G
	// CommandError = false;// �Y�䤣����w����A�h�]�wCommandError=false
	// driver.set(j, null);
	// CurrentErrorDevice++;// �έp�]�X�����]�Ƽ�
	// }
	// }
	// }
	//
	// }
	// /*
	// * �W�U�H���ư�n�� public void Swipe() { Random rand = new Random(); boolean
	// * items[] = { true, false }; for (int i = 0; i < driver.size(); i++) {
	// for
	// * (int j = 0; j < iterative; j++) { if
	// (items[rand.nextInt(items.size())])
	// * { driver.get(i).swipe(startx, starty, endx, endy, 500); }else{
	// * driver.get(i).swipe(endx, endy, startx , starty , 500); } } } }
	// *
	// */

}