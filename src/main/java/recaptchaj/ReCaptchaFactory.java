package recaptchaj;

import recaptchaj.rest.ReCaptchaAPI;
import si.mazi.rescu.ClientConfig;
import si.mazi.rescu.RestProxyFactory;

/**
 * Factory for creating an instance of the API interface as a POJO.
 * 
 * <h2>Usage</h2>
 * <pre><code>
 * ReCaptcha reCaptcha = new ReCaptchaFactory().create("<em>your secret key</em>");
 * </code></pre>
 * @author bryant_harris
 *
 */
public class ReCaptchaFactory {
    public ReCaptcha create(String secretKey) {
        ReCaptchaAPI api =  RestProxyFactory.createProxy(ReCaptchaAPI.class, "https://www.google.com", new ClientConfig());
        return new ReCaptcha(secretKey, api);
    }
}
