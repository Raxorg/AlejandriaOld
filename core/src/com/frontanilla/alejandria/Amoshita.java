package com.frontanilla.alejandria;

import com.badlogic.gdx.Game;

public class Amoshita extends Game {


    @Override
    public void create() {
        for(int i=0;i<=10;i++){
            if(i<6){
                System.out.println(i);
            }
        }
    }
}
