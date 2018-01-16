package controllers;

import com.auth0.beans.Any;
import com.auth0.utils.JWTUtils;

import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;

import java.util.HashMap;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok("Olaha LavaOne");
    }

    public Result returnMap(){
        HashMap<String, Object> result = new HashMap<String, Object>(){
            {
                put("attendes", 15);
                put("when", "In a day off!!!");
            }
        };

        return ok(Json.toJson(result));
    }

    public Result returnObj(){
        Any any = new Any("LavaOne 2018", 15);
        return ok(Json.toJson(any));
    }

    public Result catchObj(){
        JsonNode jsonNode = request().body().asJson();
        Any any = Json.fromJson(jsonNode, Any.class);

        return ok(Json.toJson(any));
    }

    public Result returnJWT(){
        Any any = new Any("LavaOne 2018", 15);
        return ok(JWTUtils.generateJWT(any));
    }

    public Result validateJWT(){
        String token = request().body().asText();
        return ok("Is a valid token: " + JWTUtils.validateJWT(token));
    }

}
