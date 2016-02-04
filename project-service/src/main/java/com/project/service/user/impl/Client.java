package com.project.service.user.impl;

import com.project.api.slice.common.BussinessResponse;
import com.project.api.slice.user.UserDto;
import com.project.api.slice.user.UserSlicePrx;
import com.project.api.slice.user.UserSlicePrxHelper;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Client {
	public static void main(String[] args) {
//		int status = 0;
//		Ice.Communicator ic = null;
//		try {
//			ic = Ice.Util.initialize(args);
//			Ice.ObjectPrx base = ic.stringToProxy("SimplePrinter:default -p 10000");
//			UserSlicePrx userSlicePrx = UserSlicePrxHelper.checkedCast(base);
//			if (userSlicePrx == null)
//				throw new Error("Invalid proxy");
//
//			BussinessResponse bussinessResponse = userSlicePrx.getUserInfoByPeimaryKey(1L);
//			log.info("return:" + bussinessResponse.jsonObject);
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