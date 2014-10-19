package com.appdynamicspilot.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;

import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;

/**
 * User: jayesh
 * Date: Dec 28, 2009
 */
public class DiskIoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inside disk io servlet ");
        int dataAmount = request.getParameter("data") == null ? 1 : Integer.valueOf(request.getParameter("data"));
        System.out.println("Data written will be " + (dataAmount * 1024));
        response.getWriter().println("Data written will be " + (dataAmount * 1024));
        writeFile(dataAmount);
        response.getWriter().println("File successfully written");
    }

    private void writeFile(int dataAmount) {
        FileOutputStream fOut;
        FileChannel fChan;
        ByteBuffer mBuf;
        System.out.println("Write file Called");
        try {
            fOut = new FileOutputStream("test.txt");
            fChan = fOut.getChannel();
            int databytes = 1024 * dataAmount;
            mBuf = ByteBuffer.allocateDirect(databytes);
            for (int i = 0; i < databytes; i++)
                mBuf.put((byte) ('A' + i));
            mBuf.rewind();
            fChan.write(mBuf);
            fChan.close();
            fOut.close();
        } catch (IOException exc) {
            System.out.println(exc);

        }
    }
}
