package com.compsci702.compsci702app;

import android.content.Context;

import com.compsci702.compsci702app.Level.Level;
import com.compsci702.compsci702app.Level.Level1;
import com.compsci702.compsci702app.Level.Level2;
import com.compsci702.compsci702app.Level.Level3;
import com.compsci702.compsci702app.Level.LevelType;

public class LevelController {

    public Level getNextLevel(Level currentLevel, Context context){

        switch (currentLevel.getLevelType()){
            case LEVEL_1:
                return new Level2(context);
            case LEVEL_2:
                return new Level3(context);
            case LEVEL_3:
                return null;
        }
        return null;
    }
}
