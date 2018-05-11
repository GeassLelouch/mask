
package com.shell.view;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.InternalResourceView;

/**
 * 
 * 日期:2017年8月6日
 * 
 * @author Shell
 *
 * Copyright © 2017 Shell. All rights reserved
 */
public class JstlView extends InternalResourceView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String template = "";//Template頁面
        if(null != request.getAttribute("template")) {
            template = (String)request.getAttribute("template"); 
        }
        //String dispatcherPath = prepareForRendering(request, response);
        //String action = (String)request.getAttribute("action");
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("/WEB-INF/views/" + template);
        requestDispatcher.include(request, response);
    }
}
