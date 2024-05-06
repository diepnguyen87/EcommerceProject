package tests.registration;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.model.registration.RegistrationAccount;
import test_data.util.DataObjectBuilder;
import test_flow.register.RegistrationFlow;
import tests.BaseTest;

public class TestRegistration extends BaseTest {

    @Test(dataProvider = "getRegistrationAccount")
    public void testRegister(RegistrationAccount account) {
        RegistrationFlow registrationFlow = new RegistrationFlow(getDriver(), account.getEmail(), account.getName(), account.getPhone(), account.getPassword());

        registrationFlow.navigateToUserPage();
        registrationFlow.clickOnRegisterBtn();
        registrationFlow.registerAccount();
        registrationFlow.verifySucceedRegistration();
    }

    @DataProvider
    public RegistrationAccount[] getRegistrationAccount() {
        String jsonFile = "/src/main/java/test_data/model/registration/RegistrationAccount.json";
        return DataObjectBuilder.buildDataObject(jsonFile, RegistrationAccount[].class);
    }
}
