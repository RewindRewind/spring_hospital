package com.rf.springsecurity.dto;

import com.rf.springsecurity.entity.Treatment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TreatmentsDTO {
    private List<Treatment> treatments;
}
