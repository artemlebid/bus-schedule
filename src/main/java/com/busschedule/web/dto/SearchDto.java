package com.busschedule.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
public class SearchDto {
    @NotEmpty(message = "")
    private String searchLine;
}
