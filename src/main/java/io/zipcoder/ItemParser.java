package io.zipcoder;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    public List<Item> parseItemList(String valueToParse) {

      String myString = valueToParse.toLowerCase();
      List<Item> myList = new ArrayList<>();
      Pattern pattern = Pattern.compile("(?=).*?(?>##)");
      Matcher matcher = pattern.matcher(myString);
      while(matcher.find()){
          try {
              myList.add(parseSingleItem(matcher.group(0)));
          }catch(ItemParseException e){
          }
        }
      return myList;
    }

    public Item parseSingleItem (String singleItem) throws ItemParseException {
        String myStr = singleItem.toLowerCase();
        String[] newArr = myStr.split("[:,@,*,%,^,#,;]");
       // System.out.println(Arrays.asList(newArr).toString()+ newArr.length);

        List<String> list = Arrays.asList(newArr);

        if(list.size()==8){
            Double price = Double.parseDouble(list.get(3));
            return  new Item(list.get(1),price, list.get(5),list.get(7));
        }else{
            throw new ItemParseException();
     }
    }
}
