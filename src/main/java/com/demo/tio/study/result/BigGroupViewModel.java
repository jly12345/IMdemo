package com.demo.tio.study.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fyp
 * @crate 2017/11/2 21:54
 * @project SpringBootLayIM
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BigGroupViewModel extends BaseViewModel{
    private String groupname;
    private String avatar;

}
