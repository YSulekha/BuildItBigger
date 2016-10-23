package com.gradle;


//Java Class which provides jokes
public class JokeWizard {

    private String jokes[]={"I think my neighbor is stalking me as she's been googling my name on her computer. I saw it through my telescope last night.",
            "I can totally keep secrets. It's the people I tell them to that can't.",
    "Apparently I snore so loudly that it scares everyone in the car I'm driving.",
    "I changed my password to \"incorrect\". So whenever I forget what it is the computer will say " +
            "\"Your password is incorrect\"."};

    //Method which provides Jokes
    public String getJokes(){
        int random = (int)(Math.random()*jokes.length);
        return jokes[random];
    }

}
