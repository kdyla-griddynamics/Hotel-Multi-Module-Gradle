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
    List<Properties> propertiesList = new ArrayList<>();
    String[] split = string.split(SPLIT_CHAR);
    for (int i = 0; i < split.length; i++) {
      propertiesList.add(Enum.valueOf(Properties.class, split[i]));

    }
    return propertiesList;
  }
}
