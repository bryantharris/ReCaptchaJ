package recaptchaj.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import recaptchaj.ReCaptchaResponse;

/**
 * Description of the REST interface.
 * @author bryant_harris
 */
@Path("/recaptcha/api/")
@Produces(MediaType.APPLICATION_JSON)
public interface ReCaptchaAPI {
    @POST
    @Path("siteverify")
    public ReCaptchaResponse siteVerify(@FormParam("secret") String secret, @FormParam("response") String response);
        
    @POST
    @Path("siteverify")
    public ReCaptchaResponse siteVerify(@FormParam("secret") String secret, @FormParam("response") String response, @FormParam("remoteip") String remoteip);
}
