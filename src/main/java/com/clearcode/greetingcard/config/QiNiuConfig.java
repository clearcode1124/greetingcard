package com.clearcode.greetingcard.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qiniu.common.Zone;
import com.qiniu.storage.UploadManager;

import lombok.Data;

/**
 * 
 * @company: hua9group
 * @author wdChen
 * @date:2018年10月15日 下午3:47:08
 * @Description:
 */
@Configuration
@ConfigurationProperties(prefix = "qi-niu")
@Data
public class QiNiuConfig {

  private String accessKey;
  private String secretKey;
  private String bucKey;
  private String domainName;

  @Bean
  public UploadManager uploadManager() {
    // 构造一个带指定Zone对象的配置类
    com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Zone.zone2());
    return new UploadManager(cfg);
  }

}

