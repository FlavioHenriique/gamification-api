package io.github.gamification.gamificationapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ExceptionBody {

    private long code;
    private String message;
}
