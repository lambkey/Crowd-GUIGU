package net.seehope.crowd.util;

import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import net.seehope.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 工具方法类
 * 手机验证码发送
 * @author JoinYang
 * @date 2022/3/17 11:57
 */
public class CrowdUtil {

    // 调用腾讯第三方接口发送短信验证码工具类
    public static JSONResult<String> sendSmsCode(
            int appid,
            String appKey,
            String templateId,
            String signContent,
            int validTime,
            String phoneNumber) {
        // 验证码
        String code = "";
        // 生成随机验证码
        Random random = new Random();

        for (int i = 0; i <= 5; i++) {

            code += random.nextInt(10);
        }
        try {

            String[] params = {code, Integer.toString(validTime)};

            SmsSingleSender sender = new SmsSingleSender(appid, appKey);

            SmsSingleSenderResult smsSingleSenderResult = sender.sendWithParam("86", phoneNumber, Integer.parseInt(templateId), params, signContent, "", "");

            System.out.println(smsSingleSenderResult);
            // 将调用发送短信返回的回调结果转为json对象
            JSONObject jsonObject = JSONObject.parseObject(smsSingleSenderResult.toString());

            System.out.println(jsonObject);

            // 返回结果result为0表示成功
            if (jsonObject.getString("result").equals("0")) {

                // 如果发送短信成功则把生成的验证码返回
                return JSONResult.successNeedMessage(code);

            }
            return JSONResult.FailureNeedMessage(jsonObject.getString("errmsg"));

        } catch (Exception e) {
            return JSONResult.FailureNeedMessage(e.getMessage());
        }

    }


    // md5明文加密
    public static String md5(String source) {
        // 1、判断source是否有效
        if (source == null || source.length() == 0) {
            // 2、如果不是有效字符串，就抛异常
            throw new RuntimeException(CrowdConstant.MASSAGE_STRING_INVALIDATE);
        }

        // 3、获取MessageDigest对象
        String algorithm = "md5";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // 4、获取明文字符对应的字节数组
            byte[] input = source.getBytes();

            // 5、执行加密
            byte[] output = messageDigest.digest(input);

            // 6、创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            //7、按照16进制将bigInteger的值转换为字符串
            int radix = 16;
            String encoding = bigInteger.toString(radix);

            return encoding;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    // 判断当前是否为Ajax请求
    public static boolean isAjaxRequestType(HttpServletRequest request) {
        //1、获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        // 2、判断
        return ((acceptHeader != null && acceptHeader.contains("application/json")) || (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest")));
    }

}
