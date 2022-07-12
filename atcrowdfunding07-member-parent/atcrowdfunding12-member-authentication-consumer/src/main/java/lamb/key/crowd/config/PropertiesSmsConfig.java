package lamb.key.crowd.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoinYang
 * @date 2022/6/2 19:22
 */
// 创建发送短信的参数配置，方便在yml文件就可以配置
@Configuration
@ConfigurationProperties("send.sms")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertiesSmsConfig {
    public int appid;
    public String appKey;
    public String templateId;
    public String signContent;
    public int validTime;
}
