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

public interface _UserSliceOperationsNC
{
    com.project.api.slice.common.BussinessResponse getUserInfoByPeimaryKey(long id);

    UserDto updateByPeimaryKey(UserDto userDto);
}
