package aop;

import beans.ResultBean;
import exceptions.CheckException;
import exceptions.NoPermissionException;
import exceptions.UserOperateException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerAOP {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAOP.class);

    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> result;
        try {
            //权限校验

            //国际化

            //获取方法的中文名称

            result = (ResultBean<?>) pjp.proceed();
            long elapsedTime = System.currentTimeMillis() - startTime;
            LOGGER.info("[{}] 执行时间 :{}", pjp.getSignature(), elapsedTime + "ms");
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        return result;
    }

    //统一异常处理
    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();
        // 已知异常【注意：已知异常不要打印堆栈，否则会干扰日志】
        // 校验出错，参数非法
        if (e instanceof CheckException) {
            result.setReturnMsg(e.getMessage());
            result.setReturnCode(ResultBean.CHECK_FAIL);
        }
        // 没有权限
        else if (e instanceof NoPermissionException) {
            result.setReturnMsg("非法操作, 您没有权限!");
            result.setReturnCode(ResultBean.NO_PERMISSION);
        }
        // 用户操作异常
        else if (e instanceof UserOperateException) {
            result.setReturnMsg(e.getMessage());
            result.setReturnCode(ResultBean.USER_OPERATE_ERROR);
        }
        // 未知异常
        else {
            System.out.println(pjp.getSignature() + " 异常 " + e);
            result.setReturnMsg(pjp.getSignature() + "异常!");
            result.setReturnCode(ResultBean.UNKNOWN_EXCEPTION);
        }
        return result;
    }
}
