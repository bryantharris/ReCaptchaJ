package recaptchaj;

import recaptchaj.rest.ReCaptchaAPI;

/**
 * Google's ReCaptcha API as a Java POJO.  An instance of this class is thread safe and suitable for sharing
 * across threads.
 * 
 * <h2>Usage</h2>
 * <pre><code>
 * ReCaptcha reCaptcha = new ReCaptchaFactory().create("<em>your secret key</em>");
 * ...
 * reCaptcha.siteVerify("<em>response from captcha</em>");
 * </code></pre>
 * @author bryant_harris
 *
 */
public class ReCaptcha {
    String secretKey;
    ReCaptchaAPI api;
        
    protected ReCaptcha(String secretKey, ReCaptchaAPI api) {
        this.secretKey = secretKey;
        this.api = api;
    }
        
    /**
     * @param response The user response token provided by reCAPTCHA, verifying the user on your site.
     */
    public ReCaptchaResponse siteVerify(String response) {
        return api.siteVerify(secretKey, response);
    }
        
    /**
     * @param response The user response token provided by reCAPTCHA, verifying the user on your site.
     * @param remoteip  <em>Optional.</em> The user's IP address.
     */
    public ReCaptchaResponse siteVerify(String response, String remoteip) {
        return api.siteVerify(secretKey, response, remoteip);
    }
}
