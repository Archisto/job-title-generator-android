package com.laurikosonen.jobtitlegenerator;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class CustomXmlResourceParser {

    private static final String categoryStr = "category";
    private static final String wordStr = "word";
    private static final String idStr = "id";

    private static int parseInt(String str) {
        int result = -1;
        if (str != null && str.length() > 0) {
            try {
                result = Integer.parseInt(str);
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static void parseWords(Resources resources,
                                  int resourceID,
                                  List<List<Word>> pools,
                                  List<Word> poolAll) {
        XmlResourceParser parser = resources.getXml(resourceID);

        try {
            parser.next();
            int eventType = parser.getEventType();
            String startTagName = "_";
            int categoryId = 0;
            Word word;
            boolean isPlural = false;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    startTagName = parser.getName();
//                    Log.d("TitleGnr", "startTagName: " + startTagName);

                    // Parses the category
                    if (startTagName.equalsIgnoreCase(categoryStr)) {
                        String strId = parser.getAttributeValue(null, idStr);
                        categoryId = parseInt(strId);

                        // Increases the pool count if the ID is too large
                        if (pools.size() <= categoryId) {
                            List<Word> newPool = new ArrayList<>();
                            pools.add(newPool);
                        }
                    }
                }
                else if (eventType == XmlPullParser.TEXT) {
                    String text = parser.getText();

                    // Creates a new word
                    if (startTagName.equalsIgnoreCase(wordStr)) {
                        word = getParsedWord(text, categoryId);
                        if (word != null) {
                            pools.get(categoryId).add(word);
                            poolAll.add(word);
                        }
                    }
                }

                eventType = parser.next();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        finally {
            Log.d("JobTitleGnr", "Word parsing complete");
        }
    }

    private static Word getParsedWord(String text, int id) {
        if (text != null && !text.isEmpty()) {
            return new Word(text, id);
        }

        return null;
    }
}