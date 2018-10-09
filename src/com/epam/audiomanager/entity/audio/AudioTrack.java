package com.epam.audiomanager.entity.audio;

import com.epam.audiomanager.entity.Entity;
import java.math.BigDecimal;
import java.util.Objects;

public class AudioTrack extends Entity {
    private String name;
    private String band;
    private int year;
    private BigDecimal price;
    private String fullAudioPath;
    private String demoAudioPath;
    private String album;

    public AudioTrack(){super();}

    public AudioTrack(int id, String name, String band, int year, BigDecimal price, String fullAudioPath,
                      String demoAudioPath, String album) {
        super(id);
        this.name = name;
        this.band = band;
        this.year = year;
        this.price = price;
        this.fullAudioPath = fullAudioPath;
        this.demoAudioPath = demoAudioPath;
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getFullAudioPath() {
        return fullAudioPath;
    }

    public void setFullAudioPath(String fullAudioPath) {
        this.fullAudioPath = fullAudioPath;
    }

    public String getDemoAudioPath() {
        return demoAudioPath;
    }

    public void setDemoAudioPath(String demoAudioPath) {
        this.demoAudioPath = demoAudioPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AudioTrack)) return false;
        if (!super.equals(o)) return false;
        AudioTrack that = (AudioTrack) o;
        return getYear() == that.getYear() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getBand(), that.getBand()) &&
                Objects.equals(getPrice(), that.getPrice()) &&
                Objects.equals(getFullAudioPath(), that.getFullAudioPath()) &&
                Objects.equals(getDemoAudioPath(), that.getDemoAudioPath()) &&
                Objects.equals(getAlbum(), that.getAlbum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getBand(), getYear(), getPrice(), getFullAudioPath(),
                getDemoAudioPath(), getAlbum());
    }

    @Override
    public String toString() {
        return "AudioTrack{" +
                "name='" + name + '\'' +
                ", band='" + band + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", fullAudioPath='" + fullAudioPath + '\'' +
                ", demoAudioPath='" + demoAudioPath + '\'' +
                ", album='" + album + '\'' +
                '}';
    }
}