[["java:package:com.project.slice"]]
module user {

	struct UserDto {
	   	long id;
	   	string loginName;
	};


    interface UserSlice {
        UserDto getUserInfoByPeimaryKey(long id);
    };
};
