#pragma
#include <Common.ice>
[["java:package:com.project.api.slice"]]
module user {
	struct UserDto {
	   	long id;
	   	string loginName;
	};

    interface UserSlice {
        common::BussinessResponse getUserInfoByPeimaryKey(long id);

        UserDto updateByPeimaryKey(UserDto userDto);
    };
};


