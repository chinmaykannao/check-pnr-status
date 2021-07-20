package com.pnr.checker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PNRStatus {

    @JsonProperty("passenger")
    private List<Passenger> passengers;

    @JsonProperty("chart_status")
    private String chartStatus;

    @JsonProperty("train_name")
    private String trainName;
}
