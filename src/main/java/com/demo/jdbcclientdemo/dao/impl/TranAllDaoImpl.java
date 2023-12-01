package com.demo.jdbcclientdemo.dao.impl;


import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.demo.jdbcclientdemo.constant.CommonConstant;
import com.demo.jdbcclientdemo.constant.DatabaseConstant;
import com.demo.jdbcclientdemo.dao.TranAllDao;
import com.demo.jdbcclientdemo.domain.TranAll;
import com.demo.jdbcclientdemo.service.LoggerService;
import com.demo.jdbcclientdemo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TranAllDaoImpl implements TranAllDao {

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String TABLE = "tran_all";
    private final String CREATE_DATE = "create_date";
    private final String CREATE_BY = "create_by";
    private final String UPDATE_DATE = "update_date";
    private final String UPDATE_BY = "update_by";
    private final String IS_DELETE = "is_delete";

    private final String TRAN_ID = "tran_id";
    private final String TRAN_GROUP = "tran_group";
    private final String TRAN_CODE = "tran_code";
    private final String TRAN_REF_ID = "tran_ref_id";
    private final String SID = "sid";

    final RowMapper<TranAll> ROW_MAPPER = (ResultSet rs, int i) -> {
        final TranAll mapperObject = new TranAll();
        mapperObject.setCreateDate(rs.getTimestamp(CREATE_DATE));
        mapperObject.setCreateBy(rs.getString(CREATE_BY));
        mapperObject.setUpdateDate(rs.getTimestamp(UPDATE_DATE));
        mapperObject.setUpdateBy(rs.getString(UPDATE_BY));
        mapperObject.setIsDelete(rs.getString(IS_DELETE));

        mapperObject.setTranID(rs.getObject(TRAN_ID) != null ? BigInteger.valueOf(rs.getLong(TRAN_ID)) : null);
        mapperObject.setTranGroup(rs.getString(TRAN_GROUP));
        mapperObject.setTranCode(rs.getString(TRAN_CODE));
        mapperObject.setTranRefID(rs.getObject(TRAN_REF_ID) != null ? BigInteger.valueOf(rs.getLong(TRAN_REF_ID)) : null);
        mapperObject.setSid(rs.getString(SID));

        return mapperObject;
    };

    @Override
    public List<TranAll> find(TranAll findObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<TranAll> resultList = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        try {

            sql.append(" select * from ").append(TABLE);
            sql.append(" where 0 = 0 ");

            if (findObject != null) {
                if (findObject.getCreateDate() != null) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(CREATE_DATE)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getCreateDate());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getCreateBy())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(CREATE_BY)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getCreateBy());
                }
                if (findObject.getUpdateDate() != null) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(UPDATE_DATE)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getUpdateDate());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getUpdateBy())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(UPDATE_BY)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getUpdateBy());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getIsDelete())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(IS_DELETE)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getIsDelete());
                }
                if (findObject.getTranID() != null) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(TRAN_ID)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTranID().longValue());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getTranGroup())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(TRAN_GROUP)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTranGroup());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getTranCode())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(TRAN_CODE)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTranCode());
                }
                if (findObject.getTranRefID() != null) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(TRAN_REF_ID)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getTranRefID().longValue());
                }
                if (StringUtils.isNotEmptyOrNull(findObject.getSid())) {
                    sql
                            .append(DatabaseConstant.AND)
                            .append(SID)
                            .append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getSid());
                }

            }

            sql.append(" order by ").append(CREATE_DATE).append(" DESC ");

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY,
                    sql.toString());
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS,
                    Arrays.toString(parameters.toArray()));

            resultList = jdbcTemplate.query(sql.toString(), parameters.toArray(), ROW_MAPPER);

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? String.valueOf(findObject.getTranID()) : null,
                    CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;

        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(findObject != null ? String.valueOf(findObject.getTranID()) : null,
                    CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

        return resultList;
    }

    @Override
    public void insert(List<TranAll> insertObjectList) throws Exception {
        StringBuilder sql = new StringBuilder();
        StringBuilder prepareObject = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();

        try {

            sql
                    .append(" insert into ").append(TABLE).append(" (")
                    .append(IS_DELETE)
                    .append(DatabaseConstant.SIGN_COMMA)
                    .append(CREATE_DATE)
                    .append(DatabaseConstant.SIGN_COMMA)
                    .append(CREATE_BY)
                    .append(DatabaseConstant.SIGN_COMMA);

            sql
                    .append(TRAN_ID)
                    .append(DatabaseConstant.SIGN_COMMA)
                    .append(TRAN_GROUP)
                    .append(DatabaseConstant.SIGN_COMMA)
                    .append(TRAN_CODE)
                    .append(DatabaseConstant.SIGN_COMMA)
                    .append(TRAN_REF_ID)
                    .append(DatabaseConstant.SIGN_COMMA)
                    .append(SID)
                    .append(")");

            if (insertObjectList != null && insertObjectList.size() > 0) {
                int size = insertObjectList.size();
                sql.append(" values ");
                for (int i = 0; i < size; i++) {
                    TranAll insertObj = insertObjectList.get(i);

                    sql.append(" ( ")
                            .append(DatabaseConstant.SIGN_QUESTION_MARK)
                            .append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK)
                            .append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK)
                            .append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK)
                            .append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK)
                            .append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK)
                            .append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK)
                            .append(DatabaseConstant.SIGN_COMMA)
                            .append(DatabaseConstant.SIGN_QUESTION_MARK)
                            .append(" ) ");

                    if (i < size - 1) {
                        sql.append(DatabaseConstant.SIGN_COMMA);
                    }

                    //setup object statement
                    parameters.add(insertObj.getIsDelete());
                    parameters.add(insertObj.getCreateDate());
                    parameters.add(insertObj.getCreateBy());

                    parameters.add(insertObj.getTranID() != null ? insertObj.getTranID().longValue() : null);
                    parameters.add(insertObj.getTranGroup());
                    parameters.add(insertObj.getTranCode());

                    parameters.add(insertObj.getTranRefID() != null ? insertObj.getTranRefID().longValue() : null);
                    parameters.add(insertObj.getSid());

                }
            }

            sql.append(prepareObject.toString());

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString());
            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_PARAMETERS, Arrays.toString(parameters.toArray()));

            jdbcTemplate.update(sql.toString(), parameters.toArray());

        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(TABLE, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(TABLE, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }
    }



}
