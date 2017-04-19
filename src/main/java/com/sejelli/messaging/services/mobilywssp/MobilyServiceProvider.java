package com.sejelli.messaging.services.mobilywssp;

import com.sejelli.messaging.domain.model.ErrorCode;
import com.sejelli.messaging.domain.model.Setting;
import com.sejelli.messaging.domain.model.SmsMessage;
import com.sejelli.messaging.services.SmsServicerProvider;
import com.sejelli.messaging.services.YamamahServiceProvider.YamamahServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by aibano on 4/19/2017.
 */
public class MobilyServiceProvider implements SmsServicerProvider {

    private String username = "";
    private String password = "";
    private static final Logger log = LoggerFactory.getLogger(YamamahServiceProvider.class);

    public MobilyServiceProvider(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public ErrorCode send(SmsMessage smsMessage) {
        ErrorCode retVal = ErrorCode.UNKOWN;
        MobilySmsMessage message = new MobilySmsMessage(this.username,
                this.password,
                smsMessage.getReceiver(),
                smsMessage.getSender(),
                smsMessage.getMessage());

        try{
            URL url;
            URLConnection urlConnection;
            DataOutputStream outStream;
            // Create connection
            url = new URL(message.getUrl());
            urlConnection = url.openConnection();
            ((HttpURLConnection)urlConnection).setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Content-Length", ""+ message.getMessageBody().length());
            urlConnection.setRequestProperty("User-agent","Mozilla/4.0");
            // Create I/O streams
            outStream = new DataOutputStream(urlConnection.getOutputStream());
            // Send request
            outStream.writeBytes(message.getMessageBody());
            outStream.flush();
            outStream.close();
            // Get Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            // - For debugging purposes only!
            String buffer = "";
            String line = "";
            while((line = rd.readLine()) != null) {
                buffer += line;
            }
            retVal = getErrorCode(Integer.parseInt(buffer));
            // Close I/O streams
            rd.close();
            outStream.close();
        }catch (Exception ex){
            log.error("Exception Occured during sending the message");
            log.error(ex.getStackTrace().toString());
            retVal = ErrorCode.UNKOWN;
        }

        return retVal;
    }

    private ErrorCode getErrorCode(int errorCode) {
        ErrorCode retVal = ErrorCode.NONE;

        switch (errorCode)
        {
            case 1:
                retVal = ErrorCode.NONE;
                break;
            case 2:
                retVal = ErrorCode.NO_FUNDS;
                break;
            case 3:
                retVal = ErrorCode.NOT_ENOUGH_FUNDS;
                break;
            case 4:
                retVal = ErrorCode.UNKNOWN_MOBILE_NUMBER;
                break;
            case 5:
                retVal = ErrorCode.WRONG_PASSWORD;
                break;
            case 6:
                retVal = ErrorCode.SERVICE_UNAVAILABLE;
                break;
            case 13:
                retVal = ErrorCode.SENDER_NAME_REJECTED;
                break;
            case 14:
                retVal = ErrorCode.UNKOWN_SENDER_NAME;
                break;
            case 15:
                retVal = ErrorCode.WRONG_RECIEVER_NUMBER;
                break;
            case 16:
                retVal = ErrorCode.EMPTY_SENDER_NAME;
                break;
            case 17:
                retVal = ErrorCode.WRONG_UNICODE;
                break;
            case 18:
                retVal = ErrorCode.USER_BLOCKED;
                break;
            case 19:
                retVal = ErrorCode.INVALID_FORMAT;
                break;
            default:
                retVal = ErrorCode.UNKOWN;
                break;
        }
        return retVal;
    }


    @Override
    public SmsServicerProvider setSetting(Setting setting) {
        return null;
    }
}
