package com.cakeshopspringboot.utils;

import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 * @Author 张伟雄
 * @Date 2022/12/12
 **/
public class EmailUtils {
    @Test
    public  void qqmailtest()   {
        String s = EmailUtils.codeFen();
        System.out.println(s);

        try {
            testMail("QQ邮箱验证密码",s,"3102036223@qq.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //生成验证码
    public static String codeFen(){
        String str = "0123456789";
        //将字符串转换为一个新的字符数组。
        char[] VerificationCodeArray = str.toCharArray();
        Random random = new Random();
        int count = 0;//计数器
        StringBuilder stringBuilder = new StringBuilder();
        while(true) {
            //随机生成一个随机数
            int index = random.nextInt(VerificationCodeArray.length);
            char c = VerificationCodeArray[index];
            //限制四位不重复数字
            if (stringBuilder.indexOf(c + "") == -1) {
                stringBuilder.append(c);
                //计数器加1
                count++;
            }
            //当count等于4时结束，随机生成四位数的验证码
            if (count == 4) {
                break;
            }
        }
        return stringBuilder.toString();
    }
    public static void testMail (String topic,String msg,String sendto) throws Exception {
        // 给用户发送邮件的邮箱
        final String from = "3036354190@qq.com";
        // 邮箱的用户名
        final String username = "3036354190@qq.com";
        // 邮箱授权码，刚刚保存的授权码，不是qq密码
        final String password = "aczwsvvgafjmddic";
        // 发送邮件的服务器地址，QQ服务器
        final String host = "smtp.qq.com";
        // 接收人邮箱
        final String to =  sendto;
        // 邮件主题
        final String title = topic;

        // 使用QQ邮箱时配置
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");    // 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp");      // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true");      // 需要验证用户名和密码


        // 关于QQ邮箱，还要设置SSL加密，其他邮箱不需要  可删减
//            MailSSLSocketFactory sf = new MailSSLSocketFactory();
//            sf.setTrustAllHosts(true);
//            prop.put("mail.smtp.ssl.enable", "true");
//            prop.put("mail.smtp.ssl.socketFactory", sf);

        // 创建定义整个邮件程序所需的环境信息的 Session 对象，QQ才有，其他邮箱就不用了
        Session session = Session.getDefaultInstance(prop, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮箱用户名，授权码
                return new PasswordAuthentication(username, password);
            }
        });

        // 开启 Session 的 debug 模式，这样就可以查看程序发送 Email 的运行状态
        session.setDebug(false);  //开发时打卡， 发布关闭

        // 通过 session 得到 transport 对象
        Transport ts = session.getTransport();

        // 使用邮箱的用户名和授权码连上邮箱服务器
        ts.connect(host, username, password);

        // 创建邮件，写邮件
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from)); // 指明邮件的发件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));   // 指明邮件的收件人
        message.setSubject(title);     // 邮件主题
        message.setContent(msg, "text/html;charset=utf-8");    // 邮件内容

        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        System.out.println("验证码发送成功");
        // 释放资源
        ts.close();
    }
}
