package com.example.javaweb.utils;

import com.example.javaweb.constant.SystemConstant;

import javax.servlet.http.HttpServletRequest;

public class MessageUtils {
    public static void showMessage(HttpServletRequest request) {
        if (request.getParameter("message") != null) {
            String message = request.getParameter("message");
            String messageResponse = "";
            String alert = "";
            if (message.equals("insert_success")) {
                messageResponse = SystemConstant.INSERT_SUCCESS;
                alert = SystemConstant.SUCCESS;
            } else if (message.equals("update_success")) {
                messageResponse =SystemConstant.UPDATE_SUCCESS;
                alert = SystemConstant.SUCCESS;
            } else if (message.equals("delete_success")) {
                messageResponse = SystemConstant.DELETE_SUCCESS;
                alert =SystemConstant.SUCCESS;
            } else if (message.equals("error_system")) {
                messageResponse =SystemConstant.ERROR_SYSTEM;
                alert = SystemConstant.DANGER;
            }

            request.setAttribute("messageResponse", messageResponse);
            request.setAttribute("alert", alert);
        }
    }
}