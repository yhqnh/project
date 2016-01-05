package com.project.service.user.impl;

import com.project.slice.User.UserSlicePrx;
import com.project.slice.User.UserSlicePrxHelper;


public class Client {
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize(args);
			Ice.ObjectPrx base = ic.stringToProxy("SimplePrinter:default -p 10000");
			UserSlicePrx userSlicePrx = UserSlicePrxHelper.checkedCast(base);
			if (userSlicePrx == null)
				throw new Error("Invalid proxy");

			userSlicePrx.getUserInfoByPeimaryKey(1L);
		} catch (Ice.LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		}
		if (ic != null) {
			// Clean up
			//
			try {
				ic.destroy();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				status = 1;
			}
		}
		System.exit(status);
	}
}