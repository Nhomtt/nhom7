package com.example.changetheme;

import android.app.ActionBar;
import android.app.Activity;

import android.content.Intent;
import android.view.View;


import android.app.Activity;

import android.content.Intent;


public class themeUtils

{

    private static int cTheme;



    public final static int DARK = 0;

    public final static int LIGHT = 1;

    public static void changeToTheme(Activity activity, int theme)

    {

        cTheme = theme;

        activity.finish();



        activity.startActivity(new Intent(activity, activity.getClass()));


    }

    public static void onActivityCreateSetTheme(Activity activity)
    {


        switch (cTheme)

        {

            default:

            case DARK:
                activity.setTheme(R.style.Darktheme);

                break;

            case LIGHT:

                activity.setTheme(R.style.Lighttheme);

                break;

        }

    }

}