package com.sejelli.messaging.services.YamamahServiceProvider;

import com.sejelli.messaging.domain.model.ErrorCode;
import com.sejelli.messaging.domain.model.Setting;
import com.sejelli.messaging.domain.model.SmsMessage;
import com.sejelli.messaging.services.SmsServicerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * Created by aibano on 10/16/2016.
 */
public class YamamahServiceProvider implements SmsServicerProvider {

    private String username;
    private String password;
    private static final Logger log = LoggerFactory.getLogger(YamamahServiceProvider.class);

    public YamamahServiceProvider(String username,
                                  String password){
        this.username = username;
        this.password = password;

    }

    @Override
    public ErrorCode send(SmsMessage smsMessage) {
        ErrorCode retVal = ErrorCode.UNKOWN;
        YamamahSmsMessage yamamahMessageFormat = new YamamahSmsMessage(this.username,
                this.password,
                smsMessage);


        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

            YamamahResponse response = restTemplate.postForObject("https://api.yamamah.com/SendSMS",
                    yamamahMessageFormat,
                    YamamahResponse.class);

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


    private ErrorCode getErrorCode(YamamahResponse response)
    {
        ErrorCode retVal = ErrorCode.NONE;

        switch (response.getStatus())
        {
            case 1:
                retVal = ErrorCode.NONE;
                break;
            case 10:
                retVal = ErrorCode.WRONG_PASSWORD;
                break;
            case 20:
                retVal = ErrorCode.INVALID_FORMAT;
                break;
            case 30:
                retVal = ErrorCode.INVALID_FORMAT;
                break;
            case 40:
                retVal = ErrorCode.NO_FUNDS;
                break;
            case 50:
                retVal = ErrorCode.WRONG_RECIEVER_NUMBER;
                break;
            case 51:
                retVal = ErrorCode.WRONG_RECIEVER_NUMBER;
                break;
            case 60:
                retVal = ErrorCode.WRONG_RECIEVER_NUMBER;
                break;
            case 70:
                retVal = ErrorCode.SERVICE_UNAVAILABLE;
                break;
            case 80:
                retVal = ErrorCode.INVALID_FORMAT;
                break;
            case 90:
                retVal = ErrorCode.INVALID_FORMAT;
                break;
            default:
                retVal = ErrorCode.UNKOWN;
                break;
        }
        return retVal;
    }
}
