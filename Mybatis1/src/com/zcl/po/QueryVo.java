package com.zcl.po;

import java.util.List;

/**
 * Created by Administrator on 2018-05-15.
 */
public class QueryVo {
    //传入多个id
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    private UserCustom userCustom;

    public UserCustom getUserCustom() {
        return userCustom;
    }

    public void setUserCustom(UserCustom userCustom) {
        this.userCustom = userCustom;
    }


}
