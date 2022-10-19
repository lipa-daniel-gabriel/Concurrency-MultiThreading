package com.playtika.java.academy.challenge2.lipa.daniel.models.threads;

import com.playtika.java.academy.challenge2.lipa.daniel.models.SaveStatistic;
import com.playtika.java.academy.challenge2.lipa.daniel.powerups.classes.BonusShield;
import com.playtika.java.academy.challenge2.lipa.daniel.statistics.models.BonusShieldsDataSet;

import java.io.IOException;
import java.util.List;

public class StreamsManager {

    public void startEachStreamOnANewThread(List<BonusShield> bonusShieldList){
        BonusShieldsDataSet bonusShieldDataSet = new BonusShieldsDataSet(bonusShieldList);
        BonusShield[] mostUsedShieldTypeCategory = bonusShieldDataSet.process(new ThreadMostUsedShieldType());
        BonusShield[] topTenBonusShields = bonusShieldDataSet.process(new ThreadForTopTenBonusShield());
        BonusShield[] bonusShieldsByKeyword = bonusShieldDataSet.process(new ThreadThatContainKeyword());
        BonusShield[] bonusShieldsWithMaxScore = bonusShieldDataSet.process(new ThreadMaxScoreCategory());

        try {
            SaveStatistic.saveStatisticToFile("mostUsedShieldTypeCategory.txt", mostUsedShieldTypeCategory);
            SaveStatistic.saveStatisticToFile("topTenBonusShields.txt", topTenBonusShields);
            SaveStatistic.saveStatisticToFile("containsKeywordBonusShields.txt", bonusShieldsByKeyword);
            SaveStatistic.saveStatisticToFile("maxScoreInCategory.txt", bonusShieldsWithMaxScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
