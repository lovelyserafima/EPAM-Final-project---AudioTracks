package com.epam.audiomanager.entity.audio;

import com.epam.audiomanager.entity.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Assembly extends Entity {
    private List<AudioTrack> audioTrackList = new ArrayList<>();
    private String name;
    private BigDecimal price;

    public Assembly(){super();}
}
