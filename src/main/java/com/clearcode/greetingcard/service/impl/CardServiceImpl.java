package com.clearcode.greetingcard.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.clearcode.greetingcard.domain.Contact;
import com.clearcode.greetingcard.domain.Greeting;
import com.clearcode.greetingcard.qiniu.service.QiNiuService;
import com.clearcode.greetingcard.service.CardService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiniu.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

  public static final String DIAGONAL = "/";

  @Value("${hk.img.images-path}")
  private String imagesPath;

  @Value("${hk.img.images-zip-path}")
  private String imagesZipPath;

  @Value("${hk.res.img-templete-path}")
  private String imgTempletePath;

  @Value("${hk.res.contact-path}")
  private String contactPath;

  @Value("${hk.res.greeting-path}")
  private String greetingPath;

  @Value("${hk.res.ttf-path}")
  private String ttfPath;

  @Autowired
  private QiNiuService qiNiuService;

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  public String gen() {
    try {
      List<Contact> contacts = getContacts();
      List<Greeting> greetings = getGreetings();
      int index = 0;
      for (Contact contact : contacts) {
        if (index >= greetings.size()) {
          index = 0;
        }
        editImage(contact.getRemarkName(), greetings.get(index).getMessage());
        index++;
      }
      File srcFile = new File(imagesPath);
      File[] files = srcFile.listFiles();
      ArrayList filesToAdd = new ArrayList(Arrays.asList(files));
      ZipParameters parameters = new ZipParameters();
      String hkImagesZipName = System.currentTimeMillis() + ".zip";
      String hkImagesZipPath = imagesZipPath + DIAGONAL + hkImagesZipName;
      ZipFile zipFile = new ZipFile(hkImagesZipPath);
      zipFile.addFiles(filesToAdd, parameters);
      qiNiuService.upload(new File(hkImagesZipPath), hkImagesZipName);
      deleteDir(imagesPath);
      deleteDir(hkImagesZipPath);
    } catch (IOException | FontFormatException | ZipException e) {
      log.error("生成贺卡失败:{}", e);
      return "fail";
    }
    return "success";
  }

  @Override
  public String gen(String name, String message) {
    try {
      editImage(name, message);
      String imageName = String.format("hk1-%s.png", name);
      qiNiuService.upload(new File(imagesPath + DIAGONAL + imageName), imageName);
      deleteDir(imagesPath);
    } catch (IOException | FontFormatException e) {
      log.error("生成贺卡失败:{}", e);
      return "fail";
    }
    return "success";
  }

  public List<Contact> getContacts() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(new File(contactPath), new TypeReference<List<Contact>>() {});
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  public List<Greeting> getGreetings() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(new File(greetingPath), new TypeReference<List<Greeting>>() {});
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  public void editImage(String name, String message) throws IOException, FontFormatException {
    BufferedImage image = ImageIO.read(new File(imgTempletePath));
    Graphics graphics = image.getGraphics();
    graphics.setColor(Color.BLACK);
    graphics.setFont(Font.createFont(Font.TRUETYPE_FONT, new File(ttfPath)).deriveFont(120f));
    int startX = 750;
    int nextX = 910;
    String username = name;
    if (!StringUtils.isNullOrEmpty(name)) {
      username = name.replaceAll("\\d+", "");
    }
    graphics.drawString(username + "：", 150, startX);
    int length = message.length();
    int index = 0;
    for (int i = 0; i <= (length + 2) / 11; i++) {
      if (i == 0) {
        if (length <= 9) {
          graphics.drawString(message.substring(index, length), 390, nextX + i * 160);
        } else {
          graphics.drawString(message.substring(index, 9), 390, nextX + i * 160);
        }
        index += 9;
      } else {
        if ((i + 1) * 11 >= length) {
          graphics.drawString(message.substring(index, length), 150, nextX + i * 160);
        } else {
          graphics.drawString(message.substring(index, (i + 1) * 11 - 2), 150, nextX + i * 160);
        }
        index += 11;
      }
    }
    graphics.dispose();
    String genImgPath = imagesPath + DIAGONAL + "hk1-%s.png";
    ImageIO.write(image, "png", new File(String.format(genImgPath, name)));
  }

  public boolean deleteDir(String path) {
    File file = new File(path);
    if (!file.exists()) {// 判断是否待删除目录是否存在
      return false;
    }
    if (!file.isDirectory()) {
      return file.delete();
    }
    String[] content = file.list();// 取得当前目录下所有文件和文件夹
    for (String name : content) {
      File temp = new File(path, name);
      if (temp.isDirectory()) {
        deleteDir(temp.getAbsolutePath());// 递归调用，删除目录里的内容
      } else {
        if (temp.delete()) {
          continue;
        }
      }
    }
    return true;
  }
}
