package com.clearcode.greetingcard.qiniu.service;

import java.io.File;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

/**
 * 
 * @company: hua9group
 * @author wdChen
 * @date:2019年1月29日 上午11:00:38
 * @Description:
 */
public interface QiNiuService {

  Response upload(byte[] data, String key) throws QiniuException;

  Response upload(File file, String key) throws QiniuException;
}

