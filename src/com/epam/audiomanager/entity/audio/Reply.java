package com.epam.audiomanager.entity.audio;

import com.epam.audiomanager.entity.Entity;

import java.util.Objects;

public class Reply extends Entity {
    private int userId;
    private int audioId;
    private String text;
    private String userLogin;

    public Reply(int id, String userLogin, String text){
        super(id);
        this.userLogin = userLogin;
        this.text = text;
    }

    public Reply(int userId, int audioId, String text, String userLogin) {
        this.userId = userId;
        this.audioId = audioId;
        this.text = text;
        this.userLogin = userLogin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reply)) return false;
        if (!super.equals(o)) return false;
        Reply reply = (Reply) o;
        return getUserId() == reply.getUserId() &&
                getAudioId() == reply.getAudioId() &&
                Objects.equals(getText(), reply.getText()) &&
                Objects.equals(getUserLogin(), reply.getUserLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUserId(), getAudioId(), getText(), getUserLogin());
    }

    @Override
    public String toString() {
        return "Reply{" +
                "userId=" + userId +
                ", audioId=" + audioId +
                ", text='" + text + '\'' +
                ", userLogin='" + userLogin + '\'' +
                '}';
    }
}
