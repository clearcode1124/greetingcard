package com.clearcode.greetingcard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clearcode.greetingcard.domain.GenParam;
import com.clearcode.greetingcard.service.CardService;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

  @Autowired
  private CardService cardService;

  @RequestMapping("/gen")
  public String gen() {
    return cardService.gen();
  }

  @PostMapping("/gen-single")
  public String gen(@RequestBody GenParam genParam) {
    return cardService.gen(genParam.getName(), genParam.getMessage());
  }
}
