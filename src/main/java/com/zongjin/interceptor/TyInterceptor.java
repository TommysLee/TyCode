package com.zongjin.interceptor;

import com.zongjin.adapter.DBAdapter;
import com.zongjin.entity.DataSourceDesc;
import com.zongjin.service.IDataSourceDescService;
import com.zongjin.utils.DBHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自动选取合适的数据库DAO实现
 *
 * @Author Tommy
 * @Date 2021/12/10
 */
@Slf4j
public class TyInterceptor implements HandlerInterceptor {

    @Autowired
    private IDataSourceDescService dataSourceDescService;

    public TyInterceptor() {
        log.info("拦截器TyInterceptor::HandlerInterceptor初始化完毕！");
    }

    /**
     * 请求前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            final String datasource = request.getParameter("datasource");
            final DataSourceDesc dsDesc = dataSourceDescService.getById(datasource);
            if (null != dsDesc) {
                DBAdapter.setDBType(DBHelper.getDBType(dsDesc.getUrl()));
                log.info("拦截器::自动选取合适的数据库DAO实现::" + DBAdapter.getDBType() + " 。。。");
            }
        }
        return true;
    }
}
