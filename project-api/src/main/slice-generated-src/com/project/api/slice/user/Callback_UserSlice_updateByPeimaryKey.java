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

package com.project.api.slice.user;

public abstract class Callback_UserSlice_updateByPeimaryKey
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackArg1<com.project.api.slice.user.UserDto>
{
    public final void __completed(Ice.AsyncResult __result)
    {
        UserSlicePrxHelper.__updateByPeimaryKey_completed(this, __result);
    }
}