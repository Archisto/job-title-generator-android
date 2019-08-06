package com.laurikosonen.jobtitlegenerator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.EditText;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int maxDisplayedTitles = 10;
    private final int maxTitleWords = 5;

    private List<TextView> titleSlots;
    private List<TextView> titleNumberSlots;
//    private MenuItem currentDisplayedCatItem;
//    private MenuItem titleCountMenuItem;
//    private MenuItem titleWordCountMenuItem;
//    private ToggleButton titleDecorationsToggle;
//    private EditText customTemplateInput;
//    private MenuItem customTemplateToggle;

    private List<List<Word>> wordLists;
    private List<Word> allWords;

    private int displayedCategory = -1;
    private int displayedTitleCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initWords();
        initTitleSlots();

        displayTitles();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTitles();
            }
        });
    }

    private void initWords() {
        wordLists = new ArrayList<>();
        allWords = new ArrayList<>();
        CustomXmlResourceParser.parseWords(getResources(), R.xml.title_words, wordLists, allWords);
    }

    private void initTitleSlots() {
        titleSlots = new ArrayList<>(10);
        titleSlots.add((TextView) findViewById(R.id.titleSlot01));
        titleSlots.add((TextView) findViewById(R.id.titleSlot02));
        titleSlots.add((TextView) findViewById(R.id.titleSlot03));
        titleSlots.add((TextView) findViewById(R.id.titleSlot04));
        titleSlots.add((TextView) findViewById(R.id.titleSlot05));
        titleSlots.add((TextView) findViewById(R.id.titleSlot06));
        titleSlots.add((TextView) findViewById(R.id.titleSlot07));
        titleSlots.add((TextView) findViewById(R.id.titleSlot08));
        titleSlots.add((TextView) findViewById(R.id.titleSlot09));
        titleSlots.add((TextView) findViewById(R.id.titleSlot10));

        titleNumberSlots = new ArrayList<>(10);
        titleNumberSlots.add((TextView) findViewById(R.id.titleSlotNumber01));
        titleNumberSlots.add((TextView) findViewById(R.id.titleSlotNumber02));
        titleNumberSlots.add((TextView) findViewById(R.id.titleSlotNumber03));
        titleNumberSlots.add((TextView) findViewById(R.id.titleSlotNumber04));
        titleNumberSlots.add((TextView) findViewById(R.id.titleSlotNumber05));
        titleNumberSlots.add((TextView) findViewById(R.id.titleSlotNumber06));
        titleNumberSlots.add((TextView) findViewById(R.id.titleSlotNumber07));
        titleNumberSlots.add((TextView) findViewById(R.id.titleSlotNumber08));
        titleNumberSlots.add((TextView) findViewById(R.id.titleSlotNumber09));
        titleNumberSlots.add((TextView) findViewById(R.id.titleSlotNumber10));
    }

    private void displayTitles() {
        for (int i = 0; i < titleSlots.size(); i++) {
            boolean emptySlot = i >= displayedTitleCount;
            if (emptySlot) {
                titleSlots.get(i).setText("");
                titleNumberSlots.get(i).setText("");
                continue;
            }

            StringBuilder title = new StringBuilder();

            // Sets the number text before the title, e.g. "1) Title"
            String numberText = String.format(getString(R.string.titleNumber), i + 1);
            titleNumberSlots.get(i).setText(numberText);

            // Creates the title
            int wordCount = wordLists.size();
            for (int j = 0; j < wordCount; j++) {
                boolean isLastWord = j == wordCount - 1;

                title.append(getRandomWord(j));

                if (!isLastWord && title.length() > 0 && title.charAt(title.length() - 1) != '-') {
                    title.append(' ');
                }
            }

            titleSlots.get(i).setText(title);
        }
    }

    private Word getRandomWord(int wordCategoryId) {
        List<Word> wordList;

        if (wordCategoryId >= wordLists.size()) {
            return new Word("ERROR", wordCategoryId);
        }

        if (wordCategoryId < 0)
            wordList = allWords;
        else
            wordList = wordLists.get(wordCategoryId);

        int wordIndex = (int) (Math.random() * wordList.size());
        return wordList.get(wordIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        initMenu(menu);
        initCategories(menu);
        return true;
    }

//    private void initMenu(Menu menu) {
//        titleCountMenuItem = menu.findItem(R.id.submenu_titleCount);
//        titleCountMenuItem.
//            setTitle(String.format(getString(R.string.titleCount), displayedTitleCount));
//
//        titleWordCountMenuItem = menu.findItem(R.id.submenu_titleWordCount);
//        titleWordCountMenuItem.
//            setTitle(String.format(getString(R.string.titleWordCount), titleWordCount));
//    }

    private void initCategories(Menu menu) {
//        currentDisplayedCatItem = menu.findItem(R.id.action_displayAll);
//        currentDisplayedCatItem.setEnabled(false);
//
//        menu.findItem(R.id.action_displayCategory0).setTitle(String.format(
//            getString(R.string.action_displayCategory), getString(R.string.category_feature)));
//        menu.findItem(R.id.action_displayCategory1).setTitle(String.format(
//            getString(R.string.action_displayCategory), getString(R.string.category_concept)));
//        menu.findItem(R.id.action_displayCategory2).setTitle(String.format(
//            getString(R.string.action_displayCategory), getString(R.string.category_thing)));
//        menu.findItem(R.id.action_displayCategory3).setTitle(String.format(
//            getString(R.string.action_displayCategory), getString(R.string.category_actorAndAction)));
//        menu.findItem(R.id.action_displayCategory4).setTitle(String.format(
//            getString(R.string.action_displayCategory), getString(R.string.category_placeAndTime)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        if (handleSetTitleCount(id)) {
//            return true;
//        }
//        else if (handleSetTitleWordCount(id)) {
//            return true;
//        }
//        else if (handleDisplayedCategoryOptions(id, item)) {
//            return true;
//        }
//        else if (handleCustomTemplateActivation(id, item)) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    /*private boolean handleSetTitleCount(int id) {
        switch (id) {
            case R.id.action_setTitleCount_1: {
                return setDisplayedTitleCount(1, maxDisplayedTitles);
            }
            case R.id.action_setTitleCount_2: {
                return setDisplayedTitleCount(2, maxDisplayedTitles);
            }
            case R.id.action_setTitleCount_3: {
                return setDisplayedTitleCount(3, maxDisplayedTitles);
            }
            case R.id.action_setTitleCount_4: {
                return setDisplayedTitleCount(4, maxDisplayedTitles);
            }
            case R.id.action_setTitleCount_5: {
                return setDisplayedTitleCount(5, maxDisplayedTitles);
            }
            case R.id.action_setTitleCount_6: {
                return setDisplayedTitleCount(6, maxDisplayedTitles);
            }
            case R.id.action_setTitleCount_7: {
                return setDisplayedTitleCount(7, maxDisplayedTitles);
            }
            case R.id.action_setTitleCount_8: {
                return setDisplayedTitleCount(8, maxDisplayedTitles);
            }
            case R.id.action_setTitleCount_9: {
                return setDisplayedTitleCount(9, maxDisplayedTitles);
            }
            case R.id.action_setTitleCount_10: {
                return setDisplayedTitleCount(10, maxDisplayedTitles);
            }
        }

        return false;
    }

    private boolean handleSetTitleWordCount(int id) {
        switch (id) {
            case R.id.action_setTitleWordCount_1: {
                return setTitleWordCount(1, maxTitleWords);
            }
            case R.id.action_setTitleWordCount_2: {
                return setTitleWordCount(2, maxTitleWords);
            }
            case R.id.action_setTitleWordCount_3: {
                return setTitleWordCount(3, maxTitleWords);
            }
            case R.id.action_setTitleWordCount_4: {
                return setTitleWordCount(4, maxTitleWords);
            }
            case R.id.action_setTitleWordCount_5: {
                return setTitleWordCount(5, maxTitleWords);
            }
        }

        return false;
    }

    private boolean handleDisplayedCategoryOptions(int id, MenuItem item) {
        switch (id) {
            case R.id.action_displayAll: {
                return setDisplayedCategory(item, -1);
            }
            case R.id.action_displayCategory0: {
                return setDisplayedCategory(item, 0);
            }
            case R.id.action_displayCategory1: {
                return setDisplayedCategory(item, 1);
            }
            case R.id.action_displayCategory2: {
                return setDisplayedCategory(item, 2);
            }
            case R.id.action_displayCategory3: {
                return setDisplayedCategory(item, 3);
            }
            case R.id.action_displayCategory4: {
                return setDisplayedCategory(item, 4);
            }
        }

        return false;
    }*/

    /*private boolean setDisplayedTitleCount(int value, int max) {
        if (value >= 1 && value <= max) {
            displayedTitleCount = value;
            titleCountMenuItem.
                setTitle(String.format(getString(R.string.titleCount), displayedTitleCount));
            return true;
        }

        return false;
    }

    private boolean setTitleWordCount(int value, int max) {
        if (value >= 1 && value <= max) {
            titleWordCount = value;
            titleWordCountMenuItem.
                setTitle(String.format(getString(R.string.titleWordCount), titleWordCount));
            return true;
        }

        return false;
    }

    private boolean setDisplayedCategory(MenuItem item, int categoryId) {
        if (categoryId < wordLists.size()) {
            displayedCategory = categoryId;
            item.setEnabled(false);
            currentDisplayedCatItem.setEnabled(true);
            currentDisplayedCatItem = item;
            return true;
        }

        return false;
    }*/
}
