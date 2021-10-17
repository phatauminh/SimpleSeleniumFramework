import driver.CreateDriver;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageObjects.PassionTeaCoWelcomePO;
import utils.Global_VARS;
import utils.JSONDataProvider;

public class PassionTeaCoTest {
    // local vars
    private PassionTeaCoWelcomePO<WebElement> welcome = null;
    private static final String DATA_FILE =
            "src/test/resources/PassionTeaCo.json";

    // constructor
    public PassionTeaCoTest() throws Exception {
    }
// setup/teardown methods

    /**
     * suiteSetup method
     *
     * @param environment
     * @param context
     * @throws Exception
     */
    @Parameters({"environment"})
    @BeforeSuite(alwaysRun = true, enabled = true)
    protected void suiteSetup(@Optional(Global_VARS.ENVIRONMENT) String environment, ITestContext context)
            throws Exception {
        Global_VARS.DEF_ENVIRONMENT = System.getProperty("environment",
                environment);
        Global_VARS.SUITE_NAME =
                context.getSuite().getXmlSuite().getName();
    }

    @AfterSuite(alwaysRun = true, enabled = true)
    protected void suiteTeardown() throws Exception {
    }

    @Parameters({"browser", "platform", "includePattern", "excludePattern"})
    @BeforeTest(alwaysRun = true, enabled = true)
    protected void testSetup(@Optional(Global_VARS.BROWSER)
                                     String browser,
                             @Optional(Global_VARS.PLATFORM)
                                     String platform,
                             @Optional String includePattern,
                             @Optional String excludePattern,
                             ITestContext ctxt) throws Exception {
        // data provider filters
        if (includePattern != null) {
            System.setProperty("includePattern",
                    includePattern);
        }
        if (excludePattern != null) {
            System.setProperty("excludePattern",
                    excludePattern);
        }
        // global variables
        Global_VARS.DEF_BROWSER = System.getProperty("browser",
                browser);
        Global_VARS.DEF_PLATFORM = System.getProperty("platform",
                platform);
        // create driver
        CreateDriver.getInstance().setDriver(Global_VARS.DEF_BROWSER,
                Global_VARS.DEF_PLATFORM,
                Global_VARS.DEF_ENVIRONMENT);
    }

    /**
     * testTeardown method
     *
     * @throws Exception
     */
    @AfterTest(alwaysRun = true, enabled = true)
    protected void testTeardown() throws Exception {
        // close driver
        CreateDriver.getInstance().closeDriver();
    }

    @BeforeClass(alwaysRun = true, enabled = true)
    protected void testClassSetup(ITestContext context) throws
            Exception {
        // instantiate page object classes
        welcome = new PassionTeaCoWelcomePO<WebElement>();
        // set datafile for data provider
        JSONDataProvider.dataFile = DATA_FILE;
        // load page
        welcome.loadPage(Global_VARS.TARGET_URL,
                Global_VARS.TIMEOUT_MINUTE);
    }

    @AfterClass(alwaysRun = true, enabled = true)
    protected void testClassTeardown(ITestContext context) throws Exception {
    }

    /**
     * testMethodSetup method
     *
     * @param result
     * @throws Exception
     */
    @BeforeMethod(alwaysRun = true, enabled = true)
    protected void testMethodSetup(ITestResult result) throws Exception {
    }

    /**
     * testMethodTeardown method
     *
     * @param result
     * @throws Exception
     */
    @AfterMethod(alwaysRun = true, enabled = true)
    protected void testMethodTeardown(ITestResult result) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        if (!driver.getCurrentUrl().contains("welcome.html")) {
            welcome.setTitle("Welcome");
            welcome.navigate("Welcome");
        }
    }

    @Test(groups = {"PASSION_TEA"},
            dataProvider = "fetchData_JSON",
            dataProviderClass = JSONDataProvider.class,
            enabled = true)
    public void tc001_passionTeaCo(String rowID,
                                   String description,
                                   JSONObject testData) throws Exception {
        // set the page title on-the-fly
        welcome.setTitle(testData.get("title").toString());
        // navigate to the new page
        welcome.navigate(testData.get("menu").toString());
        // retrieve and verify the page title
        welcome.verifyTitle(testData.get("title").toString());
    }

    /**
     * tc002_passionTeaCo method to test image source
     *
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"PASSION_TEA"},
            dataProvider = "fetchData_JSON",
            dataProviderClass = JSONDataProvider.class,
            enabled = true)
    public void tc002_passionTeaCo(String rowID,
                                   String description,
                                   JSONObject testData) throws Exception {
        // verify image source
        welcome.verifyImgSrc(PassionTeaCoWelcomePO.WELCOME_PAGE_IMG.valueOf(
                        testData.get("img").toString()),
                testData.get("src").toString());
    }

    /**
     * tc003_passionTeaCo method to test page span text
     *
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"PASSION_TEA"},
            dataProvider = "fetchData_JSON",
            dataProviderClass = JSONDataProvider.class,
            enabled = true)
    public void tc003_passionTeaCo(String rowID,
                                   String description,
                                   JSONObject testData) throws Exception {
        // verify text labels
        welcome.verifySpan(testData.get("pattern").toString(), testData.get("text").toString());
    }

    /**
     * tc004_passionTeaCo method to test page heading text
     *
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"PASSION_TEA"},
            dataProvider = "fetchData_JSON",
            dataProviderClass = JSONDataProvider.class,
            enabled = true)
    public void tc004_passionTeaCo(String rowID,
                                   String description,
                                   JSONObject testData) throws Exception {
        // verify headings
        welcome.verifyHeading(testData.get("pattern").toString(), testData.get("text").toString());
    }

    @Test(groups = {"PASSION_TEA"},
            dataProvider = "fetchData_JSON",
            dataProviderClass = JSONDataProvider.class,
            enabled = true)
    public void tc005_passionTeaCo(String rowID,
                                   String description,
                                   JSONObject testData)
            throws Exception {
        // verify paragraphs
        welcome.verifyParagraph(testData.get("pattern").toString(), testData.get("text").toString());
    }

    /**
     * tc006_passionTeaCo method to test navigating all "Menu" links
     *
     * @param rowID
     * @param description
     * @param testData
     * @throws Exception
     */
    @Test(groups = {"PASSION_TEA"},
            dataProvider = "fetchData_JSON",
            dataProviderClass = JSONDataProvider.class,
            enabled = true)
    public void tc006_passionTeaCo(String rowID,
                                   String description,
                                   JSONObject testData) throws Exception {
        welcome.navigateMenuLink(PassionTeaCoWelcomePO.MENU_LINKS.valueOf(
                        testData.get("element").toString()),
                testData.get("title").toString());
    }
}
