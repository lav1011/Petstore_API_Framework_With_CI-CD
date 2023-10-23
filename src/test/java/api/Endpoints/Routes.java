package api.Endpoints;

public interface Routes {

    public static  String base_url="https://petstore.swagger.io/v2";

     //USER MODULE
    String Post_user_url_withArray="/user/createWithArray";
    String Post_user_url="/user";
    String Get_user_url=base_url+"/user/{username}";
    String Update_user_url=base_url+"/user/{username}";
    String Delete_user_url=base_url+"/user/{username}";
    String Login_user_url=base_url+"/user/login";

    //Store Module

    String Post_store_url="/store/order";
    String Get_store_url="/store/order/{orderId}";
    String Delete_store_url="/store/order/{orderId}";

    //Pet Module automation*#@!1011



}
