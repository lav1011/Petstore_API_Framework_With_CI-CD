package api.test;

import api.Endpoints.UserEndPoints;
import api.Payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
//import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserTests {

    Faker faker;
    User userPayLoad;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void setupData() throws Exception {
        faker = new Faker();
        userPayLoad = new User();
        userPayLoad.setId(faker.idNumber().hashCode());
        userPayLoad.setUsername(faker.name().username());
        userPayLoad.setFirstname(faker.name().firstName());
        userPayLoad.setLastname(faker.name().lastName());
        userPayLoad.setEmail(faker.internet().safeEmailAddress());
        userPayLoad.setPassword(faker.internet().password(5, 10));
        userPayLoad.setPhone(faker.phoneNumber().cellPhone());

    }

    @Test(priority = 1)
    public void testPostUser() {
        Response response = UserEndPoints.createUser((userPayLoad));
        response.then().log().all();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertAll();
//        Assert.assertEquals(response.body(matchesJsonSchemaInClasspath()));
    }

    @Test(priority = 2)
    public void testGetUserByName() {
        Response response = UserEndPoints.readUser(this.userPayLoad.getUsername());
        response.then().log().all();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(this.userPayLoad.getUsername(), response.body().jsonPath().get("username").toString());
        softAssert.assertAll();
    }

    @Test (priority = 3)
    public void testUpdateUserByName(){

        userPayLoad.setFirstname(faker.name().firstName());
        userPayLoad.setLastname(faker.name().lastName());
        userPayLoad.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.UpdateUser(this.userPayLoad.getUsername(),userPayLoad);
        response.then().log().all();
        softAssert.assertEquals(response.getStatusCode(),415);
        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayLoad.getUsername());
        softAssert.assertEquals(this.userPayLoad.getUsername(),responseAfterUpdate.body().jsonPath().get("username"));
        softAssert.assertAll();

    }

    @Test (priority = 4)
    public void testDeleteUserByName(){

        Response response = UserEndPoints.deleteUser(this.userPayLoad.getUsername());
        softAssert.assertEquals(response.getStatusCode(),200);
    }
}