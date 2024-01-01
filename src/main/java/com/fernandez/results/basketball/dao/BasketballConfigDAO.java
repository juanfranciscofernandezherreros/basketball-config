package com.fernandez.results.basketball.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "BASKETBALL_CONFIG")
public class BasketballConfigDAO {
    @Id
    private BasketballConfigPKDAO basketballConfigPKDAO;
    private Boolean hasSummary;
    private Boolean hasPlayerStatics;
    private Boolean hasStats0;
    private Boolean hasStats1;
    private Boolean hasStats2;
    private Boolean hasStats3;
    private Boolean hasStats4;
    private Boolean hasStats5;
    private Boolean hasLineUps;
    private Boolean hasMatchHistory1;
    private Boolean hasMatchHistory2;
    private Boolean hasMatchHistory3;
    private Boolean hasMatchHistory4;

}
