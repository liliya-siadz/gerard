package com.gerard.site.service.view;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Photo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String photoPath;
    private Date photoDate;
    private String nickname;

    public Photo() {

    }

    public Photo(String photoPath, Date photoDate, String nickname) {
        this.photoPath = photoPath;
        this.photoDate = photoDate;
        this.nickname = nickname;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Date getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(Date photoDate) {
        this.photoDate = photoDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Photo that = (Photo) object;
        return Objects.equals(photoPath, that.photoPath)
                && Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoPath, nickname);
    }

    @Override
    public String toString() {
        return "PhotoWithDog{"
                + "photoPath='" + photoPath + '\''
                + ", photoDate=" + photoDate
                + ", nickname='" + nickname + '\''
                + '}';
    }
}
