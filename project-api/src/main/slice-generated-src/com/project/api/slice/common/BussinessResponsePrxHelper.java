// **********************************************************************
//
// Copyright (c) 2003-2015 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.1
//
// <auto-generated>
//
// Generated from file `Common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.project.api.slice.common;

public final class BussinessResponsePrxHelper extends Ice.ObjectPrxHelperBase implements BussinessResponsePrx
{
    public static BussinessResponsePrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), BussinessResponsePrx.class, BussinessResponsePrxHelper.class);
    }

    public static BussinessResponsePrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), BussinessResponsePrx.class, BussinessResponsePrxHelper.class);
    }

    public static BussinessResponsePrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), BussinessResponsePrx.class, BussinessResponsePrxHelper.class);
    }

    public static BussinessResponsePrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), BussinessResponsePrx.class, BussinessResponsePrxHelper.class);
    }

    public static BussinessResponsePrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, BussinessResponsePrx.class, BussinessResponsePrxHelper.class);
    }

    public static BussinessResponsePrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, BussinessResponsePrx.class, BussinessResponsePrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::common::BussinessResponse",
        "::common::SimpleResponse"
    };

    public static String ice_staticId()
    {
        return __ids[1];
    }

    public static void __write(IceInternal.BasicStream __os, BussinessResponsePrx v)
    {
        __os.writeProxy(v);
    }

    public static BussinessResponsePrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            BussinessResponsePrxHelper result = new BussinessResponsePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
