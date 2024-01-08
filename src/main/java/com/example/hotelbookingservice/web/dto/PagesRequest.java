package com.example.hotelbookingservice.web.dto;

import com.example.hotelbookingservice.validation.PageFilterValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PageFilterValid
public class PagesRequest {

  private Integer pageSize;

  private Integer pageNumber;
}
