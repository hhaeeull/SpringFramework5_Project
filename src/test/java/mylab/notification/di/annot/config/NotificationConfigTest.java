package mylab.notification.di.annot.config;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.NotificationConfig;
import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.SmsNotificationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationConfig.class)
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    public void testUserService() {

        // 1. NotificationManager 객체 확인
        assertNotNull(notificationManager);

        // 2. 이메일 서비스 확인
        assertNotNull(notificationManager.getEmailService());

        EmailNotificationService emailService =
            (EmailNotificationService) notificationManager.getEmailService();

        assertEquals("smtp.gmail.com", emailService.getSmtpServer());
        assertEquals(587, emailService.getPort());

        // 3. SMS 서비스 확인
        assertNotNull(notificationManager.getSmsService());

        SmsNotificationService smsService =
            (SmsNotificationService) notificationManager.getSmsService();

        assertEquals("SKT", smsService.getProvider());

        // 4. 이메일 알림 실행
        notificationManager.sendNotificationByEmail("테스트 이메일");

        // 5. SMS 알림 실행
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}