package ru.sfedu.AppForRunner.utils.csvConvs;

import com.opencsv.bean.AbstractBeanField;
import ru.sfedu.AppForRunner.models.Challenge;
import ru.sfedu.AppForRunner.models.TrainingTarget;
import ru.sfedu.AppForRunner.models.TypeOfChallenge;

public class ChallengeConv extends AbstractBeanField {

    private String fieldsDel = "@";

    @Override
    protected Object convert(String val) {
        Challenge challenge = new Challenge();
        String[] parsArgs = val.split(fieldsDel);
        challenge.setChallName(parsArgs[0]);
        challenge.setStartDate(parsArgs[1]);
        challenge.setFinishDate(parsArgs[2]);
        challenge.setTrnTarget(TrainingTarget.valueOf(parsArgs[3]));
        challenge.setChallDistance(Long.parseLong(parsArgs[4]));
        challenge.setChallTime(Long.parseLong(parsArgs[5]));
        challenge.setTypeOfChallenge(TypeOfChallenge.valueOf(parsArgs[6]));
        return challenge;
    }

    @Override
    public String convertToWrite(Object val) {
        Challenge challenge = (Challenge) val;
        return String.format("%s" + fieldsDel + "%s" + fieldsDel + "%s" + fieldsDel +
                "%s" + fieldsDel + "%d" + fieldsDel + "%d" + fieldsDel + "%s",
                challenge.getChallName(), challenge.getStartDate(), challenge.getFinishDate(),
                challenge.getTrnTarget(), challenge.getChallDistance(), challenge.getChallTime(),
                challenge.getTypeOfChallenge());
    }

}
