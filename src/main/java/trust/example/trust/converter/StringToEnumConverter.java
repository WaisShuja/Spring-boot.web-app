package trust.example.trust.converter;

import org.springframework.core.convert.converter.Converter;
import trust.example.trust.beans.Gender;

public class StringToEnumConverter implements Converter<String, Gender> {

//    Simple Converter

     @Override
    public Gender convert(String gender){
         if(gender.equalsIgnoreCase("Male")){
             return Gender.MALE;
         }
         else if(gender.equalsIgnoreCase("Female")){
             return Gender.FEMALE;
         }
         else{
             return Gender.OTHER;
         }
     }
}
