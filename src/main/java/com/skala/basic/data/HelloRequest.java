package com.skala.basic.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HelloRequest {
  private int id;
  private String name;
  private String address;
}
