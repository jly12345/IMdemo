package com.demo.tio.study.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author fyp
 * @crate 2017/11/2 21:51
 * @project SpringBootLayIM
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FriendGroupViewModel extends BaseViewModel{
    private String groupname;
    private Integer online;
    private List<UserViewModel> list;
}
