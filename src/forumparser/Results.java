/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumparser;

import java.util.ArrayList;

public class Results {
    private ArrayList<Result> results=new ArrayList<>();

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResult(ArrayList<Result> results) {
        this.results = results;
    }
    
    public void setResult(Result result){
        results.add(result);
    }
}
