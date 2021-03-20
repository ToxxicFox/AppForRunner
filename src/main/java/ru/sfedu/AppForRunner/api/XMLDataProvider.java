package ru.sfedu.AppForRunner.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.AppForRunner.models.*;
import ru.sfedu.AppForRunner.utils.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XMLDataProvider implements DataProvider{

    private final String PATH = ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_XML);

    public final String FILE_EXT = Constants.XML_EXT;

    private Logger log = LogManager.getLogger(XMLDataProvider.class);

    public XMLDataProvider() throws IOException {
    }

    /**
     *
     * @param runnerList
     * @param check
     * @return
     * @throws Exception
     */
    @Override
    public Result<Runner> addRunner(List<Runner> runnerList, boolean check) throws Exception {
        return addRecord(runnerList, check, Runner.class);
    }

    /**
     *
     * @param trainingList
     * @param check
     * @return
     * @throws Exception
     */
    @Override
    public Result<Training> addTraining(List<Training> trainingList, boolean check) throws Exception{
        return addRecord(trainingList, check, Training.class);
    }

    /**
     *
     * @param marathonTrainingList
     * @param check
     * @return
     * @throws Exception
     */
    @Override
    public Result<MarathonTraining> addMarathonTrn(List<MarathonTraining> marathonTrainingList, boolean check) throws Exception{
        return addRecord(marathonTrainingList, check, MarathonTraining.class);
    }

    /**
     *
     * @param sprinterTrainingList
     * @param check
     * @return
     * @throws Exception
     */
    @Override
    public Result<SprinterTraining> addSprinterTrn(List<SprinterTraining> sprinterTrainingList, boolean check) throws Exception{
        return addRecord(sprinterTrainingList, check, SprinterTraining.class);
    }

    /**
     *
     * @param challengeList
     * @param check
     * @return
     * @throws Exception
     */
    @Override
    public Result<Challenge> addChallenge(List<Challenge> challengeList, boolean check) throws Exception{
        return addRecord(challengeList, check, Challenge.class);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<Runner> getRunner() throws Exception {
        return getRecords(Runner.class);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<Training> getTraining() throws Exception {
        return getRecords(Training.class);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<MarathonTraining> getMarathonTrn() throws Exception {
        return getRecords(MarathonTraining.class);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<SprinterTraining> getSprinterTrn() throws Exception {
        return getRecords(SprinterTraining.class);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result<Challenge> getChallenge() throws Exception {
        return getRecords(Challenge.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<Runner> getRunnerById(Long id) throws Exception {
        return getRecordById(id, Runner.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<Training> getTrainingById(Long id) throws Exception {
        return getRecordById(id, Training.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<MarathonTraining> getMarathonTrainingById(Long id) throws Exception {
        return getRecordById(id, MarathonTraining.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<SprinterTraining> getSprinterTrainingById(Long id) throws Exception {
        return getRecordById(id, SprinterTraining.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<Challenge> getChallengeById(Long id) throws Exception {
        return getRecordById(id, Challenge.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<Runner> deleteRunner(Long id) throws Exception{
        return deleteRecord(id, Runner.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<Training> deleteTraining(Long id) throws Exception {
        return deleteRecord(id, Training.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<MarathonTraining> deleteMarathonTraining(Long id) throws Exception {
        return deleteRecord(id, MarathonTraining.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<SprinterTraining> deleteSprinterTraining(Long id) throws Exception{
        return deleteRecord(id, SprinterTraining.class);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result<Challenge> deleteChallenge(Long id) throws Exception{
        return deleteRecord(id, Challenge.class);
    }

    /**
     *
     * @param id
     * @param runner
     * @return
     * @throws Exception
     */
    @Override
    public Result<Runner> updateRunner(Long id, Runner runner) throws Exception {
        return updateRecord(id, runner);
    }

    /**
     *
     * @param id
     * @param training
     * @return
     * @throws Exception
     */
    @Override
    public Result<Training> updateTraining(Long id, Training training) throws Exception{
        return updateRecord(id, training);
    }

    /**
     *
     * @param id
     * @param marathonTraining
     * @return
     * @throws Exception
     */
    @Override
    public Result<MarathonTraining> updateMarathonTraining(Long id, MarathonTraining marathonTraining) throws Exception{
        return updateRecord(id, marathonTraining);
    }

    /**
     *
     * @param id
     * @param sprinterTraining
     * @return
     * @throws Exception
     */
    @Override
    public Result<SprinterTraining> updateSprinterTraining(Long id, SprinterTraining sprinterTraining) throws Exception{
        return updateRecord(id, sprinterTraining);
    }

    /**
     *
     * @param id
     * @param challenge
     * @return
     * @throws Exception
     */
    @Override
    public Result<Challenge> updateChallenge(Long id, Challenge challenge) throws Exception{
        return updateRecord(id, challenge);
    }



    public void initDS(String path) throws IOException{
        File file = new File(path);
        if (!file.exists()) {
            Path dirPath = Paths.get(PATH);
            Files.createDirectories(dirPath);
            file.createNewFile();
        }
    }

    public String getPath(Class tClass) throws IOException{
        return PATH + tClass.getSimpleName().toLowerCase() + ConfigurationUtil.getConfigurationEntry(FILE_EXT);
    }


    private <T>Result<T> get(Class<T> tClass) {
        try {
            String path = getPath(tClass);
            initDS(path);
            FileReader reader = new FileReader(path);
            Serializer serializer = new Persister();
            log.debug(Constants.SUCCESS_INIT_XML);
            XMLWrapper<T> tResult = serializer.read(XMLWrapper.class, reader);
            reader.close();
            return new Result<>(tResult.getList(), ResultType.OK, Constants.FOUND);
        }
        catch (Exception e) {
            log.error(e);
            return new Result<>(null, ResultType.ERROR, Constants.FILE_ERR);
        }
    }

    private <T> Result<T> add(XMLWrapper<T> wrapper, Class<?> nClass) {
        try {
            String path = getPath(nClass);
            initDS(path);
            FileWriter fileWriter = new FileWriter(path);
            Serializer serializer = new Persister();
            log.debug(Constants.SUCCESS_WRITTER_INIT);
            serializer.write(wrapper, fileWriter);
            fileWriter.close();
            return new Result<T>(wrapper.getList(), ResultType.OK, Constants.ADD_SUCCCES);
        } catch (Exception e) {
            log.error(e);
            return new Result<T>(null, ResultType.ERROR, Constants.FILE_ERR);
        }
    }


    @Override
    public <T>Result<T> addRecord(List<T> recLst, boolean check, Class<T> tClass) throws IOException, IllegalAccessException {
        if (check) {
            Result<T> tResult = get(tClass);
            List<T> prevRecs = tResult.getData();
            if (tResult.getResultType() == ResultType.ERROR) {
                prevRecs = new ArrayList<>();
            }
            if (AppliedMethods.isReplicate(recLst, prevRecs)) {
                return new Result<T>(null, ResultType.ERROR, Constants.EXIST_ERROR);
            }
            recLst = Stream.concat(prevRecs.stream(), recLst.stream()).collect(Collectors.toList());
        }
        XMLWrapper<T> wrapper = new XMLWrapper<>(recLst);
        log.info(Constants.LOG_REC + wrapper.getList().toString());
        return add(wrapper, tClass);
    }


    @Override
    public <T> Result<T> getRecords(Class nClass) {
        Result<T> tResult = get(nClass);
        if (tResult.getResultType() == ResultType.ERROR) {
            return tResult;
        } else if (tResult.getData().size() == 0) {
            return new Result<T>(tResult.getData(), ResultType.ERROR, Constants.EMPTY);
        }
        return tResult;
    }

    @Override
    public <T> Result<T> getRecordById(Long id, Class<T> tClass) {
        Result nResult = getRecords(tClass);
        if (nResult.getResultType() == ResultType.ERROR) {
            log.info(nResult.getMessage());
            return nResult;
        }
        log.info(nResult.getData());
        return AppliedMethods.getById(nResult.getData(), id, tClass);
    }

    @Override
    public <T> Result<T> deleteRecord(Long id, Class<T> tClass) throws Exception{
        Result<T> tResult;
        tResult = getRecordById(id, tClass);
        if(tResult.getResultType() == ResultType.ERROR) {
            return tResult;
        }
        T recsToDel = tResult.getData().get(0);
        tResult = getRecords(tClass);
        if (tResult.getResultType() == ResultType.ERROR) {
            return tResult;
        }
        List<T> tList = tResult.getData();
        tList.remove(recsToDel);
        tResult = addRecord(tResult.getData(), false, tClass);
        if (tResult.getResultType() == ResultType.ERROR) {
            return tResult;
        }
        return new Result<>(tResult.getData(), ResultType.OK, Constants.DELETE_SUCCES);
    }

    @Override
    public <T> Result<T> updateRecord(Long id, T rec) throws Exception{
        Class<T> tClass = (Class<T>) rec.getClass();
        Result<T> tResult = this.deleteRecord(id, tClass);
        if (tResult.getResultType() == ResultType.ERROR) {
            return tResult;
        }
        List<T> tList = tResult.getData();
        tList.add(rec);
        return addRecord(tList, false, tClass);
    }


}
