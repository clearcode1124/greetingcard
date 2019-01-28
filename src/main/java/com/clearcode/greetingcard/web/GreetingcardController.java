package com.clearcode.greetingcard.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @company: hua9group
 * @author wdChen
 * @date:2019年1月28日 下午3:22:19
 * @Description:
 */
@RestController
@RequestMapping("/api/v1/cards")
public class GreetingcardController {

  @RequestMapping("/gen")
  public String gen() {
    try {
      BufferedImage image = ImageIO.read(new File("src/main/resources/static/1.jpg"));
      Graphics graphics = image.getGraphics();
      graphics.setColor(Color.BLACK);
      graphics
          .setFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/static/1.ttf"))
              .deriveFont(40f));
      graphics.drawString("哈哈", 50, 150);
      graphics.dispose();
      ImageIO.write(image, "png", new File("src/main/resources/static/2.jpg"));
    } catch (IOException | FontFormatException e) {
      return "fail";
    }
    return "success";
  }
}

