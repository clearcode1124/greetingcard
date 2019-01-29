package com.clearcode.greetingcard.qiniu.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clearcode.greetingcard.config.QiNiuConfig;
import com.clearcode.greetingcard.qiniu.service.QiNiuService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * 
 * @company: hua9group
 * @author wdChen
 * @date:2019年1月29日 上午11:01:00
 * @Description:
 */
@Service
public class QiNiuServiceImpl implements QiNiuService {

  @Autowired
  private UploadManager uploadManager;

  @Autowired
  private QiNiuConfig qiNiuConfig;

  @Override
  public Response upload(byte[] data, String key) throws QiniuException {
    return uploadManager.put(data, key, uploadToken());
  }

  @Override
  public Response upload(File file, String key) throws QiniuException {
    return uploadManager.put(file, key, uploadToken());
  }

  public String uploadToken() {
    Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
    return auth.uploadToken(qiNiuConfig.getBucKey());
  }
}

