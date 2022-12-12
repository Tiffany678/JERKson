package io.zipcoder;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    public List<Item> parseItemList(String valueToParse) {

        String singleItemMy = valueToParse.toLowerCase();
        Pattern pattern = Pattern.compile("(?=).*?(?>##)");
        Matcher matcher = pattern.matcher(singleItemMy);

        ArrayList<Item> list = new ArrayList<>();
        while (matcher.find()) {
            try {
                list.add(parseSingleItem(matcher.group(0)));
            }
            catch(ItemParseException e){
            }
        }
        return list;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        String singleItemMy = singleItem.toLowerCase();
        Pattern pattern = Pattern.compile("(?<=[:, @, ^, *, %]).*?(?=[;#])");
        Matcher matcher = pattern.matcher(singleItemMy);
        ArrayList<String> list = new ArrayList<>();

        while (matcher.find()) {
            list.add(matcher.group(0));

        }

        if (list.size()==4) {

            double d = Double.parseDouble(list.get(1));


            Item item = new Item(list.get(0), d, list.get(2), list.get(3));
            return item;
        }
        else {
            throw new ItemParseException();
        }

    }
}
