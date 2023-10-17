package api.test;

import api.Endpoints.UserEndPoints;
import api.Payload.User;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import api.utilities.DataProviders;
import org.testng.asserts.SoftAssert;

public class DataDrivenTest {
    SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void TestPostUsers(String userid, String username, String firstname, String lastname, String useremail, String password, String phoneno) {

        User payload = new User();
        payload.setId(Integer.parseInt(userid));
        payload.setUsername(username);
        payload.setFirstname(firstname);
        payload.setLastname(lastname);
        payload.setEmail(useremail);
        payload.setUsername(username);

        Response response = UserEndPoints.createUser((payload));
        response.then().log().all();
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertAll();
    }

    @Test(priority = 2, dataProvider = "UserName", dataProviderClass = DataProviders.class)
    public  void testDeleteUserByName(String UserName){

        Response response = UserEndPoints.deleteUser(UserName);
        softAssert.assertEquals(response.getStatusCode(),200);


    }

}

