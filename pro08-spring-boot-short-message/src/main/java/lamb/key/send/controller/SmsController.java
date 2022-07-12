package lamb.key.send.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lamb.key.send.entity.Sms;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author JoinYang
 * @date 2022/6/1 19:27
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @RequestMapping("/sendCode")
    public void sms(@RequestBody Sms sms){

        // SDK
        int appid = 1400686692;

        // SDK
        String appKey = "966349078da0de20484d51a5b25297e4";

        // 信息模板id
        int templatedId = 1425202;

        // 签名内容
        String smsSign = "个人项目部署";

        try {

            String[] params = {sms.getCode(),Integer.toString(sms.getMin())};

            SmsSingleSender sender =new SmsSingleSender(appid,appKey);

            SmsSingleSenderResult smsSingleSenderResult = sender.sendWithParam("86", sms.getPhoneNumber(), templatedId, params, smsSign, "", "");

            // 转为json对象
            JSONObject jsonObject = JSONObject.parseObject(smsSingleSenderResult.toString());

            // 返回结果result为0表示成功
            jsonObject.getString("result");

            System.out.println(smsSingleSenderResult);

        }catch (HTTPException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
