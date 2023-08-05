package com.rasha.movieInfo.bean;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvInfoDto {

   @CsvBindByName(column = "Year", required = true)
    private String year;
   @CsvBindByName(column = "Category", required = true)
    private String category;
   @CsvBindByName(column = "Nominee", required = true)
    private String nominee;
   @CsvBindByName(column = "Additional Info")
    private String additionalInfo;
   @CsvBindByName(column = "Won?", required = true)
    private String won;

}
