package com.atm.services;

import com.atm.model.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ApiMapper {

    @Select("SELECT * " +
            "FROM client " +
            "WHERE clientid = #{clientId}")
    Client getClientById(@Param("clientId") int clientId);

    @Update("UPDATE client " +
            "SET bankbalance=#{bankBalance}, checkingbalance=#{checkingBalance}, savingsbalance=#{savingsBalance}, numoftransactions=#{numOfTransactions}" +
            "WHERE clientid = #{clientId}")
    int updateClient(Client client);

}
