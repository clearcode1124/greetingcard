package com.clearcode.greetingcard.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @company: hua9group
 * @author wdChen
 * @date:2019年1月29日 上午11:35:14
 * @Description:
 */
@Data
public class GenParam implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;

  private String message;
}

