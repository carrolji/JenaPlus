package com.mood.jenaPlus;


/**
 * Created by Bernice on 2017-02-25.
 */

public class MoodListController {

    MoodPlus moodPlus = null; // a singleton


    public MoodListController(MoodPlus aMoodPlus) {
        this.moodPlus = aMoodPlus;
    }
}
