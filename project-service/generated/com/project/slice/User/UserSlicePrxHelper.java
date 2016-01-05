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
// Generated from file `User.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.project.slice.User;

public final class UserSlicePrxHelper extends Ice.ObjectPrxHelperBase implements UserSlicePrx
{
    private static final String __getUserInfoByPeimaryKey_name = "getUserInfoByPeimaryKey";

    public UserDto getUserInfoByPeimaryKey(long id)
    {
        return getUserInfoByPeimaryKey(id, null, false);
    }

    public UserDto getUserInfoByPeimaryKey(long id, java.util.Map<String, String> __ctx)
    {
        return getUserInfoByPeimaryKey(id, __ctx, true);
    }

    private UserDto getUserInfoByPeimaryKey(long id, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__getUserInfoByPeimaryKey_name);
        return end_getUserInfoByPeimaryKey(begin_getUserInfoByPeimaryKey(id, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_getUserInfoByPeimaryKey(long id)
    {
        return begin_getUserInfoByPeimaryKey(id, null, false, false, null);
    }

    public Ice.AsyncResult begin_getUserInfoByPeimaryKey(long id, java.util.Map<String, String> __ctx)
    {
        return begin_getUserInfoByPeimaryKey(id, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_getUserInfoByPeimaryKey(long id, Ice.Callback __cb)
    {
        return begin_getUserInfoByPeimaryKey(id, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getUserInfoByPeimaryKey(long id, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_getUserInfoByPeimaryKey(id, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getUserInfoByPeimaryKey(long id, Callback_UserSlice_getUserInfoByPeimaryKey __cb)
    {
        return begin_getUserInfoByPeimaryKey(id, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_getUserInfoByPeimaryKey(long id, java.util.Map<String, String> __ctx, Callback_UserSlice_getUserInfoByPeimaryKey __cb)
    {
        return begin_getUserInfoByPeimaryKey(id, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_getUserInfoByPeimaryKey(long id, 
                                                         IceInternal.Functional_GenericCallback1<UserDto> __responseCb, 
                                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getUserInfoByPeimaryKey(id, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getUserInfoByPeimaryKey(long id, 
                                                         IceInternal.Functional_GenericCallback1<UserDto> __responseCb, 
                                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                         IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getUserInfoByPeimaryKey(id, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_getUserInfoByPeimaryKey(long id, 
                                                         java.util.Map<String, String> __ctx, 
                                                         IceInternal.Functional_GenericCallback1<UserDto> __responseCb, 
                                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_getUserInfoByPeimaryKey(id, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_getUserInfoByPeimaryKey(long id, 
                                                         java.util.Map<String, String> __ctx, 
                                                         IceInternal.Functional_GenericCallback1<UserDto> __responseCb, 
                                                         IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                         IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getUserInfoByPeimaryKey(id, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_getUserInfoByPeimaryKey(long id, 
                                                          java.util.Map<String, String> __ctx, 
                                                          boolean __explicitCtx, 
                                                          boolean __synchronous, 
                                                          IceInternal.Functional_GenericCallback1<UserDto> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                          IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_getUserInfoByPeimaryKey(id, __ctx, __explicitCtx, __synchronous, 
                                             new IceInternal.Functional_TwowayCallbackArg1<com.project.slice.User.UserDto>(__responseCb, __exceptionCb, __sentCb)
                                                 {
                                                     public final void __completed(Ice.AsyncResult __result)
                                                     {
                                                         UserSlicePrxHelper.__getUserInfoByPeimaryKey_completed(this, __result);
                                                     }
                                                 });
    }

    private Ice.AsyncResult begin_getUserInfoByPeimaryKey(long id, 
                                                          java.util.Map<String, String> __ctx, 
                                                          boolean __explicitCtx, 
                                                          boolean __synchronous, 
                                                          IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__getUserInfoByPeimaryKey_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__getUserInfoByPeimaryKey_name, __cb);
        try
        {
            __result.prepare(__getUserInfoByPeimaryKey_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeLong(id);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public UserDto end_getUserInfoByPeimaryKey(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __getUserInfoByPeimaryKey_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            UserDto __ret = null;
            __ret = UserDto.__read(__is, __ret);
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __getUserInfoByPeimaryKey_completed(Ice.TwowayCallbackArg1<UserDto> __cb, Ice.AsyncResult __result)
    {
        com.project.slice.User.UserSlicePrx __proxy = (com.project.slice.User.UserSlicePrx)__result.getProxy();
        UserDto __ret = null;
        try
        {
            __ret = __proxy.end_getUserInfoByPeimaryKey(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    public static UserSlicePrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), UserSlicePrx.class, UserSlicePrxHelper.class);
    }

    public static UserSlicePrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), UserSlicePrx.class, UserSlicePrxHelper.class);
    }

    public static UserSlicePrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), UserSlicePrx.class, UserSlicePrxHelper.class);
    }

    public static UserSlicePrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), UserSlicePrx.class, UserSlicePrxHelper.class);
    }

    public static UserSlicePrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, UserSlicePrx.class, UserSlicePrxHelper.class);
    }

    public static UserSlicePrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, UserSlicePrx.class, UserSlicePrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::User::UserSlice"
    };

    public static String ice_staticId()
    {
        return __ids[1];
    }

    public static void __write(IceInternal.BasicStream __os, UserSlicePrx v)
    {
        __os.writeProxy(v);
    }

    public static UserSlicePrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            UserSlicePrxHelper result = new UserSlicePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
