package com.xinshe.web.common.restmodel;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.xinshe.web.common.commonmodel.NashPaging;
import com.xinshe.web.common.constant.IntBaseType;
import com.xinshe.web.common.constant.RestfulServiceErrorCodeEnum;
import com.xinshe.web.common.exceptions.SystemExceptionErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode
public class AgentApiResult<T> implements Serializable{

    /**
     * 状态码
     * 0表示成功
     */
    private Integer code = 0;

    /**
     * 状态信息
     */
    private String msg = "";

    private Object data;

    public static <T> AgentApiResult<T> Fail(Integer code, String msg){
        return Fail(code, msg, new Object());
    }

    public static <T> AgentApiResult<T> Fail(Integer code, String msg, Object data){
        AgentApiResult<T> tResultFail = new AgentApiResult<>();
        tResultFail.setCode(code);
        tResultFail.setMsg(msg);
        tResultFail.setData(data);
        return tResultFail;
    }

    /**
     * 系统级错误
     * @param <T>
     * @return
     */
    public static <T> AgentApiResult<T> Fail(SystemExceptionErrorCodeEnum systemErrorCodeEnum) {
        return Fail(systemErrorCodeEnum.getValue(), systemErrorCodeEnum.getDesc());
    }

    public static <T> AgentApiResult<T> Fail(SystemExceptionErrorCodeEnum systemErrorCodeEnum, Object data) {
        return Fail(systemErrorCodeEnum.getValue(), systemErrorCodeEnum.getDesc(), data);
    }

    /**
     * 服务级错误
     * @param serviceErrorCodeEnum
     * @param <T>
     * @return
     */
    public static <T> AgentApiResult<T> Fail(RestfulServiceErrorCodeEnum serviceErrorCodeEnum) {
        return Fail(serviceErrorCodeEnum.getValue(), serviceErrorCodeEnum.getDesc());
    }

    public static <T> AgentApiResult<T> Fail(RestfulServiceErrorCodeEnum serviceErrorCodeEnum, Object data) {
        return Fail(serviceErrorCodeEnum.getValue(), serviceErrorCodeEnum.getDesc(), data);
    }

    /**
     * 自定义错误
     * @param exceptionEnum
     * @param <T>
     * @return
     */
    public static <T extends IntBaseType> AgentApiResult Fail(T exceptionEnum){
        return Fail(exceptionEnum.getValue(),exceptionEnum.getDesc());
    }
    public static <T extends IntBaseType> AgentApiResult Fail(T exceptionEnum,Object data){
        return Fail(exceptionEnum.getValue(),exceptionEnum.getDesc(),data);
    }

    /**
     * NashResult
     * 如果 T对象是通过分页插件查询的数据
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> AgentApiResult<T> build(T obj){
        AgentApiResult<T> tResultOK = new AgentApiResult<>();
        if (obj instanceof Page) {
            tResultOK.setData(NashPaging.build(obj));
        } else if(obj instanceof List){
            tResultOK.setData(NashPaging.noPagebuild(obj));
        } else if(obj instanceof PageInfo){
            tResultOK.setData(NashPaging.pageInfobuild(obj));
        } else{
            tResultOK.setData(obj);
        }
        return tResultOK;
    }

}
