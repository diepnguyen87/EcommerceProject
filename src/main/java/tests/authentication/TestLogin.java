package tests.authentication;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.model.authentication.LoginAccount;
import test_data.util.DataObjectBuilder;
import test_flow.authentication.LoginFlow;
import tests.BaseTest;

public class TestLogin extends BaseTest {

    @Test(dataProvider = "getCorrectCredentials")
    public void testLoginWithCorrectCredentials(LoginAccount account){
        LoginFlow loginFlow = new LoginFlow(getDriver());
        loginFlow.navigateToUserPage();
        loginFlow.login(account.getEmail(), account.getPassword());
        loginFlow.verifyLoginWithCorrectCredentials();
    }

    @Test(dataProvider = "getCorrectCredentials")
    public void testLoginWithIncorrectCredentials(LoginAccount account){
        LoginFlow loginFlow = new LoginFlow(getDriver());
        loginFlow.navigateToUserPage();
        loginFlow.login(account.getEmail(), "abc");
        loginFlow.verifyLoginWithIncorrectCredentials();
    }

    @DataProvider
    public LoginAccount[] getCorrectCredentials() {
        String jsonFile = "/src/main/java/test_data/model/authentication/CorrectLoginAccount.json";
        return DataObjectBuilder.buildDataObject(jsonFile, LoginAccount[].class);
    }
}
