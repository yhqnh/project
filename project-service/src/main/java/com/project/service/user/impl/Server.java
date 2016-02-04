package com.project.service.user.impl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
	public static void main(String[] args) {

//		int status = 0;
//		Ice.Communicator ic = null;
//		try {
//			ic = Ice.Util.initialize(args);
//			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("SimplePrinterAdapter", "default -p 10000");
//			Ice.Object object = new UserServiceImpl();
//			adapter.add(object, ic.stringToIdentity("SimplePrinter"));
//			adapter.activate();
//			ic.waitForShutdown();
//		} catch (Ice.LocalException e) {
//			e.printStackTrace();
//			status = 1;
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//			status = 1;
//		}
//		if (ic != null) {
//			// Clean up
//			//
//			try {
//				ic.destroy();
//			} catch (Exception e) {
//				System.err.println(e.getMessage());
//				status = 1;
//			}
//		}
//		System.exit(status);
	}
}