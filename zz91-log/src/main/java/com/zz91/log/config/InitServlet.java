package com.zz91.log.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.zz91.log.thread.ControlThread;
import com.zz91.util.db.pool.DBPoolFactory;

/**
 * 
 * @author Leon
 * 
 */
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1315545405117443146L;

	public void init() throws ServletException {
		DBPoolFactory.getInstance().init("file:/usr/tools/config/db/db-zzlog.properties");

		ControlThread controlThread = new ControlThread();
		controlThread.start();
		
	}

	public void destroy() {

	}
}
