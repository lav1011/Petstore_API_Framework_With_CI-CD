package api.Endpoints;

import api.Payload.User;
import io.restassured.response.Response;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;


public class UserEndPoints {


    public static Response createUser(User payload) {
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when().post(Routes.Post_user_url);

        return response;
    }

    public static Response readUser(String username) {
        Response response = given().pathParam("username",username).when().get(Routes.Get_user_url);

        return response;
    }

    public static Response UpdateUser(String username,User payload) {
        Response response = given().accept(ContentType.JSON).pathParam("username",username).body(payload).when().put(Routes.Update_user_url);

        return response;
    }

    public static Response deleteUser(String username) {
        Response response = given().pathParam("username",username).when().delete(Routes.Delete_user_url);

        return response;
    }
}
