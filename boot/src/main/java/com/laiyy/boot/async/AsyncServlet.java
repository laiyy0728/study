package com.laiyy.boot.async;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * @author laiyy
 * @date 2018/8/30 16:14
 * @description
 */
@WebServlet(urlPatterns = "servlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

}
