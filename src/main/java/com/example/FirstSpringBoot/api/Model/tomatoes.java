package com.example.FirstSpringBoot.api.Model;

import com.mongodb.lang.Nullable;

import java.util.Date;

public class tomatoes {
    String website;
    String boxOffice;
    String production;
    Critic viewer;

    Critic critic;

    Integer rotten;
    Integer fresh;

    @Override
    public String toString() {
        return "tomatoes{" +
                "viewer=" + viewer +

                ", critic=" + critic +

                ", rotten=" + rotten +
                ", fresh=" + fresh +
                '}';
    }

    public Critic getViewer() {
        return viewer;
    }

    public Critic getCritic() {
        return critic;
    }

    public Integer getRotten() {
        return rotten;
    }

    public Integer getFresh() {
        return fresh;
    }

    public String getWebsite() {
        return website;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public void setViewer(Critic viewer) {
        this.viewer = viewer;
    }

    public void setCritic(Critic critic) {
        this.critic = critic;
    }

    public void setRotten(Integer rotten) {
        this.rotten = rotten;
    }

    public void setFresh(Integer fresh) {
        this.fresh = fresh;
    }


}
