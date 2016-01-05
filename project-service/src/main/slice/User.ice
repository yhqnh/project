[["java:package:com.project.slice"]]
module User {

	struct UserDto {
	   	long id;
	   	string loginName;
	};


    interface UserSlice {
        UserDto getUserInfoByPeimaryKey(long id);
    };
};
