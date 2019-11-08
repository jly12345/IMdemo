package com.demo.tio.study.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fyp
 * @crate 2017/11/2 22:58
 * @project SpringBootLayIM
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserViewModel extends BaseViewModel {

    private String username;

    private String avatar;
    private String status;
    private String sign;
    @JsonIgnore
    private int sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.status = sort==1?"online":"offline";
        this.sort = sort;
    }
}
