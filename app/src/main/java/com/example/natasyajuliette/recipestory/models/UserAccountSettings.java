package com.example.natasyajuliette.recipestory.models;

import com.example.natasyajuliette.recipestory.Utils.StringManipulation;

/**
 * Created by natasyajuliette on 14/03/18.
 */

public class UserAccountSettings {

    private String description;
    private String display_name;
    private long followers;
    private long following;
    private long post;
    private String profile_photo;
    private String username;
    private String website;

    public UserAccountSettings(String description, String display_name, long followers, long following,
                               long post, String profile_photo, String username, String website) {
        this.description = description;
        this.display_name = display_name;
        this.followers = followers;
        this.following = following;
        this.post = post;
        this.profile_photo = profile_photo;
        this.username = username;
        this.website = website;
    }
    public UserAccountSettings() {

    }

    public String getDescription() {
        return description;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPost() {
        return post;
    }

    public void setPost(long post) {
        this.post = post;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    @Override
    public String toString() {
        return "UserAccountSettings{" +
                "description='" + description + '\'' +
                ", display_name='" + display_name + '\'' +
                ", followers=" + followers +
                ", following=" + following +
                ", posts=" + post +
                ", profile_photo='" + profile_photo + '\'' +
                ", username='" + username + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}