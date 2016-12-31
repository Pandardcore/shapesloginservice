package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.*;

import views.html.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class LoginController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok("This is the login service of Shapes");
    }

    public Result login() {
        if(request().body().asJson() != null) {
            JsonNode loginNode = request().body().asJson().findValue("login");
            if(loginNode != null) {
                return logUser(loginNode.asText());
            } else {
                return badRequest("Login parameter missing");
            }
        } else {
            return badRequest("Not a JSON format");
        }
    }

    private Result logUser(String login) {
        System.out.println("Sending login " + login + " to queue");
        return ok("Hello " + login + ". You've been logged !");
    }

}
