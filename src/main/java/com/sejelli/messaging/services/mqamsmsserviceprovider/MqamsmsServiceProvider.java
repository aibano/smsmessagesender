package com.sejelli.messaging.services.mqamsmsserviceprovider;

import com.sejelli.messaging.domain.model.ErrorCode;
import com.sejelli.messaging.domain.model.Setting;
import com.sejelli.messaging.domain.model.SmsMessage;
import com.sejelli.messaging.services.SmsServicerProvider;
import com.sejelli.messaging.services.YamamahServiceProvider.YamamahServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by aibano on 2/5/2017.
 */
public class MqamsmsServiceProvider implements SmsServicerProvider
{
    private String username;
    private String password;

    private static final Logger log = LoggerFactory.getLogger(YamamahServiceProvider.class);
    private final String USER_AGENT = "Mozilla/5.0";

    public MqamsmsServiceProvider(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public ErrorCode send(SmsMessage smsMessage) {
        ErrorCode retVal = ErrorCode.UNKOWN;
        MqamsmsSmsMessage mqamsmsSmsMessage = new MqamsmsSmsMessage(this.username,
                this.password,
                smsMessage);


        try {
            String url = mqamsmsSmsMessage.getGetUrl();
            String response = sendGet(url);
            retVal = getErrorCode(response);
        } catch (Exception ex){
            log.error("Exception Occured during sending the message");
            log.error(ex.getStackTrace().toString());
            retVal = ErrorCode.UNKOWN;
        }
        return retVal;
    }

    @Override
    public SmsServicerProvider setSetting(Setting setting) {
        return null;
    }

    // HTTP GET request
    private String sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private ErrorCode getErrorCode(String response)
    {
        ErrorCode retVal = ErrorCode.NONE;

        switch (response)
        {
            case "1":
                retVal = ErrorCode.NONE;
                break;
            case "Invalid login":
                retVal = ErrorCode.WRONG_PASSWORD;
                break;
            case "Missing or Empty Field":
                retVal = ErrorCode.INVALID_FORMAT;
                break;
            case "Invalid Message Language":
                retVal = ErrorCode.INVALID_FORMAT;
                break;
            case "Insufficent Balance":
                retVal = ErrorCode.NO_FUNDS;
                break;
            case "Sender name is not activated":
                retVal = ErrorCode.UNKOWN_SENDER_NAME;
                break;
            case "Invalid Sender":
                retVal = ErrorCode.INVALID_FORMAT;
                break;
            default:
                retVal = ErrorCode.UNKOWN;
                break;
        }
        return retVal;
    }
}
