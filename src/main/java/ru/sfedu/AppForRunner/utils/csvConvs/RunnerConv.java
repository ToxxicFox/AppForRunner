package ru.sfedu.AppForRunner.utils.csvConvs;

import com.opencsv.bean.AbstractBeanField;
import ru.sfedu.AppForRunner.models.Runner;

public class RunnerConv extends AbstractBeanField {

    private String fieldsDel = "#";

    @Override
    protected Object convert(String val) {
        Runner runner = new Runner();
        String[] parsArgs = val.split(fieldsDel);
        runner.setId(Long.parseLong(parsArgs[0]));
        runner.setFirstName(parsArgs[1]);
        runner.setLastName(parsArgs[2]);
        runner.setAge(Long.parseLong(parsArgs[3]));
        runner.setWeight(Long.parseLong(parsArgs[4]));
        runner.setHeight(Long.parseLong(parsArgs[5]));
        return runner;
    }

    @Override
    public String convertToWrite(Object val){
        Runner runner = (Runner) val;
        return String.format("%d" + fieldsDel + "%s" + fieldsDel + "%s" + fieldsDel +
                "%d" + fieldsDel + "%d" + fieldsDel + "%d", runner.getId(),
                runner.getFirstName(), runner.getLastName(), runner.getAge(),
                runner.getWeight(), runner.getHeight()
                );
    }

}
