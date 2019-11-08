package com.demo.tio.study.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author fyp
 * @crate 2017/11/2 21:49
 * @project SpringBootLayIM
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseDataViewModel extends BaseViewModel {
    private UserViewModel mine;

    private List<FriendGroupViewModel> friend;

    private List<BigGroupViewModel> group;

}
