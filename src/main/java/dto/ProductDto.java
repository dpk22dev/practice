package dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDto {
  public String productName;
  public String productDesc;
  public Long priceId;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  public Long[] categories;
}
