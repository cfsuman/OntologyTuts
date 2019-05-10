/*
 * Copyright (C) 2018 The ontology Authors
 * This file is part of The ontology library.
 *
 *  The ontology is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  The ontology is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with The ontology.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.github.ontio.network.rest;

import java.io.IOException;

import com.github.ontio.common.ErrorCode;
import com.github.ontio.common.Helper;
import com.github.ontio.core.block.Block;
import com.github.ontio.io.Serializable;
import com.github.ontio.network.connect.AbstractConnector;
import com.github.ontio.core.transaction.Transaction;

import com.alibaba.fastjson.JSON;

public class RestClient extends AbstractConnector {
    private Interfaces api;
    private String version = "v1.0.0", action = "sendrawtransaction";

    public RestClient(String restUrl) {
        api = new Interfaces(restUrl);
    }

    @Override
    public String getUrl() {
        return api.getUrl();
    }

    @Override
    public String sendRawTransaction(String hexData) throws Exception {
        String rs = api.sendTransaction(false, null, action, version, hexData);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return rs;
        }
        throw new Exception(to(rr));
    }

    @Override
    public String sendRawTransaction(boolean preExec, String userid, String hexData) throws Exception {
        String rs = api.sendTransaction(preExec, userid, action, version, hexData);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return rs;
        }
        throw new Exception(to(rr));
    }

    @Override
    public Transaction getRawTransaction(String txhash) throws Exception {
        String rs = api.getTransaction(txhash, true);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return Transaction.deserializeFrom(Helper.hexToBytes((String) rr.Result));
        }
        throw new Exception(to(rr));
    }

    @Override
    public int getGenerateBlockTime() throws Exception {
        String rs = api.getGenerateBlockTime();
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return (int) rr.Result;
        }
        throw new Exception(to(rr));

    }

    @Override
    public int getNodeCount() throws Exception {
        String rs = api.getNodeCount();
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return (int) rr.Result;
        }
        throw new Exception(to(rr));

    }

    @Override
    public int getBlockHeight() throws Exception {
        String rs = api.getBlockHeight();
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return (int) rr.Result;
        }
        throw new Exception(to(rr));

    }

    @Override
    public Block getBlock(int height) throws Exception {
        String rs = api.getBlock(height, "1");
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return Serializable.from(Helper.hexToBytes((String) rr.Result), Block.class);
        }
        throw new Exception(to(rr));
    }


    @Override
    public Block getBlock(String hash) throws Exception {
        String rs = api.getBlock(hash, "1");
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error != 0) {
            throw new Exception(to(rr));
        }
        return Serializable.from(Helper.hexToBytes((String) rr.Result), Block.class);
    }

    @Override
    public Object getBalance(String address) throws Exception {
        String rs = api.getBalance(address);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return rr.Result;
        }
        throw new Exception(to(rr));
    }

    @Override
    public Object getRawTransactionJson(String txhash) throws Exception {
        String rs = api.getTransaction(txhash, true);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return JSON.toJSONString(Transaction.deserializeFrom(Helper.hexToBytes((String) rr.Result)).json());
        }
        throw new Exception(to(rr));
    }

    @Override
    public Object getBlockJson(int height) throws Exception {
        String rs = api.getBlock(height, "0");
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return rr.Result;
        }
        throw new Exception(to(rr));
    }

    @Override
    public Object getBlockJson(String hash) throws Exception {
        String rs = api.getBlock(hash, "0");
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return rr.Result;
        }
        throw new Exception(to(rr));

    }

    @Override
    public Object getContract(String hash) throws Exception {
        String rs = api.getContract(hash);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return rr.Result;
        }
        throw new Exception(to(rr));
    }

    @Override
    public Object getContractJson(String hash) throws Exception {
        String rs = api.getContract(hash);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return rr.Result;
        }
        throw new Exception(to(rr));
    }

    @Override
    public Object getSmartCodeEvent(int height) throws Exception {
        String rs = api.getSmartCodeEvent(height);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return rr.Result;
        }
        throw new Exception(to(rr));

    }

    @Override
    public Object getSmartCodeEvent(String hash) throws Exception {
        String rs = api.getSmartCodeEvent(hash);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return rr.Result;
        }
        throw new Exception(to(rr));
    }

    @Override
    public int getBlockHeightByTxHash(String hash) throws Exception {
        String rs = api.getBlockHeightByTxHash(hash);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return (int) rr.Result;
        }
        throw new Exception(to(rr));
    }

    @Override
    public String getStorage(String codehash, String key) throws Exception {
        String rs = api.getStorage(codehash, key);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return (String) rr.Result;
        }
        throw new Exception(to(rr));
    }

    @Override
    public Object getMerkleProof(String hash) throws Exception {
        String rs = api.getMerkleProof(hash);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return rr.Result;
        }
        throw new Exception(to(rr));
    }
    @Override
    public String getAllowance(String asset,String from,String to) throws Exception{
        String rs = api.getAllowance(asset,from,to);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return (String)rr.Result;
        }
        throw new Exception(to(rr));
    }
    @Override
    public Object getMemPoolTxCount() throws Exception{
        String rs = api.getMemPoolTxCount();
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return rr.Result;
        }
        throw new Exception(to(rr));
    }

    @Override
    public Object getMemPoolTxState(String hash) throws Exception{
        String rs = api.getMemPoolTxState(hash);
        Result rr = JSON.parseObject(rs, Result.class);
        if (rr.Error == 0) {
            return rr.Result;
        }
        throw new Exception(to(rr));
    }

    private String to(Result rr) {
        return JSON.toJSONString(rr);
    }
}



