package me.kzv.elasticsearch.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Subway {
  @CsvBindByPosition(position = 0)
  private String code;

  @CsvBindByPosition(position = 1)
  private String station;

  @CsvBindByPosition(position = 2)
  private String line;

  @CsvBindByPosition(position = 3)
  private String excode;
}
