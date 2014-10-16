package com.appdynamicspilot.action;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appdynamicspilot.model.User;
import com.appdynamicspilot.util.ArgumentUtils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.appdynamicspilot.jms.*;
import com.appdynamicspilot.service.CartService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.appdynamicspilot.action.CartAction;

public class AsynCallAction extends ActionSupport implements Preparable,
		ServletResponseAware, ServletRequestAware {
	private HttpServletRequest request;
	private CartService cartService;
	private MessageProducer messageProducer;
	private HttpServletResponse response;
	private String inCount;
	private int count;

	public void prepare() throws Exception {
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public void setMessageProducer(MessageProducer messageProducer) {
		this.messageProducer = messageProducer;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getServletResponse() {
		return response;
	}

	private void setCount(int count) {
		this.count = count;
	}

	public void assertCount() {
		try {
			setCount(Integer.parseInt(this.inCount));
		} catch (NumberFormatException e) {
			setCount(1);
		}
	}

	private void async(CartService cartservice) {
		try {
            User user = (User) ActionContext.getContext().getSession()
                    .get("USER");
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("configuration");
			String axis4URl = resourceBundle.getString("axis14Url");
			cartservice.checkOut(new Long(1), cartservice.getCartSize(user.getId()),
					axis4URl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void jmxCall(MessageProducer messageProducer) {
		messageProducer.sendTextMessageWithOrderId();
	}

	private void dbCall(CartService cartservice) {
        User user = (User) ActionContext.getContext().getSession()
                .get("USER");
        if (ArgumentUtils.isNull(user)) {
            return;
        }
        cartservice.getAllCartItems(user.getId());
	}

	private void threadProcess(int countDown, MessageProducer messageProducer,
			CartService cartservice) {

		async(cartservice);
		jmxCall(messageProducer);
		dbCall(cartservice);

		if (countDown > 0) {
			createChildThread(countDown - 1, messageProducer, cartservice);
		}
	}

	public void createChildThread(final int count,
			final MessageProducer messageProducer, final CartService cartservice) {
		Thread childThread = new Thread(new Runnable() {
			public void run() {
				AsynCallAction child = new AsynCallAction();
				child.threadProcess(count, messageProducer, cartservice);
			}
		});

		childThread.start();

		try {
			childThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String createChildThread() {
		this.inCount = getServletRequest().getParameter("count");
		assertCount();
		createChildThread(this.count, this.messageProducer, this.cartService);
		return "SUCCESS";
	}
}