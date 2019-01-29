package com.clearcode.greetingcard.service;

public interface CardService {

  String gen();

  String gen(String name, String message);
}
