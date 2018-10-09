package com.epam.audiomanager.entity.audio;

import com.epam.audiomanager.entity.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Album extends Entity {
    private List<AudioTrack> audioTrackList = new ArrayList<>();
    private String name;
    private String artist;
    private int year;
    private BigDecimal price;

    public Album(){super();}

    public Album(int id, List<AudioTrack> audioTrackList, String name, String artist, int year, BigDecimal price) {
        super(id);
        this.audioTrackList = audioTrackList;
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.price = price;
    }

    public List<AudioTrack> getAudioTrackList() {
        return audioTrackList;
    }

    public void setAudioTrackList(List<AudioTrack> audioTrackList) {
        this.audioTrackList = audioTrackList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        if (!super.equals(o)) return false;
        Album album = (Album) o;
        return getYear() == album.getYear() &&
                Objects.equals(getAudioTrackList(), album.getAudioTrackList()) &&
                Objects.equals(getName(), album.getName()) &&
                Objects.equals(getArtist(), album.getArtist()) &&
                Objects.equals(getPrice(), album.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAudioTrackList(), getName(), getArtist(), getYear(), getPrice());
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + getId() +
                ", audioTrackList=" + audioTrackList +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
