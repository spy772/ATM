package com.atm.services;

import com.atm.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ApiMapper { // TODO: Implement the changes in the data model once ready (add values to the account table primarily, client secondarily)

    @Select("SELECT * " +
            "FROM client " +
            "WHERE clientid = #{clientId}")
    Account getClientById();

    @Select("SELECT * " +
            "FROM accounts " +
            "WHERE accountid = #{accountId}")
    Account getAccountById(@Param("accountId") int accountId);

    @Update("UPDATE accounts " +
            "SET balance=#{balance}, numoftransactions=#{numOfTransactions}" +
            "WHERE accountid = #{accountId}")
    int updateClient(Account account);
}
