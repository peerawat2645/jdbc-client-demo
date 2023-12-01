package com.demo.jdbcclientdemo.dao.impl;

import com.demo.jdbcclientdemo.constant.CommonConstant;
import com.demo.jdbcclientdemo.constant.DatabaseConstant;
import com.demo.jdbcclientdemo.dao.BookDao;
import com.demo.jdbcclientdemo.domain.Book;
import com.demo.jdbcclientdemo.domain.TranAll;
import com.demo.jdbcclientdemo.service.LoggerService;
import com.demo.jdbcclientdemo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String TABLE = "book";
    private final String CREATE_DATE = "create_date";
    private final String CREATE_BY = "create_by";
    private final String UPDATE_DATE = "update_date";
    private final String UPDATE_BY = "update_by";
    private final String IS_DELETE = "is_delete";

    private final String BOOK_ID = "book_id";
    private final String BOOK_NAME = "book_name";
    private final String BOOK_TITLE = "book_title";

    private final String TRAN_ID_GENERATE = "tran_id_generate";
    private final String TRAN_ID_VERIFY = "tran_id_verify";
    private final String TRAN_STATUS_CODE = "tran_status_code";
    private final String TRAN_STATUS_GROUP = "tran_status_group";

    final RowMapper<Book> ROW_MAPPER = (ResultSet rs, int i) -> {
        final Book mapperObject = new Book();
        mapperObject.setCreateDate(rs.getTimestamp(CREATE_DATE));
        mapperObject.setCreateBy(rs.getString(CREATE_BY));
        mapperObject.setUpdateDate(rs.getTimestamp(UPDATE_DATE));
        mapperObject.setUpdateBy(rs.getString(UPDATE_BY));
        mapperObject.setIsDelete(rs.getString(IS_DELETE));

        mapperObject.setBookID(rs.getObject(BOOK_ID) != null ? BigInteger.valueOf(rs.getLong(BOOK_ID)) : null);
        mapperObject.setName(rs.getString(BOOK_NAME));
        mapperObject.setTitle(rs.getString(BOOK_TITLE));

        mapperObject.setTran_id_generate(rs.getObject(TRAN_ID_GENERATE) != null ? BigInteger.valueOf(rs.getLong(TRAN_ID_GENERATE)) : null);
        mapperObject.setTran_id_verify(rs.getObject(BOOK_ID) != null ? BigInteger.valueOf(rs.getLong(BOOK_ID)) : null);
        mapperObject.setTran_status_code(rs.getString(TRAN_STATUS_CODE));
        mapperObject.setTran_status_group(rs.getString(TRAN_STATUS_GROUP));

        return mapperObject;
    };
    @Override
    public List<Book> findAll() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Book> resultList = new ArrayList<>();

        try {
            sql.append("SELECT * FROM ").append(TABLE);
            //sql.append(DatabaseConstant.WHERE_0_EQUAL_0);
            sql.append(" ORDER BY ").append(CREATE_DATE).append(" DESC;");

            loggerService.systemLogger(TABLE, CommonConstant.LOG_DATABASE_QUERY, sql.toString());

            resultList = jdbcTemplate.query(sql.toString(), ROW_MAPPER);
        } catch (DataAccessException e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_ACCESS_SQL_EXCEPTION, e);
            throw e;
        } catch (Exception e) {
            loggerService.printStackTraceToErrorLog(CommonConstant.SID, CommonConstant.LOG_DATABASE_EXCEPTION, e);
            throw e;
        }

        return resultList;
    }


    @Override
    public List<Book> find(Book findObject) throws Exception {
        return null;
    }

    @Override
    public Book findById(BigInteger id) throws Exception {
        return null;
    }

    @Override
    public Book insert(Book insertObject) throws Exception {
        return null;
    }

    @Override
    public Book update(Book updateObject) throws Exception {
        return null;
    }

    @Override
    public void delete(BigInteger id) throws Exception {

    }
}
