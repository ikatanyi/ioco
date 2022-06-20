package com.ioco.apocalypse.survivor.data;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LocationDto {
    @NotNull
    String longitude;
    @NotNull
    String latitude;
}
