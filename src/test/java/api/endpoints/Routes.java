package api.endpoints;


/*Swagger Url --> https://petstore.swagger.io
Create user(POST) --> https://petstore.swagger.io/#/v2/user
Get User (Get) --> https://petstore.swagger.io/#/user/{username}
Update User (Put) --> https://petstore.swagger.io/#/user/{username}
Delete User (Delete) --> https://petstore.swagger.io/#/user/{username}
*/

public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	
	// * user module
	
	public static String user_POST_url = base_url+"/user";
	public static String user_GET_url =base_url+"/user/{username}";
	public static String user_PUT_url =base_url+"/user/{username}";
	public static String user_DELETE_url =base_url+"/user/{username}";
	
	// * Store module
	
	// * Pet module

}
