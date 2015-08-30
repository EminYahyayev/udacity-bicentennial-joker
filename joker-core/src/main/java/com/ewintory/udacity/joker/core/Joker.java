/*
 * Copyright 2015.  Emin Yahyayev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ewintory.udacity.joker.core;

import java.util.Random;

/**
 * This class provides jokes from the joke scene of The Bicentennial Man (1999) movie with Robin Williams
 * <p/>
 * Jokes listing was taken from <a href="https://www.reddit.com/r/Jokes/comments/2dftux/the_joke_scene_from_the_bicentennial_man_1999/">here</a>
 */
public final class Joker {

    private final Random mRandom = new Random();

    private final String[] mJokes = {
            "Two cannibals were eating a clown. One turns to the other and says \n" +
                    "\"Does this taste funny to you?\"",

            "How do you make a hanky dance? \n" +
                    "Put a little boogie in it!",

            "What is a brunette between two blondes? \n" +
                    "A translator!",

            "Do you know why blind people don't like to sky-dive? \n" +
                    "It scares their dogs!",

            "A man with demensia is driving on the freeway. His wife calls him on the mobile phone and says \n" +
                    "\"Sweetheart, I heard there's someone driving the wrong way on the freeway.\" \n" +
                    "He says \"One? There's hundreds!\"",

            "What's silent and smells like worms? \n" +
                    "Bird facts.",

            "It must have been an engineer who designed the human body. Who else would put a waste processing plant next to a recreation area?",

            "A woman goes into a doctor's office, and the doctor says \n" +
                    "\"Do you mind if I numb your breasts?\" \n" +
                    "\"Not at all.\" *makes 'motor-boating' noise. \"Num-num-num-num.\""
    };

    public Joker() {}

    public String randomJoke() {
        return mJokes[mRandom.nextInt(mJokes.length)];
    }
}
