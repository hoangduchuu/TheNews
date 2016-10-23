
package com.example.hoang.thenews.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Byline {

    @SerializedName("person")
    @Expose
    private List<Person> person = new ArrayList<Person>();
    @SerializedName("original")
    @Expose
    private String original;

    /**
     * 
     * @return
     *     The person
     */
    public List<Person> getPerson() {
        return person;
    }

    /**
     * 
     * @param person
     *     The person
     */
    public void setPerson(List<Person> person) {
        this.person = person;
    }

    /**
     * 
     * @return
     *     The original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * 
     * @param original
     *     The original
     */
    public void setOriginal(String original) {
        this.original = original;
    }

}
