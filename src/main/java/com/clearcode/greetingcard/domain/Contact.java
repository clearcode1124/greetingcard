package com.clearcode.greetingcard.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Contact implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("city")
  private String city;

  @JsonProperty("country")
  private String country;

  @JsonProperty("head_img")
  private String headImg;

  @JsonProperty("nick_name")
  private String nickName;

  @JsonProperty("province")
  private String province;

  @JsonProperty("remark_name")
  private String remarkName;

  @JsonProperty("sex")
  private String sex;

  @JsonProperty("user_name")
  private String userName;

  @JsonProperty("wxid")
  private String wxid;
}
