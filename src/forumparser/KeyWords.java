/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumparser;

import java.util.ArrayList;

/**
 *
 * @author Леван
 */
public class KeyWords {
    private final String[] kw;

    public KeyWords(String[] kw) {
        this.kw = kw;
    }
    
    public boolean checkTitle(String title){http://diesel.elcat.kg/index.php?showforum=283
        for(String kwStr:kw){
            if(title.contains(kwStr.trim())) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Result> selectResults(ArrayList<Result> uncheckedResults){
        ArrayList<Result> finallyResults=new ArrayList<>();
        for(Result result:uncheckedResults){
            String title=result.getTitle();
            if(checkTitle(title))
                finallyResults.add(result);
        }
        return finallyResults;
    }
}
