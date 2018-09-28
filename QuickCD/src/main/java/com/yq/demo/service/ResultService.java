package com.yq.demo.service;

import java.util.List;

import com.yq.demo.dao.ResultDAO;
import com.yq.demo.entity.Result;


public class ResultService {
    private ResultDAO resultDao;

    public ResultDAO getResultDao() {
        return resultDao;
    }

    public void setResultDao(ResultDAO resultDao) {
        this.resultDao = resultDao;
    }

    public String addResult(Result result) {
        return resultDao.addResult(result);
    }

    public List<Result> getResultByBuildUuid(String buildUuid){
        return resultDao.getResultByBuildUuid(buildUuid);
    }

    public Result getResultByResultUuid(String resultUuid){
        return resultDao.getResultByUuid(resultUuid);
    }

    public boolean updateResult(Result result) {
        return resultDao.updateResult(result);
    }

    public boolean delResultByBuildUuid(String buildUuid) {
        return resultDao.delResultByBuildUuid(buildUuid);
    }
}
