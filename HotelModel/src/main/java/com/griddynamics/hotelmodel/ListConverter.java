package com.griddynamics.hotelmodel;

import com.griddynamics.hotelmodel.rooms.Properties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ListConverter implements AttributeConverter<List<Properties>, String> {
  private static final String SPLIT_CHAR = ",";

  @Override
  public String convertToDatabaseColumn(List<Properties> propertiesList) {
    return String.join(SPLIT_CHAR, propertiesList.toString());
  }

  @Override
  public List<Properties> convertToEntityAttribute(String string) {
    String subString = string.replaceAll("[\\[\\]\\s]", "");
    List<Properties> propertiesList = new ArrayList<>();
    String[] split = subString.split(SPLIT_CHAR);
    for (String s : split) {
      propertiesList.add(Enum.valueOf(Properties.class, s));
    }
    return propertiesList;
  }
}
