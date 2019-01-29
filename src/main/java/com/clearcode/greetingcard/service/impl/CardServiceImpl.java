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
import org.springframework.stereotype.Service;

import com.clearcode.greetingcard.domain.Contact;
import com.clearcode.greetingcard.domain.Greeting;
import com.clearcode.greetingcard.qiniu.service.QiNiuService;
import com.clearcode.greetingcard.service.CardService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

  public static final String HK_IMAGES_PATH = "src/main/resources/static/hks";

  public static final String HK_IMAGES_ZIP_PATH = "src/main/resources/static/";

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
        if (index == 5) {
          break;
        }
        if (index >= greetings.size()) {
          index = 0;
        }
        editImage(contact.getRemarkName(), greetings.get(index).getMessage());
        index++;
      }
      File srcFile = new File(HK_IMAGES_PATH);
      File[] files = srcFile.listFiles();
      ArrayList filesToAdd = new ArrayList(Arrays.asList(files));
      ZipParameters parameters = new ZipParameters();
      String hkImagesZipName = System.currentTimeMillis() + ".zip";
      String hkImagesZipPath = HK_IMAGES_ZIP_PATH + hkImagesZipName;
      ZipFile zipFile = new ZipFile(hkImagesZipPath);
      zipFile.addFiles(filesToAdd, parameters);
      qiNiuService.upload(new File(hkImagesZipPath), hkImagesZipName);
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
      qiNiuService.upload(new File("src/main/resources/static/hks/" + imageName), imageName);
    } catch (IOException | FontFormatException e) {
      return "fail";
    }
    return "success";
  }

  public List<Contact> getContacts() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(new File("src/main/resources/static/contact.json"),
          new TypeReference<List<Contact>>() {});
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  public List<Greeting> getGreetings() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(new File("src/main/resources/static/greetings.json"),
          new TypeReference<List<Greeting>>() {});
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  public void editImage(String name, String message) throws IOException, FontFormatException {
    BufferedImage image = ImageIO.read(new File("src/main/resources/static/hk1.png"));
    Graphics graphics = image.getGraphics();
    graphics.setColor(Color.BLACK);
    graphics
        .setFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/static/1.ttf"))
            .deriveFont(120f));
    int startX = 750;
    int nextX = 910;
    graphics.drawString(name + "：", 150, startX);
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
    ImageIO.write(image, "png",
        new File(String.format("src/main/resources/static/hks/hk1-%s.png", name)));
  }
}
