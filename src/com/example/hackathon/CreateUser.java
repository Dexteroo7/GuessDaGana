package com.example.hackathon;

import android.util.Log;

import com.shephertz.app42.paas.sdk.android.App42BadParameterException;
import com.shephertz.app42.paas.sdk.android.App42Exception;
import com.shephertz.app42.paas.sdk.android.App42SecurityException;
import com.shephertz.app42.paas.sdk.android.ServiceAPI;
import com.shephertz.app42.paas.sdk.android.user.User;
import com.shephertz.app42.paas.sdk.android.user.UserService;

/**
 * App42 Android Sample 
 *
 */
public class CreateUser {
    /** Called when the activity is first created. */

    private String username, password, emailID;
    private final String YOUR_API_KEY = "3e64a1d1a501cf90ca981ecd2dd4453af4ec4e0309c2746ce75ece53e9e508e3";
    private final String YOUR_SECRET_KEY = "069a4ad07ff72efc47617855da24ada4df1baa3f07baa0a068beefe8e8db1f67";

    CreateUser(String a, String b, String c) {

        username = a;
        password = b;
        emailID = c;
    }

    public void run() {

        Log.v("Ayush", username);
        Log.v("Ayush", password);
        Log.v("Ayush", emailID);
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    createUser();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
    /**
     * Test Method for creating the User in App42 Cloud.
     */
    public void createUser() {
        // Enter your Public Key and Private Key Here in Constructor. You can
        // get it once you will create a app in app42 console
        ServiceAPI sp = new ServiceAPI(YOUR_API_KEY, YOUR_SECRET_KEY);
        // Create Instance of User Service
        UserService userService = sp.buildUserService();

        // create user or call other available method on the user service
        // reference
        try {
            System.out.println(" Starting User Creation test");
            User user = userService.createUser(username, password, emailID);
            //Fetch the returned JSON response
            System.out.println(" User Creation Successfull !!! JSON Response is : " + user);

            //Using returned User object, user property can be fetched
            //Fetch User name from user object
            System.out.println(" Created User Name is : " + user.getUserName());
        } catch (App42BadParameterException ex) {
            System.out.println("App42BadParameterException ");
            // Exception Caught
            // Check if User already Exist by checking app error code
            if (ex.getAppErrorCode() == 2001) {
                // Do exception Handling for Already created User.
                System.out.println(" User already exist with given user name");
                Log.e("Login", "User already exist with given user name");
            }
        } catch (App42SecurityException ex) {
            System.out.println("App42SecurityException ");
            // Exception Caught
            // Check for authorization Error due to invalid Public/Private Key
            if (ex.getAppErrorCode() == 1401) {
                // Do exception Handling here
                Log.e("Login", "Invalid Key");
            }
        } catch (App42Exception ex) {
            System.out.println("App42Exception ");
            // Exception Caught due to other Validation
            Log.e("Login", "Some Other Shit");
        }
    }
}
