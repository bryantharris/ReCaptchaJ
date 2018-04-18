package recaptchaj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API Response.
 * @author bryant_harris
 *
 */
public class ReCaptchaResponse {
    boolean success;
    String timestamp;
    String hostname;
    String apkPackageName;
    String[] errorCodes;
    
    public ReCaptchaResponse(
              @JsonProperty("success") boolean success, 
              @JsonProperty("challenge_ts") String timestamp,
              @JsonProperty("hostname") String hostname,
              @JsonProperty("apk_package_name") String apkPackageName,
              @JsonProperty("error-codes") String[] errorCodes) {
        this.success = success;
        this.timestamp = timestamp;
        this.hostname = hostname;
        this.apkPackageName = apkPackageName;
        this.errorCodes = errorCodes;
    }
    
    public boolean isSuccess() {
        return success;
    }

    public String getChallengeTSRaw() {
        return timestamp;
    }

    public Date getChallengeTS() {
        if ( getChallengeTSRaw() == null )
            return null;
                
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ");
            return dateFormat.parse(getChallengeTSRaw());
        }
        catch (ParseException e) {
            throw new RuntimeException("Failed to properly parse date '" + getChallengeTSRaw() + "'", e);
        }
    }

    public String getHostname() {
        return hostname;
    }

    public String getApkPackageName() {
        return apkPackageName;
    }

    public String[] getErrorCodesRaw() {
        return errorCodes;
    }
    
    public ErrorCode[] getErrorCodes() {
        ErrorCode[] codes = new ErrorCode[getErrorCodesRaw().length];
        for ( int i=0; i<codes.length; i++ )
            codes[i] = ErrorCode.fromCode(getErrorCodesRaw()[i]);
        return codes;
    }
        
    @Override
    public String toString() {
        return "ReCaptchaResponse [success=" + success + ", timestamp=" + timestamp + ", hostname=" + hostname
            + ", apkPackageName=" + apkPackageName + ", errorCodes=" + Arrays.toString(errorCodes) + "]";
    }

    public enum ErrorCode {
        missingInputSecret,
        invalidInputSecret,
        missingInputResponse,
        invalidInputResponse,
        badRequest,
        
        unreckognized;
        
        public static ErrorCode fromCode(String code) {
            if ( code == null )
                return unreckognized;
            
            if ( code.equals("missing-input-secret"))
                return missingInputSecret;
            if ( code.equals("invalid-input-secret"))
                return invalidInputSecret;
            if ( code.equals("missing-input-response"))
                return missingInputResponse;
            if ( code.equals("invalid-input-response"))
                return invalidInputResponse;
            if ( code.equals("bad-request"))
                return badRequest;
            
            return unreckognized;
        }
                
        public String toDescription() {
            switch(this) {
            case missingInputSecret:
                return "The secret parameter is missing.";
                
            case invalidInputSecret:
                return "The secret parameter is invalid or malformed.";
                
            case missingInputResponse:
                return "The response parameter is missing.";
                
            case invalidInputResponse:
                return "The response parameter is invalid or malformed.";
                
            case badRequest:
                return "The request is invalid or malformed.";
                
            default:
            case unreckognized:
                return "The Error Code was not one provided by the documentation"; 
            }
        }
    }
}
