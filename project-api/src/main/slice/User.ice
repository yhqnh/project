[["java:package:com.project.api.slice"]]
module user {

	struct UserDto {
	   	long id;
	   	string loginName;
	};

    interface UserSlice {
        UserDto getUserInfoByPeimaryKey(long id);
    };
};
